package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.ventaDetalle;

public interface IVentaDetalleDao extends CrudRepository<ventaDetalle,Long> {
   
   // List<ventaDetalle> findByIdVenta(Long ventaId);
}
