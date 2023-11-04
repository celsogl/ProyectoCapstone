package com.clases.springboot.app.Models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IVentasDao;
import com.clases.springboot.app.Models.Entity.Venta;

@Service
public class VentasServiceImpl implements IVentasService {

    @Autowired
    private IVentasDao ventasDao;

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        return (List<Venta>) ventasDao.findAll();
    }

    @Override
    @Transactional
    public Venta save(Venta venta) {

        return ventasDao.save(venta);
    }

    @Transactional(readOnly = true)
    @Override
    public Venta findById(Long id) {
        return ventasDao.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {

        ventasDao.deleteById(id);
    }

}
