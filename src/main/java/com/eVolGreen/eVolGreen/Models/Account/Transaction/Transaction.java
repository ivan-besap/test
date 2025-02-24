package com.eVolGreen.eVolGreen.Models.Account.Transaction;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class Transaction {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;


    private TransactionType tipoTransaction;


    private String descripcion;


    private LocalDateTime fechaCreacion;


    private ZonedDateTime horaInicio;


    private ZonedDateTime horaFin;


    private Integer energiaEntregada;


    private Integer costo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonBackReference("Account-Transaction")
    private Account account;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    @JsonBackReference("Terminal-Transaction")
    private ChargingStation terminal;

    private String chargerPointId;
    private Integer numeroConector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_identifier_id")
    private DeviceIdentifier deviceIdentifier;

    private Boolean activo = false;

    @JsonProperty("transactionId")
    private Integer transactionId;

    public Transaction() {
    }

    public Transaction(long id, TransactionType tipoTransaction, String descripcion, LocalDateTime fechaCreacion, ZonedDateTime horaInicio, ZonedDateTime horaFin, Integer energiaEntregada, Integer costo, Account account, ChargingStation terminal, Boolean activo, Integer transactionId, String chargerPointId, Integer numeroConector) {
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
        this.activo = activo;
        this.transactionId = transactionId;
        this.chargerPointId = chargerPointId;
        this.numeroConector = numeroConector;
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

    public ZonedDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ZonedDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getEnergiaEntregada() {
        return energiaEntregada;
    }

    public void setEnergiaEntregada(Integer energiaEntregada) {
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

    public DeviceIdentifier getDeviceIdentifier() {
        return deviceIdentifier;
    }

    public void setDeviceIdentifier(DeviceIdentifier deviceIdentifier) {
        this.deviceIdentifier = deviceIdentifier;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getChargerPointId() {
        return chargerPointId;
    }

    public void setChargerPointId(String chargerPointId) {
        this.chargerPointId = chargerPointId;
    }

    public Integer getNumeroConector() {
        return numeroConector;
    }

    public void setNumeroConector(Integer numeroConector) {
        this.numeroConector = numeroConector;
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
                ", deviceIdentifier=" + deviceIdentifier +
                ", activo=" + activo +
                ", transactionId=" + transactionId +
                ", chargerPointId=" + chargerPointId +
                ", numeroConector=" + numeroConector +
                '}';
    }
}