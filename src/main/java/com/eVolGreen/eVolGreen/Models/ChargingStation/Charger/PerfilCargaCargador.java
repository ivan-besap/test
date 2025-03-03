package com.eVolGreen.eVolGreen.Models.ChargingStation.Charger;

import com.eVolGreen.eVolGreen.Models.Account.Fee.EstadoPerfil;
import com.eVolGreen.eVolGreen.Models.Account.Fee.Fee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_carga_cargador")
public class PerfilCargaCargador {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private Long id;


    private Integer connectorId; // Número del conector


    private Integer chargingProfileId; // Identificador del perfil de carga


    private Integer stackLevel; // Nivel de prioridad del perfil


    private String chargingProfilePurpose; // Propósito del perfil


    private String chargingProfileKind; // Tipo de perfil de carga

    private String recurrencyKind; // Opcional: Tipo de repetición

    private LocalDateTime validFrom; // Opcional: Fecha de inicio del perfil

    private LocalDateTime validTo; // Opcional: Fecha de finalización del perfil

    private Integer duration; // Opcional: Duración en segundos

    private LocalDateTime startSchedule; // Opcional: Fecha de inicio del cronograma


    private String chargingRateUnit; // Unidad de carga (A o W)

    private Double minChargingRate; // Opcional: Tasa mínima de carga


    private Integer startPeriod; // Inicio del período en segundos


    private Double limite; // Límite de velocidad de carga

    private Integer numberPhases; // Opcional: Número de fases de carga

    // Relación con Charger (un cargador puede tener múltiples perfiles de carga)

    private Long chargerId;

    // Campos para persistencia para Tarifa
    @Enumerated(EnumType.STRING)
    private EstadoPerfil estado; // PENDIENTE, ACTIVO, FINALIZADO

    @OneToOne(mappedBy = "perfilCarga")
    @JoinColumn(name = "tarifa_id",referencedColumnName = "id")
    @JsonBackReference
    private Fee tarifa; // Relación inversa con Tarifa

    public PerfilCargaCargador() {
    }

    // Getters y Setters
    public Long getId() { return id; }

    public Integer getConnectorId() { return connectorId; }
    public void setConnectorId(Integer connectorId) { this.connectorId = connectorId; }

    public Integer getChargingProfileId() { return chargingProfileId; }
    public void setChargingProfileId(Integer chargingProfileId) { this.chargingProfileId = chargingProfileId; }

    public Integer getStackLevel() { return stackLevel; }
    public void setStackLevel(Integer stackLevel) { this.stackLevel = stackLevel; }

    public String getChargingProfilePurpose() { return chargingProfilePurpose; }
    public void setChargingProfilePurpose(String chargingProfilePurpose) { this.chargingProfilePurpose = chargingProfilePurpose; }

    public String getChargingProfileKind() { return chargingProfileKind; }
    public void setChargingProfileKind(String chargingProfileKind) { this.chargingProfileKind = chargingProfileKind; }

    public String getRecurrencyKind() { return recurrencyKind; }
    public void setRecurrencyKind(String recurrencyKind) { this.recurrencyKind = recurrencyKind; }

    public LocalDateTime getValidFrom() { return validFrom; }
    public void setValidFrom(LocalDateTime validFrom) { this.validFrom = validFrom; }

    public LocalDateTime getValidTo() { return validTo; }
    public void setValidTo(LocalDateTime validTo) { this.validTo = validTo; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public LocalDateTime getStartSchedule() { return startSchedule; }
    public void setStartSchedule(LocalDateTime startSchedule) { this.startSchedule = startSchedule; }

    public String getChargingRateUnit() { return chargingRateUnit; }
    public void setChargingRateUnit(String chargingRateUnit) { this.chargingRateUnit = chargingRateUnit; }

    public Double getMinChargingRate() { return minChargingRate; }
    public void setMinChargingRate(Double minChargingRate) { this.minChargingRate = minChargingRate; }

    public Integer getStartPeriod() { return startPeriod; }
    public void setStartPeriod(Integer startPeriod) { this.startPeriod = startPeriod; }

    public Double getLimite() { return limite; }
    public void setLimite(Double limit) { this.limite = limit; }

    public Integer getNumberPhases() { return numberPhases; }
    public void setNumberPhases(Integer numberPhases) { this.numberPhases = numberPhases; }

    public Long getChargerId() { return chargerId; }
    public void setChargerId(Long chargerId) { this.chargerId = chargerId; } {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoPerfil getEstado() {
        return estado;
    }

    public void setEstado(EstadoPerfil estado) {
        this.estado = estado;
    }

    public Fee getTarifa() {
        return tarifa;
    }

    public void setTarifa(Fee tarifa) {
        this.tarifa = tarifa;
    }
}
