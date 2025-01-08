package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReporteDTO {
    private Long id;
    private Long chargingStationId;
    private Long chargerId;
    private Long connectorId;
    private Long empresaId;
    private BigDecimal energia;
    private String tiempo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime inicioCarga;
    private LocalDateTime finCarga;
    private Integer costo;
    private String descripcion;
    private Long accountId;
    private Long deviceIdentifierId;

    // Constructor
    public ReporteDTO(Long id, Long chargingStationId, Long chargerId, Long connectorId, Long empresaId,
                      BigDecimal energia, String tiempo, LocalDateTime fechaCreacion,
                      LocalDateTime inicioCarga, LocalDateTime finCarga, Integer costo,
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

    public Long getChargerId() {
        return chargerId;
    }

    public void setChargerId(Long chargerId) {
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

    public BigDecimal getEnergia() {
        return energia;
    }

    public void setEnergia(BigDecimal energia) {
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

    public LocalDateTime getInicioCarga() {
        return inicioCarga;
    }

    public void setInicioCarga(LocalDateTime inicioCarga) {
        this.inicioCarga = inicioCarga;
    }

    public LocalDateTime getFinCarga() {
        return finCarga;
    }

    public void setFinCarga(LocalDateTime finCarga) {
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
