package com.eVolGreen.eVolGreen.Services.ChargingStationService;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerManufacturerDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;

import java.util.List;

public interface ChargerManufacturerService {
    ChargerManufacturer findByName(String name);
    void saveChargerManufacturer(ChargerManufacturer chargerManufacturer);
    List<ChargerManufacturerDTO> getAllChargerManufacturers();  // Nuevo método

    ChargerManufacturer findById(Long id);
}