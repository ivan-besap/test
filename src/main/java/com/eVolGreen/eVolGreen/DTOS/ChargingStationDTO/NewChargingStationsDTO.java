package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import jakarta.validation.constraints.NotNull;

public class NewChargingStationsDTO {

        @NotNull(message = "El nombre de la terminal no puede ser nulo.")
        private String nombreTerminal;

        @NotNull(message = "La ubicación de la terminal no puede ser nula.")
        private String ubicacionTerminal;


    public NewChargingStationsDTO() { }

    public NewChargingStationsDTO(String nombreTerminal, String ubicacionTerminal) {

        this.nombreTerminal = nombreTerminal;
        this.ubicacionTerminal = ubicacionTerminal;

    }

    public @NotNull(message = "El nombre de la terminal no puede ser nulo.") String getNombreTerminal() {
        return nombreTerminal;
    }

    public @NotNull(message = "La ubicación de la terminal no puede ser nula.") String getUbicacionTerminal() {
        return ubicacionTerminal;
    }
}




