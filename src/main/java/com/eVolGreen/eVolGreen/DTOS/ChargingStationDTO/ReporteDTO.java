package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ReporteDTO {
    private Long id;
    private Long chargingStationId;
    private String chargerId;
    private Long connectorId;
    private Long empresaId;
    private Integer energia;
    private String tiempo;
    private LocalDateTime fechaCreacion;
    private ZonedDateTime inicioCarga;
    private ZonedDateTime finCarga;
    private Integer costo;
    private String descripcion;
    private Long accountId;
    private Long deviceIdentifierId;

    // Constructor
    public ReporteDTO(Long id, Long chargingStationId, String chargerId, Long connectorId, Long empresaId,
                      Integer energia, String tiempo, LocalDateTime fechaCreacion,
                      ZonedDateTime inicioCarga, ZonedDateTime finCarga, Integer costo,
                      String descripcion, Long accountId, Long deviceIdentifierId) {
        this.id = id;
        this.chargingStationId = chargingStationId;
        this.chargerId = chargerId;
        this.connectorId = connectorId;
        this.empresaId = empresaId;
        this.energia = energia;
        this.tiempo = tiempo;
        this.fechaCreacion = fechaCreacion;
        this.inicioCarga = inicioCarga;
        this.finCarga = finCarga;
        this.costo = costo;
        this.descripcion = descripcion;
        this.accountId = accountId;
        this.deviceIdentifierId = deviceIdentifierId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChargingStationId() {
        return chargingStationId;
    }

    public void setChargingStationId(Long chargingStationId) {
        this.chargingStationId = chargingStationId;
    }

    public String getChargerId() {
        return chargerId;
    }

    public void setChargerId(String chargerId) {
        this.chargerId = chargerId;
    }

    public Long getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(Long connectorId) {
        this.connectorId = connectorId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getEnergia() {
        return energia;
    }

    public void setEnergia(Integer energia) {
        this.energia = energia;
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

    public ZonedDateTime getFinCarga() {
        return finCarga;
    }

    public void setFinCarga(ZonedDateTime finCarga) {
        this.finCarga = finCarga;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getDeviceIdentifierId() {
        return deviceIdentifierId;
    }

    public void setDeviceIdentifierId(Long deviceIdentifierId) {
        this.deviceIdentifierId = deviceIdentifierId;
    }
}
