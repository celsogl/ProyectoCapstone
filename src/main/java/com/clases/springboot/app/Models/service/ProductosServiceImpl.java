package com.clases.springboot.app.Models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IProductosDao;
import com.clases.springboot.app.Models.Entity.Productos;

@Service
public class ProductosServiceImpl implements IProductosService {

	@Autowired
	private IProductosDao productosDao;

	@Override
	@Transactional(readOnly = true)
	public List<Productos> findAll() {
		return (List<Productos>) productosDao.findAll();
	}

	@Override
	@Transactional
	public Productos save(Productos productos) {
		return productosDao.save(productos);
	}


	@Override
	public void deleteById(Long id) {
		productosDao.deleteById(id);

	}


	@Override
	public Productos findbyId(Long id) {
		return productosDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Productos> findById(Long id) {
		return productosDao.findById(id);
	}

	@Override
	public Optional<Productos> get(Long id) {
		return productosDao.findById(id);
	}

}
