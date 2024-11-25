package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils.ChargingNeeds;
import jakarta.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Notificación de necesidades de carga del vehículo eléctrico (EV).
 *
 * <p>Este mensaje informa sobre las necesidades específicas de carga de un vehículo eléctrico,
 * como los límites de carga o restricciones relacionadas con el EVSE al que está conectado.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class NotifyEVChargingNeedsRequest extends RequestWithId {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Máximo número de tuplas de horarios soportadas por el vehículo eléctrico. */
    @Nullable
    private Integer maxScheduleTuples;

    /** Necesidades de carga. */
    private ChargingNeeds chargingNeeds;

    /** Identificador del EVSE y conector al que está conectado el vehículo eléctrico. No puede ser 0. */
    private Integer evseId;

    /**
     * Constructor para la clase NotifyEVChargingNeedsRequest.
     *
     * @param chargingNeeds Necesidades de carga del vehículo eléctrico.
     * @param evseId Identificador del EVSE y conector al que está conectado el vehículo eléctrico.
     */
    public NotifyEVChargingNeedsRequest(ChargingNeeds chargingNeeds, Integer evseId) {
        setChargingNeeds(chargingNeeds);
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
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia de la clase.
     */
    public NotifyEVChargingNeedsRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el máximo número de tuplas de horarios soportadas por el vehículo eléctrico.
     *
     * @return Máximo número de tuplas soportadas.
     */
    @Nullable
    public Integer getMaxScheduleTuples() {
        return maxScheduleTuples;
    }

    /**
     * Establece el máximo número de tuplas de horarios soportadas por el vehículo eléctrico.
     *
     * @param maxScheduleTuples Máximo número de tuplas soportadas.
     */
    public void setMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
        this.maxScheduleTuples = maxScheduleTuples;
    }

    /**
     * Agrega el máximo número de tuplas de horarios soportadas.
     *
     * @param maxScheduleTuples Máximo número de tuplas soportadas.
     * @return Esta instancia de la clase.
     */
    public NotifyEVChargingNeedsRequest withMaxScheduleTuples(@Nullable Integer maxScheduleTuples) {
        setMaxScheduleTuples(maxScheduleTuples);
        return this;
    }

    /**
     * Obtiene las necesidades de carga del vehículo eléctrico.
     *
     * @return Necesidades de carga del vehículo eléctrico.
     */
    public ChargingNeeds getChargingNeeds() {
        return chargingNeeds;
    }

    /**
     * Establece las necesidades de carga del vehículo eléctrico.
     *
     * @param chargingNeeds Necesidades de carga.
     */
    public void setChargingNeeds(ChargingNeeds chargingNeeds) {
        if (!isValidChargingNeeds(chargingNeeds)) {
            throw new PropertyConstraintException(chargingNeeds, "Necesidades de carga inválidas");
        }
        this.chargingNeeds = chargingNeeds;
    }

    /**
     * Valida si las necesidades de carga son válidas.
     *
     * @param chargingNeeds Necesidades de carga a validar.
     * @return {@code true} si son válidas, {@code false} en caso contrario.
     */
    private boolean isValidChargingNeeds(ChargingNeeds chargingNeeds) {
        return chargingNeeds != null && chargingNeeds.validate();
    }

    /**
     * Obtiene el identificador del EVSE y conector al que está conectado el vehículo eléctrico.
     *
     * @return Identificador del EVSE y conector.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE y conector al que está conectado el vehículo eléctrico.
     *
     * @param evseId Identificador del EVSE y conector.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "Identificador de EVSE inválido");
        }
        this.evseId = evseId;
    }

    /**
     * Valida si el identificador del EVSE es válido.
     *
     * @param evseId Identificador del EVSE a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidChargingNeeds(chargingNeeds)
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
        NotifyEVChargingNeedsRequest that = (NotifyEVChargingNeedsRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(maxScheduleTuples, that.maxScheduleTuples)
                && Objects.equals(chargingNeeds, that.chargingNeeds)
                && Objects.equals(evseId, that.evseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, maxScheduleTuples, chargingNeeds, evseId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("maxScheduleTuples", maxScheduleTuples)
                .add("chargingNeeds", chargingNeeds)
                .add("evseId", evseId)
                .add("isValid", validate())
                .toString();
    }
}
