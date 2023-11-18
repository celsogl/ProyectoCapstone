package com.clases.springboot.app.Models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IPedidoDao;
import com.clases.springboot.app.Models.Entity.Pedido;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private IPedidoDao pedidoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findAll() {
        return (List<Pedido>) pedidoDao.findAll();
    }

    @Override
    public Pedido save(Pedido pedido) {
       return pedidoDao.save(pedido);
    }

    @Override
    public Pedido findById(Long id) {
      return pedidoDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
       pedidoDao.deleteById(id);
    }

    @Override
    public Optional<Pedido> get(Long id) {
        return pedidoDao.findById(id);
    }

    

    
}
