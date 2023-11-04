package com.clases.springboot.app.Models.service;

import java.util.List;

import com.clases.springboot.app.Models.Entity.Delivery;


public interface IDeliveryService {
    public List<Delivery> findAll();

	public Delivery save(Delivery delivery);

	public Delivery findById(Long id);

	public void deleteById(Long id);

}
