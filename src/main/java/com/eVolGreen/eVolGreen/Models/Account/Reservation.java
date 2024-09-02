package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime horaFin;

    @NotNull(message = "La Cuenta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference("Account-Reservation")
    private Account account;

    @NotNull(message = "El Terminal es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    @JsonBackReference("Terminal-Reservation")
    private ChargingStation terminal;

    @NotNull(message = "El Cargador es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargador_id")
    @JsonBackReference("Cargador-Reservation")
    private Charger cargador;

    @NotNull(message = "El Conector es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conector_id")
    @JsonBackReference("Conector-Reservation")
    private Connector conector;

    public Reservation() { }

    public Reservation(LocalDateTime horaInicio, LocalDateTime horaFin, Account account, ChargingStation terminal, Charger cargador, Connector conector) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.account = account;
        this.terminal = terminal;
        this.cargador = cargador;
        this.conector = conector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ChargingStation getTerminal() {
        return terminal;
    }

    public void setTerminal(ChargingStation terminal) {
        this.terminal = terminal;
    }

    public Charger getCargador() {
        return cargador;
    }

    public void setCargador(Charger cargador) {
        this.cargador = cargador;
    }

    public Connector getConector() {
        return conector;
    }

    public void setConector(Connector conector) {
        this.conector = conector;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", account=" + account +
                ", terminal=" + terminal +
                ", cargador=" + cargador +
                ", conector=" + conector +
                '}';
    }
}