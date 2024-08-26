package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;

import java.util.List;

public interface ChargingStationsService {

    List<ChargingStationsDTO> getChargingStationsDTO();
    void saveChargingStations(ChargingStation chargingStations);

    ChargingStation findById(Long stationId);

    ChargingStationsDTO getChargingStationDTO(Long id);
}
