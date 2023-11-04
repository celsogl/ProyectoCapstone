package com.clases.springboot.app.dto;


public class UsuarioRegistroDTO {

	private Long id;
	private String nombre;
	private String user;
	private String password;
	private String telefono;
	private int estado;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	

	public UsuarioRegistroDTO(Long id, String nombre, String user, String password, String telefono, int estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.user = user;
		this.password = password;
		this.telefono = telefono;
		this.estado = estado;
	}


	public UsuarioRegistroDTO(String nombre, String user, String password, String telefono, int estado) {
		super();
		this.nombre = nombre;
		this.user = user;
		this.password = password;
		this.telefono = telefono;
		this.estado = estado;
	}

	public UsuarioRegistroDTO(String user) {
		super();
		this.user = user;
	}

	public UsuarioRegistroDTO() {
		super();
	}

}
