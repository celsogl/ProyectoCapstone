package com.clases.springboot.app.Models.service;

import java.util.List;
import java.util.Optional;

import com.clases.springboot.app.Models.Entity.Pedido;

public interface IPedidoService {

    public List<Pedido> findAll();

	public Pedido save(Pedido pedido); 

	public Pedido findById(Long id); 

	public void deleteById(Long id);

    public Optional<Pedido> get(Long id);

}
