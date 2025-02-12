package com.eVolGreen.eVolGreen.Models.ChargingStation;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Charger;
import com.eVolGreen.eVolGreen.Models.ChargingStation.ChargingStation;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charging_station_id")
    private ChargingStation chargingStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charger_id")
    private Charger charger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "connector_id")
    private Connector connector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private Integer energiaEntregada;

    private String tiempo;

    private LocalDateTime fechaCreacion;

    private ZonedDateTime inicioCarga;

    private ZonedDateTime finCarga;

    private Integer costo;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_identifier_id")
    private DeviceIdentifier deviceIdentifier;

    private Boolean activo;

    @JsonProperty("transactionId")
    private Integer transactionId;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChargingStation getChargingStation() {
        return chargingStation;
    }

    public void setChargingStation(ChargingStation chargingStation) {
        this.chargingStation = chargingStation;
    }

    public Charger getCharger() {
        return charger;
    }

    public void setCharger(Charger charger) {
        this.charger = charger;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getEnergia() {
        return energiaEntregada;
    }

    public void setEnergia(Integer energiaEntregada) {
        this.energiaEntregada = energiaEntregada;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ZonedDateTime getInicioCarga() {
        return inicioCarga;
    }

    public void setInicioCarga(ZonedDateTime inicioCarga) {
        this.inicioCarga = inicioCarga;
    }

    public Integer getEnergiaEntregada() {
        return energiaEntregada;
    }

    public void setEnergiaEntregada(
            Integer energiaEntregada) {
        this.energiaEntregada = energiaEntregada;
    }

    public ZonedDateTime getFinCarga() {
        return finCarga;
    }

    public void setFinCarga(ZonedDateTime finCarga) {
        this.finCarga = finCarga;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getCosto() {
        return costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
}