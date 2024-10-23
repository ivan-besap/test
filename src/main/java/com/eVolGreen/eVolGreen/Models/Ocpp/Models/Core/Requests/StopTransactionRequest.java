package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Configurations.MQ.CustomZonedDateTimeDeserializer;
import com.eVolGreen.eVolGreen.Configurations.MQ.CustomZonedDateTimeSerializer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.StopTransactionReason;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.MeterValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ModelUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para detener una transacción en OCPP 1.6.
 * Enviada por la estación de carga al sistema central.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code idTag}: Identificador que solicitó detener la carga (opcional, máx. 20 caracteres).
 * - {@code meterStop}: Valor del medidor en Wh al final de la transacción (obligatorio).
 * - {@code timestamp}: Fecha y hora en que se detuvo la transacción (obligatorio).
 * - {@code transactionId}: ID de la transacción que se está deteniendo (obligatorio).
 * - {@code reason}: Razón por la que se detuvo la transacción (opcional).
 * - {@code transactionData}: Detalles de uso de la transacción relevantes para la facturación (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     StopTransactionRequest request = new StopTransactionRequest(
 *         1000,
 *         ZonedDateTime.now(),
 *         12345
 *     );
 *     request.setIdTag("TAG001");
 *     request.setReason(StopTransactionReason.LOCAL);
 *
 *     if (request.validate()) {
 *         System.out.println("Solicitud válida: " + request);
 *         // Enviar la solicitud al sistema central
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
@XmlType(propOrder = {"transactionId", "idTag", "timestamp", "meterStop", "reason", "transactionData"})
public class StopTransactionRequest extends RequestWithId {

    private static final int IDTAG_MAX_LENGTH = 20;

    @Size(max = IDTAG_MAX_LENGTH, message = "idTag no puede superar los 20 caracteres")
    @JsonProperty("idTag")
    private String idTag;

    @NotNull(message = "meterStop es obligatorio")
    @Positive(message = "meterStop debe ser un número positivo")
    @JsonProperty("meterStop")
    private Integer meterStop;

    @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
    @JsonSerialize(using = CustomZonedDateTimeSerializer.class)
    @JsonProperty("timestamp")
    private ZonedDateTime timestamp;

    @NotNull(message = "transactionId es obligatorio")
    @Positive(message = "transactionId debe ser un número positivo")
    @JsonProperty("transactionId")
    private Integer transactionId;

    @JsonProperty("reason")
    private StopTransactionReason reason;

    @JsonProperty("transactionData")
    private MeterValue[] transactionData;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #StopTransactionRequest(Integer, ZonedDateTime, Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public StopTransactionRequest() {}

    /**
     * Constructor con campos obligatorios.
     *
     * @param meterStop Valor del medidor en Wh al final de la transacción.
     * @param timestamp Fecha y hora en que se detuvo la transacción.
     * @param transactionId ID de la transacción que se está deteniendo.
     */
    public StopTransactionRequest(Integer meterStop, ZonedDateTime timestamp, Integer transactionId) {
        setMeterStop(meterStop);
        setTimestamp(timestamp);
        setTransactionId(transactionId);
    }

    // Getters y setters con validaciones
    public String getIdTag() {
        return idTag;
    }

    @XmlElement
    public void setIdTag(String idTag) {
        if (idTag != null && !ModelUtil.validate(idTag, IDTAG_MAX_LENGTH)) {
            throw new IllegalArgumentException("idTag no puede superar los 20 caracteres");
        }
        this.idTag = idTag;
    }

    public Integer getMeterStop() {
        return meterStop;
    }

    @XmlElement
    public void setMeterStop(Integer meterStop) {
        this.meterStop = meterStop;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @XmlElement
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    @XmlElement
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public StopTransactionReason getReason() {
        return reason;
    }

    @XmlElement
    public void setReason(StopTransactionReason reason) {
        this.reason = reason;
    }

    public MeterValue[] getTransactionData() {
        return transactionData;
    }

    @XmlElement
    public void setTransactionData(MeterValue[] transactionData) {
        this.transactionData = transactionData;
    }

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
    public boolean validate() {
        boolean valid = meterStop != null && timestamp != null && transactionId != null;
        if (transactionData != null) {
            for (MeterValue meterValue : transactionData) {
                valid &= meterValue.validate();
            }
        }
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopTransactionRequest that = (StopTransactionRequest) o;
        return Objects.equals(idTag, that.idTag) &&
                Objects.equals(meterStop, that.meterStop) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(transactionId, that.transactionId) &&
                reason == that.reason &&
                Arrays.equals(transactionData, that.transactionData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTag, meterStop, timestamp, transactionId, reason, Arrays.hashCode(transactionData));
    }

    @Override
    public String toString() {
        return "StopTransactionRequest{" +
                "idTag='" + idTag + '\'' +
                ", meterStop=" + meterStop +
                ", timestamp=" + timestamp +
                ", transactionId=" + transactionId +
                ", reason=" + reason +
                ", transactionData=" + Arrays.toString(transactionData) +
                ", isValid=" + validate() +
                '}';
    }
}