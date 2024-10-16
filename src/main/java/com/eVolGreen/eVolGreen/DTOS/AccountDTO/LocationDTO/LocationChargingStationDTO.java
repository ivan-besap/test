package com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;

import javax.validation.constraints.NotNull;


public class LocationChargingStationDTO {

    private long id;

    @NotNull(message = "La Dirección es obligatoria")
    private String direccion;

    // Constructor por defecto
    public LocationChargingStationDTO() {}

    // Constructor con parámetros
    public LocationChargingStationDTO(Location ubicacion) {
        this.id = ubicacion.getId();
        this.direccion = ubicacion.getDireccion();
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "LocationChargingStationDTO{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}