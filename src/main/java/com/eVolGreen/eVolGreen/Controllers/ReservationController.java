package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ReservationDTO;
import com.eVolGreen.eVolGreen.Models.*;
import com.eVolGreen.eVolGreen.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ChargingStationsService chargingStationsService;

    @Autowired
    private ChargerService chargerService;

    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public List<ReservationDTO> getReservations() {
        return reservationService.getReservationsDTO();
    }

    @PostMapping("/reservations")
    public ResponseEntity<Object> createReservation(Authentication authentication,
                                                    @RequestBody ReservationDTO reservationRequest) {

        String email = authentication.getName();
        Client client = clientService.findByEmail(email);

        if (client == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        Optional<Account> clientAccount = client.getAccounts().stream().findFirst();
        if (clientAccount.isEmpty()) {
            return ResponseEntity.status(404).body("Client account not found");
        }
        Account account = clientAccount.get();

        if (reservationRequest.getStationId() == null) {
            return ResponseEntity.status(400).body("Charging station ID must not be null");
        }
        ChargingStation chargingStation = chargingStationsService.findById(reservationRequest.getStationId());
        if (chargingStation == null) {
            return ResponseEntity.status(404).body("Charging station not found");
        }

        if (reservationRequest.getChargerId() == null) {
            return ResponseEntity.status(400).body("Charger ID must not be null");
        }
        Charger charger = chargerService.findById(reservationRequest.getChargerId());
        if (charger == null || !charger.getChargingStation().getId().equals(chargingStation.getId())) {
            return ResponseEntity.status(404).body("Charger not found in the specified charging station");
        }

        if (reservationRequest.getConnectorId() == null) {
            return ResponseEntity.status(400).body("Connector ID must not be null");
        }
        Connector connector = connectorService.findById(reservationRequest.getConnectorId());
        if (connector == null || !connector.getCharger().getId().equals(charger.getId())) {
            return ResponseEntity.status(404).body("Connector not found or does not belong to the specified charger");
        }

        LocalDateTime startTime = reservationRequest.getStartTime().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime endTime = startTime.plusMinutes(30).truncatedTo(ChronoUnit.SECONDS);

        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            return ResponseEntity.status(400).body("End time must be after start time");
        }

        Reservation reservation = new Reservation(startTime, endTime, account, chargingStation, charger, connector );
        reservationService.saveReservation(reservation);

        return ResponseEntity.status(201).body("Reservation created successfully");
    }

}
