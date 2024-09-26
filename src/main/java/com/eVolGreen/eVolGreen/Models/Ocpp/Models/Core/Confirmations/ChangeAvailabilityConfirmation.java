package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.AvailabilityStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el punto de carga al sistema central
 * en respuesta a una solicitud de cambio de disponibilidad (ChangeAvailabilityRequest).
 * Esta confirmación indica si el punto de carga puede realizar el cambio de disponibilidad solicitado.
 */
@XmlRootElement(name = "changeAvailabilityResponse")
public class ChangeAvailabilityConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private AvailabilityStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #ChangeAvailabilityConfirmation(AvailabilityStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ChangeAvailabilityConfirmation() {}

    /**
     * Constructor con el campo requerido.
     *
     * @param status Estado de disponibilidad del conector.
     */
    public ChangeAvailabilityConfirmation(AvailabilityStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de disponibilidad del conector.
     * Este estado indica si el punto de carga puede realizar el cambio de disponibilidad solicitado.
     *
     * @return Estado de disponibilidad como {@link AvailabilityStatus}.
     */
    public AvailabilityStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de disponibilidad del conector.
     * Este estado indica si el punto de carga puede realizar el cambio de disponibilidad solicitado.
     *
     * @param status Estado de disponibilidad. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    /**
     * Obtiene el estado de disponibilidad del conector.
     *
     * @return Estado de disponibilidad como {@link AvailabilityStatus}.
     * @deprecated Use {@link #getStatus()} en su lugar.
     */
    @Deprecated
    public AvailabilityStatus objStatus() {
        return status;
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
        ChangeAvailabilityConfirmation that = (ChangeAvailabilityConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "ChangeAvailabilityConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}