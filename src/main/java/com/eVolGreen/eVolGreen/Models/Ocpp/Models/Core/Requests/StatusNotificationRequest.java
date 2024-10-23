package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.ChargePointErrorCode;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.ChargePointStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ModelUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de notificación de estado enviada desde el punto de carga al sistema central.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: ID del conector (obligatorio).
 * - {@code errorCode}: Código de error del punto de carga (obligatorio).
 * - {@code status}: Estado actual del punto de carga (obligatorio).
 * - {@code info}: Información adicional sobre el error (opcional, máx. 50 caracteres).
 * - {@code timestamp}: Momento en que se reporta el estado (opcional).
 * - {@code vendorId}: Identificador del proveedor (opcional, máx. 255 caracteres).
 * - {@code vendorErrorCode}: Código de error específico del proveedor (opcional, máx. 50 caracteres).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     StatusNotificationRequest request = new StatusNotificationRequest(
 *         1,
 *         ChargePointErrorCode.NO_ERROR,
 *         ChargePointStatus.AVAILABLE
 *     );
 *     request.setInfo("Operación normal");
 *     request.setTimestamp(ZonedDateTime.now());
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
@XmlType(propOrder = {"connectorId", "status", "errorCode", "info", "timestamp", "vendorId", "vendorErrorCode"})
public class StatusNotificationRequest extends RequestWithId {

    private static final String ERROR_MESSAGE = "Excede el límite de %s caracteres";

    @NotNull(message = "El id del conector es obligatorio")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "El código de error es obligatorio")
    @JsonProperty("errorCode")
    private ChargePointErrorCode errorCode;

    @Size(max = 50, message = "La información no debe exceder los 50 caracteres")
    @JsonProperty("info")
    private String info;

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private ChargePointStatus status;

    @JsonProperty("timestamp")
    private ZonedDateTime timestamp;

    @Size(max = 255, message = "El vendorId no debe exceder los 255 caracteres")
    @JsonProperty("vendorId")
    private String vendorId;

    @Size(max = 50, message = "El vendorErrorCode no debe exceder los 50 caracteres")
    @JsonProperty("vendorErrorCode")
    private String vendorErrorCode;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #StatusNotificationRequest(Integer, ChargePointErrorCode, ChargePointStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public StatusNotificationRequest() {}

    /**
     * Constructor con campos requeridos.
     *
     * @param connectorId ID del conector.
     * @param errorCode Código de error del punto de carga.
     * @param status Estado actual del punto de carga.
     */
    public StatusNotificationRequest(Integer connectorId, ChargePointErrorCode errorCode, ChargePointStatus status) {
        setConnectorId(connectorId);
        setErrorCode(errorCode);
        setStatus(status);
    }

    // Getters y setters con validación

    public Integer getConnectorId() {
        return connectorId;
    }

    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId == null || connectorId < 0) {
            throw new IllegalArgumentException("connectorId debe ser >= 0");
        }
        this.connectorId = connectorId;
    }

    public ChargePointErrorCode getErrorCode() {
        return errorCode;
    }

    @XmlElement
    public void setErrorCode(ChargePointErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getInfo() {
        return info;
    }

    @XmlElement
    public void setInfo(String info) {
        if (!ModelUtil.validate(info, 50)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, 50));
        }
        this.info = info;
    }

    public ChargePointStatus getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(ChargePointStatus status) {
        this.status = status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @XmlElement
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getVendorId() {
        return vendorId;
    }

    @XmlElement
    public void setVendorId(String vendorId) {
        if (!ModelUtil.validate(vendorId, 255)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, 255));
        }
        this.vendorId = vendorId;
    }

    public String getVendorErrorCode() {
        return vendorErrorCode;
    }

    @XmlElement
    public void setVendorErrorCode(String vendorErrorCode) {
        if (!ModelUtil.validate(vendorErrorCode, 50)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, 50));
        }
        this.vendorErrorCode = vendorErrorCode;
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
    public boolean validate() {
        return connectorId != null && connectorId >= 0 &&
                errorCode != null &&
                status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusNotificationRequest that = (StatusNotificationRequest) o;
        return Objects.equals(connectorId, that.connectorId) &&
                errorCode == that.errorCode &&
                Objects.equals(info, that.info) &&
                status == that.status &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(vendorId, that.vendorId) &&
                Objects.equals(vendorErrorCode, that.vendorErrorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, errorCode, info, status, timestamp, vendorId, vendorErrorCode);
    }

    @Override
    public String toString() {
        return "StatusNotificationRequest{" +
                "connectorId=" + connectorId +
                ", errorCode=" + errorCode +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                ", vendorId='" + vendorId + '\'' +
                ", vendorErrorCode='" + vendorErrorCode + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}