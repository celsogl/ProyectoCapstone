package com.clases.springboot.app.Models.service;

import java.util.List;

import com.clases.springboot.app.Models.Entity.Venta;



public interface IVentasService {
    public List<Venta> findAll();

	public Venta save(Venta venta); // Guardar 

	public Venta findById(Long id); // buscar

	public void deleteById(Long id);
}
