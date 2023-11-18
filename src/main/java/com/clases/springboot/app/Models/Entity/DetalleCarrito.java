package com.clases.springboot.app.Models.Entity;

public class DetalleCarrito {
    
    private Long productoId;
    private String nombre;
    private int cantidad = 1;
    private double precio;
    private double motoapagar;
    
    
    public double getSubtotal() {
        return precio * cantidad;
    }
    

    
    public DetalleCarrito() {
        this.cantidad = 1; // Establecer la cantidad a 1 en el constructor
    }
    
    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getMotoapagar() {
        return motoapagar;
    }
    public void setMotoapagar(double motoapagar) {
        this.motoapagar = motoapagar;
    }

    
   

    
}
