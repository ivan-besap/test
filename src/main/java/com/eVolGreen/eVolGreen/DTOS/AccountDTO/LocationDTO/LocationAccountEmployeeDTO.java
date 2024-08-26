package com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

public class LocationAccountEmployeeDTO {

    private long id;

    @NotNull(message = "La Direccion es obligatoria")
    private String Direccion;

    @OneToOne(mappedBy = "UbicacionCuentaTrabajador")
    private long CuentaEmpleado;

    public LocationAccountEmployeeDTO(Location Ubicacion) {

        id = Ubicacion.getId();
        Direccion = Ubicacion.getDireccion();
        CuentaEmpleado = Ubicacion.getCuentaEmpleado().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "La Direccion es obligatoria") String getDireccion() {
        return Direccion;
    }

    public long getCuentaEmpleado() {
        return CuentaEmpleado;
    }

    @Override
    public String toString() {
        return "LocationAccountEmployeeDTO{" +
                "id=" + id +
                ", Direccion='" + Direccion + '\'' +
                ", CuentaEmpleado=" + CuentaEmpleado +
                '}';
    }
}
