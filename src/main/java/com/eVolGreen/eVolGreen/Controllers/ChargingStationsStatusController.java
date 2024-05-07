package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationStatusDTO;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChargingStationsStatusController {
    @Autowired
    private ChargingStationStatusRepository chargingStationStatusRepository;

    @GetMapping("/chargingstationsstatus")
    public List<ChargingStationStatusDTO> getChargingStationsStatus() {
        return chargingStationStatusRepository.findAll()
                .stream()
                .map(ChargingStationStatusDTO::new)
                .collect(Collectors.toList());
    }
}
