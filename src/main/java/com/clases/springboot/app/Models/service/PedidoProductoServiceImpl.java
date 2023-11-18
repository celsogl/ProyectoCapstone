package com.clases.springboot.app.Models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IPedidoProductoServiceDao;
import com.clases.springboot.app.Models.Entity.PedidoProducto;

@Service
public class PedidoProductoServiceImpl implements IPedidoProductoService {

    @Autowired
    private IPedidoProductoServiceDao pedidoproductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoProducto> findAll() {
      return (List<PedidoProducto>)pedidoproductoDao.findAll();
    }

    @Override
    @Transactional
    public PedidoProducto save(PedidoProducto pedidoProducto) {
        
        return pedidoproductoDao.save(pedidoProducto);
    }

}
