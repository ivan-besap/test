package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerManufacturerDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerManufacturer;
import com.eVolGreen.eVolGreen.Repositories.ChargerManufacturerRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargerManufacturerServiceImplement implements ChargerManufacturerService {

    @Autowired
    private ChargerManufacturerRepository chargerManufacturerRepository;

    @Override
    public ChargerManufacturer findByName(String name) {
        return chargerManufacturerRepository.findByName(name);
    }

    @Override
    public void saveChargerManufacturer(ChargerManufacturer chargerManufacturer) {
        chargerManufacturerRepository.save(chargerManufacturer);
    }

    @Override
    public List<ChargerManufacturerDTO> getAllChargerManufacturers() {
        return chargerManufacturerRepository.findAll().stream()
                .map(manufacturer -> new ChargerManufacturerDTO(manufacturer.getId(), manufacturer.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public ChargerManufacturer findById(Long id) {
        return chargerManufacturerRepository.findById(id).orElse(null);
    }
}