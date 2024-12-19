package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Utils.MessageInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para establecer un mensaje que será mostrado en una estación de carga.
 */
public class SetDisplayMessageRequest extends RequestWithId {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Información del mensaje.
     *
     * <p>Detalles del mensaje que será mostrado en la estación de carga.
     */
    private MessageInfo message;

    /**
     * Constructor de la clase SetDisplayMessageRequest.
     *
     * @param message Detalles del mensaje que será mostrado en la estación de carga.
     */
    public SetDisplayMessageRequest(MessageInfo message) {
        setMessage(message);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados.
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
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
     * @return La instancia actual de la solicitud.
     */
    public SetDisplayMessageRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los detalles del mensaje que será mostrado en la estación de carga.
     *
     * @return Detalles del mensaje.
     */
    public MessageInfo getMessage() {
        return message;
    }

    /**
     * Establece los detalles del mensaje que será mostrado en la estación de carga.
     *
     * @param message Detalles del mensaje.
     */
    public void setMessage(MessageInfo message) {
        if (!isValidMessage(message)) {
            throw new PropertyConstraintException(message, "El mensaje no es válido.");
        }
        this.message = message;
    }

    /**
     * Valida si los detalles del mensaje son válidos.
     *
     * @param message Detalles del mensaje a validar.
     * @return {@code true} si los detalles son válidos, {@code false} en caso contrario.
     */
    private boolean isValidMessage(MessageInfo message) {
        return message != null && message.validate();
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidMessage(message);
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
        SetDisplayMessageRequest that = (SetDisplayMessageRequest) o;
        return Objects.equals(customData, that.customData) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, message);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("message", message)
                .add("isValid", validate())
                .toString();
    }
}
