package com.eVolGreen.eVolGreen.Services.ImplementService.ChargingStationServiceImplement;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargingUnit;
import com.eVolGreen.eVolGreen.Repositories.ChargingUnitRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ChargingUnitServiceImplement implements ChargingUnitService {
    @Autowired
    private ChargingUnitRepository chargingUnitRepository;

    @Override
    public List<ChargingUnitDTO> getChargingUnitsDTO() {
        return chargingUnitRepository.findAll()
                .stream()
                .map(ChargingUnitDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void saveChargingUnit(ChargingUnit chargingUnit) { // Corregido el nombre del método
        chargingUnitRepository.save(chargingUnit);
    }

    @Override
    public void saveUnidadCarga(ChargingUnit chargingUnit) {
        chargingUnitRepository.save(chargingUnit);

    }
}
