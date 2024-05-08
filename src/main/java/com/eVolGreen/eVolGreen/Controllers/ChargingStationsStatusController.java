package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationStatusDTO;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationStatusRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationsStatusService;
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
    private ChargingStationsStatusService chargingStationsStatusService;
    @Autowired
    private ChargingStationStatusRepository chargingStationStatusRepository;

    @GetMapping("/chargingStationsStatus")
    public List<ChargingStationStatusDTO> getChargingStationsStatus() {
        return chargingStationsStatusService.getChargingStationsStatusDTO();
    }
}
