package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ChargingStationsController {
    @Autowired
    private ChargingStationsService chargingStationsService;
    @Autowired
    private ChargingStationRepository chargingStationsRepository;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/chargingStations")
    public List<ChargingStationsDTO> getChargingStations() {
        return chargingStationsService.getChargintStationsDTO();
    }

//    @PostMapping ("/company/current/chargingStations")
//    public ResponseEntity<Object> registerChargingStation(Authentication authentication,
//                                                          @RequestBody ChargingStationsDTO chargingStationsDTO) {
//
//        Company company = companyService.findByEmailCompany(authentication.getName());
//        if (company == null) {
//            return ResponseEntity.status(404).body("Company not found");
//        }
//        if (chargingStationsDTO.getName().isBlank()) {
//            return ResponseEntity.status(400).body("Charging station name cannot be empty");
//        }
//    }
}
