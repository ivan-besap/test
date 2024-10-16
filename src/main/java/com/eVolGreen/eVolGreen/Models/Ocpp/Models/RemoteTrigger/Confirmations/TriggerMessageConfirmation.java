package com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Confirmations.Enums.TriggerMessageStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central tras la solicitud {@link TriggerMessageRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <p>El campo {@code status} indica si la solicitud fue aceptada, rechazada o no implementada.
 * Los posibles valores para {@code status} incluyen:
 * <ul>
 *   <li>{@code Accepted}: La solicitud fue aceptada y se procesará.</li>
 *   <li>{@code Rejected}: La solicitud fue rechazada y no se procesará.</li>
 *   <li>{@code NotImplemented}: La funcionalidad solicitada no está implementada en el Punto de Carga.</li>
 * </ul>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     TriggerMessageConfirmation confirmacion = new TriggerMessageConfirmation(TriggerMessageStatus.Accepted);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "triggerMessageResponse")
public class TriggerMessageConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private TriggerMessageStatus status;

    /**
     * Constructor por defecto.
     * @deprecated use {@link #TriggerMessageConfirmation(TriggerMessageStatus)} para asegurar que se establezcan los campos requeridos
     */
    @Deprecated
    public TriggerMessageConfirmation() {}

    /**
     * Construye una nueva TriggerMessageConfirmation con el estado especificado.
     *
     * @param status el estado de la solicitud; no debe ser nulo.
     */
    public TriggerMessageConfirmation(TriggerMessageStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la solicitud de disparo de mensaje.
     *
     * @return el {@link TriggerMessageStatus} que indica el éxito o fracaso de la solicitud.
     */
    public TriggerMessageStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de disparo de mensaje.
     *
     * @param status el {@link TriggerMessageStatus} que indica el éxito o fracaso de la solicitud; no debe ser nulo.
     */
    @XmlElement
    public void setStatus(TriggerMessageStatus status) {
        this.status = status;
    }

    /**
     * Valida la respuesta para asegurar que el campo {@code status} no sea nulo.
     *
     * @return {@code true} si la respuesta es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerMessageConfirmation that = (TriggerMessageConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "TriggerMessageConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}