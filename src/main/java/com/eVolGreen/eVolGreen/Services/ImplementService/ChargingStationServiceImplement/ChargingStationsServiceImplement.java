package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingStationsServiceImplement implements ChargingStationsService {

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Override
    public List<ChargingStationsDTO> getChargingStationsDTO() {
        return chargingStationRepository.findAll()
                .stream()
                .map(ChargingStationsDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveChargingStations(ChargingStation chargingStations) {
        chargingStationRepository.save(chargingStations);
    }

    @Override
    public ChargingStation findById(Long stationId) {
        return chargingStationRepository.findById(stationId).orElse(null);
    }

    @Override
    public ChargingStationsDTO getChargingStationDTO(Long id) {
        return new ChargingStationsDTO(this.findById(id));
    }
}
