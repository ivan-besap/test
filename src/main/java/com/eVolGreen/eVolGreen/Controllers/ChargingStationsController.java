package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChargingStationsController {
    @Autowired
    private ChargingStationRepository chargingStationsRepository;

    @GetMapping("/chargingStations")
    public List<ChargingStationsDTO> getChargingStations() {
        return chargingStationsRepository.findAll()
                .stream()
                .map(ChargingStationsDTO::new)
                .collect(Collectors.toList());
    }


}
