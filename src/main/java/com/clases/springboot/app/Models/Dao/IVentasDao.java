package com.clases.springboot.app.Models.Dao;
import org.springframework.data.repository.CrudRepository;
import com.clases.springboot.app.Models.Entity.Venta;

public interface IVentasDao extends CrudRepository<Venta,Long> {
    
}
