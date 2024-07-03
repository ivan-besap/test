package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Models.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingUnit;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Services.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingUnitService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChargingUnitController {

    @Autowired
    private ChargingUnitService chargingUnitService;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/chargingUnits")
    public List<ChargingUnitDTO> getChargingUnits() {
        return chargingUnitService.getChargingUnitsDTO();
    }

    @PostMapping("/chargingUnits")
    public ResponseEntity<Object> createChargingUnit(Authentication authentication,
                                                     @RequestBody ChargingUnitDTO chargingUnitDTO) {

        String email = authentication.getName();
        Company company = companyService.findByEmailCompany(email);
        if (company == null) {
            return ResponseEntity.status(404).body("Company not found for the authenticated user.");
        }

        Charger charger = chargerService.findById(chargingUnitDTO.getChargerId());
        if (charger == null) {
            return ResponseEntity.status(404).body("Charger not found.");
        }

        ChargingUnit chargingUnit = new ChargingUnit();
        chargingUnit.setUnit(chargingUnitDTO.getUnit());
        chargingUnit.setCharger(charger);

        chargingUnitService.saveChargingUnit(chargingUnit);

        return ResponseEntity.status(201).body("Charging unit created successfully and associated with the charger.");
    }
}
