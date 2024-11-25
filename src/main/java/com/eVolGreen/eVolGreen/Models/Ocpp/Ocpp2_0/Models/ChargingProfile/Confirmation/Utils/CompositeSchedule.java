package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingSchedulePeriod;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingRateUnitEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representa un horario compuesto para la programación de carga.
 *
 * <p>El horario compuesto incluye detalles como los períodos de carga, la duración,
 * la hora de inicio y la unidad de medida del límite de carga.
 */
public class CompositeSchedule {

    @Nullable
    private CustomData customData;

    private ChargingSchedulePeriod[] chargingSchedulePeriod;

    private Integer evseId;

    private Integer duration;

    private ZonedDateTime scheduleStart;

    private ChargingRateUnitEnum chargingRateUnit;

    /**
     * Constructor para la clase CompositeSchedule.
     *
     * @param chargingSchedulePeriod Lista de períodos de carga en el horario.
     * @param evseId Identificador de EVSE al que se refiere el horario.
     * @param duration Duración del horario en segundos.
     * @param scheduleStart Fecha y hora en que el horario entra en vigencia.
     * @param chargingRateUnit Unidad de medida del límite de carga (W o A).
     */
    public CompositeSchedule(
            ChargingSchedulePeriod[] chargingSchedulePeriod,
            Integer evseId,
            Integer duration,
            ZonedDateTime scheduleStart,
            ChargingRateUnitEnum chargingRateUnit) {
        setChargingSchedulePeriod(chargingSchedulePeriod);
        setEvseId(evseId);
        setDuration(duration);
        setScheduleStart(scheduleStart);
        setChargingRateUnit(chargingRateUnit);
    }

    /**
     * Obtiene los datos personalizados asociados con este horario.
     *
     * @return Objeto CustomData, si existe.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados con este horario.
     *
     * @param customData Objeto CustomData a asociar.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido.");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Obtiene los períodos de carga incluidos en este horario.
     *
     * @return Lista de períodos de carga.
     */
    public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
        return chargingSchedulePeriod;
    }

    /**
     * Establece los períodos de carga incluidos en este horario.
     *
     * @param chargingSchedulePeriod Lista de períodos de carga.
     */
    public void setChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
        if (!isValidChargingSchedulePeriod(chargingSchedulePeriod)) {
            throw new PropertyConstraintException(chargingSchedulePeriod, "chargingSchedulePeriod no es válido.");
        }
        this.chargingSchedulePeriod = chargingSchedulePeriod;
    }

    private boolean isValidChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
        return chargingSchedulePeriod != null
                && chargingSchedulePeriod.length >= 1
                && Arrays.stream(chargingSchedulePeriod).allMatch(ChargingSchedulePeriod::validate);
    }

    /**
     * Obtiene el identificador del EVSE al que se refiere este horario.
     *
     * @return ID del EVSE.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE al que se refiere este horario.
     *
     * @param evseId ID del EVSE.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "evseId no es válido.");
        }
        this.evseId = evseId;
    }

    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    /**
     * Obtiene la duración del horario en segundos.
     *
     * @return Duración en segundos.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Establece la duración del horario en segundos.
     *
     * @param duration Duración en segundos.
     */
    public void setDuration(Integer duration) {
        if (!isValidDuration(duration)) {
            throw new PropertyConstraintException(duration, "duration no es válida.");
        }
        this.duration = duration;
    }

    private boolean isValidDuration(Integer duration) {
        return duration != null;
    }

    /**
     * Obtiene la fecha y hora en que el horario entra en vigencia.
     *
     * @return Fecha y hora de inicio.
     */
    public ZonedDateTime getScheduleStart() {
        return scheduleStart;
    }

    /**
     * Establece la fecha y hora en que el horario entra en vigencia.
     *
     * @param scheduleStart Fecha y hora de inicio.
     */
    public void setScheduleStart(ZonedDateTime scheduleStart) {
        if (!isValidScheduleStart(scheduleStart)) {
            throw new PropertyConstraintException(scheduleStart, "scheduleStart no es válida.");
        }
        this.scheduleStart = scheduleStart;
    }

    private boolean isValidScheduleStart(ZonedDateTime scheduleStart) {
        return scheduleStart != null;
    }

    /**
     * Obtiene la unidad de medida utilizada para el límite de carga.
     *
     * @return Unidad de medida (W o A).
     */
    public ChargingRateUnitEnum getChargingRateUnit() {
        return chargingRateUnit;
    }

    /**
     * Establece la unidad de medida utilizada para el límite de carga.
     *
     * @param chargingRateUnit Unidad de medida (W o A).
     */
    public void setChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
        if (!isValidChargingRateUnit(chargingRateUnit)) {
            throw new PropertyConstraintException(chargingRateUnit, "chargingRateUnit no es válido.");
        }
        this.chargingRateUnit = chargingRateUnit;
    }

    private boolean isValidChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
        return chargingRateUnit != null;
    }

    /**
     * Valida que todos los campos del horario sean correctos.
     *
     * @return {@code true} si todos los campos son válidos, de lo contrario {@code false}.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidChargingSchedulePeriod(chargingSchedulePeriod)
                && isValidEvseId(evseId)
                && isValidDuration(duration)
                && isValidScheduleStart(scheduleStart)
                && isValidChargingRateUnit(chargingRateUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeSchedule that = (CompositeSchedule) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(duration, that.duration)
                && Objects.equals(scheduleStart, that.scheduleStart)
                && Objects.equals(chargingRateUnit, that.chargingRateUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                Arrays.hashCode(chargingSchedulePeriod),
                evseId,
                duration,
                scheduleStart,
                chargingRateUnit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingSchedulePeriod", chargingSchedulePeriod)
                .add("evseId", evseId)
                .add("duration", duration)
                .add("scheduleStart", scheduleStart)
                .add("chargingRateUnit", chargingRateUnit)
                .add("isValid", validate())
                .toString();
    }
}
