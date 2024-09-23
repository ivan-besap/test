package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el punto de carga al sistema central
 * en respuesta a una solicitud de cancelación de reserva (CancelReservationRequest).
 * Esta confirmación indica el éxito o fracaso de la operación de cancelación.
 */
@XmlRootElement(name = "cancelReservationResponse")
public class CancelReservationConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private CancelReservationStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #CancelReservationConfirmation(CancelReservationStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public CancelReservationConfirmation() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado de la cancelación de la reserva.
     */
    public CancelReservationConfirmation(CancelReservationStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la cancelación de la reserva.
     *
     * @return Estado de la cancelación como {@link CancelReservationStatus}.
     */
    public CancelReservationStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la cancelación de la reserva.
     * Este estado indica el éxito o fracaso de la cancelación de una reserva por parte del sistema central.
     *
     * @param status Estado de la cancelación. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(CancelReservationStatus status) {
        this.status = status;
    }

    /**
     * Valida que el campo obligatorio status esté presente.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancelReservationConfirmation that = (CancelReservationConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "CancelReservationConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}