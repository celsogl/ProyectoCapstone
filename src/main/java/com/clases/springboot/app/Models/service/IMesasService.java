package com.clases.springboot.app.Models.service;

import java.util.List;
import java.util.Optional;

import com.clases.springboot.app.Models.Entity.Mesas;


public interface IMesasService {
    
	public List<Mesas> findAll();

    public Mesas findById(Long id);

    public Mesas save(Mesas mesas);

    public Optional<Mesas> get(Long id);
}
