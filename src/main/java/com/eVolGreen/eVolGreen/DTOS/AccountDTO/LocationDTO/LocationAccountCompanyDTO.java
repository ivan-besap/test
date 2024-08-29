package com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

public class LocationAccountCompanyDTO {

    private long id;

    @NotNull(message = "La Direccion es obligatoria")
    private String Direccion;

    private long CuentaCompania;

    public LocationAccountCompanyDTO(Location Ubicacion) {

        id = Ubicacion.getId();
        Direccion = Ubicacion.getDireccion();
        CuentaCompania = Ubicacion.getCuentaCompania().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "La Direccion es obligatoria") String getDireccion() {
        return Direccion;
    }

    public long getCuentaCompania() {
        return CuentaCompania;
    }

    @Override
    public String toString() {
        return "LocationAccountCompanyDTO{" +
                "id=" + id +
                ", Direccion='" + Direccion + '\'' +
                '}';
    }
}
