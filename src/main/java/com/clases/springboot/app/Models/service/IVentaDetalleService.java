package com.clases.springboot.app.Models.service;

import java.util.List;



import com.clases.springboot.app.Models.Entity.ventaDetalle;

public interface IVentaDetalleService {
    
    ventaDetalle save (ventaDetalle ventaDetalle);

    public List<ventaDetalle> findAll();

	public List<ventaDetalle> findById(Long id); 


}
