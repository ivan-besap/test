package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa un periodo en el horario de carga en OCPP 1.6.
 * Define el límite de potencia o corriente durante un intervalo de tiempo específico.
 */
@XmlRootElement
public class ChargingSchedulePeriod {

    @NotNull(message = "startPeriod es obligatorio")
    @JsonProperty("startPeriod")
    private Integer startPeriod;

    @NotNull(message = "limit es obligatorio")
    @JsonProperty("limit")
    private Double limit;

    @JsonProperty("numberPhases")
    private Integer numberPhases = 3;

    // Constructor por defecto
    public ChargingSchedulePeriod() {}

    // Constructor con los campos requeridos
    public ChargingSchedulePeriod(Integer startPeriod, Double limit) {
        this.startPeriod = startPeriod;
        this.limit = limit;
    }

    // Getters y setters

    public Integer getStartPeriod() {
        return startPeriod;
    }

    @XmlElement
    public void setStartPeriod(Integer startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Double getLimit() {
        return limit;
    }

    @XmlElement
    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Integer getNumberPhases() {
        return numberPhases;
    }

    @XmlElement
    public void setNumberPhases(Integer numberPhases) {
        this.numberPhases = numberPhases;
    }

    /**
     * Método para validar que el periodo del horario de carga sea válido.
     * Verifica que los campos requeridos estén presentes.
     *
     * @return true si el periodo es válido, false en caso contrario.
     */
    public boolean validate() {
        boolean valid = startPeriod != null;
        valid &= limit != null;
        return valid;
    }

    @Override
    public String toString() {
        return "ChargingSchedulePeriod{" +
                "startPeriod=" + startPeriod +
                ", limit=" + limit +
                ", numberPhases=" + numberPhases +
                ", isValid=" + validate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingSchedulePeriod that = (ChargingSchedulePeriod) o;
        return Objects.equals(startPeriod, that.startPeriod) &&
                Objects.equals(limit, that.limit) &&
                Objects.equals(numberPhases, that.numberPhases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPeriod, limit, numberPhases);
    }
}
