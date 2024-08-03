package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Services.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChargerController {

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private ChargingStationsService chargingStationsService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/chargers")
    public List<ChargerDTO> getChargers() {
        return chargerService.getChargersDTO();
    }

    @GetMapping("/chargers/{id}")
    public ChargerDTO getCharger(@PathVariable Long id) {
        return chargerService.getChargerDTO(id);
    }

    @PostMapping("/chargers")
    public ResponseEntity<Object> createCharger(Authentication authentication,
                                                @RequestBody ChargerDTO chargerDTO) {

        String email = authentication.getName();
        Company company = companyService.findByEmailCompany(email);
        if (company == null) {
            return ResponseEntity.status(404).body("Company not found for the authenticated user.");
        }

        if(chargerDTO.getModel() == null || chargerDTO.getModel().isBlank()) {
            return ResponseEntity.status(400).body("Charger model cannot be empty.");
        }

        ChargingStation chargingStation = chargingStationsService.findById(chargerDTO.getChargingStationId());
        if (chargingStation == null) {
            return ResponseEntity.status(404).body("Charging station not found.");
        }

        Charger charger = new Charger();
        charger.setModel(chargerDTO.getModel());
        charger.setChargingStation(chargingStation);
        charger.setEstimatedLoadingTime(Time.valueOf(LocalTime.of(0, 1)));
        charger.setVoltage(chargerDTO.getVoltage() != null ? chargerDTO.getVoltage() : BigDecimal.valueOf(0.0));
        charger.setEnabled(false);
        charger.setCreatedDay(LocalDate.now());
        charger.setTypeOfLoads(chargerDTO.getTypeOfLoads());

        chargerService.saveCharger(charger);


        return ResponseEntity.status(201).body("Charger created successfully and associated with the charging station.");
    }
}
