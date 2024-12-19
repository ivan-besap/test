package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Período del Horario de Carga.
 *
 * <p>Define un período específico dentro de un horario de carga, incluyendo detalles como el inicio
 * del período, el límite de tasa de carga, el número de fases, y otras configuraciones específicas.
 */
public class ChargingSchedulePeriod {

    /** Datos personalizados opcionales asociados al período. */
    @Nullable
    private CustomData customData;

    /**
     * Inicio del período.
     *
     * <p>Especifica el inicio del período en segundos desde el inicio del horario. Además, el valor
     * de `startPeriod` define el tiempo de finalización del período anterior.
     */
    private Integer startPeriod;

    /**
     * Límite de tasa de carga durante el período.
     *
     * <p>Se expresa en la unidad de carga aplicable, como amperios (A) o vatios (W). Acepta como
     * máximo un decimal (por ejemplo, 8.1).
     */
    private Double limit;

    /**
     * Número de fases disponibles para la carga.
     *
     * <p>Si no se especifica, se asume un valor predeterminado de 3 fases.
     */
    @Nullable private Integer numberPhases;

    /**
     * Fase específica a utilizar.
     *
     * <p>Solo se aplica si `numberPhases=1` y si el EVSE soporta el cambio de fases (es decir,
     * `ACPhaseSwitchingSupported` está definido y es verdadero). Si no se especifica, la selección
     * de fase será realizada automáticamente por la estación de carga.
     */
    @Nullable private Integer phaseToUse;

    /**
     * Constructor de la clase `ChargingSchedulePeriod`.
     *
     * @param startPeriod Inicio del período en segundos desde el inicio del horario.
     * @param limit Límite de la tasa de carga durante el período en la unidad aplicable.
     */
    public ChargingSchedulePeriod(Integer startPeriod, Double limit) {
        setStartPeriod(startPeriod);
        setLimit(limit);
    }

    /**
     * Obtiene los datos personalizados asociados al período.
     *
     * @return Datos personalizados (pueden ser nulos).
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al período.
     *
     * @param customData Datos personalizados (pueden ser nulos).
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados al período.
     *
     * @param customData Datos personalizados.
     * @return El período actualizado.
     */
    public ChargingSchedulePeriod withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el inicio del período en segundos desde el inicio del horario.
     *
     * @return Inicio del período.
     */
    public Integer getStartPeriod() {
        return startPeriod;
    }

    /**
     * Establece el inicio del período en segundos desde el inicio del horario.
     *
     * @param startPeriod Inicio del período.
     */
    public void setStartPeriod(Integer startPeriod) {
        if (!isValidStartPeriod(startPeriod)) {
            throw new PropertyConstraintException(startPeriod, "startPeriod no puede ser nulo");
        }
        this.startPeriod = startPeriod;
    }

    /**
     * Valida si el inicio del período es válido.
     *
     * @param startPeriod Inicio del período.
     * @return `true` si el inicio del período es válido, `false` en caso contrario.
     */
    private boolean isValidStartPeriod(Integer startPeriod) {
        return startPeriod != null;
    }

    /**
     * Obtiene el límite de la tasa de carga durante el período.
     *
     * @return Límite de la tasa de carga.
     */
    public Double getLimit() {
        return limit;
    }

    /**
     * Establece el límite de la tasa de carga durante el período.
     *
     * @param limit Límite de la tasa de carga.
     */
    public void setLimit(Double limit) {
        if (!isValidLimit(limit)) {
            throw new PropertyConstraintException(limit, "limit no puede ser nulo");
        }
        this.limit = limit;
    }

    /**
     * Valida si el límite de la tasa de carga es válido.
     *
     * @param limit Límite de la tasa de carga.
     * @return `true` si el límite de la tasa de carga es válido, `false` en caso contrario.
     */
    private boolean isValidLimit(Double limit) {
        return limit != null;
    }

    /**
     * Obtiene el número de fases disponibles para la carga.
     *
     * @return Número de fases (puede ser nulo).
     */
    @Nullable
    public Integer getNumberPhases() {
        return numberPhases;
    }

    /**
     * Establece el número de fases disponibles para la carga.
     *
     * @param numberPhases Número de fases.
     */
    public void setNumberPhases(@Nullable Integer numberPhases) {
        this.numberPhases = numberPhases;
    }

    /**
     * Añade el número de fases disponibles para la carga.
     *
     * @param numberPhases Número de fases.
     * @return El período actualizado.
     */
    public ChargingSchedulePeriod withNumberPhases(@Nullable Integer numberPhases) {
        setNumberPhases(numberPhases);
        return this;
    }

    /**
     * Obtiene la fase específica a utilizar durante el período.
     *
     * @return Fase a utilizar (puede ser nulo).
     */
    @Nullable
    public Integer getPhaseToUse() {
        return phaseToUse;
    }

    /**
     * Establece la fase específica a utilizar durante el período.
     *
     * @param phaseToUse Fase a utilizar.
     */
    public void setPhaseToUse(@Nullable Integer phaseToUse) {
        this.phaseToUse = phaseToUse;
    }

    /**
     * Añade la fase específica a utilizar durante el período.
     *
     * @param phaseToUse Fase a utilizar.
     * @return El período actualizado.
     */
    public ChargingSchedulePeriod withPhaseToUse(@Nullable Integer phaseToUse) {
        setPhaseToUse(phaseToUse);
        return this;
    }

    /**
     * Valida que las propiedades obligatorias de la clase sean válidas.
     *
     * @return `true` si todas las propiedades son válidas, `false` en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidStartPeriod(startPeriod) && isValidLimit(limit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingSchedulePeriod that = (ChargingSchedulePeriod) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(startPeriod, that.startPeriod)
                && Objects.equals(limit, that.limit)
                && Objects.equals(numberPhases, that.numberPhases)
                && Objects.equals(phaseToUse, that.phaseToUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, startPeriod, limit, numberPhases, phaseToUse);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("startPeriod", startPeriod)
                .add("limit", limit)
                .add("numberPhases", numberPhases)
                .add("phaseToUse", phaseToUse)
                .add("isValid", validate())
                .toString();
    }
}
