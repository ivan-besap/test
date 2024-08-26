package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;


public class ChargingStationsDTO {

    private Long id;

    @NotNull(message = "El nombre de la terminal no puede ser nulo.")
    private String NombreTerminal;

    @NotNull(message = "La fecha de creación no puede ser nula.")
    private LocalDate FechaDeCreacion = LocalDate.now();

    @NotNull(message = "El estado de la terminal no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    private ChargingStationStatus EstadoTerminal;

    private Set<Reservation> Reservaciones;

    private Set<Transaction> Transacciones;

    private Set<Charger> Cargadores;

    @NotNull(message = "La ubicación de la terminal no puede ser nula.")
    private Location UbicacionTerminal;

    private long CuentaCompañia;

    private Boolean activo;

    public ChargingStationsDTO() {}

    public ChargingStationsDTO(ChargingStation Terminal) {

        id = Terminal.getId();
        NombreTerminal = Terminal.getNombreTerminal();
        FechaDeCreacion = Terminal.getFechaDeCreacion();
        EstadoTerminal = Terminal.getEstadoTerminal();
        Reservaciones = Terminal.getReservaciones();
        Transacciones = Terminal.getTransacciones();
        Cargadores = Terminal.getCargadores();
        UbicacionTerminal = Terminal.getUbicacionTerminal();
        CuentaCompañia = Terminal.getCuentaCompañia().getId();
        activo = Terminal.getActivo();
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "El nombre de la terminal no puede ser nulo.") String getNombreTerminal() {
        return NombreTerminal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public @NotNull(message = "La fecha de creación no puede ser nula.") LocalDate getFechaDeCreacion() {
        return FechaDeCreacion;
    }

    public @NotNull(message = "El estado de la terminal no puede ser nulo.") ChargingStationStatus getEstadoTerminal() {
        return EstadoTerminal;
    }

    public Set<Reservation> getReservaciones() {
        return Reservaciones;
    }

    public Set<Transaction> getTransacciones() {
        return Transacciones;
    }

    public Set<Charger> getCargadores() {
        return Cargadores;
    }

    public @NotNull(message = "La ubicación de la terminal no puede ser nula.") Location getUbicacionTerminal() {
        return UbicacionTerminal;
    }

    public long getCuentaCompañia() {
        return CuentaCompañia;
    }

    @Override
    public String toString() {
        return "ChargingStationsDTO{" +
                "id=" + id +
                ", NombreTerminal='" + NombreTerminal + '\'' +
                ", FechaDeCreacion=" + FechaDeCreacion +
                ", EstadoTerminal=" + EstadoTerminal +
                ", Reservaciones=" + Reservaciones +
                ", Transacciones=" + Transacciones +
                ", Cargadores=" + Cargadores +
                ", UbicacionTerminal=" + UbicacionTerminal +
                ", CuentaCompañia=" + CuentaCompañia +
                '}';
    }
}
