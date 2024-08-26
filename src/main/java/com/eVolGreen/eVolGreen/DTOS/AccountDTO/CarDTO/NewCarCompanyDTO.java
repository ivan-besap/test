package com.eVolGreen.eVolGreen.DTOS.AccountDTO.CarDTO;

import com.eVolGreen.eVolGreen.Models.Account.Car.Car;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class NewCarCompanyDTO {

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

    private Long cuentaCompañia;

    private Boolean activo = false;


    public NewCarCompanyDTO() {}

    public NewCarCompanyDTO(Car Auto) {

        patente = Auto.getPatente();
        modelo = Auto.getModelo();
        vin = Auto.getVin();
        color = Auto.getColor();
        marca = Auto.getMarca();
        añoFabricacion = Auto.getAñoFabricacion();
        capacidadPotencia = Auto.getCapacidadPotencia();
        cuentaCompañia = Auto.getCuentaCompañia().getId();
        activo = Auto.getActivo();

    }

    public @NotNull(message = "La Patente es obligatoria") String getPatente() {
        return patente;
    }

    public Boolean getActivo() {
        return activo;
    }

    public @NotNull(message = "El modelo es obligatorio") String getModelo() {
        return modelo;
    }

    public @NotNull(message = "El VIN es obligatorio") String getVin() {
        return vin;
    }

    public @NotNull(message = "El Color es obligatorio") String getColor() {
        return color;
    }

    public @NotNull(message = "La Marca es obligatoria") String getMarca() {
        return marca;
    }

    public @NotNull(message = "El Año de Fabricacion es obligatorio") String getAñoFabricacion() {
        return añoFabricacion;
    }

    public @NotNull(message = "La Capacidad de Potencia es obligatoria") BigDecimal getCapacidadPotencia() {
        return capacidadPotencia;
    }

    public Long getCuentaCompañia() {
        return cuentaCompañia;
    }
}
