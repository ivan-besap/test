package com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.Confirmations.Enums.GetCompositeScheduleStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.Request.GetCompositeScheduleRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Punto de Carga al Sistema Central en respuesta a una {@link GetCompositeScheduleRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code status}: Estado de la solicitud (obligatorio).
 * - {@code connectorId}: ID del conector (opcional).
 * - {@code scheduleStart}: Inicio del horario planificado (opcional).
 * - {@code chargingSchedule}: Horario de carga compuesto planificado (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     GetCompositeScheduleConfirmation confirmation = new GetCompositeScheduleConfirmation(GetCompositeScheduleStatus.Accepted);
 *     confirmation.setConnectorId(1);
 *     confirmation.setScheduleStart(ZonedDateTime.now());
 *     confirmation.setChargingSchedule(new ChargingSchedule());
 *
 *     if (confirmation.validate()) {
 *         System.out.println("Confirmación válida: " + confirmation);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class GetCompositeScheduleConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private GetCompositeScheduleStatus status;

    @JsonProperty("connectorId")
    private Integer connectorId;

    @JsonProperty("scheduleStart")
    private ZonedDateTime scheduleStart;

    @JsonProperty("chargingSchedule")
    private ChargingSchedule chargingSchedule;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #GetCompositeScheduleConfirmation(GetCompositeScheduleStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public GetCompositeScheduleConfirmation() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado de la solicitud. No puede ser nulo.
     */
    public GetCompositeScheduleConfirmation(GetCompositeScheduleStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la solicitud.
     *
     * @return Estado de la solicitud.
     */
    public GetCompositeScheduleStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud.
     *
     * @param status Estado de la solicitud. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(GetCompositeScheduleStatus status) {
        this.status = status;
    }

    /**
     * Obtiene el ID del conector.
     *
     * @return ID del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el ID del conector.
     *
     * @param connectorId ID del conector.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }

    /**
     * Obtiene el inicio del horario planificado.
     *
     * @return Inicio del horario planificado.
     */
    public ZonedDateTime getScheduleStart() {
        return scheduleStart;
    }

    /**
     * Establece el inicio del horario planificado.
     *
     * @param scheduleStart Inicio del horario planificado.
     */
    @XmlElement
    public void setScheduleStart(ZonedDateTime scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    /**
     * Obtiene el horario de carga compuesto planificado.
     *
     * @return Horario de carga compuesto planificado.
     */
    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }

    /**
     * Establece el horario de carga compuesto planificado.
     *
     * @param chargingSchedule Horario de carga compuesto planificado.
     */
    @XmlElement
    public void setChargingSchedule(ChargingSchedule chargingSchedule) {
        this.chargingSchedule = chargingSchedule;
    }

    /**
     * Valida que el campo obligatorio status esté presente.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetCompositeScheduleConfirmation that = (GetCompositeScheduleConfirmation) o;
        return status == that.status &&
                Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(scheduleStart, that.scheduleStart) &&
                Objects.equals(chargingSchedule, that.chargingSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, connectorId, scheduleStart, chargingSchedule);
    }

    @Override
    public String toString() {
        return "GetCompositeScheduleConfirmation{" +
                "status=" + status +
                ", connectorId=" + connectorId +
                ", scheduleStart=" + scheduleStart +
                ", chargingSchedule=" + chargingSchedule +
                ", isValid=" + validate() +
                '}';
    }
}