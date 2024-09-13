package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;

public class TypeConnectorDTO {

    private Long id;
    private String nombre;

    public TypeConnectorDTO(TypeConnector typeConnector) {
        this.id = typeConnector.getId();
        this.nombre = typeConnector.getNombre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
