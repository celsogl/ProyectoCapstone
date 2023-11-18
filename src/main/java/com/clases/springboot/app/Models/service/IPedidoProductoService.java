package com.clases.springboot.app.Models.service;

import java.util.List;


import com.clases.springboot.app.Models.Entity.PedidoProducto;

public interface IPedidoProductoService {

    public List<PedidoProducto> findAll();

	public PedidoProducto save(PedidoProducto pedidoProducto); // Guardar una Persona

}
