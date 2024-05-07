package com.eVolGreen.eVolGreen.Models;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Entity
public class Account {
    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;
    private String number;
    private LocalDate creationDate;
    private TypeAccounts typeAccounts;
    private Boolean isActive =true;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private Set<Plan> plans = new HashSet<>();
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private Set<Location> locations = new HashSet<>();
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private Set<Car> cars = new HashSet<>();
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    private Set<ChargingStation> chargingStations = new HashSet<>();
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Account() {
    }

    public Account(String number, LocalDate creationDate, TypeAccounts typeAccounts) {
        this.number = number;
        this.creationDate = creationDate;
        this.typeAccounts = typeAccounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public TypeAccounts getTypeAccounts() {
        return typeAccounts;
    }

    public void setTypeAccounts(TypeAccounts typeAccounts) {
        this.typeAccounts = typeAccounts;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
    public void addTransaction(Transaction transaction){
        transaction.setAccount(this);
        this.transactions.add(transaction);
    }

    public void addPlan(Plan plan){
        plan.setAccount(this);
        this.plans.add(plan);
    }

    public void addLocation(Location location){
        location.setAccount(this);
        this.locations.add(location);
    }

    public void addCar(Car car){
        car.setAccount(this);
        this.cars.add(car);
    }

    public Set<ChargingStation> getChargingStations() {
        return chargingStations;
    }

    public void setChargingStations(Set<ChargingStation> chargingStations) {
        this.chargingStations = chargingStations;
    }

}
