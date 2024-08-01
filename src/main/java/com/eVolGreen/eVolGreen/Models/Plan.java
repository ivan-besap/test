package com.eVolGreen.eVolGreen.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Entity
public class Plan {
    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    private String name;
    private String description;
    private Integer days;
    private BigDecimal cost;
    private BigDecimal totalKWh; // Total de kWh incluidos en el plan
    private BigDecimal availableKWh; // kWh disponibles que quedan en el plan

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chargingStation_id")
    @JsonIgnore
    private ChargingStation chargingStation;

    public Plan() { }

    public Plan(String name, String description, Integer days, BigDecimal cost, BigDecimal  totalKWh, BigDecimal availableKWh) {
        this.name = name;
        this.description = description;
        this.days = days;
        this.cost = cost;
        this.totalKWh = totalKWh;
        this.availableKWh = availableKWh;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    public BigDecimal getTotalKWh() {
        return totalKWh;
    }

    public void setTotalKWh(BigDecimal totalKWh) {
        this.totalKWh = totalKWh;
    }

    public BigDecimal getAvailableKWh() {
        return availableKWh;
    }

    public void setAvailableKWh(BigDecimal availableKWh) {
        this.availableKWh = availableKWh;
    }
}
