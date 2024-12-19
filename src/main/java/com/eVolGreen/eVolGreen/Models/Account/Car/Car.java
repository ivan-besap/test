package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private long id;

    @NotNull(message = "La Patente es obligatoria")
    private String patente;


    private String modelo;

    @NotNull(message = "El VIN es obligatorio")
    private String vin;


    private String color;


    private String marca;


    private String anoFabricacion;


    private BigDecimal capacidadPotencia;

    @OneToMany(mappedBy = "auto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Auto-RFID")
    private Set<DeviceIdentifier> rfid = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")  // Cambio aquí
    @JsonBackReference("Empresa-Car")
    private Empresa empresa;

    private Boolean activo = false;

    private String alias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flota_id")
    @JsonBackReference("Flota-Car") // Añadimos esta referencia para evitar ciclos en la serialización
    private Flota flota;

    public Car() { }

    public Car(String patente, String modelo, String vin, String color, String marca, String anoFabricacion, BigDecimal capacidadPotencia, Empresa empresa, Boolean activo, String alias, Flota flota) {
        this.patente = patente;
        this.modelo = modelo;
        this.vin = vin;
        this.color = color;
        this.marca = marca;
        this.anoFabricacion = anoFabricacion;
        this.capacidadPotencia = capacidadPotencia;
        this.empresa = empresa;
        this.activo = activo;
        this.alias = alias;
        this.flota = flota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setanoFabricacion(String anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public BigDecimal getCapacidadPotencia() {
        return capacidadPotencia;
    }

    public void setCapacidadPotencia(BigDecimal capacidadPotencia) {
        this.capacidadPotencia = capacidadPotencia;
    }

    public Set<DeviceIdentifier> getRfid() {
        return rfid;
    }

    public void setRfid(Set<DeviceIdentifier> rfid) {
        this.rfid = rfid;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Flota getFlota() {
        return flota;
    }

    public void setFlota(Flota flota) {
        this.flota = flota;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", modelo='" + modelo + '\'' +
                ", vin='" + vin + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                ", anoFabricacion='" + anoFabricacion + '\'' +
                ", capacidadPotencia=" + capacidadPotencia +
                ", rfid=" + rfid +
                ", empresa=" + empresa +
                ", activo=" + activo +
                ", alias=" + alias +
                ", flota=" + (flota != null ? flota.getId() : "null") +
                '}';
    }
}