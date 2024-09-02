package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import com.eVolGreen.eVolGreen.Services.AccountService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ChargingStationsController {

    @Autowired
    private ChargingStationsService chargingStationsService;

    @Autowired
    private ChargingStationRepository chargingStationsRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService  locationService;

    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ConnectorService connectorService;

    @GetMapping("/chargingStations")
    public List<ChargingStationsDTO> getChargingStations() {
        return chargingStationsService.getActiveChargingStationsDTO(); // Llamada al nuevo método
    }
    @GetMapping("/chargingStations/{id}")
    public ChargingStationsDTO getChargingStations(@PathVariable Long id) {
        return chargingStationsService.getChargingStationDTO(id);
    }

    @PostMapping("/companies/current/chargingStations")
    public ResponseEntity<Object> registerChargingStation(Authentication authentication,
                                                          @RequestBody NewChargingStationsDTO chargingStationsDTO) {

        Optional<Account> account = accountService.findByEmail(authentication.getName());
        String message = " ";

        if (account.isEmpty()) {
            message = "No se encontró la cuenta";
            return ResponseEntity.status(400).body(message);
        }

        if (chargingStationsDTO.getNombreTerminal().isBlank()) {
            message = "El nombre de la estación de carga no puede estar vacío";
            return ResponseEntity.status(400).body(message);
        }

        if(chargingStationsDTO.getUbicacionTerminal() == null || chargingStationsDTO.getUbicacionTerminal().getDireccion().isBlank()) {
            message = "La dirección no puede estar vacía";
            return ResponseEntity.status(400).body(message);
        }

        // Crear una nueva ubicación a partir del DTO
        Location ubicacionTerminal = new Location(
                chargingStationsDTO.getUbicacionTerminal().getDireccion());
        locationService.saveLocation(ubicacionTerminal);

        ChargingStation newChargingStation = new ChargingStation(
                chargingStationsDTO.getNombreTerminal(),
                LocalDate.now(),
                ChargingStationStatus.INACTIVE,
                ubicacionTerminal,
                account.get(), // Asocia directamente la cuenta obtenida
                true
        );

        chargingStationsService.saveChargingStations(newChargingStation);


        return ResponseEntity.status(201).body(new ChargingStationsDTO(newChargingStation));
    }

    @PutMapping("/companies/current/chargingStations/{id}")
    public ResponseEntity<Object> updateChargingStation(Authentication authentication,
                                                        @PathVariable Long id,
                                                        @RequestBody NewChargingStationsDTO chargingStationsDTO) {

        Optional<Account> account = accountService.findByEmail(authentication.getName());
        String message = " ";

        if (account.isEmpty()) {
            message = "No se encontró la cuenta";
            return ResponseEntity.status(400).body(message);
        }

        ChargingStation existingChargingStation = chargingStationsService.findById(id);
        if (existingChargingStation == null) {
            message = "No se encontró la estación de carga";
            return ResponseEntity.status(404).body(message);
        }

        if (chargingStationsDTO.getNombreTerminal().isBlank()) {
            message = "El nombre de la estación de carga no puede estar vacío";
            return ResponseEntity.status(400).body(message);
        }

        if (chargingStationsDTO.getUbicacionTerminal() == null || chargingStationsDTO.getUbicacionTerminal().getDireccion().isBlank()) {
            message = "La dirección no puede estar vacía";
            return ResponseEntity.status(400).body(message);
        }

        Location ubicacionTerminal = existingChargingStation.getUbicacionTerminal();
        ubicacionTerminal.setDireccion(chargingStationsDTO.getUbicacionTerminal().getDireccion());
        locationService.saveLocation(ubicacionTerminal);

        existingChargingStation.setNombreTerminal(chargingStationsDTO.getNombreTerminal());
        chargingStationsService.saveChargingStations(existingChargingStation);

        return ResponseEntity.status(200).body(new ChargingStationsDTO(existingChargingStation));
    }

    @PatchMapping("/companies/current/chargingStations/{id}/delete")
    public ResponseEntity<Object> deleteChargingStation(Authentication authentication,
                                                        @PathVariable Long id) {

        // Obtener el usuario autenticado
        Optional<Account> account = accountService.findByEmail(authentication.getName());
        String message = " ";

        if (account.isEmpty()) {
            message = "No se encontró la cuenta";
            return ResponseEntity.status(400).body(message);
        }

        // Buscar la estación de carga por su ID
        ChargingStation chargingStation = chargingStationsService.findById(id);
        if (chargingStation == null) {
            message = "Estación de carga no encontrada";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Verificar si la estación de carga ya está desactivada
        if (!chargingStation.getActivo()) {
            message = "La estación de carga ya está desactivada";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        // Desactivar la estación de carga
        chargingStation.setActivo(false);
        chargingStationsService.saveChargingStations(chargingStation);

        for (Charger charger : chargingStation.getCargadores()) {
            charger.setActivo(false);
            chargerService.saveCharger(charger);

            for (Connector connector : charger.getConectores()) {
                connector.setActivo(false);
                connectorService.saveConnector(connector);
            }
        }

        message = "Estación de carga desactivada correctamente";
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/chargingStationsStatus/change-active-status")
    public ResponseEntity<String> changeChargingStationStatus(@RequestParam Long id, @RequestParam ChargingStationStatus activeStatus) {
        boolean result = chargingStationsService.updateChargingStationStatus(id, activeStatus);
        if (result) {
            return ResponseEntity.ok("Estado de la estación actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estación no encontrada.");
        }
    }

}
