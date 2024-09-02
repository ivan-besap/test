package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Location {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "La Direccion es obligatoria")
    private String direccion;

    @OneToOne(mappedBy = "ubicacionTerminal")
    @JsonBackReference
    private ChargingStation terminal;

    public Location() { }

    public Location(String direccion) {
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull(message = "La Direccion es obligatoria") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotNull(message = "La Direccion es obligatoria") String direccion) {
        this.direccion = direccion;
    }

    public ChargingStation getTerminal() {
        return terminal;
    }

    public void setTerminal(ChargingStation terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}