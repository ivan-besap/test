package com.eVolGreen.eVolGreen.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ChargingStation {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal currentLoad;
    private LocalDate createdDay;
    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.EAGER)
    private Set<ChargingStationStatus> chargingStationStatus = new HashSet<>();
    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();
    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.EAGER)
    private Set<Plan> plans = new HashSet<>();
    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.EAGER)
    private Set<Charger> chargers = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public ChargingStation() {
    }

    public ChargingStation(String name, BigDecimal currentLoad, LocalDate createdDay) {
        this.name = name;
        this.currentLoad = currentLoad;
        this.createdDay = createdDay;
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

    public BigDecimal getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(BigDecimal currentLoad) {
        this.currentLoad = currentLoad;
    }

    public LocalDate getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(LocalDate createdDay) {
        this.createdDay = createdDay;
    }

    public Set<ChargingStationStatus> getChargingStationStatus() {
        return chargingStationStatus;
    }

    public void setChargingStationStatus(Set<ChargingStationStatus> chargingStationStatus) {
        this.chargingStationStatus = chargingStationStatus;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }

    public Set<Charger> getChargers() {
        return chargers;
    }

    public void setChargers(Set<Charger> chargers) {
        this.chargers = chargers;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addTransaction(Transaction transaction){
        transaction.setChargingStation(this);
        this.transactions.add(transaction);
    }

    public void addChargingStationStatus(ChargingStationStatus chargingStationStatus){
        chargingStationStatus.setChargingStation(this);
        this.chargingStationStatus.add(chargingStationStatus);
    }

    public void addPlan(Plan plan){
        plan.setChargingStation(this);
        this.plans.add(plan);
    }

    public void addCharger(Charger charger){
        charger.setChargingStation(this);
        this.chargers.add(charger);
    }
}
