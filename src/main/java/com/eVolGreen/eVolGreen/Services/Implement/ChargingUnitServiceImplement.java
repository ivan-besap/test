package com.eVolGreen.eVolGreen.Services.Implement;

import com.eVolGreen.eVolGreen.DTOS.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Models.ChargingUnit;
import com.eVolGreen.eVolGreen.Repositories.ChargingUnitRepository;
import com.eVolGreen.eVolGreen.Services.ChargingUnitService;
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
    public void saveChargingUnits(ChargingUnit chargingUnit) {
        chargingUnitRepository.save(chargingUnit);
    }
}
