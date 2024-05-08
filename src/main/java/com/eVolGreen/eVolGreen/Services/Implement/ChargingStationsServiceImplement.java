package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingStationsServiceImplement implements ChargingStationsService {
    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Override
    public List<ChargingStationsDTO> getChargintStationsDTO() {
        return chargingStationRepository.findAll()
                .stream()
                .map(ChargingStationsDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveChargingStations(ChargingStation chargingStations) {
        chargingStationRepository.save(chargingStations);
    }
}
