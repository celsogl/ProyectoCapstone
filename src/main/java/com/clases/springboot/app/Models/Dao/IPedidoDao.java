package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.Pedido;

public interface IPedidoDao  extends CrudRepository<Pedido,Long>{


}
