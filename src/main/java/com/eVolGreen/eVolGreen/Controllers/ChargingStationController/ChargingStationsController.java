package com.eVolGreen.eVolGreen.Controllers.ChargingStationController;

import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargingStationsDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.NewChargingStationsDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.CompanyUser;
import com.eVolGreen.eVolGreen.Repositories.ChargingStationRepository;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.DUserService.CompanyUserService;
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
    private CompanyUserService companyUserService;

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

    @PostMapping("/companies/current/chargingStations")
    public ResponseEntity<Object> registerChargingStation(Authentication authentication,
                                                          @RequestBody NewChargingStationsDTO chargingStationsDTO) {

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String message;

        if (company == null) {
            message = "No se encontró la empresa";
            return ResponseEntity.status(400).body(message);
        }

        if (chargingStationsDTO.getNombreTerminal().isBlank()) {
            message = "El nombre de la estación de carga no puede estar vacío";
            return ResponseEntity.status(400).body(message);
        }

        if(chargingStationsDTO.getUbicacionTerminal() == null || chargingStationsDTO.getUbicacionTerminal().isBlank()) {
            message = "La dirección no puede estar vacía";
            return ResponseEntity.status(400).body(message);
        }

        Location ubicacionTerminal = new Location(
                chargingStationsDTO.getUbicacionTerminal());
        locationService.saveLocationCompany(ubicacionTerminal);

        ChargingStation newChargingStation = new ChargingStation(
                chargingStationsDTO.getNombreTerminal(),
                LocalDate.now(),
                ChargingStationStatus.CONSTRUCTION,
                ubicacionTerminal,
                false
        );

        Optional<AccountCompany> optionalAccount = company.getCuentaCompañia()
                .stream()
                .findFirst();
        if (optionalAccount.isEmpty()) {
            message = "No se encontró una cuenta asociada a la empresa";
            return ResponseEntity.status(400).body(message);
        }

        newChargingStation.setCuentaCompañia(optionalAccount.get());
        chargingStationsService.saveChargingStations(newChargingStation);

        return ResponseEntity.status(201).body(new ChargingStationsDTO(newChargingStation));
    }

    @PutMapping("/companies/current/chargingStations/{id}")
    public ResponseEntity<Object> updateChargingStation(Authentication authentication,
                                                        @PathVariable Long id,
                                                        @RequestBody NewChargingStationsDTO chargingStationsDTO) {

        CompanyUser company = companyUserService.findByEmailCompanyUser(authentication.getName());
        String message;

        if (company == null) {
            message = "No se encontró la empresa";
            return ResponseEntity.status(400).body(message);
        }

        // Buscar la estación de carga existente por su ID
        ChargingStation existingChargingStation = chargingStationsService.findById(id);
        if (existingChargingStation == null) {
            message = "No se encontró la estación de carga";
            return ResponseEntity.status(404).body(message);
        }

        if (chargingStationsDTO.getNombreTerminal().isBlank()) {
            message = "El nombre de la estación de carga no puede estar vacío";
            return ResponseEntity.status(400).body(message);
        }

        if(chargingStationsDTO.getUbicacionTerminal() == null || chargingStationsDTO.getUbicacionTerminal().isBlank()) {
            message = "La dirección no puede estar vacía";
            return ResponseEntity.status(400).body(message);
        }

        // Actualizar la ubicación de la estación de carga
        Location ubicacionTerminal = existingChargingStation.getUbicacionTerminal();
        ubicacionTerminal.setDireccion(chargingStationsDTO.getUbicacionTerminal());
        locationService.saveLocationCompany(ubicacionTerminal);

        // Actualizar la estación de carga existente con los nuevos datos
        existingChargingStation.setNombreTerminal(chargingStationsDTO.getNombreTerminal());
        existingChargingStation.setUbicacionTerminal(ubicacionTerminal);

        chargingStationsService.saveChargingStations(existingChargingStation);

        return ResponseEntity.status(200).body(new ChargingStationsDTO(existingChargingStation));
    }

    @PatchMapping("/companies/current/chargingStations/{id}/delete")
    public ResponseEntity<Object> deleteChargingStation(Authentication authentication,
                                                        @PathVariable Long id) {

        // Obtener el usuario autenticado
        CompanyUser companyUser = companyUserService.findByEmailCompanyUser(authentication.getName());
        String message = "";

        if (companyUser == null) {
            message = "No se encontró la empresa";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
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

        message = "Estación de carga desactivada correctamente";
        return ResponseEntity.ok(message);
    }

}
