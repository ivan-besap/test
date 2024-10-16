package com.eVolGreen.eVolGreen.Models.Account.Transaction;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "El tipo de transacción es obligatorio")
    private TransactionType tipoTransaction;

    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalDateTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalDateTime horaFin;

    @NotNull(message = "La energía entregada es obligatoria")
    private BigDecimal energiaEntregada;

    @NotNull(message = "El costo es obligatorio")
    private Integer costo;

    @NotNull(message = "La cuenta es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference("Account-Transaction")
    private Account account;

    @NotNull(message = "El terminal es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    @JsonBackReference("Terminal-Transaction")
    private ChargingStation terminal;

    public Transaction() {
    }

    public Transaction(long id, TransactionType tipoTransaction, String descripcion, LocalDateTime fechaCreacion, LocalDateTime horaInicio, LocalDateTime horaFin, BigDecimal energiaEntregada, Integer costo, Account account, ChargingStation terminal) {
        this.id = id;
        this.tipoTransaction = tipoTransaction;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.energiaEntregada = energiaEntregada;
        this.costo = costo;
        this.account = account;
        this.terminal = terminal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getTipoTransaction() {
        return tipoTransaction;
    }

    public void setTipoTransaction(TransactionType tipoTransaction) {
        this.tipoTransaction = tipoTransaction;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public BigDecimal getEnergiaEntregada() {
        return energiaEntregada;
    }

    public void setEnergiaEntregada(BigDecimal energiaEntregada) {
        this.energiaEntregada = energiaEntregada;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", tipoTransaction=" + tipoTransaction +
                ", descripcion='" + descripcion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", energiaEntregada=" + energiaEntregada +
                ", costo=" + costo +
                ", account=" + account +
                ", terminal=" + terminal +
                '}';
    }
}