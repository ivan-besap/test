package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Repositories.ChargingUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChargingUnitController {
    @Autowired
    private ChargingUnitRepository chargingUnitRepository;

    @GetMapping("/chargingUnits")
    public List<ChargingUnitDTO> getChargingUnits() {
        return chargingUnitRepository.findAll()
                .stream()
                .map(ChargingUnitDTO::new)
                .collect(Collectors.toList());
    }
}
