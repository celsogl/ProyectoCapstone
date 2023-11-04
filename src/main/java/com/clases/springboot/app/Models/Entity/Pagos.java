package com.clases.springboot.app.Models.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class Pagos {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_venta")
    private Venta idVenta;
    @Column(name = "metodo_pago")
    private String metodo_pago;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Venta getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }
    public String getMetodo_pago() {
        return metodo_pago;
    }
    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
   

    
}
