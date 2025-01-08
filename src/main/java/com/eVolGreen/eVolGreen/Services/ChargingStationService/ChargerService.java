package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargerStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;

import java.util.List;

public interface ChargerService {

    List<ChargerDTO> getChargersDTO();
    void saveCharger(Charger charger);

    Charger findById(Long chargerId);

    ChargerDTO getChargerDTO(Long id);

    boolean updateChargerStatus(Long id, ChargerStatus activeStatus);

    List<ChargerDTO> getActiveChargersDTO();

    List<ChargerDTO> getChargersForCurrentUser(String email);

    Charger findByOCPPid(String oCPPid);
}
