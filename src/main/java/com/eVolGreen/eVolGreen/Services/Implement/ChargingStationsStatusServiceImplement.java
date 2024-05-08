package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationStatusDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationStatusRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationsStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingStationsStatusServiceImplement implements ChargingStationsStatusService {
    @Autowired
    private ChargingStationStatusRepository chargingStationStatusRepository;

    @Override
    public List<ChargingStationStatusDTO> getChargingStationsStatusDTO() {
        return chargingStationStatusRepository.findAll()
                .stream()
                .map(ChargingStationStatusDTO::new)
                .collect(Collectors.toList());
    }
    @Override
    public void saveChargingStationsStatus(ChargingStationStatus chargingStationStatus) {
        chargingStationStatusRepository.save(chargingStationStatus);
    }
}
