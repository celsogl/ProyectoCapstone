package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.Pagos;

public interface IPagosDao extends CrudRepository<Pagos,Long> {

}
