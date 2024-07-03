package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.ChargingUnit;

public class ChargingUnitDTO {
    private Long id;
    private String unit;
    private Long chargerId;

    public ChargingUnitDTO() {
    }

    public ChargingUnitDTO(ChargingUnit chargingUnit) {
        id = chargingUnit.getId();
        unit = chargingUnit.getUnit();
        chargerId = chargingUnit.getCharger().getId();
    }

    public Long getId() {
        return id;
    }
    public String getUnit() {
        return unit;
    }

    public Long getChargerId() {
        return chargerId;
    }
}
