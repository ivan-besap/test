package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para detener una transacción en la estación de carga.
 *
 * <p>Clase que representa el mensaje para solicitar el fin de una transacción activa en una estación de carga.
 * <p>Versión: OCPP 2.0.1 FINAL
 */
public class RequestStopTransactionRequest extends RequestWithId {

    /** Datos personalizados opcionales relacionados con la solicitud. */
    @Nullable
    private CustomData customData;

    /** Identificador de la transacción que se solicita detener. */
    private String transactionId;

    /**
     * Constructor de la clase RequestStopTransactionRequest.
     *
     * @param transactionId El identificador de la transacción que se solicita detener.
     */
    public RequestStopTransactionRequest(String transactionId) {
        setTransactionId(transactionId);
    }

    /**
     * Obtiene los datos personalizados relacionados con la solicitud.
     *
     * @return Los datos personalizados o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con la solicitud.
     *
     * @param customData Los datos personalizados que se desean establecer.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados proporcionados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return true si son válidos, de lo contrario false.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados y devuelve la instancia actual.
     *
     * @param customData Los datos personalizados que se desean establecer.
     * @return La instancia actual de RequestStopTransactionRequest.
     */
    public RequestStopTransactionRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador de la transacción que se solicita detener.
     *
     * @return El identificador de la transacción.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción que se solicita detener.
     *
     * @param transactionId El identificador de la transacción a detener.
     */
    public void setTransactionId(String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "El identificador de la transacción no es válido");
        }
        this.transactionId = transactionId;
    }

    /**
     * Valida si el identificador de la transacción proporcionado es válido.
     *
     * @param transactionId El identificador de la transacción a validar.
     * @return true si es válido, de lo contrario false.
     */
    private boolean isValidTransactionId(String transactionId) {
        return transactionId != null && transactionId.length() <= 36;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidTransactionId(transactionId);
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
        RequestStopTransactionRequest that = (RequestStopTransactionRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, transactionId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("transactionId", transactionId)
                .add("isValid", validate())
                .toString();
    }
}
