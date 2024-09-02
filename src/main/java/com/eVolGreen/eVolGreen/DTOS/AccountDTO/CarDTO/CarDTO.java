package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class CarDTO {

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

    private Set<DeviceIdentifierDTO> rfid;

    private long accountId;

    private Boolean activo = false;

    public CarDTO() {}

    public CarDTO(Car car) {
        this.id = car.getId();
        this.patente = car.getPatente();
        this.modelo = car.getModelo();
        this.vin = car.getVin();
        this.color = car.getColor();
        this.marca = car.getMarca();
        this.añoFabricacion = car.getAñoFabricacion();
        this.capacidadPotencia = car.getCapacidadPotencia();
        this.rfid = car.getRfid().stream()
                .map(DeviceIdentifierDTO::new)
                .collect(Collectors.toSet());
        this.accountId = car.getAccount().getId();
        this.activo = car.getActivo();
    }

    public long getId() {
        return id;
    }

    public String getPatente() {
        return patente;
    }

    public String getModelo() {
        return modelo;
    }

    public String getVin() {
        return vin;
    }

    public String getColor() {
        return color;
    }

    public String getMarca() {
        return marca;
    }

    public String getAñoFabricacion() {
        return añoFabricacion;
    }

    public BigDecimal getCapacidadPotencia() {
        return capacidadPotencia;
    }

    public Set<DeviceIdentifierDTO> getRfid() {
        return rfid;
    }

    public long getAccountId() {
        return accountId;
    }

    public Boolean getActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "CarDTO{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", modelo='" + modelo + '\'' +
                ", vin='" + vin + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                ", añoFabricacion='" + añoFabricacion + '\'' +
                ", capacidadPotencia=" + capacidadPotencia +
                ", rfid=" + rfid +
                ", accountId=" + accountId +
                ", activo=" + activo +
                '}';
    }
}