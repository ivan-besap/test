package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.RemoteStartStopStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.RemoteStopTransactionRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Punto de Carga al Sistema Central en respuesta
 * a una solicitud de detener una transacción remota ({@link RemoteStopTransactionRequest}).
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code status}: El estado de la solicitud de detener la transacción (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     RemoteStopTransactionConfirmation confirmacion = new RemoteStopTransactionConfirmation(RemoteStartStopStatus.ACCEPTED);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "remoteStopTransactionResponse")
public class RemoteStopTransactionConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private RemoteStartStopStatus status;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #RemoteStopTransactionConfirmation(RemoteStartStopStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public RemoteStopTransactionConfirmation() {}

    /**
     * Construye una nueva instancia de RemoteStopTransactionConfirmation con el estado especificado.
     *
     * @param status El estado de la solicitud de detener la transacción.
     */
    public RemoteStopTransactionConfirmation(RemoteStartStopStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la solicitud de detener la transacción.
     *
     * @return RemoteStartStopStatus, el estado de la solicitud.
     */
    public RemoteStartStopStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de detener la transacción.
     *
     * @param status RemoteStartStopStatus, el estado de la solicitud.
     */
    @XmlElement
    public void setStatus(RemoteStartStopStatus status) {
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
        RemoteStopTransactionConfirmation that = (RemoteStopTransactionConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "RemoteStopTransactionConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}