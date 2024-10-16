package com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;


public class LocationDTO {

    private long id;
    private String direccion;

    public LocationDTO() {}

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.direccion = location.getDireccion();
        // Eliminar la referencia al campo `activo` ya que no existe en Location
    }

    public long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}