package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChargingStationsDTO {
    private Long id;
    private String name;
    private BigDecimal currentLoad;
    private LocalDate createdDay;
    private Set<ChargingStationStatusDTO> chargingStationStatus;
    private Set<TransactionDTO> transactions;
    private Set<PlanDTO> plans;
    private Set<ChargerDTO> chargers;


    public ChargingStationsDTO(ChargingStation chargingStation) {
        id = chargingStation.getId();
        name = chargingStation.getName();
        currentLoad = chargingStation.getCurrentLoad();
        createdDay = chargingStation.getCreatedDay();
        chargingStationStatus = chargingStation.getChargingStationStatus().stream().map(ChargingStationStatusDTO::new).collect(Collectors.toSet());
        transactions = chargingStation.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        plans = chargingStation.getPlans().stream().map(PlanDTO::new).collect(Collectors.toSet());
        chargers = chargingStation.getChargers().stream().map(ChargerDTO::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCurrentLoad() {
        return currentLoad;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public Set<ChargingStationStatusDTO> getChargingStationStatus() {
        return chargingStationStatus;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }

    public Set<PlanDTO> getPlans() {
        return plans;
    }

    public Set<ChargerDTO> getChargers() {
        return chargers;
    }
}
