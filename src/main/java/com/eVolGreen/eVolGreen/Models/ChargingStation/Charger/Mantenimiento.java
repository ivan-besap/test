package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;

import com.eVolGreen.eVolGreen.Models.Account.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha inicial es obligatoria")
    private LocalDate fechaInicial;

    @NotNull(message = "La fecha final es obligatoria")
    private LocalDate fechaFinal;

    @NotNull(message = "El horario de inicio es obligatorio")
    private LocalTime horarioInicio;

    @NotNull(message = "El horario de fin es obligatorio")
    private LocalTime horarioFin;

    @ElementCollection
    @CollectionTable(name = "Mantenimiento_Dias", joinColumns = @JoinColumn(name = "mantenimiento_id"))
    @Column(name = "diasDeLaSemana")
    private Set<String> diasDeLaSemana = new HashSet<>();

    // Relación con Charger (Cargador), opcional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargador_id")
    @JsonBackReference("Charger-Mantenimiento")
    private Charger cargador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Mantenimiento() { }

    // Constructores, getters y setters

    public Mantenimiento(String descripcion, LocalDate fechaInicial, LocalDate fechaFinal, LocalTime horarioInicio, LocalTime horarioFin, Set<String> diasDeLaSemana, Charger cargador, Empresa empresa) {
        this.descripcion = descripcion;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.horarioInicio = horarioInicio;
        this.horarioFin = horarioFin;
        this.diasDeLaSemana = diasDeLaSemana;
        this.cargador = cargador;
        this.empresa = empresa;
    }

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

    public Charger getCargador() {
        return cargador;
    }

    public void setCargador(Charger cargador) {
        this.cargador = cargador;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
