package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargingUnit;

import java.util.List;

public interface ChargingUnitService {

    List<ChargingUnitDTO> getChargingUnitsDTO();

    void saveChargingUnit(ChargingUnit chargingUnit);

    void saveUnidadCarga(ChargingUnit chargingUnit);
}
