package com.clases.springboot.app.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.clases.springboot.app.Models.Entity.Delivery;

public interface IDeliveryDao extends CrudRepository<Delivery,Long> {
    
    
}
