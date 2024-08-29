package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;

import jakarta.persistence.*;

@Entity
public class ChargerManufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;

    // Constructores, getters y setters

    public ChargerManufacturer() {
    }

    public ChargerManufacturer(String name) {
        this.name = name;
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
}
