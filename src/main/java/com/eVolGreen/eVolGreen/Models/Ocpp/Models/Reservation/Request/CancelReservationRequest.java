package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para cancelar una reservación en OCPP.
 * Esta solicitud es enviada por el sistema central al punto de carga para cancelar una reservación existente.
 */
@XmlRootElement
public class CancelReservationRequest extends RequestWithId {

    @NotNull(message = "reservationId es obligatorio")
    @JsonProperty("reservationId")
    private Integer reservationId;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #CancelReservationRequest(Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public CancelReservationRequest() {}

    /**
     * Constructor con campos obligatorios.
     *
     * @param reservationId ID de la reservación a cancelar.
     */
    public CancelReservationRequest(Integer reservationId) {
        setReservationId(reservationId);
    }

    /**
     * Obtiene el ID de la reservación a cancelar.
     *
     * @return ID de la reservación.
     */
    public Integer getReservationId() {
        return reservationId;
    }

    /**
     * Establece el ID de la reservación a cancelar.
     *
     * @param reservationId ID de la reservación. No puede ser nulo.
     */
    @XmlElement
    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Valida que el campo obligatorio reservationId esté presente.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return reservationId != null;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que la cancelación de reserva no está directamente relacionada con una transacción.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancelReservationRequest that = (CancelReservationRequest) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }

    @Override
    public String toString() {
        return "CancelReservationRequest{" +
                "reservationId=" + reservationId +
                ", isValid=" + validate() +
                '}';
    }
}