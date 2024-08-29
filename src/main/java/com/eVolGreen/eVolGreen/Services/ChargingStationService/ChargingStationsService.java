package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;

import java.util.List;

public interface ChargingStationsService {

    List<ChargingStationsDTO> getChargingStationsDTO();
    List<ChargingStationsDTO> getActiveChargingStationsDTO();
    void saveChargingStations(ChargingStation chargingStations);

    ChargingStation findById(Long stationId);

    ChargingStationsDTO getChargingStationDTO(Long id);


    boolean updateChargingStationStatus(Long id, ChargingStationStatus activeStatus);
}
