package com.eVolGreen.eVolGreen.Models.Ocpp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class CargasOcpp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ocppId;
    private Integer numeroConector;
    private Integer transaccionId;
    private LocalDateTime fechaCreacion;
    private Boolean activo;
    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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