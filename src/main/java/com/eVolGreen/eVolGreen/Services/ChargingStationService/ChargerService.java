package com.eVolGreen.eVolGreen.Services.ChargingStationService;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;

import java.util.List;

public interface ChargerService {

    List<ChargerDTO> getChargersDTO();
    void saveCharger(Charger charger);

    Charger findById(Long chargerId);

    ChargerDTO getChargerDTO(Long id);
}
