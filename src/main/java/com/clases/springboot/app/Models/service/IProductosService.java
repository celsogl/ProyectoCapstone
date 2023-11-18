package com.clases.springboot.app.Models.service;

import java.util.List;
import java.util.Optional;

import com.clases.springboot.app.Models.Entity.Productos;

public interface IProductosService {
	public List<Productos> findAll();

	public Productos save(Productos productos); 

	public Productos findbyId(Long id); 

	public void deleteById(Long id);

    public Optional<Productos> findById(Long id);

	public Optional<Productos> get(Long id);
}
