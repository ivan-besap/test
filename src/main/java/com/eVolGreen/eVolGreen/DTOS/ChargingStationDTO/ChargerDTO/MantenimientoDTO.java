package com.eVolGreen.eVolGreen.DTOS.ChargingStationDTO.ChargerDTO;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Charger.Mantenimiento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class MantenimientoDTO {

    private Long id;
    private String descripcion;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    private Set<String> diasDeLaSemana;
    private Long cargadorId;

    private Long empresaId;

    public MantenimientoDTO() { }

    public MantenimientoDTO(Mantenimiento mantenimiento) {
        this.id = mantenimiento.getId();
        this.descripcion = mantenimiento.getDescripcion();
        this.fechaInicial = mantenimiento.getFechaInicial();
        this.fechaFinal = mantenimiento.getFechaFinal();
        this.horarioInicio = mantenimiento.getHorarioInicio();
        this.horarioFin = mantenimiento.getHorarioFin();
        this.diasDeLaSemana = mantenimiento.getDiasDeLaSemana();
        this.cargadorId = mantenimiento.getCargador() != null ? mantenimiento.getCargador().getId() : null;
        this.empresaId = mantenimiento.getEmpresa().getId();
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    public Set<String> getDiasDeLaSemana() {
        return diasDeLaSemana;
    }

    public void setDiasDeLaSemana(Set<String> diasDeLaSemana) {
        this.diasDeLaSemana = diasDeLaSemana;
    }

    public Long getCargadorId() {
        return cargadorId;
    }

    public void setCargadorId(Long cargadorId) {
        this.cargadorId = cargadorId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }
}
