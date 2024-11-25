package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de transferencia de datos entre un sistema de gestión central y una
 * estación de carga. Utilizado para mensajes específicos del proveedor o personalizados.
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class DataTransferRequest extends RequestWithId {

    /** Datos personalizados definidos por el cliente o el proveedor. */
    @Nullable
    private CustomData customData;

    /** Mensaje o identificador de implementación específico (opcional). */
    @Nullable
    private String messageId;

    /** Datos sin longitud o formato especificado. Abierto a la implementación. */
    @Nullable
    private Object data;

    /** Identificador del proveedor para esta implementación específica. */
    private String vendorId;

    /**
     * Constructor de la clase DataTransferRequest.
     *
     * @param vendorId Identificador del proveedor para la implementación específica.
     */
    public DataTransferRequest(String vendorId) {
        setVendorId(vendorId);
    }

    /**
     * Obtiene datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados.
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
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia para encadenamiento.
     */
    public DataTransferRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador de mensaje.
     *
     * @return Identificador de mensaje.
     */
    @Nullable
    public String getMessageId() {
        return messageId;
    }

    /**
     * Establece el identificador de mensaje.
     *
     * @param messageId Identificador de mensaje.
     */
    public void setMessageId(@Nullable String messageId) {
        if (!isValidMessageId(messageId)) {
            throw new PropertyConstraintException(messageId, "messageId es inválido");
        }
        this.messageId = messageId;
    }

    /**
     * Valida el identificador de mensaje.
     *
     * @param messageId Identificador de mensaje.
     * @return {@code true} si el identificador es válido; de lo contrario, {@code false}.
     */
    private boolean isValidMessageId(@Nullable String messageId) {
        return messageId == null || messageId.length() <= 50;
    }

    /**
     * Agrega el identificador de mensaje.
     *
     * @param messageId Identificador de mensaje.
     * @return Esta instancia para encadenamiento.
     */
    public DataTransferRequest withMessageId(@Nullable String messageId) {
        setMessageId(messageId);
        return this;
    }

    /**
     * Obtiene los datos sin formato.
     *
     * @return Datos sin formato.
     */
    @Nullable
    public Object getData() {
        return data;
    }

    /**
     * Establece los datos sin formato.
     *
     * @param data Datos sin formato.
     */
    public void setData(@Nullable Object data) {
        this.data = data;
    }

    /**
     * Agrega datos sin formato.
     *
     * @param data Datos sin formato.
     * @return Esta instancia para encadenamiento.
     */
    public DataTransferRequest withData(@Nullable Object data) {
        setData(data);
        return this;
    }

    /**
     * Obtiene el identificador del proveedor.
     *
     * @return Identificador del proveedor.
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * Establece el identificador del proveedor.
     *
     * @param vendorId Identificador del proveedor.
     */
    public void setVendorId(String vendorId) {
        if (!isValidVendorId(vendorId)) {
            throw new PropertyConstraintException(vendorId, "vendorId es inválido");
        }
        this.vendorId = vendorId;
    }

    /**
     * Valida el identificador del proveedor.
     *
     * @param vendorId Identificador del proveedor.
     * @return {@code true} si el identificador es válido; de lo contrario, {@code false}.
     */
    private boolean isValidVendorId(String vendorId) {
        return vendorId != null && vendorId.length() <= 255;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidMessageId(messageId)
                && isValidVendorId(vendorId);
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
        DataTransferRequest that = (DataTransferRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(messageId, that.messageId)
                && Objects.equals(data, that.data)
                && Objects.equals(vendorId, that.vendorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, messageId, data, vendorId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("messageId", messageId)
                .add("data", data)
                .add("vendorId", vendorId)
                .add("isValid", validate())
                .toString();
    }


}
