package com.clases.springboot.app.Models.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class Pagos {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @OneToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta idVenta;
    

    @Column(name = "ruc")
    private Long ruc;
    @Column(name = "razonSocial")
    private String razonSocial;
    @Column(name = "dni")
    private Long dni;
    @Column (name = "nombreCliente")
    private String nombreCliente;
    @Column(name = "metodo_pago")
    private String metodo_pago;
    
    @Column(name = "monto")
    private Double monto;
    @Column(name = "fechapago")
    private Date fecha;
    
    
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
    public Long getRuc() {
        return ruc;
    }
    public void setRuc(Long ruc) {
        this.ruc = ruc;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public String getMetodo_pago() {
        return metodo_pago;
    }
    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
    public Double getMonto() {
        return monto;
    }
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    
    
}
