package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class ChargingUnit {

    @Id
    @GenericGenerator(name= "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private long id;

    @NotNull(message = "La Unidad de Carga es obligatoria")
    private String UnidadCarga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargador_id")
    @JsonBackReference("Cargador-UnidadCarga")
    private Charger Cargador;

    public ChargingUnit() { }

    public ChargingUnit(String UnidadCarga, Charger Cargador) {
        this.UnidadCarga = UnidadCarga;
        this.Cargador = Cargador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnidadCarga() {
        return UnidadCarga;
    }

    public void setUnidadCarga(String unidadCarga) {
        UnidadCarga = unidadCarga;
    }

    public Charger getCargador() {
        return Cargador;
    }

    public void setCargador(Charger cargador) {
        Cargador = cargador;
    }

    @Override
    public String toString() {
        return "ChargingUnit{" +
                "id=" + id +
                ", UnidadCarga='" + UnidadCarga + '\'' +
                ", Cargador=" + Cargador +
                '}';
    }
}
