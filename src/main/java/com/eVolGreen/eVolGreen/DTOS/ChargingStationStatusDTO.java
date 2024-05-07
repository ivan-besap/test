package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.ChargingStationStatus;

public class ChargingStationStatusDTO {
    private Long id;
    private String name;

    public ChargingStationStatusDTO(ChargingStationStatus chargingStationStatus) {
        id = chargingStationStatus.getId();
        name = chargingStationStatus.getName();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
