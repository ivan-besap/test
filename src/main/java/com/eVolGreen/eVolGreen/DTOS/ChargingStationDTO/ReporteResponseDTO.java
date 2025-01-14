package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO;

import java.time.LocalDateTime;

public class ReporteResponseDTO {
    private String estacionDeCarga;
    private String idCargador;
    private String conector;
    private LocalDateTime inicioCarga;
    private LocalDateTime finCarga;
    private String usuario;
    private String energia;
    private String tiempo;

    // Constructor vacío
    public ReporteResponseDTO() {
    }

    // Constructor con parámetros
    public ReporteResponseDTO(String estacionDeCarga, String idCargador, String conector,
                              LocalDateTime inicioCarga, LocalDateTime finCarga, String usuario,
                              String energia, String tiempo) {
        this.estacionDeCarga = estacionDeCarga;
        this.idCargador = idCargador;
        this.conector = conector;
        this.inicioCarga = inicioCarga;
        this.finCarga = finCarga;
        this.usuario = usuario;
        this.energia = energia;
        this.tiempo = tiempo;
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
}
