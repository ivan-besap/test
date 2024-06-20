package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Models.Company;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationsStatusService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ChargingStationsController {
    @Autowired
    private ChargingStationsService chargingStationsService;
    @Autowired
    private ChargingStationRepository chargingStationsRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ChargingStationsStatusService chargingStationsStatusService;

    @GetMapping("/chargingStations")
    public List<ChargingStationsDTO> getChargingStations() {
        return chargingStationsService.getChargingStationsDTO();
    }

    @PostMapping("/company/current/chargingStations")
    public ResponseEntity<Object> registerChargingStation(Authentication authentication,
                                                          @RequestBody ChargingStationsDTO chargingStationsDTO) {

        Company company = companyService.findByEmailCompany(authentication.getName());
        String message;

        if (company == null) {
            message = "No se encontró la empresa";
            return ResponseEntity.status(400).body(message);
        }

        if (chargingStationsDTO.getName().isBlank()) {
            message = "El nombre de la estación de carga no puede estar vacío";
            return ResponseEntity.status(400).body(message);
        }

        if (chargingStationsDTO.getCurrentLoad().compareTo(BigDecimal.ZERO) < 0) {
            message = "La carga actual de la estación de carga no puede ser negativa";
            return ResponseEntity.status(400).body(message);
        }

        ChargingStationStatus newChargingStationStatus = new ChargingStationStatus("No disponible / En creación");
        chargingStationsStatusService.saveChargingStationsStatus(newChargingStationStatus);

        ChargingStation newChargingStation = new ChargingStation();
        newChargingStation.setName(chargingStationsDTO.getName());
        newChargingStation.setCurrentLoad(chargingStationsDTO.getCurrentLoad());
        newChargingStation.setCreatedDay(LocalDate.now());

        Set<ChargingStationStatus> statusSet = new HashSet<>();
        statusSet.add(newChargingStationStatus);
        newChargingStation.setChargingStationStatus(statusSet);

        Optional<Account> optionalAccount = company.getAccounts().stream().findFirst();
        if (optionalAccount.isEmpty()) {
            message = "No se encontró una cuenta asociada a la empresa";
            return ResponseEntity.status(400).body(message);
        }

        newChargingStation.setAccount(optionalAccount.get());
        chargingStationsRepository.save(newChargingStation);

        return ResponseEntity.status(201).body(new ChargingStationsDTO(newChargingStation));
    }
}
