package com.clases.springboot.app.Models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clases.springboot.app.Models.Dao.IDeliveryDao;
import com.clases.springboot.app.Models.Entity.Delivery;


@Service
public class DeliveryServiceImpl implements IDeliveryService {

    @Autowired
	private IDeliveryDao deliveryDao;

    @Override
	@Transactional(readOnly = true)
    public List<Delivery> findAll() {
        return (List<Delivery>) deliveryDao.findAll();
    }

    @Override
	@Transactional
	public Delivery save(Delivery delivery) {
		return deliveryDao.save(delivery);
	}

	@Transactional(readOnly = true)
	@Override
	public Delivery findById(Long id) {
		return deliveryDao.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		deliveryDao.deleteById(id);
	}


}
