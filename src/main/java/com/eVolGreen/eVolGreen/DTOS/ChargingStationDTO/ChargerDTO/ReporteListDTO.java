package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.ChargerModel;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.TypeConnector;

import java.time.LocalDateTime;

public class ReporteListDTO {

    private Long id;
    private LocalDateTime fechaCreacion;
    private String estacionDeCarga;
    private String cargador;
    private String conector;
    private String chargePointId;
    private String modelo;
    private String tipo;
    private String estado;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstacionDeCarga() {
        return estacionDeCarga;
    }

    public void setEstacionDeCarga(String estacionDeCarga) {
        this.estacionDeCarga = estacionDeCarga;
    }

    public String getCargador() {
        return cargador;
    }

    public void setCargador(String cargador) {
        this.cargador = cargador;
    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getChargePointId() {
        return chargePointId;
    }

    public void setChargePointId(String chargePointId) {
        this.chargePointId = chargePointId;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}