package com.eVolGreen.eVolGreen.Models.Account.Fee;

import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.Connector;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class FeeConnector {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tarifa_id", nullable = false)
    @JsonBackReference("Tarifa-TarifaConector")
    private Fee Tarifa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Conector_id", nullable = false)
    @JsonBackReference("Conector-FeeConnector")
    private Connector Conector;

    @NotNull(message = "La fecha de activacion es obligatoria")
    @Column(name = "Fecha_Activacion")
    private LocalDateTime fechaActivacion = LocalDateTime.now();

    private Boolean activo = false;

    public FeeConnector() {}

    public FeeConnector(Fee Tarifa, Connector Conector, LocalDateTime fechaActivacion,Boolean activo) {
        this.Tarifa = Tarifa;
        this.Conector = Conector;
        this.fechaActivacion = fechaActivacion;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fee getTarifa() {
        return Tarifa;
    }

    public void setTarifa(Fee tarifa) {
        Tarifa = tarifa;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Connector getConector() {
        return Conector;
    }

    public void setConector(Connector conector) {
        Conector = conector;
    }

    public @NotNull(message = "La fecha de activacion es obligatoria") LocalDateTime getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(@NotNull(message = "La fecha de activacion es obligatoria") LocalDateTime fechaActivacion) {
        fechaActivacion = fechaActivacion;
    }

    @Override
    public String toString() {
        return "FeeConnector{" +
                "id=" + id +
                ", Tarifa=" + Tarifa +
                ", Conector=" + Conector +
                ", FechaActivacion=" + fechaActivacion +
                '}';
    }
}
