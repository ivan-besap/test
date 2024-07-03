package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Models.ChargingUnit;

import java.util.List;

public interface ChargingUnitService {

    List<ChargingUnitDTO> getChargingUnitsDTO();

    void saveChargingUnit(ChargingUnit chargingUnit);
}
