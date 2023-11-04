package com.clases.springboot.app.Models.service;

import java.util.List;

import com.clases.springboot.app.Models.Entity.Pagos;



public interface IPagosService {
	public List<Pagos> findAll();

	public Pagos save(Pagos pagos); // Guardar una Persona

	public Pagos findById(Long id); // buscar una Persona

	public void deleteById(Long id);
}
