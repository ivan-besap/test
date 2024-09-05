package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class NewFeeDTO {

    @NotNull(message = "El nombre de la tarifa es obligatorio")
    private String NombreTarifa;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate FechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate FechaFin;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime HoraInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime HoraFin;

    @NotNull(message = "Los dias de la semana son obligatorios")
    private Set<String> DiasDeLaSemana;

    @NotNull(message = "El precio de la tarifa es obligatorio")
    private BigDecimal PrecioTarifa;

    private Boolean activo = false;

    private Long empresaId;

    // Constructor por defecto
    public NewFeeDTO() { }

    // Constructor que toma un objeto Fee
    public NewFeeDTO(Fee Tarifa) {
        this.NombreTarifa = Tarifa.getNombreTarifa();
        this.FechaInicio = Tarifa.getFechaInicio();
        this.FechaFin = Tarifa.getFechaFin();
        this.HoraInicio = Tarifa.getHoraInicio();
        this.HoraFin = Tarifa.getHoraFin();
        this.DiasDeLaSemana = Tarifa.getDiasDeLaSemana();
        this.PrecioTarifa = Tarifa.getPrecioTarifa();
        this.activo = Tarifa.getActivo();
        this.empresaId = Tarifa.getEmpresa().getId();
    }

    // Getters
    public String getNombreTarifa() {
        return NombreTarifa;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public LocalTime getHoraInicio() {
        return HoraInicio;
    }

    public LocalTime getHoraFin() {
        return HoraFin;
    }

    public Set<String> getDiasDeLaSemana() {
        return DiasDeLaSemana;
    }

    public BigDecimal getPrecioTarifa() {
        return PrecioTarifa;
    }

    // Setters
    public void setNombreTarifa(String nombreTarifa) {
        this.NombreTarifa = nombreTarifa;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.FechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.FechaFin = fechaFin;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.HoraInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.HoraFin = horaFin;
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
}
