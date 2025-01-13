package com.eVolGreen.eVolGreen.DTOS;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class CargasOcppDTO {

    private String ocppId;
    private Integer numeroConector;
    private Integer transaccionId;
    private ZonedDateTime fechaCreacion;
    private Boolean activo;

    // Constructor
    public CargasOcppDTO(String ocppId, Integer numeroConector, Integer transaccionId, ZonedDateTime fechaCreacion, Boolean activo) {
        this.ocppId = ocppId;
        this.numeroConector = numeroConector;
        this.transaccionId = transaccionId;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }

    // Getters y Setters
    public String getOcppId() {
        return ocppId;
    }

    public void setOcppId(String ocppId) {
        this.ocppId = ocppId;
    }

    public Integer getNumeroConector() {
        return numeroConector;
    }

    public void setNumeroConector(Integer numeroConector) {
        this.numeroConector = numeroConector;
    }

    public Integer getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(Integer transaccionId) {
        this.transaccionId = transaccionId;
    }

    public ZonedDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(ZonedDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}