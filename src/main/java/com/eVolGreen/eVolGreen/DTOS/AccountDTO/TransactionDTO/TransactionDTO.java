package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionType;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TransactionDTO {

    private long id;

    @NotNull(message = "El tipo de transacción es obligatorio")
    private TransactionType TipoTransaction;

    @NotNull(message = "La descripción es obligatoria")
    private String Descripcion;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime FechaCreacion;

    @NotNull(message = "La hora de inicio es obligatoria")
    private ZonedDateTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private ZonedDateTime HoraFin;

    @NotNull(message = "La energía entregada es obligatoria")
    private Integer EnergiaEntregada;

    @NotNull(message = "El costo es obligatorio")
    private Integer Costo;

    @NotNull(message = "La cuenta es obligatoria")
    private long CuentaId;

    @NotNull(message = "El terminal es obligatorio")
    private long TerminalId;

    private long deviceIdentifierId;

    private Boolean activo;

    private Integer transactionId;

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
        this.deviceIdentifierId = Transaccion.getDeviceIdentifier().getId();
        this.activo = Transaccion.getActivo();
        this.transactionId = Transaccion.getTransactionId();
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

    public @NotNull(message = "La hora de inicio es obligatoria") ZonedDateTime getHoraInicio() {
        return HoraInicio;
    }

    public @NotNull(message = "La hora de fin es obligatoria") ZonedDateTime getHoraFin() {
        return HoraFin;
    }

    public @NotNull(message = "La energía entregada es obligatoria") Integer getEnergiaEntregada() {
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

    public long getDeviceIdentifierId() {
        return deviceIdentifierId;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Integer getTransactionId() {
        return transactionId;
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