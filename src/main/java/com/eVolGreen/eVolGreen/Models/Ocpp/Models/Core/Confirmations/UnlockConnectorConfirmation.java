package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.UnlockStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.UnlockConnectorRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central en respuesta a una {@link UnlockConnectorRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <p>El campo {@code status} indica si el Punto de Carga ha desbloqueado con éxito el conector.
 * Los posibles valores para {@code status} incluyen:
 * <ul>
 *   <li>{@code Unlocked} - El conector fue desbloqueado con éxito.</li>
 *   <li>{@code UnlockFailed} - La operación de desbloqueo falló.</li>
 *   <li>{@code NotSupported} - El Punto de Carga no soporta el desbloqueo del conector especificado.</li>
 * </ul>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     UnlockConnectorConfirmation confirmacion = new UnlockConnectorConfirmation(UnlockStatus.Unlocked);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 *
 * @see UnlockConnectorRequest
 */
@XmlRootElement(name = "unlockConnectorResponse")
public class UnlockConnectorConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private UnlockStatus status;

    /**
     * Constructor por defecto.
     * @deprecated use {@link #UnlockConnectorConfirmation(UnlockStatus)} para asegurar que se establezcan los campos requeridos
     */
    @Deprecated
    public UnlockConnectorConfirmation() {}

    /**
     * Construye una nueva UnlockConnectorConfirmation con el estado especificado.
     *
     * @param status el estado de la operación de desbloqueo; no debe ser nulo.
     */
    public UnlockConnectorConfirmation(UnlockStatus status) {
        setStatus(status);
    }

    /**
     * Devuelve el estado de la operación de desbloqueo.
     *
     * @return el estado de la operación de desbloqueo.
     */
    public UnlockStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la operación de desbloqueo.
     *
     * @param status el estado a establecer; no debe ser nulo.
     */
    @XmlElement
    public void setStatus(UnlockStatus status) {
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
        UnlockConnectorConfirmation that = (UnlockConnectorConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "UnlockConnectorConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}