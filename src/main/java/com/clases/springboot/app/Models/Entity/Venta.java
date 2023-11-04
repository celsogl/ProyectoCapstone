package com.clases.springboot.app.Models.Entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fechaventa")
    private Date fecha;
    @Column(name = "montoventa")
    private Double montoventa;
    @ManyToOne()
    @JoinColumn(name = "ID_USUARIO")
    private Usuario idUsuario;
    @ManyToOne()
    @JoinColumn(name = "id_mesa")
    private Mesas mesa;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getMontoventa() {
        return montoventa;
    }
    public void setMontoventa(Double montoventa) {
        this.montoventa = montoventa;
    }
    public Usuario getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Mesas getMesa() {
        return mesa;
    }
    public void setMesa(Mesas mesa) {
        this.mesa = mesa;
    }

}
