package com.eVolGreen.eVolGreen.Models.Account.Fee;

import com.eVolGreen.eVolGreen.Models.Account.Account;
import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fee {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
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
    @ElementCollection
    @CollectionTable(name = "Tarifa_Dias", joinColumns = @JoinColumn(name = "Tarifa_id"))
    @Column(name = "diasDeLaSemana")
    private Set<String> diasDeLaSemana = new HashSet<>();

    @NotNull(message = "El precio de la tarifa es obligatorio")
    private BigDecimal precioTarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    @JsonBackReference("Empresa-Fee")
    private Empresa empresa;

    private Boolean activo = false;

    public Fee() {}

    public Fee(String nombreTarifa, LocalDate fechaInicio, LocalDate fechaFin, LocalTime horaInicio, LocalTime horaFin, Set<String> diasDeLaSemana, BigDecimal precioTarifa, Boolean activo, Empresa empresa) {
        this.nombreTarifa = nombreTarifa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasDeLaSemana = diasDeLaSemana;
        this.precioTarifa = precioTarifa;
        this.activo = activo;
        this.empresa = empresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Set<String> getDiasDeLaSemana() {
        return diasDeLaSemana;
    }

    public void setDiasDeLaSemana(Set<String> diasDeLaSemana) {
        this.diasDeLaSemana = diasDeLaSemana;
    }

    public BigDecimal getPrecioTarifa() {
        return precioTarifa;
    }

    public void setPrecioTarifa(BigDecimal precioTarifa) {
        this.precioTarifa = precioTarifa;
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", nombreTarifa='" + nombreTarifa + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +
                ", diasDeLaSemana=" + diasDeLaSemana +
                ", precioTarifa=" + precioTarifa +
                ", activo=" + activo +
                ", empresa=" + empresa +
                '}';
    }
}