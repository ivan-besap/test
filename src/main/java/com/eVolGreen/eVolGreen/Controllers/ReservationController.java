package com.eVolGreen.eVolGreen.Controllers;

import com.eVolGreen.eVolGreen.DTOS.ReservationDTO;
import com.eVolGreen.eVolGreen.DTOS.ReservationRequest;
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
    private CompanyService companyService;

    @Autowired
    private AccountService accountService;

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
                                                    @RequestBody ReservationRequest reservationRequest) {

        String email = authentication.getName();
        Client client = clientService.findByEmail(email);
        Company company = companyService.findByEmailCompany(email);

        if (client == null && company == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        Account account;
        if (client != null) {
            Optional<Account> clientAccount = client.getAccounts().stream().findFirst();
            if (clientAccount.isEmpty()) {
                return ResponseEntity.status(404).body("Client account not found");
            }
            account = clientAccount.get();
        } else {
            Optional<Account> companyAccount = company.getAccounts().stream().findFirst();
            if (companyAccount.isEmpty()) {
                return ResponseEntity.status(404).body("Company account not found");
            }
            account = companyAccount.get();
        }

        Charger charger = chargerService.findById(reservationRequest.getChargerId());
        if (charger == null) {
            return ResponseEntity.status(404).body("Charger not found");
        }

        Connector connector = connectorService.findById(reservationRequest.getConnectorId());
        if (connector == null) {
            return ResponseEntity.status(404).body("Connector not found");
        }

        if (!charger.getConnectors().contains(connector)) {
            return ResponseEntity.status(400).body("The connector does not belong to the specified charger");
        }


        LocalDateTime startTime = reservationRequest.getStartTime().truncatedTo(ChronoUnit.SECONDS);
        if (startTime == null) {
            return ResponseEntity.status(400).body("Start time must be provided");
        }
        LocalDateTime endTime = startTime.plusMinutes(30).truncatedTo(ChronoUnit.SECONDS);

        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            return ResponseEntity.status(400).body("End time must be after start time");
        }

        Reservation reservation = new Reservation(startTime, endTime, account, charger, connector);
        reservationService.saveReservation(reservation);

        return ResponseEntity.status(201).body("Reservation created successfully");
    }
}