package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.RegistrationStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Respuesta a la solicitud de notificación de arranque.
 *
 * <p>Se envía desde el CSMS (Sistema Central de Gestión de Carga) en respuesta a una solicitud de
 * BootNotification enviada por una estación de carga.
 */
public class BootNotificationResponse extends Confirmation {

    /** Datos personalizados asociados con la respuesta. */
    @Nullable
    private CustomData customData;

    /** Hora actual del CSMS. */
    private ZonedDateTime currentTime;

    /**
     * Intervalo de latidos (heartbeat) en segundos.
     *
     * <p>Cuando el estado es "Accepted", este campo contiene el intervalo en segundos entre los
     * mensajes de latidos de la estación de carga. Si el estado no es "Accepted", este valor indica
     * el tiempo mínimo de espera antes de enviar otra solicitud de BootNotification.
     */
    private Integer interval;

    /** Estado del registro de la estación de carga en el CSMS. */
    private RegistrationStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor de la clase BootNotificationResponse.
     *
     * @param currentTime Hora actual del CSMS.
     * @param interval Intervalo de latidos (heartbeat) en segundos.
     * @param status Estado del registro de la estación de carga.
     */
    public BootNotificationResponse(
            ZonedDateTime currentTime, Integer interval, RegistrationStatusEnum status) {
        setCurrentTime(currentTime);
        setInterval(interval);
        setStatus(status);
    }

    /** @return Datos personalizados asociados con la respuesta. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados con la respuesta.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /** Valida si los datos personalizados son válidos. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    public BootNotificationResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return Hora actual del CSMS. */
    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Establece la hora actual del CSMS.
     *
     * @param currentTime Hora actual.
     */
    public void setCurrentTime(ZonedDateTime currentTime) {
        if (!isValidCurrentTime(currentTime)) {
            throw new PropertyConstraintException(currentTime, "La hora actual no es válida");
        }
        this.currentTime = currentTime;
    }

    /** Valida si la hora actual es válida. */
    private boolean isValidCurrentTime(ZonedDateTime currentTime) {
        return currentTime != null;
    }

    /** @return Intervalo de latidos (heartbeat) en segundos. */
    public Integer getInterval() {
        return interval;
    }

    /**
     * Establece el intervalo de latidos (heartbeat) en segundos.
     *
     * @param interval Intervalo en segundos.
     */
    public void setInterval(Integer interval) {
        if (!isValidInterval(interval)) {
            throw new PropertyConstraintException(interval, "El intervalo no es válido");
        }
        this.interval = interval;
    }

    /** Valida si el intervalo es válido. */
    private boolean isValidInterval(Integer interval) {
        return interval != null;
    }

    /** @return Estado del registro de la estación de carga. */
    public RegistrationStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del registro de la estación de carga.
     *
     * @param status Estado del registro.
     */
    public void setStatus(RegistrationStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido");
        }
        this.status = status;
    }

    /** Valida si el estado es válido. */
    private boolean isValidStatus(RegistrationStatusEnum status) {
        return status != null;
    }

    /** @return Información adicional sobre el estado. */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida");
        }
        this.statusInfo = statusInfo;
    }

    /** Valida si la información adicional sobre el estado es válida. */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    public BootNotificationResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida si todos los datos de la respuesta son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidCurrentTime(currentTime)
                && isValidInterval(interval)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BootNotificationResponse that = (BootNotificationResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(currentTime, that.currentTime)
                && Objects.equals(interval, that.interval)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, currentTime, interval, status, statusInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("currentTime", currentTime)
                .add("interval", interval)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("isValid", validate())
                .toString();
    }
}
