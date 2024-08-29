package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;


import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public boolean updateChargingStationStatus(Long id, ChargingStationStatus activeStatus) {
        Optional<ChargingStation> optionalStation = chargingStationRepository.findById(id);
        if (optionalStation.isPresent()) {
            ChargingStation station = optionalStation.get();
            station.setEstadoTerminal(activeStatus);
            chargingStationRepository.save(station);
            return true;
        }
        return false;
    }

    @Override
    public List<ChargingStationsDTO> getActiveChargingStationsDTO() {
        return chargingStationRepository.findAll()
                .stream()
                .filter(ChargingStation::getActivo) // Filtra las estaciones activas
                .map(ChargingStationsDTO::new)
                .collect(Collectors.toList());
    }
}
