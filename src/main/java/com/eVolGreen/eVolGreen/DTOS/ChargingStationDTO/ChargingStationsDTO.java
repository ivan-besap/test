package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import com.eVolGreen.eVolGreen.DTOS.AccountDTO.LocationDTO.LocationChargingStationDTO;
import com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO.ChargerDTO;
import com.eVolGreen.eVolGreen.Models.Account.Location;
import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    private List<ChargerDTO> Cargadores;

    @NotNull(message = "La ubicación de la terminal no puede ser nula.")
    private LocationChargingStationDTO UbicacionTerminal;

    private long EmpresaId;

    private Boolean activo;

    public ChargingStationsDTO() {}

    public ChargingStationsDTO(ChargingStation Terminal) {
        this.id = Terminal.getId();
        this.NombreTerminal = Terminal.getNombreTerminal();
        this.FechaDeCreacion = Terminal.getFechaDeCreacion();
        this.EstadoTerminal = Terminal.getEstadoTerminal();
        this.Reservaciones = Terminal.getReservaciones();
        this.Transacciones = Terminal.getTransacciones();
        this.Cargadores = Terminal.getCargadores().stream()
                .map(ChargerDTO::new)
                .collect(Collectors.toList());
        this.UbicacionTerminal = new LocationChargingStationDTO(Terminal.getUbicacionTerminal());
        this.EmpresaId = Terminal.getEmpresa().getId();
        this.activo = Terminal.getActivo();
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

    public List<ChargerDTO> getCargadores() { // Usar el DTO existente
        return Cargadores;
    }

    public LocationChargingStationDTO getUbicacionTerminal() {
        return UbicacionTerminal;
    }

    public long getEmpresaId() {
        return EmpresaId;
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
                ", Empresa=" + EmpresaId +
                ", activo=" + activo +
                '}';
    }
}