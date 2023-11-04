package com.clases.springboot.app.Models.Dao;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {

	public Usuario findByUser(String user);


	Optional<Usuario> findByuser(String usuario);

}
