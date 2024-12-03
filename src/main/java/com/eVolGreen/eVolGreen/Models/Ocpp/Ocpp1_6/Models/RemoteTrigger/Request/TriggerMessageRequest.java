package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.Enums.TriggerMessageRequestType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa la solicitud enviada desde el Sistema Central al Punto de Carga para disparar un mensaje específico.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <p>El campo obligatorio {@code requestedMessage} indica el tipo de mensaje que se quiere disparar.
 * El campo opcional {@code connectorId} identifica el conector asociado.</p>
 *
 * <p>Los valores posibles de {@code requestedMessage} incluyen:
 * <ul>
 *   <li>{@code BootNotification}</li>
 *   <li>{@code DiagnosticsStatusNotification}</li>
 *   <li>{@code FirmwareStatusNotification}</li>
 *   <li>{@code Heartbeat}</li>
 *   <li>{@code MeterValues}</li>
 *   <li>{@code StatusNotification}</li>
 * </ul>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     TriggerMessageRequest solicitud = new TriggerMessageRequest(TriggerMessageRequestType.BootNotification);
 *     solicitud.setConnectorId(1);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al punto de carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
@XmlType(propOrder = {"requestedMessage", "connectorId"})
public class TriggerMessageRequest implements Request {

    @NotNull(message = "El campo requestedMessage es obligatorio")
    @JsonProperty("requestedMessage")
    private TriggerMessageRequestType requestedMessage;

    @Positive(message = "connectorId debe ser mayor que 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    /**
     * Constructor por defecto.
     * @deprecated use {@link #TriggerMessageRequest(TriggerMessageRequestType)} para asegurar que se establezcan los campos requeridos
     */
    @Deprecated
    public TriggerMessageRequest() {}

    /**
     * Construye una nueva solicitud TriggerMessageRequest con el tipo de mensaje especificado.
     *
     * @param requestedMessage el tipo de mensaje que se desea disparar; no debe ser nulo.
     */
    public TriggerMessageRequest(TriggerMessageRequestType requestedMessage) {
        this.requestedMessage = requestedMessage;
    }

    /**
     * Obtiene el tipo de mensaje solicitado.
     *
     * @return el tipo de mensaje solicitado.
     */
    public TriggerMessageRequestType getRequestedMessage() {
        return requestedMessage;
    }

    /**
     * Establece el tipo de mensaje solicitado.
     *
     * @param requestedMessage el tipo de mensaje solicitado; no debe ser nulo.
     */
    @XmlElement
    public void setRequestedMessage(TriggerMessageRequestType requestedMessage) {
        this.requestedMessage = requestedMessage;
    }

    /**
     * Obtiene el ID del conector asociado a esta solicitud.
     *
     * @return el ID del conector, o null si no se ha especificado.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el ID del conector asociado a esta solicitud.
     *
     * @param connectorId el ID del conector; debe ser mayor que 0 si se especifica.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId != null && connectorId <= 0) {
            throw new IllegalArgumentException("connectorId debe ser mayor que 0");
        }
        this.connectorId = connectorId;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    @JsonIgnore
    public String getOcppMessageId() {
        return "";
    }

    @Override
    public void setOcppMessageId(String id) {

    }

    @Override
    @JsonIgnore
    public String getAction() {
        return "TriggerMessage";
    }

    @Override
    @JsonIgnore
    public UUID getSessionIndex() {
        return null;
    }

    /**
     * Valida la solicitud para asegurar que el campo {@code requestedMessage} no sea nulo
     * y que {@code connectorId}, si está presente, sea mayor que 0.
     *
     * @return {@code true} si la solicitud es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return requestedMessage != null && (connectorId == null || connectorId > 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerMessageRequest that = (TriggerMessageRequest) o;
        return Objects.equals(requestedMessage, that.requestedMessage) &&
                Objects.equals(connectorId, that.connectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedMessage, connectorId);
    }

    @Override
    public String toString() {
        return "TriggerMessageRequest{" +
                "requestedMessage=" + requestedMessage +
                ", connectorId=" + connectorId +
                ", isValid=" + validate() +
                '}';
    }
}