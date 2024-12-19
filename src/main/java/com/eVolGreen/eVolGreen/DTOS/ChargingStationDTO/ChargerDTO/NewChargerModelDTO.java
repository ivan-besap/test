package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

public class NewChargerModelDTO {
    private String name;
    private Long empresaId; // Nuevo campo para la asociación con Empresa

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