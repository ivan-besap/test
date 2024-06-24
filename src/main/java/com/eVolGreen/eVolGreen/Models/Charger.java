package com.eVolGreen.eVolGreen.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Charger {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private Time estimatedLoadingTime;
    private BigDecimal voltage;
    private Boolean enabled;
    private LocalDate createdDay;
    private TypeOfLoad typeOfLoads;
    @ManyToOne
    @JoinColumn(name = "chargingStation_id")
    private ChargingStation chargingStation;

    @OneToMany(mappedBy = "charger", fetch = FetchType.EAGER)
    private Set<ChargingUnit> chargingUnits = new HashSet<>();
    @OneToMany(mappedBy = "charger", fetch = FetchType.EAGER)
    private Set<Connector> connectors = new HashSet<>();
    @OneToMany(mappedBy = "charger", fetch = FetchType.EAGER)
    @JsonManagedReference("charger-reservation")
    private Set<Reservation> reservations = new HashSet<>();


    public Charger() {

    }

    public Charger(String model, Time estimatedLoadingTime, BigDecimal voltage, Boolean enabled, LocalDate createdDay, TypeOfLoad typeOfLoads, ChargingStation chargingStation) {
        this.model = model;
        this.estimatedLoadingTime = estimatedLoadingTime;
        this.voltage = voltage;
        this.enabled = enabled;
        this.createdDay = createdDay;
        this.typeOfLoads = typeOfLoads;
        this.chargingStation = chargingStation;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Time getEstimatedLoadingTime() {
        return estimatedLoadingTime;
    }

    public void setEstimatedLoadingTime(Time estimatedLoadingTime) {
        this.estimatedLoadingTime = estimatedLoadingTime;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(LocalDate createdDay) {
        this.createdDay = createdDay;
    }

    public TypeOfLoad getTypeOfLoads() {
        return typeOfLoads;
    }

    public void setTypeOfLoads(TypeOfLoad typeOfLoads) {
        this.typeOfLoads = typeOfLoads;
    }

    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    public Set<ChargingUnit> getChargingUnits() {
        return chargingUnits;
    }

    public void setChargingUnits(Set<ChargingUnit> chargingUnits) {
        this.chargingUnits = chargingUnits;
    }

    public Set<Connector> getConnectors() {
        return connectors;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setConnectors(Set<Connector> connectors) {
        this.connectors = connectors;
    }
    public void addConnector( Connector connector) {
        connector.setCharger(this);
        this.connectors.add(connector);
    }
    public void addChargingUnit(ChargingUnit chargingUnit) {
        chargingUnit.setCharger(this);
        this.chargingUnits.add(chargingUnit);
    }
}
