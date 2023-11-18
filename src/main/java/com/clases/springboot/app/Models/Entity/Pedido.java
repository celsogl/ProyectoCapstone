package com.clases.springboot.app.Models.Entity;


import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private Date fechaPedido;
   
    private String tipoPedido; /// Delivery o Mesa


    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = true)
    private Mesas mesa;

    @OneToOne(mappedBy = "pedido")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "venta_id") // Ajusta el nombre de la columna según tu esquema de BD
    private Venta venta;

    @OneToMany(mappedBy = "pedidos")
    private Set<PedidoProducto> pedidoProductos; // Los productos en este pedido

  
public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}



public Date getFechaPedido() {
    return fechaPedido;
}

public void setFechaPedido(Date fechaPedido) {
    this.fechaPedido = fechaPedido;
}



public String getTipoPedido() {
    return tipoPedido;
}



public void setTipoPedido(String tipoPedido) {
    this.tipoPedido = tipoPedido;
}



public Mesas getMesa() {
    return mesa;
}



public void setMesa(Mesas mesa) {
    this.mesa = mesa;
}


public Set<PedidoProducto> getPedidoProductos() {
    return pedidoProductos;
}



public void setPedidoProductos(Set<PedidoProducto> pedidoProductos) {
    this.pedidoProductos = pedidoProductos;
}



public Venta getVenta() {
    return venta;
}



public void setVenta(Venta venta) {
    this.venta = venta;
}



public Delivery getDelivery() {
    return delivery;
}



public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
}


}