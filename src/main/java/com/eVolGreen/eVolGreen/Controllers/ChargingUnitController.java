package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Repositories.ChargingUnitRepository;
import com.eVolGreen.eVolGreen.Services.ChargingUnitService;
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
    private ChargingUnitService chargingUnitService;
    @Autowired
    private ChargingUnitRepository chargingUnitRepository;

    @GetMapping("/chargingUnits")
    public List<ChargingUnitDTO> getChargingUnits() {
        return chargingUnitService.getChargingUnitsDTO();
    }
}
