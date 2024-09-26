package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.ResetStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.ResetRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Punto de Carga al Sistema Central en respuesta
 * a una solicitud de reinicio ({@link ResetRequest}).
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 * El estado de la confirmación puede ser "Accepted" o "Rejected".
 *
 * <b>Campos:</b>
 * - {@code status}: El estado de la solicitud de reinicio (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ResetConfirmation confirmacion = new ResetConfirmation(ResetStatus.ACCEPTED);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class ResetConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private ResetStatus status;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #ResetConfirmation(ResetStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ResetConfirmation() {}

    /**
     * Construye una nueva instancia de ResetConfirmation con el estado especificado.
     *
     * @param status El estado de la solicitud de reinicio.
     */
    public ResetConfirmation(ResetStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado que indica si el Punto de Carga puede realizar el reinicio.
     *
     * @return ResetStatus, el estado de la solicitud de reinicio.
     */
    public ResetStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si el Punto de Carga puede realizar el reinicio.
     *
     * @param status ResetStatus, el estado de la solicitud de reinicio.
     */
    @XmlElement
    public void setStatus(ResetStatus status) {
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
        ResetConfirmation that = (ResetConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "ResetConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}