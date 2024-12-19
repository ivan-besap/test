package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Flota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreFlota;

    private BigDecimal precioFlota;

    private Boolean activo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flota_id")
    private Set<Car> autos = new HashSet<>();

    public Flota() {}

    public Flota(String nombreFlota, Empresa empresa, Boolean activo, BigDecimal precioFlota) {
        this.nombreFlota = nombreFlota;
        this.empresa = empresa;
        this.activo = activo;
        this.precioFlota = precioFlota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreFlota() {
        return nombreFlota;
    }

    public void setNombreFlota(String nombreFlota) {
        this.nombreFlota = nombreFlota;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<Car> getAutos() {
        return autos;
    }

    public void setAutos(Set<Car> autos) {
        this.autos = autos;
    }

    public BigDecimal getPrecioFlota() {
        return precioFlota;
    }

    public void setPrecioFlota(BigDecimal precioFlota) {
        this.precioFlota = precioFlota;
    }
}
