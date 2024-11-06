package com.eVolGreen.eVolGreen.Services.ChargingStationService;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerModelDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;

import java.util.List;

public interface ChargerModelService {
    ChargerModel findByName(String name);
    void saveChargerModel(ChargerModel chargerModel);
    List<ChargerModelDTO> getAllChargerModels();  // Nuevo método

    ChargerModel findById(Long id);

    List<ChargerModelDTO> getChargerModelsByEmpresa(Long empresaId);

    void deleteById(Long id);
}