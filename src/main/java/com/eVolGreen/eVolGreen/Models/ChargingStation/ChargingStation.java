package com.eVolGreen.eVolGreen.Models.ChargingStation;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountCompany;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountEmployee;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

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
    private String NombreTerminal;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private LocalDate FechaDeCreacion = LocalDate.now();

    @NotNull(message = "El estado de la terminal no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    private ChargingStationStatus EstadoTerminal;

    @OneToMany(mappedBy = "Terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Reservacion")
    private Set<Reservation> Reservaciones = new HashSet<>();

    @OneToMany(mappedBy = "Terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Transaction")
    private Set<Transaction> Transacciones = new HashSet<>();

    @OneToMany(mappedBy = "Terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Cargador")
    private Set<Charger> Cargadores = new HashSet<>();

    @OneToMany(mappedBy = "Terminal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("Terminal-Connector")
    private Set<Connector> Conectores = new HashSet<>();

    @NotNull(message = "La ubicación de la terminal no puede ser nula.")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Ubicacion_id")
    @JsonManagedReference("Terminal-Ubicacion")
    private Location UbicacionTerminal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCompañia_id")
    @JsonBackReference("Terminal-CuentaCompañia")
    private AccountCompany CuentaCompañia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaTrabajador_id")
    @JsonBackReference("CuentaTrabajador-Terminal")
    private AccountEmployee CuentaTrabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CuentaCliente_id")
    @JsonBackReference("CuentaCliente-Terminal")
    private AccountClient CuentaCliente;

    private Boolean activo = false;

    public ChargingStation() { }

    public ChargingStation(String nombreTerminal, LocalDate fechaDeCreacion, ChargingStationStatus estadoTerminal, Location ubicacionTerminal, Boolean activo) {
        this.NombreTerminal = nombreTerminal;
        this.FechaDeCreacion = fechaDeCreacion;
        this.EstadoTerminal = estadoTerminal;
        this.UbicacionTerminal = ubicacionTerminal;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre de la terminal no puede ser nulo.") String getNombreTerminal() {
        return NombreTerminal;
    }

    public void setNombreTerminal(@NotNull(message = "El nombre de la terminal no puede ser nulo.") String nombreTerminal) {
        NombreTerminal = nombreTerminal;
    }

    public Set<Connector> getConectores() {
        return Conectores;
    }

    public void setConectores(Set<Connector> conectores) {
        Conectores = conectores;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public @NotNull(message = "La fecha de creación no puede ser nula.") LocalDate getFechaDeCreacion() {
        return FechaDeCreacion;
    }

    public void setFechaDeCreacion(@NotNull(message = "La fecha de creación no puede ser nula.") LocalDate fechaDeCreacion) {
        FechaDeCreacion = fechaDeCreacion;
    }

    public @NotNull(message = "El estado de la terminal no puede ser nulo.") ChargingStationStatus getEstadoTerminal() {
        return EstadoTerminal;
    }

    public void setEstadoTerminal(@NotNull(message = "El estado de la terminal no puede ser nulo.") ChargingStationStatus estadoTerminal) {
        EstadoTerminal = estadoTerminal;
    }

    public Set<Reservation> getReservaciones() {
        return Reservaciones;
    }

    public void setReservaciones(Set<Reservation> reservaciones) {
        Reservaciones = reservaciones;
    }

    public Set<Transaction> getTransacciones() {
        return Transacciones;
    }

    public void setTransacciones(Set<Transaction> transacciones) {
        Transacciones = transacciones;
    }

    public Set<Charger> getCargadores() {
        return Cargadores;
    }

    public void setCargadores(Set<Charger> cargadores) {
        Cargadores = cargadores;
    }

    public @NotNull(message = "La ubicación de la terminal no puede ser nula.") Location getUbicacionTerminal() {
        return UbicacionTerminal;
    }

    public void setUbicacionTerminal(@NotNull(message = "La ubicación de la terminal no puede ser nula.") Location ubicacionTerminal) {
        UbicacionTerminal = ubicacionTerminal;
    }

    public AccountCompany getCuentaCompañia() {
        return CuentaCompañia;
    }

    public void setCuentaCompañia(AccountCompany cuentaCompañia) {
        CuentaCompañia = cuentaCompañia;
    }

    public AccountEmployee getCuentaTrabajador() {
        return CuentaTrabajador;
    }

    public void setCuentaTrabajador(AccountEmployee cuentaTrabajador) {
        CuentaTrabajador = cuentaTrabajador;
    }

    public AccountClient getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(AccountClient cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    @Override
    public String toString() {
        return "ChargingStation{" +
                "id=" + id +
                ", NombreTerminal='" + NombreTerminal + '\'' +
                ", FechaDeCreacion=" + FechaDeCreacion +
                ", EstadoTerminal=" + EstadoTerminal +
                ", Reservaciones=" + Reservaciones +
                ", Transacciones=" + Transacciones +
                ", Cargadores=" + Cargadores +
                ", UbicacionTerminal=" + UbicacionTerminal +
                ", CuentaCompañia=" + CuentaCompañia +
                ", CuentaTrabajador=" + CuentaTrabajador +
                ", CuentaCliente=" + CuentaCliente +
                '}';
    }
}
