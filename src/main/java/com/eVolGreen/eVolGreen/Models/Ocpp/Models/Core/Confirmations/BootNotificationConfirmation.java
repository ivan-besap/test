package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.RegistrationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Confirmación para BootNotification en OCPP 1.6.
 * Enviada por el sistema central a la estación de carga en respuesta a una solicitud de BootNotification.
 * Contiene información sobre el estado de registro de la estación, la hora actual del sistema central
 * y el intervalo de heartbeat.
 */
@XmlRootElement(name = "bootNotificationResponse")
@XmlType(propOrder = {"status", "currentTime", "interval"})
public class BootNotificationConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    private RegistrationStatus status;

    @NotNull(message = "La hora actual es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "UTC")
    private ZonedDateTime currentTime;

    @NotNull(message = "El intervalo es obligatorio")
    private Integer interval;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #BootNotificationConfirmation(ZonedDateTime, Integer, RegistrationStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public BootNotificationConfirmation() {}

    /**
     * Constructor con campos requeridos.
     *
     * @param currentTime Hora actual del sistema central.
     * @param interval Intervalo de heartbeat en segundos. Valor mínimo 0.
     * @param status Estado de registro de la estación de carga.
     */
    public BootNotificationConfirmation(ZonedDateTime currentTime, Integer interval, RegistrationStatus status) {
        setCurrentTime(currentTime);
        setInterval(interval);
        setStatus(status);
    }

    /**
     * Obtiene el estado de registro de la estación de carga.
     *
     * @return Estado de registro como {@link RegistrationStatus}.
     */
    public RegistrationStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de registro de la estación de carga.
     *
     * @param status Estado de registro de la estación de carga.
     */
    @XmlElement
    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    /**
     * Obtiene la hora actual del sistema central.
     *
     * @return Hora actual como {@link ZonedDateTime}.
     */
    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Establece la hora actual del sistema central.
     *
     * @param currentTime Hora actual del sistema central.
     */
    @XmlElement
    public void setCurrentTime(ZonedDateTime currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Obtiene el intervalo de heartbeat.
     *
     * @return Intervalo de heartbeat en segundos.
     */
    public Integer getInterval() {
        return interval;
    }

    /**
     * Establece el intervalo de heartbeat.
     * Cuando el RegistrationStatus es Accepted, este es el intervalo de heartbeat en segundos.
     * Si el sistema central devuelve un estado diferente a Accepted, este valor indica el tiempo
     * mínimo de espera antes de enviar la siguiente solicitud de BootNotification.
     *
     * @param interval Intervalo de heartbeat en segundos. Debe ser un valor no negativo.
     * @throws IllegalArgumentException si el intervalo es negativo.
     */
    @XmlElement
    public void setInterval(Integer interval) {
        if (interval < 0) {
            throw new IllegalArgumentException("El intervalo debe ser un número no negativo");
        }
        this.interval = interval;
    }

    /**
     * Valida que todos los campos requeridos estén presentes y sean válidos.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null && currentTime != null && interval != null && interval >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BootNotificationConfirmation that = (BootNotificationConfirmation) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(currentTime, that.currentTime) &&
                Objects.equals(interval, that.interval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, currentTime, interval);
    }

    @Override
    public String toString() {
        return "BootNotificationConfirmation{" +
                "status=" + status +
                ", currentTime=" + currentTime +
                ", interval=" + interval +
                ", isValid=" + validate() +
                '}';
    }
}