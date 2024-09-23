package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la solicitud enviada desde el Punto de Carga al Sistema Central para notificar el estado del diagnóstico.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code status}: Estado actual del diagnóstico (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     DiagnosticsStatusNotificationRequest solicitud = new DiagnosticsStatusNotificationRequest(DiagnosticsStatus.Uploading);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Sistema Central
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 *
 * @see DiagnosticsStatus
 */
@XmlRootElement
public class DiagnosticsStatusNotificationRequest extends RequestWithId {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private DiagnosticsStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #DiagnosticsStatusNotificationRequest(DiagnosticsStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public DiagnosticsStatusNotificationRequest() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado del diagnóstico. No puede ser nulo.
     */
    public DiagnosticsStatusNotificationRequest(DiagnosticsStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado actual del diagnóstico.
     *
     * @return Estado del diagnóstico.
     */
    public DiagnosticsStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado del diagnóstico.
     *
     * @param status Estado del diagnóstico. No puede ser nulo.
     * @throws IllegalArgumentException si el status es nulo.
     */
    @XmlElement
    public void setStatus(DiagnosticsStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado del diagnóstico no puede ser nulo");
        }
        this.status = status;
    }

    /**
     * Valida que el campo obligatorio status esté presente.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagnosticsStatusNotificationRequest that = (DiagnosticsStatusNotificationRequest) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "DiagnosticsStatusNotificationRequest{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}