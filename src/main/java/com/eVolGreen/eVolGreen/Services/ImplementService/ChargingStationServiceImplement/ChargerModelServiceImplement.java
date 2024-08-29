package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerModelDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import com.eVolGreen.eVolGreen.Repositories.ChargerModelRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargerModelServiceImplement implements ChargerModelService {

    @Autowired
    private ChargerModelRepository chargerModelRepository;

    @Override
    public ChargerModel findByName(String name) {
        return chargerModelRepository.findByName(name);
    }

    @Override
    public void saveChargerModel(ChargerModel chargerModel) {
        chargerModelRepository.save(chargerModel);
    }

    @Override
    public List<ChargerModelDTO> getAllChargerModels() {
        return chargerModelRepository.findAll().stream()
                .map(model -> new ChargerModelDTO(model.getId(), model.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public ChargerModel findById(Long id) {
        return chargerModelRepository.findById(id).orElse(null);
    }
}