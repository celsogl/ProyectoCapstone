package com.clases.springboot.app.Models.Entity;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.JoinColumn;






@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "user"))
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", length = 255)
	private String nombre;

	@Column(name = "user", length = 255)
	private String user;

	@Column(name = "password", length = 255)
	private String password;
	
	@Column(name = "telefono", length = 255)
	private String telefono;
	
	@Column(name = "estado")
	private int estado;

	 @ManyToMany(fetch= FetchType.EAGER,cascade = CascadeType.ALL)
	 @JoinTable(
				name="usuarios_roles",
				joinColumns =@JoinColumn(
							name = "usuario_id",referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id")
				)
	    private Collection<Rol> roles;
	
	@OneToMany(mappedBy = "idUsuario")
    private List<Venta> ventas;



	public Usuario(Integer id, String nombre, String user, String password, Collection<Rol> roles,String telefono,int estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.user = user;
		this.password = password;
		this.roles = roles;
		this.telefono = telefono;
		this.estado=estado;
	}

	public Usuario(String nombre, String user, String password, Collection<Rol> roles, String telefono,int estado) {
		super();
		this.nombre = nombre;
		this.user = user;
		this.password = password;
		this.roles = roles;
		this.telefono = telefono;
		this.estado=estado;
	}

	public Usuario() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", user=" + user + ", password=" + password + ", telefono="
				+ telefono + ", estado=" + estado + ", roles=" + roles + "]";
	}

	
	

	

}
