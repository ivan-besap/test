package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionType;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;

    @NotNull(message = "El tipo de transacción es obligatorio")
    private TransactionType TipoTransaction;

    @NotNull(message = "La descripción es obligatoria")
    private String Descripcion;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime FechaCreacion;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime HoraFin;

    @NotNull(message = "La energía entregada es obligatoria")
    private BigDecimal EnergiaEntregada;

    @NotNull(message = "El costo es obligatorio")
    private Integer Costo;

    @NotNull(message = "La cuenta es obligatoria")
    private long CuentaId;

    @NotNull(message = "El terminal es obligatorio")
    private long TerminalId;

    public TransactionDTO(Transaction Transaccion) {
        this.id = Transaccion.getId();
        this.TipoTransaction = Transaccion.getTipoTransaction();
        this.Descripcion = Transaccion.getDescripcion();
        this.FechaCreacion = Transaccion.getFechaCreacion();
        this.HoraInicio = Transaccion.getHoraInicio();
        this.HoraFin = Transaccion.getHoraFin();
        this.EnergiaEntregada = Transaccion.getEnergiaEntregada();
        this.Costo = Transaccion.getCosto();
        this.CuentaId = Transaccion.getAccount().getId();
        this.TerminalId = Transaccion.getTerminal().getId();
    }

    public long getId() {
        return id;
    }

    public @NotNull(message = "El tipo de transacción es obligatorio") TransactionType getTipoTransaction() {
        return TipoTransaction;
    }

    public @NotNull(message = "La descripción es obligatoria") String getDescripcion() {
        return Descripcion;
    }

    public @NotNull(message = "La fecha de creación es obligatoria") LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public @NotNull(message = "La hora de inicio es obligatoria") LocalDateTime getHoraInicio() {
        return HoraInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") LocalDateTime getHoraFin() {
        return HoraFin;
    }

    public @NotNull(message = "La energía entregada es obligatoria") BigDecimal getEnergiaEntregada() {
        return EnergiaEntregada;
    }

    public @NotNull(message = "El costo es obligatorio") Integer getCosto() {
        return Costo;
    }

    public long getCuentaId() {
        return CuentaId;
    }

    public long getTerminalId() {
        return TerminalId;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", TipoTransaction=" + TipoTransaction +
                ", Descripcion='" + Descripcion + '\'' +
                ", FechaCreacion=" + FechaCreacion +
                ", HoraInicio=" + HoraInicio +
                ", HoraFin=" + HoraFin +
                ", EnergiaEntregada=" + EnergiaEntregada +
                ", Costo=" + Costo +
                ", CuentaId=" + CuentaId +
                ", TerminalId=" + TerminalId +
                '}';
    }
}