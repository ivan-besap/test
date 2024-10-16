package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;


import javax.validation.constraints.NotNull;

public class NewTypeConnectorDTO {

    @NotNull(message = "El nombre del tipo de conector es obligatorio")
    private String nombre;

    // Constructor vacío
    public NewTypeConnectorDTO() {}

    // Constructor con parámetros
    public NewTypeConnectorDTO(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
