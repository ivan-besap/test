package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa una solicitud para reservar un conector en una estación de carga en OCPP 1.6.
 * Esta solicitud es enviada desde el Sistema Central al Punto de Carga.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: Identificador del conector a reservar (obligatorio).
 * - {@code expiryDate}: Fecha y hora de expiración de la reserva (obligatorio).
 * - {@code idTag}: Identificador para el cual se debe reservar el conector (obligatorio).
 * - {@code parentIdTag}: Identificador padre (opcional).
 * - {@code reservationId}: Identificador único para esta reserva (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ZonedDateTime expiryDate = ZonedDateTime.now().plusHours(2);
 *     ReserveNowRequest solicitud = new ReserveNowRequest(1, expiryDate, "TAG001", 12345);
 *     solicitud.setParentIdTag("PARENT001");
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
@XmlType(propOrder = {"connectorId", "expiryDate", "idTag", "parentIdTag", "reservationId"})
public class ReserveNowRequest extends RequestWithId {

    private static final int ID_TAG_MAX_LENGTH = 20;
    private static final String ERROR_MESSAGE = "Excedido el límite de " + ID_TAG_MAX_LENGTH + " caracteres";

    @Min(value = 0, message = "connectorId debe ser >= 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "La fecha de expiración es obligatoria")
    @JsonProperty("expiryDate")
    private ZonedDateTime expiryDate;

    @NotNull(message = "El idTag es obligatorio")
    @Size(max = ID_TAG_MAX_LENGTH, message = "El idTag no puede exceder los " + ID_TAG_MAX_LENGTH + " caracteres")
    @JsonProperty("idTag")
    private String idTag;

    @Size(max = ID_TAG_MAX_LENGTH, message = "El parentIdTag no puede exceder los " + ID_TAG_MAX_LENGTH + " caracteres")
    @JsonProperty("parentIdTag")
    private String parentIdTag;

    @NotNull(message = "El reservationId es obligatorio")
    @JsonProperty("reservationId")
    private Integer reservationId;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #ReserveNowRequest(Integer, ZonedDateTime, String, Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ReserveNowRequest() {}

    /**
     * Construye una nueva instancia de ReserveNowRequest con los campos requeridos.
     *
     * @param connectorId Identificador del conector a reservar.
     * @param expiryDate Fecha y hora de expiración de la reserva.
     * @param idTag Identificador para el cual se debe reservar el conector.
     * @param reservationId Identificador único para esta reserva.
     */
    public ReserveNowRequest(Integer connectorId, ZonedDateTime expiryDate, String idTag, Integer reservationId) {
        setConnectorId(connectorId);
        setExpiryDate(expiryDate);
        setIdTag(idTag);
        setReservationId(reservationId);
    }

    // Getters y setters con sus respectivas anotaciones y validaciones

    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId < 0) {
            throw new IllegalArgumentException("connectorId debe ser >= 0");
        }
        this.connectorId = connectorId;
    }

    @XmlElement
    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @XmlElement
    public void setIdTag(String idTag) {
        if (idTag != null && idTag.length() > ID_TAG_MAX_LENGTH) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.idTag = idTag;
    }

    @XmlElement
    public void setParentIdTag(String parentIdTag) {
        if (parentIdTag != null && parentIdTag.length() > ID_TAG_MAX_LENGTH) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.parentIdTag = parentIdTag;
    }

    @XmlElement
    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    // Los getters se mantienen sin cambios

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return connectorId != null && connectorId >= 0
                && expiryDate != null
                && idTag != null && !idTag.isEmpty() && idTag.length() <= ID_TAG_MAX_LENGTH
                && reservationId != null;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que esta solicitud no está directamente relacionada con una transacción.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReserveNowRequest that = (ReserveNowRequest) o;
        return Objects.equals(connectorId, that.connectorId)
                && Objects.equals(expiryDate, that.expiryDate)
                && Objects.equals(idTag, that.idTag)
                && Objects.equals(parentIdTag, that.parentIdTag)
                && Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, expiryDate, idTag, parentIdTag, reservationId);
    }

    @Override
    public String toString() {
        return "ReserveNowRequest{" +
                "connectorId=" + connectorId +
                ", expiryDate=" + expiryDate +
                ", idTag='" + idTag + '\'' +
                ", parentIdTag='" + parentIdTag + '\'' +
                ", reservationId=" + reservationId +
                ", isValid=" + validate() +
                '}';
    }
}