package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.PerfilCargaCargador;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Set;

public class NewFeeDTO {

    @NotNull(message = "El nombre de la tarifa es obligatorio")
    private String NombreTarifa;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private ZonedDateTime FechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private ZonedDateTime FechaFin;

    @NotNull(message = "Los dias de la semana son obligatorios")
    private Set<String> DiasDeLaSemana;

    @NotNull(message = "El precio de la tarifa es obligatorio")
    private BigDecimal PrecioTarifa;

    private Boolean activo = false;

    private Long empresaId;

    private String consumoDeEnergiaAlarma;

    @NotNull(message = "El nombre del cargador es obligatorio")
    private String nombreCargador; // Nuevo campo obligatorio

    private String nombreConector;

    private Long perfilCarga;

    // Constructor por defecto
    public NewFeeDTO() { }

    // Constructor que toma un objeto Fee
    public NewFeeDTO(Fee Tarifa) {
        this.NombreTarifa = Tarifa.getNombreTarifa();
        this.FechaInicio = Tarifa.getFechaInicio();
        this.FechaFin = Tarifa.getFechaFin();
        this.DiasDeLaSemana = Tarifa.getDiasDeLaSemana();
        this.PrecioTarifa = Tarifa.getPrecioTarifa();
        this.activo = Tarifa.getActivo();
        this.empresaId = Tarifa.getEmpresa().getId();
        this.consumoDeEnergiaAlarma = Tarifa.getConsumoDeEnergiaAlarma();
        this.nombreCargador = Tarifa.getNombreCargador();
        this.nombreConector = Tarifa.getNombreConector();
        this.perfilCarga = Tarifa.getPerfilCarga().getId();
    }

    // Getters
    public String getNombreTarifa() {
        return NombreTarifa;
    }

    public ZonedDateTime getFechaInicio() {
        return FechaInicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public ZonedDateTime getFechaFin() {
        return FechaFin;
    }

    public Set<String> getDiasDeLaSemana() {
        return DiasDeLaSemana;
    }

    public BigDecimal getPrecioTarifa() {
        return PrecioTarifa;
    }

    public String getConsumoDeEnergiaAlarma() {
        return consumoDeEnergiaAlarma;
    }

    // Setters
    public void setNombreTarifa(String nombreTarifa) {
        this.NombreTarifa = nombreTarifa;
    }

    public void setFechaInicio(ZonedDateTime fechaInicio) {
        this.FechaInicio = fechaInicio;
    }

    public void setFechaFin(ZonedDateTime fechaFin) {
        this.FechaFin = fechaFin;
    }

    public void setDiasDeLaSemana(Set<String> diasDeLaSemana) {
        this.DiasDeLaSemana = diasDeLaSemana;
    }

    public void setPrecioTarifa(BigDecimal precioTarifa) {
        this.PrecioTarifa = precioTarifa;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public void setConsumoDeEnergiaAlarma(String consumoDeEnergiaAlarma) {
        this.consumoDeEnergiaAlarma = consumoDeEnergiaAlarma;
    }

    public Long getPerfilCarga() {
        return perfilCarga;
    }

    public String getNombreCargador() {
        return nombreCargador;
    }

    public String getNombreConector() {
        return nombreConector;
    }
}
