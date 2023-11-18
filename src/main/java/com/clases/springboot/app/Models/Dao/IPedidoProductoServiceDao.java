package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.PedidoProducto;

public interface IPedidoProductoServiceDao extends CrudRepository<PedidoProducto,Long> {

    

    
}
