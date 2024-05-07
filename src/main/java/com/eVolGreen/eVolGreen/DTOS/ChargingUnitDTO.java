package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.ChargingUnit;

public class ChargingUnitDTO {
    private Long id;
    private String unit;

    public ChargingUnitDTO(ChargingUnit chargingUnit) {
        id = chargingUnit.getId();
        unit = chargingUnit.getUnit();
    }

    public Long getId() {
        return id;
    }
    public String getUnit() {
        return unit;
    }
}
