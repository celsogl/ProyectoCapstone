package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.Mesas;

public interface IMesaDao extends CrudRepository<Mesas,Long> {
    
}
