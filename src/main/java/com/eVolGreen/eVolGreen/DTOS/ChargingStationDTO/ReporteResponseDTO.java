package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ReporteResponseDTO {
    private String estacionDeCarga;
    private String idCargador;
    private String conector;
    private ZonedDateTime inicioCarga;
    private ZonedDateTime finCarga;
    private String usuario;
    private String energia;
    private String tiempo;
    private Integer costo;

    // Constructor vacío
    public ReporteResponseDTO() {
    }

    // Constructor con parámetros
    public ReporteResponseDTO(String estacionDeCarga, String idCargador, String conector,
                              ZonedDateTime inicioCarga, ZonedDateTime finCarga, String usuario,
                              String energia, String tiempo, Integer costo) {
        this.estacionDeCarga = estacionDeCarga;
        this.idCargador = idCargador;
        this.conector = conector;
        this.inicioCarga = inicioCarga;
        this.finCarga = finCarga;
        this.usuario = usuario;
        this.energia = energia;
        this.tiempo = tiempo;
        this.costo = costo;
    }

    // Getters y Setters

    public String getEstacionDeCarga() {
        return estacionDeCarga;
    }

    public void setEstacionDeCarga(String estacionDeCarga) {
        this.estacionDeCarga = estacionDeCarga;
    }

    public String getIdCargador() {
        return idCargador;
    }

    public void setIdCargador(String idCargador) {
        this.idCargador = idCargador;
    }

    public String getConector() {
        return conector;
    }

    public void setConector(String conector) {
        this.conector = conector;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEnergia() {
        return energia;
    }

    public void setEnergia(String energia) {
        this.energia = energia;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
}
