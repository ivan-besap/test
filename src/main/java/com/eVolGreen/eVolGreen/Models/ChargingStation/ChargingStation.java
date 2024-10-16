package com.eVolGreen.eVolGreen.Models.ChargingStation;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ChargingStation {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @NotNull(message = "El nombre de la terminal no puede ser nulo.")
    private String nombreTerminal;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private LocalDate fechaDeCreacion = LocalDate.now();

    @NotNull(message = "El estado de la terminal no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    private ChargingStationStatus estadoTerminal;

    @OneToMany(mappedBy = "terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Reservacion")
    private Set<Reservation> reservaciones = new HashSet<>();

    @OneToMany(mappedBy = "terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Transaction")
    private Set<Transaction> transacciones = new HashSet<>();

    @OneToMany(mappedBy = "terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Cargador")
    private Set<Charger> cargadores = new HashSet<>();

    @OneToMany(mappedBy = "terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Connector")
    private Set<Connector> conectores = new HashSet<>();

    @NotNull(message = "La ubicación de la terminal no puede ser nula.")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id")
    @JsonManagedReference("Terminal-Ubicacion")
    private Location ubicacionTerminal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    @JsonBackReference("Empresa-Terminal")
    private Empresa empresa;

    private Boolean activo = false;

    public ChargingStation() {}

    public ChargingStation(String nombreTerminal, LocalDate fechaDeCreacion, ChargingStationStatus estadoTerminal, Location ubicacionTerminal, Empresa empresa, Boolean activo) {
        this.nombreTerminal = nombreTerminal;
        this.fechaDeCreacion = fechaDeCreacion;
        this.estadoTerminal = estadoTerminal;
        this.ubicacionTerminal = ubicacionTerminal;
        this.empresa = empresa;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTerminal() {
        return nombreTerminal;
    }

    public void setNombreTerminal(String nombreTerminal) {
        this.nombreTerminal = nombreTerminal;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public ChargingStationStatus getEstadoTerminal() {
        return estadoTerminal;
    }

    public void setEstadoTerminal(ChargingStationStatus estadoTerminal) {
        this.estadoTerminal = estadoTerminal;
    }

    public Set<Reservation> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(Set<Reservation> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public Set<Transaction> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(Set<Transaction> transacciones) {
        this.transacciones = transacciones;
    }

    public Set<Charger> getCargadores() {
        return cargadores;
    }

    public void setCargadores(Set<Charger> cargadores) {
        this.cargadores = cargadores;
    }

    public Set<Connector> getConectores() {
        return conectores;
    }

    public void setConectores(Set<Connector> conectores) {
        this.conectores = conectores;
    }

    public Location getUbicacionTerminal() {
        return ubicacionTerminal;
    }

    public void setUbicacionTerminal(Location ubicacionTerminal) {
        this.ubicacionTerminal = ubicacionTerminal;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "ChargingStation{" +
                "id=" + id +
                ", nombreTerminal='" + nombreTerminal + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", estadoTerminal=" + estadoTerminal +
                ", reservaciones=" + reservaciones +
                ", transacciones=" + transacciones +
                ", cargadores=" + cargadores +
                ", conectores=" + conectores +
                ", ubicacionTerminal=" + ubicacionTerminal +
                ", empresa=" + empresa +
                ", activo=" + activo +
                '}';
    }
}
