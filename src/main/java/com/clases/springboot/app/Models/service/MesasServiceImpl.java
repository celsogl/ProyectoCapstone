package com.clases.springboot.app.Models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IMesaDao;
import com.clases.springboot.app.Models.Entity.Mesas;


@Service
public class MesasServiceImpl implements IMesasService {

    @Autowired
	private IMesaDao mesaDao;


    @Override
	@Transactional(readOnly = true)
    public List<Mesas> findAll() {
      return (List<Mesas>) mesaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mesas findById(Long id) {
      
        return mesaDao.findById(id).orElse(null);
    }

    @Override
	@Transactional
    public Mesas save(Mesas mesas) {
       return mesaDao.save(mesas);
    }
    
}
