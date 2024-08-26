package com.eVolGreen.eVolGreen.Models.Account.Car;

import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
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

    @NotNull(message = "El Año de Fabricacion es obligatorio")
    private String añoFabricacion;

    @NotNull(message = "La Capacidad de Potencia es obligatoria")
    private BigDecimal capacidadPotencia;

    @OneToMany(mappedBy = "Auto", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Auto-RFID")
    private Set<DeviceIdentifier> RFID = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCliente_id")
    @JsonBackReference("CuentaCliente-Auto")
    private AccountClient CuentaCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaTrabajador_id")
    @JsonBackReference("CuentaTrabajador-Auto")
    private AccountEmployee CuentaTrabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCompañia_id")
    @JsonBackReference("CuentaCompañia-Auto")
    private AccountCompany CuentaCompañia;

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

    public @NotNull(message = "La Patente es obligatoria") String getPatente() {
        return patente;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setPatente(@NotNull(message = "La Patente es obligatoria") String patente) {
        this.patente = patente;
    }

    public @NotNull(message = "El modelo es obligatorio") String getModelo() {
        return modelo;
    }

    public void setModelo(@NotNull(message = "El modelo es obligatorio") String modelo) {
        this.modelo = modelo;
    }

    public @NotNull(message = "El VIN es obligatorio") String getVin() {
        return vin;
    }

    public void setVin(@NotNull(message = "El VIN es obligatorio") String vin) {
        this.vin = vin;
    }

    public @NotNull(message = "El Color es obligatorio") String getColor() {
        return color;
    }

    public void setColor(@NotNull(message = "El Color es obligatorio") String color) {
        this.color = color;
    }

    public @NotNull(message = "La Marca es obligatoria") String getMarca() {
        return marca;
    }

    public void setMarca(@NotNull(message = "La Marca es obligatoria") String marca) {
        this.marca = marca;
    }

    public @NotNull(message = "El Año de Fabricacion es obligatorio") String getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(@NotNull(message = "El Año de Fabricacion es obligatorio") String añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public @NotNull(message = "La Capacidad de Potencia es obligatoria") BigDecimal getCapacidadPotencia() {
        return capacidadPotencia;
    }

    public void setCapacidadPotencia(@NotNull(message = "La Capacidad de Potencia es obligatoria") BigDecimal capacidadPotencia) {
        this.capacidadPotencia = capacidadPotencia;
    }

    public Set<DeviceIdentifier> getRFID() {
        return RFID;
    }

    public void setRFID(Set<DeviceIdentifier> RFID) {
        this.RFID = RFID;
    }

    public AccountClient getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(AccountClient cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    public AccountEmployee getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public void setCuentaTrabajador(AccountEmployee cuentaTrabajador) {
        CuentaTrabajador = cuentaTrabajador;
    }

    public AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }
}
