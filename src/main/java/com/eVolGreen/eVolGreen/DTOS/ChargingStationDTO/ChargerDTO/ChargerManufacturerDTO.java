package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

public class ChargerManufacturerDTO {
    private Long id;
    private String name;

    public ChargerManufacturerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}