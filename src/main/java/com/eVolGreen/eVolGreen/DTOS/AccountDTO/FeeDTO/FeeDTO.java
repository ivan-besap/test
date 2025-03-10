package com.eVolGreen.eVolGreen.DTOS.AccountDTO.FeeDTO;

import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Set;

public class FeeDTO {

    private long id;

    @NotNull(message = "El nombre de la tarifa es obligatorio")
    private String nombreTarifa;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    @NotNull(message = "Los días de la semana son obligatorios")
    private Set<String> diasDeLaSemana;

    @NotNull(message = "El precio de la tarifa es obligatorio")
    private BigDecimal precioTarifa;

    private String nombreConector;
    private String nombreCargador;



    private long empresaId;

    private Boolean activo = false;

    public FeeDTO() {}

    public FeeDTO(Fee tarifa) {
        id = tarifa.getId();
        nombreTarifa = tarifa.getNombreTarifa();
        fechaInicio = tarifa.getFechaInicio().toLocalDate();
        fechaFin = tarifa.getFechaFin().toLocalDate();
        diasDeLaSemana = tarifa.getDiasDeLaSemana();
        precioTarifa = tarifa.getPrecioTarifa();
        empresaId = tarifa.getEmpresa().getId();
        activo = tarifa.getActivo();
        nombreConector = tarifa.getNombreConector();
        nombreCargador = tarifa.getNombreCargador();
    }

    public long getId() {
        return id;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public ChronoLocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Set<String> getDiasDeLaSemana() {
        return diasDeLaSemana;
    }

    public BigDecimal getPrecioTarifa() {
        return precioTarifa;
    }

    public String getNombreConector() {
        return nombreConector;
    }

    public String getNombreCargador() {
        return nombreCargador;
    }

    public long getEmpresaId() {
        return empresaId;
    }

    public Boolean getActivo() {
        return activo;
    }
}