package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representa el horario de carga que define los límites de potencia o corriente en función del tiempo en OCPP 1.6.
 * Este horario se utiliza como parte de un perfil de carga para controlar el proceso de carga del vehículo eléctrico.
 */
@XmlRootElement
public class ChargingSchedule {

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("startSchedule")
    private ZonedDateTime startSchedule;

    @NotNull(message = "chargingRateUnit es obligatorio")
    @JsonProperty("chargingRateUnit")
    private ChargingRateUnitType chargingRateUnit;

    @NotNull(message = "chargingSchedulePeriod es obligatorio")
    @JsonProperty("chargingSchedulePeriod")
    private ChargingSchedulePeriod[] chargingSchedulePeriod;

    @JsonProperty("minChargingRate")
    private Double minChargingRate;

    /**
     * Constructor por defecto.
     */
    public ChargingSchedule() {}

    /**
     * Constructor con los campos obligatorios.
     *
     * @param chargingRateUnit Unidad de medida del límite de potencia (Amperios o Vatios).
     * @param chargingSchedulePeriod Periodos del horario de carga.
     */
    public ChargingSchedule(ChargingRateUnitType chargingRateUnit, ChargingSchedulePeriod[] chargingSchedulePeriod) {
        this.chargingRateUnit = chargingRateUnit;
        this.chargingSchedulePeriod = chargingSchedulePeriod;
    }

    // Getters y setters

    public Integer getDuration() {
        return duration;
    }

    @XmlElement
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ZonedDateTime getStartSchedule() {
        return startSchedule;
    }

    @XmlElement
    public void setStartSchedule(ZonedDateTime startSchedule) {
        this.startSchedule = startSchedule;
    }

    public ChargingRateUnitType getChargingRateUnit() {
        return chargingRateUnit;
    }

    @XmlElement
    public void setChargingRateUnit(ChargingRateUnitType chargingRateUnit) {
        this.chargingRateUnit = chargingRateUnit;
    }

    public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
        return chargingSchedulePeriod;
    }

    @XmlElement
    public void setChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
        this.chargingSchedulePeriod = chargingSchedulePeriod;
    }

    public Double getMinChargingRate() {
        return minChargingRate;
    }

    @XmlElement
    public void setMinChargingRate(Double minChargingRate) {
        this.minChargingRate = minChargingRate;
    }

    /**
     * Método para validar que el horario de carga sea válido.
     * Verifica que los campos requeridos estén presentes y correctos.
     *
     * @return true si el horario es válido, false en caso contrario.
     */
    public boolean validate() {
        boolean valid = chargingRateUnit != null;
        valid &= chargingSchedulePeriod != null;
        if (valid) {
            for (ChargingSchedulePeriod period : chargingSchedulePeriod) {
                valid &= period.validate();
            }
        }
        return valid;
    }

    @Override
    public String toString() {
        return "ChargingSchedule{" +
                "duration=" + duration +
                ", startSchedule=" + startSchedule +
                ", chargingRateUnit=" + chargingRateUnit +
                ", chargingSchedulePeriod=" + Arrays.toString(chargingSchedulePeriod) +
                ", minChargingRate=" + minChargingRate +
                ", isValid=" + validate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingSchedule that = (ChargingSchedule) o;
        return Objects.equals(duration, that.duration) &&
                Objects.equals(startSchedule, that.startSchedule) &&
                chargingRateUnit == that.chargingRateUnit &&
                Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod) &&
                Objects.equals(minChargingRate, that.minChargingRate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(duration, startSchedule, chargingRateUnit, minChargingRate);
        result = 31 * result + Arrays.hashCode(chargingSchedulePeriod);
        return result;
    }
}
