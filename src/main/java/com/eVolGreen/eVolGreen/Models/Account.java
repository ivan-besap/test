package com.eVolGreen.eVolGreen.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Boolean isActive = false;
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference("account-transaction")
    private Set<Transaction> transactions = new HashSet<>();
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference("account-plan")
    private Set<Plan> plans = new HashSet<>();
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference("account-location")
    private Set<Location> locations = new HashSet<>();
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    @JsonManagedReference("account-car")
    private Set<Car> cars = new HashSet<>();
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
    @JsonManagedReference("account-chargingStation")
    private Set<ChargingStation> chargingStations = new HashSet<>();
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonManagedReference("account-reservation")
    private Set<Reservation> reservations = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference("client-account")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference("company-account")
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
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
