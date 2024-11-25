package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingSchedule;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de notificación del horario de carga del vehículo eléctrico (EV).
 *
 * <p>Esta clase representa una solicitud para notificar al sistema de gestión de la estación de carga (CSMS)
 * sobre el horario de carga planificado para un vehículo eléctrico.
 */
public class NotifyEVChargingScheduleRequest extends RequestWithId {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Punto de referencia de tiempo.
     *
     * <p>Los periodos contenidos en el perfil de carga son relativos a este momento en el tiempo.
     */
    private ZonedDateTime timeBase;

    /**
     * Horario de carga.
     *
     * <p>Estructura del horario de carga que define una lista de periodos de carga, utilizada en
     * GetCompositeSchedule.conf y ChargingProfile.
     */
    private ChargingSchedule chargingSchedule;

    /**
     * Identificador de la EVSE (Electric Vehicle Supply Equipment).
     *
     * <p>El horario de carga contenido en esta notificación se aplica a una EVSE específica.
     * El identificador (evseId) debe ser mayor a 0.
     */
    private Integer evseId;

    /**
     * Constructor de la clase NotifyEVChargingScheduleRequest.
     *
     * @param timeBase Punto de referencia de tiempo para los periodos en el perfil de carga.
     * @param chargingSchedule Horario de carga que contiene la lista de periodos de carga.
     * @param evseId Identificador de la EVSE al que aplica este horario de carga.
     */
    public NotifyEVChargingScheduleRequest(
            ZonedDateTime timeBase, ChargingSchedule chargingSchedule, Integer evseId) {
        setTimeBase(timeBase);
        setChargingSchedule(chargingSchedule);
        setEvseId(evseId);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public NotifyEVChargingScheduleRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el punto de referencia de tiempo.
     *
     * @return Punto de referencia de tiempo.
     */
    public ZonedDateTime getTimeBase() {
        return timeBase;
    }

    /**
     * Establece el punto de referencia de tiempo.
     *
     * @param timeBase Punto de referencia de tiempo para los periodos en el perfil de carga.
     */
    public void setTimeBase(ZonedDateTime timeBase) {
        if (!isValidTimeBase(timeBase)) {
            throw new PropertyConstraintException(timeBase, "El punto de referencia de tiempo es inválido.");
        }
        this.timeBase = timeBase;
    }

    /**
     * Verifica si el punto de referencia de tiempo es válido.
     *
     * @param timeBase Punto de referencia de tiempo a validar.
     * @return {@code true} si el punto de referencia es válido, de lo contrario {@code false}.
     */
    private boolean isValidTimeBase(ZonedDateTime timeBase) {
        return timeBase != null;
    }

    /**
     * Obtiene el horario de carga.
     *
     * @return Horario de carga que contiene la lista de periodos de carga.
     */
    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }

    /**
     * Establece el horario de carga.
     *
     * @param chargingSchedule Horario de carga que contiene la lista de periodos de carga.
     */
    public void setChargingSchedule(ChargingSchedule chargingSchedule) {
        if (!isValidChargingSchedule(chargingSchedule)) {
            throw new PropertyConstraintException(chargingSchedule, "El horario de carga es inválido.");
        }
        this.chargingSchedule = chargingSchedule;
    }

    /**
     * Verifica si el horario de carga es válido.
     *
     * @param chargingSchedule Horario de carga a validar.
     * @return {@code true} si el horario de carga es válido, de lo contrario {@code false}.
     */
    private boolean isValidChargingSchedule(ChargingSchedule chargingSchedule) {
        return chargingSchedule != null && chargingSchedule.validate();
    }

    /**
     * Obtiene el identificador de la EVSE.
     *
     * @return Identificador de la EVSE al que aplica este horario de carga.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador de la EVSE.
     *
     * @param evseId Identificador de la EVSE al que aplica este horario de carga.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "El identificador de la EVSE es inválido.");
        }
        this.evseId = evseId;
    }

    /**
     * Verifica si el identificador de la EVSE es válido.
     *
     * @param evseId Identificador de la EVSE a validar.
     * @return {@code true} si el identificador es válido, de lo contrario {@code false}.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidTimeBase(timeBase)
                && isValidChargingSchedule(chargingSchedule)
                && isValidEvseId(evseId);
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
        NotifyEVChargingScheduleRequest that = (NotifyEVChargingScheduleRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(timeBase, that.timeBase)
                && Objects.equals(chargingSchedule, that.chargingSchedule)
                && Objects.equals(evseId, that.evseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, timeBase, chargingSchedule, evseId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("timeBase", timeBase)
                .add("chargingSchedule", chargingSchedule)
                .add("evseId", evseId)
                .add("isValid", validate())
                .toString();
    }
}
