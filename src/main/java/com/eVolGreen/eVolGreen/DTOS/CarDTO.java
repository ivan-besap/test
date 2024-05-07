package com.eVolGreen.eVolGreen.DTOS;

import com.eVolGreen.eVolGreen.Models.Car;
import com.eVolGreen.eVolGreen.Models.DeviceIdentifier;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CarDTO {
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
    private Set<DeviceIdentifierDTO> deviceIdentifiers = new HashSet<>();


    public CarDTO(Car car) {
        id = car.getId();
        licensePlate = car.getLicensePlate();
        model = car.getModel();
        vin = car.getVin();
        color = car.getColor();
        brand = car.getBrand();
        manufactureYear = car.getManufactureYear();
        capacityFullPower = car.getCapacityFullPower();
        deviceIdentifiers = car.getDeviceIdentifiers().stream().map(DeviceIdentifier -> new DeviceIdentifierDTO(DeviceIdentifier)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public BigDecimal getCapacityFullPower() {
        return capacityFullPower;
    }

    public Set<DeviceIdentifierDTO> getDeviceIdentifiers() {
        return deviceIdentifiers;
    }
}
