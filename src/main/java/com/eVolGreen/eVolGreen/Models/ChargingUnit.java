package com.eVolGreen.eVolGreen.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ChargingUnit {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charger_id")
    private Charger charger;

    public ChargingUnit() {
    }

    public ChargingUnit(String unit, Charger charger) {
        this.unit = unit;
        this.charger = charger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }
}
