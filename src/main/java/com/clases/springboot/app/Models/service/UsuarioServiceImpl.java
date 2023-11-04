package com.clases.springboot.app.Models.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.clases.springboot.app.Models.Dao.IUsuarioDao;
import com.clases.springboot.app.Models.Entity.Rol;
import com.clases.springboot.app.Models.Entity.Usuario;
import com.clases.springboot.app.dto.UsuarioRegistroDTO;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private IUsuarioDao clienteDao;

	

	@Override
	public Usuario save(UsuarioRegistroDTO registroDTO) {
		

		Usuario cliente = new Usuario(registroDTO.getNombre(),
				registroDTO.getUser(),
				passwordEncoder.encode(registroDTO.getPassword()),
				Arrays.asList(new Rol("ROLE_USER")),
				registroDTO.getTelefono(),
				registroDTO.getEstado());
				cliente.setEstado(1);
		return clienteDao.save(cliente);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario cliente = clienteDao.findByUser(username);
		if(cliente == null) {
			throw new UsernameNotFoundException("Cliente o password invalidos");
		}
		return new User(cliente.getUser(),cliente.getPassword(),mapearAutoridadesARoles(cliente.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesARoles(Collection<Rol> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	public UsuarioServiceImpl(IUsuarioDao clienteDao) {
		super();
		this.clienteDao = clienteDao;
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) clienteDao.findAll();
	}

	@Override
	public Optional<Usuario> findById(Integer id) {
		return clienteDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		clienteDao.deleteById(id);
	}

	@Override
	public Usuario findByUsername(String username) {
		return clienteDao.findByUser(username);
	}

}
