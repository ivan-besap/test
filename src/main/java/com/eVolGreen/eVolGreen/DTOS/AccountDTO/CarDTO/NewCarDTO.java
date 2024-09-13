package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NewCarDTO {

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
    private String anoFabricacion;

    @NotNull(message = "La Capacidad de Potencia es obligatoria")
    private BigDecimal capacidadPotencia;

    private Long empresaId;

    private Boolean activo = false;

    private String alias;

    public NewCarDTO() {}

    public NewCarDTO(Car car) {
        this.patente = car.getPatente();
        this.modelo = car.getModelo();
        this.vin = car.getVin();
        this.color = car.getColor();
        this.marca = car.getMarca();
        this.anoFabricacion = car.getAnoFabricacion();
        this.capacidadPotencia = car.getCapacidadPotencia();
        this.empresaId = car.getEmpresa().getId();
        this.activo = car.getActivo();
        this.alias = car.getAlias();
    }

    public String getPatente() {
        return patente;
    }

    public Boolean getActivo() {
        return activo;
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

    public String getAnoFabricacion() {
        return anoFabricacion;
    }

    public BigDecimal getCapacidadPotencia() {
        return capacidadPotencia;
    }

    public long getEmpresaId() {
        return empresaId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "NewCarDTO{" +
                "patente='" + patente + '\'' +
                ", modelo='" + modelo + '\'' +
                ", vin='" + vin + '\'' +
                ", color='" + color + '\'' +
                ", marca='" + marca + '\'' +
                ", anoFabricacion='" + anoFabricacion + '\'' +
                ", capacidadPotencia=" + capacidadPotencia +
                ", empresaId=" + empresaId +
                ", activo=" + activo +
                ", alias=" + alias +
                '}';
    }
}
