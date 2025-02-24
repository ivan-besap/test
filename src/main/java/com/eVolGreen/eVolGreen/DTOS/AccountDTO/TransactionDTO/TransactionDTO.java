package com.eVolGreen.eVolGreen.DTOS.AccountDTO.TransactionDTO;

import com.eVolGreen.eVolGreen.Models.Account.Transaction.Transaction;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionType;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TransactionDTO {

    private long id;


    private TransactionType TipoTransaction;


    private String Descripcion;


    private LocalDateTime FechaCreacion;


    private ZonedDateTime HoraInicio;


    private ZonedDateTime HoraFin;


    private Integer EnergiaEntregada;


    private Integer Costo;


    private long CuentaId;


    private long TerminalId;

    private long deviceIdentifierId;

    private Boolean activo;

    private Integer transactionId;

    private String chargerPointId;

    private Integer numeroConector;

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
        this.chargerPointId = Transaccion.getChargerPointId();
        this.numeroConector = Transaccion.getNumeroConector();
    }

    public long getId() {
        return id;
    }

    public  TransactionType getTipoTransaction() {
        return TipoTransaction;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public  LocalDateTime getFechaCreacion() {
        return FechaCreacion;
    }

    public  ZonedDateTime getHoraInicio() {
        return HoraInicio;
    }

    public ZonedDateTime getHoraFin() {
        return HoraFin;
    }

    public  Integer getEnergiaEntregada() {
        return EnergiaEntregada;
    }

    public  Integer getCosto() {
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

    public String getChargerPointId() {
        return chargerPointId;
    }
    public Integer getNumeroConector() {return numeroConector;}

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
                ", chargerPointId=" + chargerPointId +
                ", numeroConector=" + numeroConector +
                '}';
    }
}