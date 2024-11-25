package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.Enums.FirmwareStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa la solicitud enviada por el Punto de Carga al Sistema Central que notifica sobre el estado del firmware.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code status}: Estado actual del firmware (obligatorio).
 *
 * <b>Posibles valores de estado:</b>
 * <ul>
 *   <li>{@code Downloaded} - El firmware ha sido descargado con éxito.</li>
 *   <li>{@code DownloadFailed} - La descarga del firmware ha fallado.</li>
 *   <li>{@code Downloading} - El firmware está en proceso de descarga.</li>
 *   <li>{@code Idle} - El punto de carga está inactivo en cuanto al firmware.</li>
 *   <li>{@code InstallationFailed} - La instalación del firmware ha fallado.</li>
 *   <li>{@code Installing} - El firmware está siendo instalado.</li>
 *   <li>{@code Installed} - El firmware ha sido instalado con éxito.</li>
 * </ul>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     FirmwareStatusNotificationRequest solicitud = new FirmwareStatusNotificationRequest(FirmwareStatus.Installed);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Sistema Central
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class FirmwareStatusNotificationRequest extends RequestWithId {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private FirmwareStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #FirmwareStatusNotificationRequest(FirmwareStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public FirmwareStatusNotificationRequest() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado del firmware. No puede ser nulo.
     */
    public FirmwareStatusNotificationRequest(FirmwareStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado actual del firmware.
     *
     * @return Estado del firmware.
     */
    public FirmwareStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado del firmware.
     *
     * @param status Estado del firmware. No puede ser nulo.
     * @throws IllegalArgumentException si el status es nulo.
     */
    @XmlElement
    public void setStatus(FirmwareStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("El estado del firmware no puede ser nulo");
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
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirmwareStatusNotificationRequest that = (FirmwareStatusNotificationRequest) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "FirmwareStatusNotificationRequest{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}