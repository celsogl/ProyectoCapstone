package com.clases.springboot.app.Models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IVentaDetalleDao;
import com.clases.springboot.app.Models.Entity.ventaDetalle;

@Service
public class VentaDetalleServiceImpl implements IVentaDetalleService {

    @Autowired
    private IVentaDetalleDao ventaDetalleDao;

    @Override
    public ventaDetalle save(ventaDetalle ventaDetalle) {
       return ventaDetalleDao.save(ventaDetalle);
    }

    @Override
	@Transactional(readOnly = true)
    public List<ventaDetalle> findAll() {
        return (List<ventaDetalle>) ventaDetalleDao.findAll();
    }

    @Transactional(readOnly = true)
	@Override
    public ventaDetalle findById(Long id) {
       return ventaDetalleDao.findById(id).orElse(null);
    }
    
}
