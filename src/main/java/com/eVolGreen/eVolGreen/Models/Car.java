package com.eVolGreen.eVolGreen.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {
    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;
    @Column(nullable = false)
    private String model;
    @Column(name = "vehicle_identification_number", nullable = false, unique = true)
    private String vin;
    private String color;
    private String brand;
    @Column(name = "manufacture_year", nullable = false)
    private String manufactureYear;
    @Column(name = "capacity_full_power", precision = 10, scale = 2)
    private BigDecimal capacityFullPower;
    @OneToMany (mappedBy = "car", fetch = FetchType.EAGER)
    private Set<DeviceIdentifier> deviceIdentifiers = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;
    public Car() {
    }

    public Car(String licensePlate, String model, String vin, String color, String brand, String manufactureYear, BigDecimal capacityFullPower) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.vin = vin;
        this.color = color;
        this.brand = brand;
        this.manufactureYear = manufactureYear;
        this.capacityFullPower = capacityFullPower;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public BigDecimal getCapacityFullPower() {
        return capacityFullPower;
    }

    public void setCapacityFullPower(BigDecimal capacityFullPower) {
        this.capacityFullPower = capacityFullPower;
    }

    public Set<DeviceIdentifier> getDeviceIdentifiers() {
        return deviceIdentifiers;
    }

    public void setDeviceIdentifiers(Set<DeviceIdentifier> deviceIdentifiers) {
        this.deviceIdentifiers = deviceIdentifiers;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addDeviceIdentifier(DeviceIdentifier deviceIdentifier) {
        this.deviceIdentifiers.add(deviceIdentifier);
        deviceIdentifier.setCar(this);
    }
}
