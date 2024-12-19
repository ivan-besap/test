package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Configurations.MQ.CustomZonedDateTimeDeserializer;
import com.eVolGreen.eVolGreen.Configurations.MQ.CustomZonedDateTimeSerializer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ModelUtil;
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
import java.util.Objects;
import java.util.UUID;

/**
 * Representa la solicitud StartTransaction en OCPP 1.6.
 * Enviada por la estación de carga al sistema central para iniciar una transacción.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: Identificador del conector utilizado (obligatorio).
 * - {@code idTag}: Identificador de la etiqueta de autorización (obligatorio).
 * - {@code meterStart}: Valor del medidor al inicio de la transacción (obligatorio).
 * - {@code timestamp}: Fecha y hora de inicio de la transacción (obligatorio).
 * - {@code reservationId}: Identificador de la reserva asociada (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     StartTransactionRequest request = new StartTransactionRequest(
 *         1,
 *         "TAG001",
 *         1000,
 *         ZonedDateTime.now()
 *     );
 *     request.setReservationId(123);
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
@XmlType(propOrder = {"connectorId", "idTag", "timestamp", "meterStart", "reservationId"})
public class StartTransactionRequest extends RequestWithId {

    private static final int IDTAG_MAX_LENGTH = 20;

    @NotNull(message = "connectorId es obligatorio")
    @Positive(message = "connectorId debe ser un número positivo")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "idTag es obligatorio")
    @Size(max = IDTAG_MAX_LENGTH, message = "idTag no puede superar los 20 caracteres")
    @JsonProperty("idTag")
    private String idTag;

    @NotNull(message = "meterStart es obligatorio")
    @Positive(message = "meterStart debe ser un número positivo")
    @JsonProperty("meterStart")
    private Integer meterStart;

    @JsonDeserialize(using = CustomZonedDateTimeDeserializer.class)
    @JsonSerialize(using = CustomZonedDateTimeSerializer.class)
    @JsonProperty("timestamp")
    private ZonedDateTime timestamp;


    @Positive(message = "reservationId debe ser un número positivo")
    @JsonProperty("reservationId")
    private Integer reservationId;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #StartTransactionRequest(Integer, String, Integer, ZonedDateTime)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public StartTransactionRequest() {}

    /**
     * Constructor con campos requeridos.
     *
     * @param connectorId Identificador del conector.
     * @param idTag Identificador de la etiqueta de autorización.
     * @param meterStart Valor del medidor al inicio.
     * @param timestamp Fecha y hora de inicio de la transacción.
     */
    public StartTransactionRequest(Integer connectorId, String idTag, Integer meterStart, ZonedDateTime timestamp) {
        this.setConnectorId(connectorId);
        this.setIdTag(idTag);
        this.setMeterStart(meterStart);
        this.setTimestamp(timestamp);
    }

    // Getters y setters con validación

    public Integer getConnectorId() {
        return connectorId;
    }

    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId == null || connectorId <= 0) {
            throw new IllegalArgumentException("connectorId debe ser mayor a 0");
        }
        this.connectorId = connectorId;
    }

    public String getIdTag() {
        return idTag;
    }

    @XmlElement
    public void setIdTag(String idTag) {
        if (!ModelUtil.validate(idTag, IDTAG_MAX_LENGTH)) {
            throw new IllegalArgumentException("idTag no puede superar los 20 caracteres");
        }
        this.idTag = idTag;
    }

    public Integer getMeterStart() {
        return meterStart;
    }

    @XmlElement
    public void setMeterStart(Integer meterStart) {
        this.meterStart = meterStart;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @XmlElement
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    @XmlElement
    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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
        return connectorId != null && connectorId > 0 &&
                ModelUtil.validate(idTag, IDTAG_MAX_LENGTH) &&
                meterStart != null &&
                timestamp != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartTransactionRequest that = (StartTransactionRequest) o;
        return Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(idTag, that.idTag) &&
                Objects.equals(meterStart, that.meterStart) &&
                Objects.equals(reservationId, that.reservationId) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, idTag, meterStart, reservationId, timestamp);
    }

    @Override
    public String toString() {
        return "StartTransactionRequest{" +
                "connectorId=" + connectorId +
                ", idTag='" + idTag + '\'' +
                ", meterStart=" + meterStart +
                ", timestamp=" + timestamp +
                ", reservationId=" + reservationId +
                ", isValid=" + validate() +
                '}';
    }
}