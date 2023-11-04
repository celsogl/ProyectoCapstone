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
@Table(name = "venta_detalle")
public class ventaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta idVenta;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Productos idProductos;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "precio_producto")
    private Double precioproducto;
    @Column(name = "total_a_pagar")
    private Double totalapagar;
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
    public Productos getIdProductos() {
        return idProductos;
    }
    public void setIdProductos(Productos idProductos) {
        this.idProductos = idProductos;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecioproducto() {
        return precioproducto;
    }
    public void setPrecioproducto(Double precioproducto) {
        this.precioproducto = precioproducto;
    }
    public Double getTotalapagar() {
        return totalapagar;
    }
    public void setTotalapagar(Double totalapagar) {
        this.totalapagar = totalapagar;
    }
    
    
    
}
