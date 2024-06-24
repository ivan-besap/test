package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private TypeAccounts typeAccounts;
    private Boolean isActive = false;
    private Set<Long> transactions;
    private Set<PlanDTO> plans;
    private Set<LocationDTO> locations;
    private List<CarDTO> cars;
    private Set<ChargingStationsDTO> chargingStations;
    private Set<ReservationDTO> reservation;



    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        typeAccounts = account.getTypeAccounts();
        isActive = account.getActive();
        transactions = account.getTransactions().stream().map(Transaction::getId).collect(Collectors.toSet());
        plans = account.getPlans().stream().map(PlanDTO::new).collect(Collectors.toSet());
        locations = account.getLocations().stream().map(LocationDTO::new).collect(Collectors.toSet());
        cars = account.getCars().stream().map(CarDTO -> new CarDTO(CarDTO)).collect(Collectors.toList());
        chargingStations = account.getChargingStations().stream().map(ChargingStationsDTO::new).collect(Collectors.toSet());
        reservation = account.getReservations().stream().map(ReservationDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public TypeAccounts getTypeAccounts() {
        return typeAccounts;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Set<Long> getTransactions() {
        return transactions;
    }

    public Set<PlanDTO> getPlans() {
        return plans;
    }

    public Set<LocationDTO> getLocations() {
        return locations;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public Set<ChargingStationsDTO> getChargingStations() {
        return chargingStations;
    }

    public Set<ReservationDTO> getReservation() {
        return reservation;
    }
}
