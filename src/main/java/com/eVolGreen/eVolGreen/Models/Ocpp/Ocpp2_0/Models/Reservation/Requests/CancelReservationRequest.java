package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * CancelReservationRequest
 *
 * <p>Representa una solicitud para cancelar una reserva en el protocolo OCPP 2.0.1.
 */
public class CancelReservationRequest extends RequestWithId {
    /** Datos personalizados adicionales para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Identificador único de la reserva a cancelar. */
    private Integer reservationId;

    /**
     * Constructor de la clase CancelReservationRequest.
     *
     * @param reservationId Identificador único de la reserva a cancelar.
     */
    public CancelReservationRequest(Integer reservationId) {
        setReservationId(reservationId);
    }

    /**
     * Obtiene los datos personalizados adicionales de la solicitud.
     *
     * @return Datos personalizados adicionales de la solicitud.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales de la solicitud.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados adicionales son válidos.
     *
     * @param customData Los datos personalizados adicionales a verificar.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales a la solicitud.
     *
     * @param customData Datos personalizados adicionales.
     * @return La solicitud actualizada.
     */
    public CancelReservationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único de la reserva a cancelar.
     *
     * @return El identificador único de la reserva.
     */
    public Integer getReservationId() {
        return reservationId;
    }

    /**
     * Establece el identificador único de la reserva a cancelar.
     *
     * @param reservationId El identificador único de la reserva.
     */
    public void setReservationId(Integer reservationId) {
        if (!isValidReservationId(reservationId)) {
            throw new PropertyConstraintException(reservationId, "reservationId no es válido");
        }
        this.reservationId = reservationId;
    }

    /**
     * Verifica si el identificador único de la reserva es válido.
     *
     * @param reservationId El identificador único a verificar.
     * @return {@code true} si el identificador es válido; de lo contrario, {@code false}.
     */
    private boolean isValidReservationId(Integer reservationId) {
        return reservationId != null;
    }

    /**
     * Valida los datos de la solicitud.
     *
     * @return {@code true} si los datos de la solicitud son válidos; de lo contrario, {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidReservationId(reservationId);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción activa.
     *
     * @return Siempre devuelve {@code false}.
     */
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

    /**
     * Compara esta instancia con otro objeto para verificar la igualdad.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario, {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CancelReservationRequest that = (CancelReservationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(reservationId, that.reservationId);
    }

    /**
     * Calcula el hash de esta instancia.
     *
     * @return El hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, reservationId);
    }

    /**
     * Genera una representación en forma de cadena de esta solicitud.
     *
     * @return Una cadena que representa esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("reservationId", reservationId)
                .add("isValid", validate())
                .toString();
    }
}
