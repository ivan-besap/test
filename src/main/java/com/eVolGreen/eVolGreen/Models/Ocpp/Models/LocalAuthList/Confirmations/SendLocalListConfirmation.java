package com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Confirmations.Enums.UpdateStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request.SendLocalListRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central en respuesta a una {@link SendLocalListRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 * El campo {@code status} indica si el Punto de Carga ha recibido y aplicado con éxito la actualización de la lista local de autorizaciones.
 *
 * <b>Campos:</b>
 * - {@code status}: El estado de la actualización de la lista de autorizaciones local (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     SendLocalListConfirmation confirmacion = new SendLocalListConfirmation(UpdateStatus.ACCEPTED);
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
public class SendLocalListConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private UpdateStatus status;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #SendLocalListConfirmation(UpdateStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public SendLocalListConfirmation() {}

    /**
     * Construye una nueva SendLocalListConfirmation con el estado especificado.
     *
     * @param status el estado de la actualización; no debe ser nulo.
     */
    public SendLocalListConfirmation(UpdateStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la actualización de la lista de autorizaciones local.
     *
     * @return {@link UpdateStatus}, el estado de la actualización.
     */
    public UpdateStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la actualización de la lista de autorizaciones local.
     *
     * @param status {@link UpdateStatus}, el estado de la actualización.
     * @throws IllegalArgumentException si el status es nulo.
     */
    @XmlElement
    public void setStatus(UpdateStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado de actualización no puede ser nulo");
        }
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
        SendLocalListConfirmation that = (SendLocalListConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "SendLocalListConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}