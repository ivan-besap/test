package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargingUnitDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargingUnit;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChargingUnitController {

    @Autowired
    private ChargingUnitService chargingUnitService;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/chargingUnits")
    public List<ChargingUnitDTO> getChargingUnits() {
        return chargingUnitService.getChargingUnitsDTO();
    }

    @PostMapping("/chargingUnits")
    public ResponseEntity<Object> createChargingUnit(Authentication authentication,
                                                     @RequestBody ChargingUnitDTO chargingUnitDTO) {

        String email = authentication.getName();
        Optional<Account> account = accountService.findByEmail(email);
        if (account.isEmpty()) {
            return ResponseEntity.status(404).body("Account not found for the authenticated user.");
        }

        Charger charger = chargerService.findById(chargingUnitDTO.getId());
        if (charger == null) {
            return ResponseEntity.status(404).body("Charger not found.");
        }

        ChargingUnit chargingUnit = new ChargingUnit();
        chargingUnit.setUnidadCarga(chargingUnitDTO.getUnidadCargas());
        chargingUnit.setCargador(charger);

        chargingUnitService.saveUnidadCarga(chargingUnit);

        return ResponseEntity.status(201).body("Charging unit created successfully and associated with the charger.");
    }
}