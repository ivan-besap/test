package com.eVolGreen.eVolGreen.Services;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation;

import java.util.List;

public interface ChargingStationsService {

    List<ChargingStationsDTO> getChargingStationsDTO();
    void saveChargingStations(ChargingStation chargingStations);

    ChargingStation findById(Long stationId);

    ChargingStationsDTO getChargingStationDTO(Long id);
}
