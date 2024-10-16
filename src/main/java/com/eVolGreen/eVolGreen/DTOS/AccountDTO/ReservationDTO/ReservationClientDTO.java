package com.eVolGreen.eVolGreen.DTOS.AccountDTO.ReservationDTO;

import com.eVolGreen.eVolGreen.Models.Account.Reservation;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReservationClientDTO {

    private Long id;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime HoraFin;

    @NotNull(message = "La cuenta es obligatoria")
    private long CuentaId;

    @NotNull(message = "El Terminal es obligatorio")
    private long TerminalId;

    @NotNull(message = "El Cargador es obligatorio")
    private long CargadorId;

    @NotNull(message = "El Conector es obligatorio")
    private long ConectorId;

    public ReservationClientDTO(Reservation Reservacion) {

        id = Reservacion.getId();
        HoraInicio = Reservacion.getHoraInicio();
        HoraFin = Reservacion.getHoraFin();
        CuentaId = Reservacion.getAccount().getId();
        TerminalId = Reservacion.getTerminal().getId();
        CargadorId = Reservacion.getCargador().getId();
        ConectorId = Reservacion.getConector().getId();
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

    @NotNull(message = "La cuenta es obligatoria")
    public long getCuentaId() {
        return CuentaId;
    }

    @NotNull(message = "El Terminal es obligatorio")
    public long getTerminalId() {
        return TerminalId;
    }

    @NotNull(message = "El Cargador es obligatorio")
    public long getCargadorId() {
        return CargadorId;
    }

    @NotNull(message = "El Conector es obligatorio")
    public long getConectorId() {
        return ConectorId;
    }

    @Override
    public String toString() {
        return "ReservationClientDTO{" +
                "id=" + id +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                ", CuentaId=" + CuentaId +
                ", TerminalId=" + TerminalId +
                ", CargadorId=" + CargadorId +
                ", ConectorId=" + ConectorId +
                '}';
    }
}