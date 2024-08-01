package com.eVolGreen.eVolGreen.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    private TransactionType type;
    private String description;
    private LocalDateTime date;
    private LocalDateTime loadingStartDate;
    private LocalDateTime loadingEndDate;
    private BigDecimal suppliedEnergy;
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chargingStation_id")
    @JsonBackReference
    private ChargingStation chargingStation;

    public Transaction() { }

    public Transaction(TransactionType type, String description, LocalDateTime date, LocalDateTime loadingStartDate, LocalDateTime loadingEndDate, BigDecimal suppliedEnergy, Integer cost) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.loadingStartDate = loadingStartDate;
        this.loadingEndDate = loadingEndDate;
        this.suppliedEnergy = suppliedEnergy;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getLoadingStartDate() {
        return loadingStartDate;
    }

    public void setLoadingStartDate(LocalDateTime loadingStartDate) {
        this.loadingStartDate = loadingStartDate;
    }

    public LocalDateTime getLoadingEndDate() {
        return loadingEndDate;
    }

    public void setLoadingEndDate(LocalDateTime loadingEndDate) {
        this.loadingEndDate = loadingEndDate;
    }

    public BigDecimal getSuppliedEnergy() {
        return suppliedEnergy;
    }

    public void setSuppliedEnergy(BigDecimal suppliedEnergy) {
        this.suppliedEnergy = suppliedEnergy;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
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
}
