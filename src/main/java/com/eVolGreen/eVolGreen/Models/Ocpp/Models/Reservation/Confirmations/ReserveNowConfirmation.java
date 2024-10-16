package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.Confirmations.Enums.ReservationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.Request.ReserveNowRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Punto de Carga al Sistema Central en respuesta
 * a una solicitud de reserva de un conector ({@link ReserveNowRequest}).
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code status}: El estado de la reserva (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ReserveNowConfirmation confirmacion = new ReserveNowConfirmation(ReservationStatus.ACCEPTED);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "reserveNowResponse")
public class ReserveNowConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private ReservationStatus status;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #ReserveNowConfirmation(ReservationStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ReserveNowConfirmation() {}

    /**
     * Construye una nueva instancia de ReserveNowConfirmation con el estado especificado.
     *
     * @param status El estado de la reserva.
     */
    public ReserveNowConfirmation(ReservationStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la reserva.
     *
     * @return ReservationStatus, el estado de la reserva.
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la reserva.
     *
     * @param status ReservationStatus, el estado de la reserva.
     */
    @XmlElement
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    /**
     * Valida si los datos de la confirmación son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserveNowConfirmation that = (ReserveNowConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "ReserveNowConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}