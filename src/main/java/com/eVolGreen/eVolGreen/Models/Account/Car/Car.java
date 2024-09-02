package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "La Patente es obligatoria")
    private String patente;

    @NotNull(message = "El modelo es obligatorio")
    private String modelo;

    @NotNull(message = "El VIN es obligatorio")
    private String vin;

    @NotNull(message = "El Color es obligatorio")
    private String color;

    @NotNull(message = "La Marca es obligatoria")
    private String marca;

    @NotNull(message = "El Año de Fabricación es obligatorio")
    private String añoFabricacion;

    @NotNull(message = "La Capacidad de Potencia es obligatoria")
    private BigDecimal capacidadPotencia;

    @OneToMany(mappedBy = "auto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Auto-RFID")
    private Set<DeviceIdentifier> rfid = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference("Account-Car")
    private Account account;

    private Boolean activo = false;

    public Car() { }

    public Car(String patente, String modelo, String vin, String color, String marca, String añoFabricacion, BigDecimal capacidadPotencia, Boolean activo) {
        this.patente = patente;
        this.modelo = modelo;
        this.vin = vin;
        this.color = color;
        this.marca = marca;
        this.añoFabricacion = añoFabricacion;
        this.capacidadPotencia = capacidadPotencia;
        this.activo = activo;
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

    public String getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(String añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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
                ", añoFabricacion='" + añoFabricacion + '\'' +
                ", capacidadPotencia=" + capacidadPotencia +
                ", rfid=" + rfid +
                ", account=" + account +
                ", activo=" + activo +
                '}';
    }
}