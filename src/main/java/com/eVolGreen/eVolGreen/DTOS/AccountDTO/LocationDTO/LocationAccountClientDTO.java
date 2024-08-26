package com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

public class LocationAccountClientDTO {

    private long id;

    @NotNull(message = "La Direccion es obligatoria")
    private String Direccion;

    @OneToOne(mappedBy = "UbicacionCuentaCliente")
    private long CuentaCliente;

    public LocationAccountClientDTO(Location Ubicacion) {

        id = Ubicacion.getId();
        Direccion = Ubicacion.getDireccion();
        CuentaCliente = Ubicacion.getCuentaCliente().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "La Direccion es obligatoria") String getDireccion() {
        return Direccion;
    }

    public long getCuentaCliente() {
        return CuentaCliente;
    }

    @Override
    public String toString() {
        return "LocationAccountClientDTO{" +
                "id=" + id +
                ", Direccion='" + Direccion + '\'' +
                ", CuentaCliente=" + CuentaCliente +
                '}';
    }
}
