package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

public class NewChargerManufacturerDTO {
    private String name;
    private Long empresaId; // Nuevo campo para asociar el fabricante con una empresa

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }
}