package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al estado de una transacción.
 *
 * <p>Esta clase representa la respuesta enviada por el Charging Station indicando si una
 * transacción está activa y si hay mensajes pendientes en la cola.
 */
public class GetTransactionStatusResponse extends Confirmation {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Indicador de si la transacción sigue activa. */
    @Nullable
    private Boolean ongoingIndicator;

    /** Indicador de si hay mensajes pendientes de entrega. */
    private Boolean messagesInQueue;

    /**
     * Constructor para la clase GetTransactionStatusResponse.
     *
     * @param messagesInQueue Indica si hay mensajes pendientes de entrega.
     */
    public GetTransactionStatusResponse(Boolean messagesInQueue) {
        setMessagesInQueue(messagesInQueue);
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
    public GetTransactionStatusResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el indicador de si la transacción sigue activa.
     *
     * @return Indicador de estado de la transacción.
     */
    @Nullable
    public Boolean getOngoingIndicator() {
        return ongoingIndicator;
    }

    /**
     * Establece el indicador de si la transacción sigue activa.
     *
     * @param ongoingIndicator Indicador de estado de la transacción.
     */
    public void setOngoingIndicator(@Nullable Boolean ongoingIndicator) {
        this.ongoingIndicator = ongoingIndicator;
    }

    /**
     * Agrega el indicador de si la transacción sigue activa.
     *
     * @param ongoingIndicator Indicador de estado de la transacción.
     * @return La instancia actual.
     */
    public GetTransactionStatusResponse withOngoingIndicator(@Nullable Boolean ongoingIndicator) {
        setOngoingIndicator(ongoingIndicator);
        return this;
    }

    /**
     * Obtiene el indicador de si hay mensajes pendientes de entrega.
     *
     * @return Indicador de mensajes pendientes.
     */
    public Boolean getMessagesInQueue() {
        return messagesInQueue;
    }

    /**
     * Establece el indicador de si hay mensajes pendientes de entrega.
     *
     * @param messagesInQueue Indicador de mensajes pendientes.
     */
    public void setMessagesInQueue(Boolean messagesInQueue) {
        if (!isValidMessagesInQueue(messagesInQueue)) {
            throw new PropertyConstraintException(messagesInQueue, "messagesInQueue es inválido");
        }
        this.messagesInQueue = messagesInQueue;
    }

    /**
     * Verifica si el indicador de mensajes pendientes es válido.
     *
     * @param messagesInQueue Indicador de mensajes pendientes.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidMessagesInQueue(Boolean messagesInQueue) {
        return messagesInQueue != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidMessagesInQueue(messagesInQueue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetTransactionStatusResponse that = (GetTransactionStatusResponse) o;
        return Objects.equals(customData, that.customData) &&
                Objects.equals(ongoingIndicator, that.ongoingIndicator) &&
                Objects.equals(messagesInQueue, that.messagesInQueue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, ongoingIndicator, messagesInQueue);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("ongoingIndicator", ongoingIndicator)
                .add("messagesInQueue", messagesInQueue)
                .add("isValid", validate())
                .toString();
    }
}
