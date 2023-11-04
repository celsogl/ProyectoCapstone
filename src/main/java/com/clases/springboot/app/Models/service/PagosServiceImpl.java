package com.clases.springboot.app.Models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IPagosDao;
import com.clases.springboot.app.Models.Entity.Pagos;

@Service
public class PagosServiceImpl implements IPagosService {

	@Autowired
	private IPagosDao pagosDao;

	@Override
	@Transactional(readOnly = true)
	public List<Pagos> findAll() {
		return (List<Pagos>) pagosDao.findAll();
	}

	@Override
	@Transactional
	public Pagos save(Pagos pagos) {
		return pagosDao.save(pagos);
	}

	@Transactional(readOnly = true)
	@Override
	public Pagos findById(Long id) {
		return pagosDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		pagosDao.deleteById(id);
	}

}
