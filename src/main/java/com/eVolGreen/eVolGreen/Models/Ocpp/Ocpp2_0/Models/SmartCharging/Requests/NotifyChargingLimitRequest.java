package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils.ChargingLimit;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * NotifyChargingLimitRequest
 *
 * <p>Solicitud para notificar los límites de carga en una estación de carga. Este mensaje se utiliza para informar los límites y programaciones de carga aplicables a un EVSE específico.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class NotifyChargingLimitRequest extends RequestWithId {
    /** Datos personalizados opcionales */
    @Nullable
    private CustomData customData;

    /**
     * Programación de carga
     *
     * <p>Estructura que define una lista de periodos de carga, utilizada en:
     * GetCompositeSchedule.conf y ChargingProfile.
     */
    @Nullable private ChargingSchedule[] chargingSchedule;

    /**
     * El ID del EVSE al que aplica esta notificación de programación de carga. Este valor debe ser mayor que 0.
     */
    @Nullable private Integer evseId;

    /** Límite de carga */
    private ChargingLimit chargingLimit;

    /**
     * Constructor de la clase NotifyChargingLimitRequest
     *
     * @param chargingLimit Límite de carga
     */
    public NotifyChargingLimitRequest(ChargingLimit chargingLimit) {
        setChargingLimit(chargingLimit);
    }

    /**
     * Obtiene los datos personalizados
     *
     * @return Datos personalizados
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados
     *
     * @param customData Datos personalizados
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos
     *
     * @param customData Datos personalizados
     * @return {@code true} si son válidos, {@code false} si no
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados
     *
     * @param customData Datos personalizados
     * @return esta instancia
     */
    public NotifyChargingLimitRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la programación de carga
     *
     * @return Programación de carga
     */
    @Nullable
    public ChargingSchedule[] getChargingSchedule() {
        return chargingSchedule;
    }

    /**
     * Establece la programación de carga
     *
     * @param chargingSchedule Programación de carga
     */
    public void setChargingSchedule(@Nullable ChargingSchedule[] chargingSchedule) {
        if (!isValidChargingSchedule(chargingSchedule)) {
            throw new PropertyConstraintException(chargingSchedule, "La programación de carga no es válida");
        }
        this.chargingSchedule = chargingSchedule;
    }

    /**
     * Verifica si la programación de carga es válida
     *
     * @param chargingSchedule Programación de carga
     * @return {@code true} si es válida, {@code false} si no
     */
    private boolean isValidChargingSchedule(@Nullable ChargingSchedule[] chargingSchedule) {
        return chargingSchedule == null
                || (chargingSchedule.length >= 1
                && Arrays.stream(chargingSchedule).allMatch(item -> item.validate()));
    }

    /**
     * Agrega la programación de carga
     *
     * @param chargingSchedule Programación de carga
     * @return esta instancia
     */
    public NotifyChargingLimitRequest withChargingSchedule(@Nullable ChargingSchedule[] chargingSchedule) {
        setChargingSchedule(chargingSchedule);
        return this;
    }

    /**
     * Obtiene el ID del EVSE
     *
     * @return ID del EVSE
     */
    @Nullable
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el ID del EVSE
     *
     * @param evseId ID del EVSE
     */
    public void setEvseId(@Nullable Integer evseId) {
        this.evseId = evseId;
    }

    /**
     * Agrega el ID del EVSE
     *
     * @param evseId ID del EVSE
     * @return esta instancia
     */
    public NotifyChargingLimitRequest withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Obtiene el límite de carga
     *
     * @return Límite de carga
     */
    public ChargingLimit getChargingLimit() {
        return chargingLimit;
    }

    /**
     * Establece el límite de carga
     *
     * @param chargingLimit Límite de carga
     */
    public void setChargingLimit(ChargingLimit chargingLimit) {
        if (!isValidChargingLimit(chargingLimit)) {
            throw new PropertyConstraintException(chargingLimit, "El límite de carga no es válido");
        }
        this.chargingLimit = chargingLimit;
    }

    /**
     * Verifica si el límite de carga es válido
     *
     * @param chargingLimit Límite de carga
     * @return {@code true} si es válido, {@code false} si no
     */
    private boolean isValidChargingLimit(ChargingLimit chargingLimit) {
        return chargingLimit != null && chargingLimit.validate();
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidChargingSchedule(chargingSchedule)
                && isValidChargingLimit(chargingLimit);
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyChargingLimitRequest that = (NotifyChargingLimitRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(chargingSchedule, that.chargingSchedule)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(chargingLimit, that.chargingLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(chargingSchedule), evseId, chargingLimit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingSchedule", chargingSchedule)
                .add("evseId", evseId)
                .add("chargingLimit", chargingLimit)
                .add("isValid", validate())
                .toString();
    }
}
