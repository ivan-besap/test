package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.Types.FirmwareStatusEnumType;


import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de notificación de estado de firmware firmado en OCPP.
 * <p>
 * Esta solicitud notifica el progreso de la instalación del firmware firmado en una estación de carga.
 * </p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SignedFirmwareStatusNotificationRequest request = new SignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType.Downloaded);
 *     request.setRequestId(123);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class SignedFirmwareStatusNotificationRequest extends RequestWithId {

    private FirmwareStatusEnumType status;
    private Integer requestId;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private SignedFirmwareStatusNotificationRequest() {
    }

    /**
     * Maneja los campos obligatorios.
     *
     * @param status Progreso de la instalación del firmware. Ver {@link #setStatus(FirmwareStatusEnumType)}
     */
    public SignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Este campo contiene el estado del progreso de la instalación del firmware.
     *
     * @return {@link FirmwareStatusEnumType}
     */
    public FirmwareStatusEnumType getStatus() {
        return status;
    }

    /**
     * Requerido. Este campo contiene el estado del progreso de la instalación del firmware.
     *
     * @param status {@link FirmwareStatusEnumType}
     */
    public void setStatus(FirmwareStatusEnumType status) {
        this.status = status;
    }

    /**
     * El id de la solicitud que fue proporcionado en la {@link SignedUpdateFirmwareRequest}
     * que inició esta actualización de firmware. Este campo es obligatorio, excepto si el mensaje
     * fue disparado por un {@link TriggerMessageRequest} o {@link ExtendedTriggerMessageRequest} Y no hay actualización en curso.
     *
     * @return Integer
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Opcional. El id de la solicitud que fue proporcionado en la {@link SignedUpdateFirmwareRequest}
     * que inició esta actualización de firmware. Este campo es obligatorio, excepto si el mensaje
     * fue disparado por un {@link TriggerMessageRequest} o {@link ExtendedTriggerMessageRequest} Y no hay actualización en curso.
     *
     * @param requestId Integer
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción. En este caso, no lo está.
     *
     * @return {@code false}, ya que no está relacionada con una transacción.
     */
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

    /**
     * Valida que la solicitud tenga los datos obligatorios.
     *
     * @return {@code true} si el estado del firmware no es nulo, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos solicitudes de notificación de estado de firmware firmado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignedFirmwareStatusNotificationRequest that = (SignedFirmwareStatusNotificationRequest) o;
        return Objects.equals(status, that.status)
                && Objects.equals(requestId, that.requestId);
    }

    /**
     * Genera un código hash único basado en el estado y el id de la solicitud.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status, requestId);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de notificación de estado de firmware firmado.
     *
     * @return una cadena que representa la solicitud y su estado de validación.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("requestId", requestId)
                .add("isValid", validate())
                .toString();
    }
}
