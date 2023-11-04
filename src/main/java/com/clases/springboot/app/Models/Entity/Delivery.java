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
@Table(name = "delivery")
public class Delivery {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono", length = 255)
	private String telefono;
    @Column(name = "estado")
    private String estado;
    @ManyToOne()
    @JoinColumn(name = "ID_VENTA")
    private Venta idVenta;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Venta getIdVenta() {
        return idVenta;
    }
    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
