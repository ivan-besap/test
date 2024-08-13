package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.ChargingStation;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class ChargingStationsDTO {
    private Long id;
    private String name;
    private LocalDate createdDay;
    private Set<ChargingStationStatusDTO> chargingStationStatus;
    private Set<TransactionDTO> transactions;
    private Set<ChargerDTO> chargers;
    private Set<ReservationDTO> reservations;
    private LocationDTO location;

    // Constructor por defecto
    public ChargingStationsDTO() {}

    // Constructor que inicializa el DTO a partir de una entidad ChargingStation
    public ChargingStationsDTO(ChargingStation chargingStation) {
        this.id = chargingStation.getId();
        this.name = chargingStation.getName();
        this.createdDay = chargingStation.getCreatedDay();
        this.chargingStationStatus = chargingStation.getChargingStationStatus().stream()
                .map(ChargingStationStatusDTO::new).collect(Collectors.toSet());
        this.transactions = chargingStation.getTransactions().stream()
                .map(TransactionDTO::new).collect(Collectors.toSet());
        this.chargers = chargingStation.getChargers().stream()
                .map(ChargerDTO::new).collect(Collectors.toSet());
        this.reservations = chargingStation.getReservations().stream()
                .map(ReservationDTO::new).collect(Collectors.toSet());
        this.location = new LocationDTO(chargingStation.getLocation());
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(LocalDate createdDay) {
        this.createdDay = createdDay;
    }

    public Set<ChargingStationStatusDTO> getChargingStationStatus() {
        return chargingStationStatus;
    }

    public void setChargingStationStatus(Set<ChargingStationStatusDTO> chargingStationStatus) {
        this.chargingStationStatus = chargingStationStatus;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public Set<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public Set<ChargerDTO> getChargers() {
        return chargers;
    }

    public void setChargers(Set<ChargerDTO> chargers) {
        this.chargers = chargers;
    }
}
