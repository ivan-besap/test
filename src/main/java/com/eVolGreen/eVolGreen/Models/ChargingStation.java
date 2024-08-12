package com.eVolGreen.eVolGreen.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    @JsonManagedReference("chargingStation-reservation")
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.LAZY)
    @JsonManagedReference("chargingStation-chargingStationStatus")
    private Set<ChargingStationStatus> chargingStationStatus = new HashSet<>();

    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.LAZY)
    @JsonManagedReference("chargingStation-transaction")
    private Set<Transaction> transactions = new HashSet<>();

    @OneToMany(mappedBy = "chargingStation", fetch = FetchType.LAZY)
    @JsonManagedReference("chargingStation-location")
    private Set<Charger> chargers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonBackReference("location-chargingStation")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference("account-chargingStation")
    private Account account;

    public ChargingStation() { }

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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addTransaction(Transaction transaction){
        transaction.setChargingStation(this);
        this.transactions.add(transaction);
    }

    public void addChargingStationStatus(ChargingStationStatus chargingStationStatus){
        chargingStationStatus.setChargingStation(this);
        this.chargingStationStatus.add(chargingStationStatus);
    }

    public void addCharger(Charger charger){
        charger.setChargingStation(this);
        this.chargers.add(charger);
    }
}
