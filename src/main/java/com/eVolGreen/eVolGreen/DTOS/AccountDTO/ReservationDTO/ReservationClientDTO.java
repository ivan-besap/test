package com.eVolGreen.eVolGreen.DTOS.AccountDTO.ReservationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Reservation;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReservationClientDTO {

    private Long id;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime HoraFin;

    @NotNull(message = "La Cuenta del Cliente es obligatoria")
    private long CuentaCliente;

    @NotNull(message = "El Terminal es obligatorio")
    private long Terminal;

    @NotNull(message = "El Cargador es obligatorio")
    private long Cargador;

    @NotNull(message = "El Conector es obligatorio")
    private long Conector;

    public ReservationClientDTO(Reservation Reservacion) {

        id = Reservacion.getId();
        HoraInicio = Reservacion.getHoraInicio();
        HoraFin = Reservacion.getHoraFin();
        CuentaCliente = Reservacion.getCuentaCliente().getId();
        Terminal = Reservacion.getTerminal().getId();
        Cargador = Reservacion.getCargador().getId();
        Conector = Reservacion.getConector().getId();
    }

    public Long getId() {
        return id;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalDateTime getHoraInicio() {
        return HoraInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalDateTime getHoraFin() {
        return HoraFin;
    }

    @NotNull(message = "La Cuenta del Cliente es obligatoria")
    public long getCuentaCliente() {
        return CuentaCliente;
    }

    @NotNull(message = "El Terminal es obligatorio")
    public long getTerminal() {
        return Terminal;
    }

    @NotNull(message = "El Cargador es obligatorio")
    public long getCargador() {
        return Cargador;
    }

    @NotNull(message = "El Conector es obligatorio")
    public long getConector() {
        return Conector;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
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
