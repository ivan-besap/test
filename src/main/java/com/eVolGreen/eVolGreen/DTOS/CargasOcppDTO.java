package com.eVolGreen.eVolGreen.DTOS;

import java.time.LocalDateTime;

public class CargasOcppDTO {

    private String ocppId;
    private Integer numeroConector;
    private Integer transaccionId;
    private LocalDateTime fechaCreacion;
    private Boolean activo;

    // Constructor
    public CargasOcppDTO(String ocppId, Integer numeroConector, Integer transaccionId, LocalDateTime fechaCreacion, Boolean activo) {
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}