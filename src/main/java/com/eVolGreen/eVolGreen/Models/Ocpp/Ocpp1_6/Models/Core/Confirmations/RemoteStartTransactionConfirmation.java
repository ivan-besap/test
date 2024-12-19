package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.RemoteStartStopStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.RemoteStartTransactionRequest;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por la Estación de Carga al Sistema Central en respuesta
 * a una solicitud de inicio remoto de transacción ({@link RemoteStartTransactionRequest}).
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code status}: El estado de la solicitud de inicio remoto (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     RemoteStartTransactionConfirmation confirmacion = new RemoteStartTransactionConfirmation(RemoteStartStopStatus.ACCEPTED);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "remoteStartTransactionResponse")
public class RemoteStartTransactionConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    private RemoteStartStopStatus status;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #RemoteStartTransactionConfirmation(RemoteStartStopStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public RemoteStartTransactionConfirmation() {}

    /**
     * Construye una nueva instancia de RemoteStartTransactionConfirmation con el estado especificado.
     *
     * @param status El estado de la solicitud de inicio remoto.
     */
    public RemoteStartTransactionConfirmation(RemoteStartStopStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la solicitud de inicio remoto.
     *
     * @return RemoteStartStopStatus, el estado de la solicitud.
     */
    public RemoteStartStopStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de inicio remoto.
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
        RemoteStartTransactionConfirmation that = (RemoteStartTransactionConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "RemoteStartTransactionConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}