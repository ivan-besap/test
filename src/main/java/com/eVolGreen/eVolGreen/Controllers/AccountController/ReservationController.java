package com.eVolGreen.eVolGreen.Controllers.AccountController;


import com.eVolGreen.eVolGreen.DTOS.AccountDTO.ReservationDTO.ReservationClientDTO;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.User.subclassUser.ClientUser;
import com.eVolGreen.eVolGreen.Services.AccountService.AccountService;
import com.eVolGreen.eVolGreen.Services.AccountService.ReservationService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargerService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ChargingStationsService;
import com.eVolGreen.eVolGreen.Services.ChargingStationService.ConnectorService;
import com.eVolGreen.eVolGreen.Services.DUserService.ClientUserService;
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
    private ClientUserService clientUserService;

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
    public List<ReservationClientDTO> getReservations() {
        return reservationService.getReservationsDTO();
    }

    @PostMapping("/reservations")
    public ResponseEntity<Object> createReservation(Authentication authentication,
                                                    @RequestBody ReservationClientDTO reservationRequest) {

        String email = authentication.getName();
        ClientUser client = clientUserService.findByEmail(email);

        if (client == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        Optional<AccountClient> clientAccount = client.getCuentaCliente().stream().findFirst();
        if (clientAccount.isEmpty()) {
            return ResponseEntity.status(404).body("Client account not found");
        }
        AccountClient account = clientAccount.get();

        if (reservationRequest.getId() == null) {
            return ResponseEntity.status(400).body("Charging station ID must not be null");
        }
        ChargingStation chargingStation = chargingStationsService.findById(reservationRequest.getTerminal());
        if (chargingStation == null) {
            return ResponseEntity.status(404).body("Charging station not found");
        }
        Charger charger = chargerService.findById(reservationRequest.getCargador());
        if (charger == null || !charger.getTerminal().getId().equals(chargingStation.getId())) {
            return ResponseEntity.status(404).body("Charger not found in the specified charging station");
        }

        Connector connector = connectorService.findById(reservationRequest.getConector());
        if (connector == null || !connector.getCargador().getId().equals(charger.getId())) {
            return ResponseEntity.status(404).body("Connector not found or does not belong to the specified charger");
        }

        LocalDateTime startTime = reservationRequest.getHoraInicio().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime endTime = startTime.plusMinutes(30).truncatedTo(ChronoUnit.SECONDS);

        if (endTime.isBefore(startTime) || endTime.equals(startTime)) {
            return ResponseEntity.status(400).body("End time must be after start time");
        }

        Reservation reservation = new Reservation(startTime, endTime, account, chargingStation, charger, connector );
        reservationService.saveReservation(reservation);

        return ResponseEntity.status(201).body("Reservation created successfully");
    }

}
