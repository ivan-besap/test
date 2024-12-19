package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.ConnectorStatusEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * StatusNotificationRequest
 *
 * <p>OCPP 2.0.1 FINAL
 *
 * <p>Mensaje utilizado para informar el estado de un conector específico de un EVSE.
 */
public class StatusNotificationRequest extends RequestWithId {
    /** Datos personalizados específicos de la implementación. */
    @Nullable
    private CustomData customData;

    /**
     * La hora para la cual se informa el estado. Si no se proporciona, se asumirá la hora de recepción del mensaje.
     */
    private ZonedDateTime timestamp;

    /** El estado actual del conector. */
    private ConnectorStatusEnum connectorStatus;

    /** Identificador del EVSE al que pertenece el conector para el cual se informa el estado. */
    private Integer evseId;

    /** Identificador del conector dentro del EVSE para el cual se informa el estado. */
    private Integer connectorId;

    /**
     * Constructor de la clase StatusNotificationRequest.
     *
     * @param timestamp La hora para la cual se informa el estado.
     * @param connectorStatus El estado actual del conector.
     * @param evseId Identificador del EVSE al que pertenece el conector.
     * @param connectorId Identificador del conector dentro del EVSE.
     */
    public StatusNotificationRequest(
            ZonedDateTime timestamp,
            ConnectorStatusEnum connectorStatus,
            Integer evseId,
            Integer connectorId) {
        setTimestamp(timestamp);
        setConnectorStatus(connectorStatus);
        setEvseId(evseId);
        setConnectorId(connectorId);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
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
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia actualizada.
     */
    public StatusNotificationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la hora para la cual se informa el estado.
     *
     * @return Hora para la cual se informa el estado.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la hora para la cual se informa el estado.
     *
     * @param timestamp Hora para la cual se informa el estado.
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        if (!isValidTimestamp(timestamp)) {
            throw new PropertyConstraintException(timestamp, "timestamp es inválido");
        }
        this.timestamp = timestamp;
    }

    /**
     * Verifica si el timestamp es válido.
     *
     * @param timestamp Hora a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidTimestamp(ZonedDateTime timestamp) {
        return timestamp != null;
    }

    /**
     * Obtiene el estado actual del conector.
     *
     * @return Estado actual del conector.
     */
    public ConnectorStatusEnum getConnectorStatus() {
        return connectorStatus;
    }

    /**
     * Establece el estado actual del conector.
     *
     * @param connectorStatus Estado actual del conector.
     */
    public void setConnectorStatus(ConnectorStatusEnum connectorStatus) {
        if (!isValidConnectorStatus(connectorStatus)) {
            throw new PropertyConstraintException(connectorStatus, "connectorStatus es inválido");
        }
        this.connectorStatus = connectorStatus;
    }

    /**
     * Verifica si el estado del conector es válido.
     *
     * @param connectorStatus Estado a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidConnectorStatus(ConnectorStatusEnum connectorStatus) {
        return connectorStatus != null;
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
     * @param evseId Identificador a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    /**
     * Obtiene el identificador del conector dentro del EVSE.
     *
     * @return Identificador del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el identificador del conector dentro del EVSE.
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
     * @param connectorId Identificador a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidConnectorId(Integer connectorId) {
        return connectorId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidTimestamp(timestamp)
                && isValidConnectorStatus(connectorStatus)
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
        StatusNotificationRequest that = (StatusNotificationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(connectorStatus, that.connectorStatus)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(connectorId, that.connectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, timestamp, connectorStatus, evseId, connectorId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("timestamp", timestamp)
                .add("connectorStatus", connectorStatus)
                .add("evseId", evseId)
                .add("connectorId", connectorId)
                .add("isValid", validate())
                .toString();
    }
}
