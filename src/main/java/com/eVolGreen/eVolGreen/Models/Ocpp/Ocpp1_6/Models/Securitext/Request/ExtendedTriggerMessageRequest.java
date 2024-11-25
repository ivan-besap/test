package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.MessageTriggerEnumType;


import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud extendida de TriggerMessage en OCPP 2.0.
 * Esta solicitud es enviada desde el sistema central para desencadenar mensajes en un punto de carga.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     ExtendedTriggerMessageRequest request = new ExtendedTriggerMessageRequest(MessageTriggerEnumType.BootNotification);
 *     request.setConnectorId(1);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class ExtendedTriggerMessageRequest extends RequestWithId {

    private MessageTriggerEnumType requestedMessage;
    private Integer connectorId;

    /**
     * Constructor privado por defecto para fines de serialización.
     */
    private ExtendedTriggerMessageRequest() {
    }

    /**
     * Constructor que inicializa la solicitud con el mensaje requerido.
     *
     * @param requestedMessage Tipo de mensaje a desencadenar. Ver {@link #setRequestedMessage(MessageTriggerEnumType)}.
     */
    public ExtendedTriggerMessageRequest(MessageTriggerEnumType requestedMessage) {
        setRequestedMessage(requestedMessage);
    }

    /**
     * Tipo de mensaje que se solicita desencadenar.
     *
     * @return {@link MessageTriggerEnumType} que representa el tipo de mensaje solicitado.
     */
    public MessageTriggerEnumType getRequestedMessage() {
        return requestedMessage;
    }

    /**
     * Campo obligatorio. Establece el tipo de mensaje que se solicita desencadenar.
     *
     * @param requestedMessage {@link MessageTriggerEnumType} que representa el tipo de mensaje solicitado.
     */
    public void setRequestedMessage(MessageTriggerEnumType requestedMessage) {
        this.requestedMessage = requestedMessage;
    }

    /**
     * Devuelve el ID del conector, si la solicitud se aplica a un conector específico.
     *
     * @return {@code Integer} que representa el ID del conector, debe ser mayor que 0.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Campo opcional. Establece el ID del conector si la solicitud se aplica a un conector específico.
     *
     * @param connectorId ID del conector, debe ser mayor que 0. Si es menor o igual a 0, se lanzará una {@link PropertyConstraintException}.
     */
    public void setConnectorId(Integer connectorId) {
        if (connectorId != null && connectorId <= 0) {
            throw new PropertyConstraintException(connectorId, "El ID del conector debe ser mayor que 0");
        }
        this.connectorId = connectorId;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
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
     * Valida que la solicitud sea válida. Comprueba que el mensaje solicitado no sea nulo y, si el conector está especificado, que su ID sea mayor que 0.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return requestedMessage != null && (connectorId == null || connectorId > 0);
    }

    /**
     * Compara si dos solicitudes de TriggerMessage son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedTriggerMessageRequest that = (ExtendedTriggerMessageRequest) o;
        return Objects.equals(requestedMessage, that.requestedMessage) &&
                Objects.equals(connectorId, that.connectorId);
    }

    /**
     * Genera un código hash único basado en el mensaje solicitado y el ID del conector.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(requestedMessage, connectorId);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de TriggerMessage.
     *
     * @return una cadena que representa la solicitud, incluyendo el mensaje solicitado, el ID del conector y su validez.
     */
    @Override
    public String toString() {
        return "ExtendedTriggerMessageRequest{" +
                "requestedMessage=" + requestedMessage +
                ", connectorId=" + connectorId +
                ", isValid=" + validate() +
                '}';
    }
}
