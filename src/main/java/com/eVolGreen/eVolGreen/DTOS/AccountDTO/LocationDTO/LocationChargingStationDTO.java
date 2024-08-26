package com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

public class LocationChargingStationDTO {

    private long id;

    @NotNull(message = "La Direccion es obligatoria")
    private String Direccion;

    @OneToOne(mappedBy = "UbicacionTerminal")
    private ChargingStation Terminal;

    public LocationChargingStationDTO(Location Ubicacion) {

        id = Ubicacion.getId();
        Direccion = Ubicacion.getDireccion();
        Terminal = Ubicacion.getTerminal();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "La Direccion es obligatoria") String getDireccion() {
        return Direccion;
    }

    public ChargingStation getTerminal() {
        return Terminal;
    }

    @Override
    public String toString() {
        return "LocationChargingStationDTO{" +
                "id=" + id +
                ", Direccion='" + Direccion + '\'' +
                ", Terminal=" + Terminal +
                '}';
    }
}
