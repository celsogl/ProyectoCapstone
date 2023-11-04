package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.Productos;

public interface IProductosDao extends CrudRepository<Productos, Long> {

}
