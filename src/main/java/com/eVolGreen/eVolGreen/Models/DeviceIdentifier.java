package com.eVolGreen.eVolGreen.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class DeviceIdentifier {
    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;
    private String identifierName;
    private long identifierNumber;
    private String identifierType;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    public DeviceIdentifier() {
    }

    public DeviceIdentifier(String identifierName, long identifierNumber, String identifierType, Car car) {
        this.identifierName = identifierName;
        this.identifierNumber = identifierNumber;
        this.identifierType = identifierType;
        this.car = car;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    public long getIdentifierNumber() {
        return identifierNumber;
    }

    public void setIdentifierNumber(long identifierNumber) {
        this.identifierNumber = identifierNumber;
    }

    public String getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}