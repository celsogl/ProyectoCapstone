package com.clases.springboot.app.Models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clases.springboot.app.Models.Entity.Usuario;
import com.clases.springboot.app.dto.UsuarioRegistroDTO;

public interface IUsuarioService extends UserDetailsService{

	public Usuario save(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> findAll();

	//public void save(Cliente cliente); // Guardar un Cliente

	//public Usuario findById(Integer id); // buscar un Administrador

	public void deleteById(Integer id);

	Optional<Usuario> findById(Integer id);

	Usuario findByUsername(String username);
	
}
