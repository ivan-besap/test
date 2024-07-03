package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChargerDTO {
    private Long id;
    private String model;
    private Time estimatedLoadingTime;
    private BigDecimal voltage;
    private Boolean enabled;
    private LocalDate createdDay;
    private TypeOfLoad typeOfLoads;
    private Set<ChargingUnitDTO> chargingUnits;
    private Set<ConnectorDTO> connectors;
    private Long chargingStationId;

    public ChargerDTO() {}

    public ChargerDTO(Charger chargingStation) {
        id = chargingStation.getId();
        model = chargingStation.getModel();
        estimatedLoadingTime = chargingStation.getEstimatedLoadingTime();
        voltage = chargingStation.getVoltage();
        enabled = chargingStation.getEnabled();
        createdDay = chargingStation.getCreatedDay();
        typeOfLoads = chargingStation.getTypeOfLoads();
        chargingUnits = chargingStation.getChargingUnits().stream().map(ChargingUnitDTO::new).collect(Collectors.toSet());
        connectors = chargingStation.getConnectors().stream().map(ConnectorDTO::new).collect(Collectors.toSet());
        chargingStationId = chargingStation.getChargingStation().getId();
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Time getEstimatedLoadingTime() {
        return estimatedLoadingTime;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public TypeOfLoad getTypeOfLoads() {
        return typeOfLoads;
    }

    public Set<ChargingUnitDTO> getChargingUnits() {
        return chargingUnits;
    }

    public Set<ConnectorDTO> getConnectors() {
        return connectors;
    }

    public Long getChargingStationId() {
        return chargingStationId;
    }
}
