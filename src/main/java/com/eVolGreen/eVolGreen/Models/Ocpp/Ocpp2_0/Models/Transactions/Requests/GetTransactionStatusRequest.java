package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener el estado de una transacción.
 *
 * <p>Esta clase representa la solicitud enviada al Charging Station para recuperar el estado
 * de una transacción específica identificada por un transactionId.
 */
public class GetTransactionStatusRequest extends RequestWithId {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** El identificador de la transacción para la cual se solicita el estado. */
    @Nullable
    private String transactionId;

    /** Constructor por defecto de la clase GetTransactionStatusRequest. */
    public GetTransactionStatusRequest() {}

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
     * @param customData Datos personalizados.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual.
     */
    public GetTransactionStatusRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador de la transacción para la cual se solicita el estado.
     *
     * @return Identificador de la transacción.
     */
    @Nullable
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción para la cual se solicita el estado.
     *
     * @param transactionId Identificador de la transacción.
     */
    public void setTransactionId(@Nullable String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "transactionId es inválido");
        }
        this.transactionId = transactionId;
    }

    /**
     * Verifica si el identificador de la transacción es válido.
     *
     * @param transactionId Identificador de la transacción.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidTransactionId(@Nullable String transactionId) {
        return transactionId == null || transactionId.length() <= 36;
    }

    /**
     * Agrega el identificador de la transacción para la cual se solicita el estado.
     *
     * @param transactionId Identificador de la transacción.
     * @return La instancia actual.
     */
    public GetTransactionStatusRequest withTransactionId(@Nullable String transactionId) {
        setTransactionId(transactionId);
        return this;
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
        GetTransactionStatusRequest that = (GetTransactionStatusRequest) o;
        return Objects.equals(customData, that.customData) &&
                Objects.equals(transactionId, that.transactionId);
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
