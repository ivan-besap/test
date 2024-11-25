package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.UnlockConnectorConfirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud enviada desde el Sistema Central al Punto de Carga para desbloquear un conector.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <p>Esta solicitud se utiliza para desbloquear remotamente un conector específico en una estación de carga.
 * El campo {@code connectorId} identifica el conector que debe ser desbloqueado.</p>
 *
 * <ul>
 *   <li>Los valores válidos para {@code connectorId} deben ser mayores que cero para referenciar un conector específico.</li>
 * </ul>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     UnlockConnectorRequest solicitud = new UnlockConnectorRequest(1);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al punto de carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 *
 * @see UnlockConnectorConfirmation
 */
@XmlRootElement
public class UnlockConnectorRequest extends RequestWithId {

    @NotNull(message = "El campo connectorId es obligatorio")
    @Positive(message = "connectorId debe ser mayor que 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    /**
     * Constructor por defecto.
     * @deprecated use {@link #UnlockConnectorRequest(Integer)} para asegurar que se establezcan los campos requeridos
     */
    @Deprecated
    public UnlockConnectorRequest() {}

    /**
     * Construye una nueva UnlockConnectorRequest con el ID del conector especificado.
     *
     * @param connectorId el ID del conector que debe ser desbloqueado; debe ser mayor que cero.
     */
    public UnlockConnectorRequest(Integer connectorId) {
        setConnectorId(connectorId);
    }

    /**
     * Devuelve el ID del conector que debe ser desbloqueado.
     *
     * @return el ID del conector
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el ID del conector que debe ser desbloqueado.
     *
     * @param connectorId el ID del conector a establecer; debe ser mayor que cero.
     * @throws IllegalArgumentException si connectorId es nulo o menor o igual a cero.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId == null || connectorId <= 0) {
            throw new IllegalArgumentException("connectorId debe ser mayor que 0");
        }
        this.connectorId = connectorId;
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

    /**
     * Valida la solicitud para asegurar que {@code connectorId} no sea nulo y sea mayor que cero.
     *
     * @return {@code true} si la solicitud es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return connectorId != null && connectorId > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnlockConnectorRequest that = (UnlockConnectorRequest) o;
        return Objects.equals(connectorId, that.connectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId);
    }

    @Override
    public String toString() {
        return "UnlockConnectorRequest{" +
                "connectorId=" + connectorId +
                ", isValid=" + validate() +
                '}';
    }
}