package com.eVolGreen.eVolGreen.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Connector {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal power;
    private ConnectorStatus connectorStatus;
    private BigDecimal charge;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charger_id")
    private Charger charger;
    @OneToMany(mappedBy = "connector", fetch = FetchType.EAGER)
    @JsonManagedReference("connector-reservation")
    private Set<Reservation> reservations = new HashSet<>();

    public Connector() {
    }

    public Connector(String name, BigDecimal power, ConnectorStatus connectorStatus, BigDecimal charge, Charger charger) {
        this.name = name;
        this.power = power;
        this.connectorStatus = connectorStatus;
        this.charge = charge;
        this.charger = charger;
    }

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

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public ConnectorStatus getConnectorStatus() {
        return connectorStatus;
    }

    public void setConnectorStatus(ConnectorStatus connectorStatus) {
        this.connectorStatus = connectorStatus;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
