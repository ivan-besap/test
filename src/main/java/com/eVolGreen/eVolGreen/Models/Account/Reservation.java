package com.eVolGreen.eVolGreen.Models.Account;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.Account.TypeOfAccount.AccountClient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    private Long id;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime HoraFin;

    @NotNull(message = "La Cuenta del Cliente es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cuenta_id", nullable = true)
    @JsonBackReference("CuentaCliente-Reservacion")
    private AccountClient CuentaCliente;

    @NotNull(message = "El Terminal es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Terminal_id")
    @JsonBackReference("Terminal-Reservacion")
    private ChargingStation Terminal;

    @NotNull(message = "El Cargador es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargador_id")
    @JsonBackReference("Cargador-Reservacion")
    private Charger Cargador;

    @NotNull(message = "El Conector es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Conector_id")
    @JsonBackReference("Conector-Reservacion")
    private Connector Conector;

    public Reservation() { }

    public Reservation(LocalDateTime horaInicio, LocalDateTime horaFin, AccountClient cuentaCliente, ChargingStation terminal, Charger cargador, Connector conector) {
        HoraInicio = horaInicio;
        HoraFin = horaFin;
        CuentaCliente = cuentaCliente;
        Terminal = terminal;
        Cargador = cargador;
        Conector = conector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalDateTime getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(@NotNull(message = "La hora de inicio es obligatoria") LocalDateTime horaInicio) {
        HoraInicio = horaInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalDateTime getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(@NotNull(message = "La hora de fin es obligatoria") LocalDateTime horaFin) {
        HoraFin = horaFin;
    }

    public @NotNull(message = "La Cuenta del Cliente es obligatoria") AccountClient getCuentaCliente() {
        return CuentaCliente;
    }

    public void setCuentaCliente(@NotNull(message = "La Cuenta del Cliente es obligatoria") AccountClient cuentaCliente) {
        CuentaCliente = cuentaCliente;
    }

    public @NotNull(message = "El Terminal es obligatorio") ChargingStation getTerminal() {
        return Terminal;
    }

    public void setTerminal(@NotNull(message = "El Terminal es obligatorio") ChargingStation terminal) {
        Terminal = terminal;
    }

    public @NotNull(message = "El Cargador es obligatorio") Charger getCargador() {
        return Cargador;
    }

    public void setCargador(@NotNull(message = "El Cargador es obligatorio") Charger cargador) {
        Cargador = cargador;
    }

    public @NotNull(message = "El Conector es obligatorio") Connector getConector() {
        return Conector;
    }

    public void setConector(@NotNull(message = "El Conector es obligatorio") Connector conector) {
        Conector = conector;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                ", CuentaCliente=" + CuentaCliente +
                ", Terminal=" + Terminal +
                ", Cargador=" + Cargador +
                ", Conector=" + Conector +
                '}';
    }
}
