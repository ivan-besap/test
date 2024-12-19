package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;


/**
 * UnlockConnectorRequest
 *
 * <p>Solicitud para desbloquear un conector.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class UnlockConnectorRequest extends RequestWithId {
    /** Datos personalizados para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Identificador del EVSE asociado al conector que se necesita desbloquear. */
    private Integer evseId;

    /** Identificador del conector que se necesita desbloquear. */
    private Integer connectorId;

    /**
     * Constructor para la clase UnlockConnectorRequest.
     *
     * @param evseId Identificador del EVSE asociado al conector que se necesita desbloquear.
     * @param connectorId Identificador del conector que se necesita desbloquear.
     */
    public UnlockConnectorRequest(Integer evseId, Integer connectorId) {
        setEvseId(evseId);
        setConnectorId(connectorId);
    }

    /**
     * Obtiene los datos personalizados de la solicitud.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la solicitud.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de UnlockConnectorRequest.
     */
    public UnlockConnectorRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE.
     *
     * @return Identificador del EVSE.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "evseId es inválido");
        }
        this.evseId = evseId;
    }

    /**
     * Verifica si el identificador del EVSE es válido.
     *
     * @param evseId Identificador del EVSE a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    /**
     * Obtiene el identificador del conector.
     *
     * @return Identificador del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el identificador del conector.
     *
     * @param connectorId Identificador del conector.
     */
    public void setConnectorId(Integer connectorId) {
        if (!isValidConnectorId(connectorId)) {
            throw new PropertyConstraintException(connectorId, "connectorId es inválido");
        }
        this.connectorId = connectorId;
    }

    /**
     * Verifica si el identificador del conector es válido.
     *
     * @param connectorId Identificador del conector a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidConnectorId(Integer connectorId) {
        return connectorId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEvseId(evseId)
                && isValidConnectorId(connectorId);
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
        UnlockConnectorRequest that = (UnlockConnectorRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(connectorId, that.connectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, evseId, connectorId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evseId", evseId)
                .add("connectorId", connectorId)
                .add("isValid", validate())
                .toString();
    }
}

