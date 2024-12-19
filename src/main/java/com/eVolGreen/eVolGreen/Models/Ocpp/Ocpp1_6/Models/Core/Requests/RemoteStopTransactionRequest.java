package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud remota para detener una transacción en OCPP 1.6.
 * Esta solicitud es enviada desde el Sistema Central hacia el Punto de Carga para
 * detener una transacción activa.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code transactionId}: Identificador de la transacción a detener (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     RemoteStopTransactionRequest solicitud = new RemoteStopTransactionRequest(123);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Punto de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class RemoteStopTransactionRequest extends RequestWithId {

    @NotNull(message = "transactionId es obligatorio")
    @JsonProperty("transactionId")
    private Integer transactionId;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #RemoteStopTransactionRequest(Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public RemoteStopTransactionRequest() {}

    /**
     * Construye una nueva instancia de RemoteStopTransactionRequest con el transactionId especificado.
     *
     * @param transactionId Identificador de la transacción a detener.
     */
    public RemoteStopTransactionRequest(Integer transactionId) {
        setTransactionId(transactionId);
    }

    /**
     * Obtiene el identificador de la transacción que se solicita detener.
     *
     * @return Integer, el identificador de la transacción.
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción que se solicita detener.
     *
     * @param transactionId Integer, el identificador de la transacción.
     */
    @XmlElement
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return transactionId != null;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que esta solicitud detiene una transacción existente pero no inicia una nueva.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoteStopTransactionRequest that = (RemoteStopTransactionRequest) o;
        return Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        return "RemoteStopTransactionRequest{" +
                "transactionId=" + transactionId +
                ", isValid=" + validate() +
                '}';
    }
}