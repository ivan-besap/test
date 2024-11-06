package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;

import jakarta.persistence.*;

@Entity
public class ChargerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Constructores, getters y setters

    public ChargerModel() {
    }

    public ChargerModel(String name, Empresa empresa) {
        this.name = name;
        this.empresa = empresa;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}