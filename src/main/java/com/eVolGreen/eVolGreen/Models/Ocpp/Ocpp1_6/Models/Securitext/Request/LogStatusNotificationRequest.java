package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.UploadLogStatusEnumType;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para notificar el estado de la carga de un registro.
 * Esta solicitud es enviada por la estación de carga para informar el estado de la subida de un archivo de registro.
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     LogStatusNotificationRequest request = new LogStatusNotificationRequest(UploadLogStatusEnumType.Uploaded);
 *     request.setRequestId(123);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class LogStatusNotificationRequest extends RequestWithId {

    private UploadLogStatusEnumType status;
    private Integer requestId;

    /**
     * Constructor por defecto privado para propósitos de serialización.
     */
    private LogStatusNotificationRequest() {
    }

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param status El estado de la subida del registro. Ver {@link #setStatus(UploadLogStatusEnumType)}.
     */
    public LogStatusNotificationRequest(UploadLogStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Este campo contiene el estado de la carga del registro.
     *
     * @return {@link UploadLogStatusEnumType} El estado actual de la subida del registro.
     */
    public UploadLogStatusEnumType getStatus() {
        return status;
    }

    /**
     * Campo obligatorio. Este campo contiene el estado de la carga del registro.
     *
     * @param status El estado de la carga del registro. {@link UploadLogStatusEnumType}.
     */
    public void setStatus(UploadLogStatusEnumType status) {
        this.status = status;
    }

    /**
     * El id de solicitud que fue proporcionado en el {@link GetLogRequest}
     * que inició esta carga de registro.
     *
     * @return Integer El id de la solicitud original.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Opcional. El id de solicitud que fue proporcionado en el {@link GetLogRequest}
     * que inició esta carga de registro.
     *
     * @param requestId El id de la solicitud original.
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción. En este caso, no lo está.
     *
     * @return {@code false}, ya que no está relacionado con una transacción.
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
     * Valida si la solicitud es válida. El campo {@code status} es obligatorio.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos solicitudes de notificación de estado de registro son equivalentes.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogStatusNotificationRequest that = (LogStatusNotificationRequest) o;
        return Objects.equals(status, that.status)
                && Objects.equals(requestId, that.requestId);
    }

    /**
     * Genera un código hash único para esta solicitud.
     *
     * @return El valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status, requestId);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de notificación de estado de registro.
     *
     * @return Una cadena que representa la solicitud, incluyendo el estado de la carga y el id de solicitud.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("requestId", requestId)
                .add("isValid", validate()).toString();
    }
}
