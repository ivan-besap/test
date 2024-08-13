package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationRequestDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.DTOS.LocationDTO;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationsStatusService;
import com.eVolGreen.eVolGreen.Services.CompanyService;
import com.eVolGreen.eVolGreen.Services.LocationService;
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
    @Autowired
    private LocationService  locationService;

    @GetMapping("/chargingStations")
    public List<ChargingStationsDTO> getChargingStations() {
        return chargingStationsService.getChargingStationsDTO();
    }

    @GetMapping("/chargingStations/{id}")
    public ChargingStationsDTO getChargingStations(@PathVariable Long id) {
        return chargingStationsService.getChargingStationDTO(id);
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

        if(chargingStationsDTO.getLocation().getAddress() == null || chargingStationsDTO.getLocation().getAddress().isBlank()) {
            message = "La dirección no puede estar vacía";
            return ResponseEntity.status(400).body(message);
        }

        ChargingStationStatus newChargingStationStatus = new ChargingStationStatus("No disponible / En creación");
        chargingStationsStatusService.saveChargingStationsStatus(newChargingStationStatus);

        Location location = new Location(chargingStationsDTO.getLocation().getAddress());
        locationService.saveLocation(location);

        ChargingStation newChargingStation = new ChargingStation();
        newChargingStation.setName(chargingStationsDTO.getName());
        newChargingStation.setLocation(location);
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
