package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.MeterValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de MeterValues enviada por el Punto de Carga al Sistema Central en OCPP 1.6.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: Identificador del conector (obligatorio).
 * - {@code transactionId}: Identificador de la transacción (opcional).
 * - {@code meterValue}: Lista de valores medidos (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     MeterValue[] values = {new MeterValue(/* ... )};
 *     MeterValuesRequest solicitud = new MeterValuesRequest(1);
 *     solicitud.setMeterValue(values);
 *     solicitud.setTransactionId(123);
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
@XmlType(propOrder = {"connectorId", "transactionId", "meterValue"})
public class MeterValuesRequest extends RequestWithId {

    @NotNull(message = "connectorId es obligatorio")
    @Min(value = 0, message = "connectorId debe ser mayor o igual a 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @JsonProperty("transactionId")
    private Integer transactionId;

    @NotNull(message = "meterValue es obligatorio")
    @JsonProperty("meterValue")
    private MeterValue[] meterValue;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #MeterValuesRequest(Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public MeterValuesRequest() {}

    /**
     * Construye una nueva instancia de MeterValuesRequest con el connectorId especificado.
     *
     * @param connectorId el identificador del conector.
     */
    public MeterValuesRequest(Integer connectorId) {
        setConnectorId(connectorId);
    }

    /**
     * Obtiene el identificador del conector.
     *
     * @return Integer, el identificador del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el identificador del conector.
     *
     * @param connectorId Integer, el identificador del conector. Debe ser mayor o igual a 0.
     * @throws IllegalArgumentException si connectorId es menor que 0.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId != null && connectorId < 0) {
            throw new IllegalArgumentException("connectorId debe ser mayor o igual a 0");
        }
        this.connectorId = connectorId;
    }

    /**
     * Obtiene el identificador de la transacción.
     *
     * @return Integer, el identificador de la transacción.
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción.
     *
     * @param transactionId Integer, el identificador de la transacción.
     */
    @XmlElement
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Obtiene los valores medidos.
     *
     * @return MeterValue[], el array de valores medidos.
     */
    public MeterValue[] getMeterValue() {
        return meterValue;
    }

    /**
     * Establece los valores medidos.
     *
     * @param meterValue MeterValue[], el array de valores medidos.
     */
    @XmlElement
    public void setMeterValue(MeterValue[] meterValue) {
        this.meterValue = meterValue;
    }

    /**
     * Valida que los campos requeridos estén presentes y sean válidos.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        boolean valid = connectorId != null && connectorId >= 0 && meterValue != null && meterValue.length > 0;

        if (valid) {
            for (MeterValue current : meterValue) {
                valid &= (current != null && current.validate());
            }
        }

        return valid;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return true, ya que esta solicitud está relacionada con una transacción.
     */
    @Override
    public boolean transactionRelated() {
        return true;
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
        MeterValuesRequest that = (MeterValuesRequest) o;
        return Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(transactionId, that.transactionId) &&
                Arrays.equals(meterValue, that.meterValue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(connectorId, transactionId);
        result = 31 * result + Arrays.hashCode(meterValue);
        return result;
    }

    @Override
    public String toString() {
        return "MeterValuesRequest{" +
                "connectorId=" + connectorId +
                ", transactionId=" + transactionId +
                ", meterValue=" + Arrays.toString(meterValue) +
                ", isValid=" + validate() +
                '}';
    }
}