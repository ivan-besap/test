package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.Enums.ChargingRateUnitType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa la solicitud enviada por el Sistema Central al Punto de Carga para obtener un horario de carga compuesto.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: Identificador del conector para el cual se solicita el horario (obligatorio).
 * - {@code duration}: Duración solicitada del horario en segundos (obligatorio).
 * - {@code chargingRateUnit}: Unidad de tasa de carga (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     GetCompositeScheduleRequest request = new GetCompositeScheduleRequest(1, 3600);
 *     request.setChargingRateUnit(ChargingRateUnitType.W);
 *
 *     if (request.validate()) {
 *         System.out.println("Solicitud válida: " + request);
 *         // Enviar la solicitud al Punto de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class GetCompositeScheduleRequest extends RequestWithId {

    @NotNull(message = "El campo connectorId es obligatorio")
    @Min(value = 0, message = "El connectorId debe ser mayor o igual a 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "El campo duration es obligatorio")
    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("chargingRateUnit")
    private ChargingRateUnitType chargingRateUnit;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #GetCompositeScheduleRequest(Integer, Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public GetCompositeScheduleRequest() {}

    /**
     * Constructor con los campos obligatorios.
     *
     * @param connectorId Identificador del conector. Debe ser mayor o igual a 0.
     * @param duration Duración del horario solicitada en segundos.
     */
    public GetCompositeScheduleRequest(Integer connectorId, Integer duration) {
        setConnectorId(connectorId);
        setDuration(duration);
    }

    /**
     * Obtiene el identificador del conector.
     *
     * @return Identificador del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el identificador del conector.
     *
     * @param connectorId Identificador del conector. Debe ser mayor o igual a 0.
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
     * Obtiene la duración del horario solicitada.
     *
     * @return Duración en segundos.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Establece la duración del horario solicitada.
     *
     * @param duration Duración en segundos.
     */
    @XmlElement
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Obtiene la unidad de tasa de carga.
     *
     * @return Unidad de tasa de carga.
     */
    public ChargingRateUnitType getChargingRateUnit() {
        return chargingRateUnit;
    }

    /**
     * Establece la unidad de tasa de carga.
     *
     * @param chargingRateUnit Unidad de tasa de carga.
     */
    @XmlElement
    public void setChargingRateUnit(ChargingRateUnitType chargingRateUnit) {
        this.chargingRateUnit = chargingRateUnit;
    }

    /**
     * Valida que los campos obligatorios estén presentes y sean válidos.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return connectorId != null && connectorId >= 0 && duration != null;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCompositeScheduleRequest that = (GetCompositeScheduleRequest) o;
        return Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(duration, that.duration) &&
                chargingRateUnit == that.chargingRateUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, duration, chargingRateUnit);
    }

    @Override
    public String toString() {
        return "GetCompositeScheduleRequest{" +
                "connectorId=" + connectorId +
                ", duration=" + duration +
                ", chargingRateUnit=" + chargingRateUnit +
                ", isValid=" + validate() +
                '}';
    }
}