package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.Enums.NotifyEVChargingNeedsStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la notificación de necesidades de carga del vehículo eléctrico.
 *
 * <p>Esta clase representa la respuesta del sistema de gestión de la estación de carga (CSMS)
 * ante una notificación de las necesidades de carga de un vehículo eléctrico.
 */
public class NotifyEVChargingNeedsResponse extends Confirmation {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Estado del procesamiento de la notificación.
     *
     * <p>Indica si el CSMS pudo procesar el mensaje exitosamente. No implica que las necesidades
     * de carga puedan ser cumplidas con el perfil de carga actual.
     */
    private NotifyEVChargingNeedsStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase NotifyEVChargingNeedsResponse.
     *
     * @param status Estado del procesamiento de la notificación.
     */
    public NotifyEVChargingNeedsResponse(NotifyEVChargingNeedsStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public NotifyEVChargingNeedsResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del procesamiento de la notificación.
     *
     * @return Estado del procesamiento de la notificación.
     */
    public NotifyEVChargingNeedsStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del procesamiento de la notificación.
     *
     * @param status Estado del procesamiento de la notificación.
     */
    public void setStatus(NotifyEVChargingNeedsStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado proporcionado es inválido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(NotifyEVChargingNeedsStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información del estado es inválida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información del estado es válida.
     *
     * @param statusInfo Información del estado a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public NotifyEVChargingNeedsResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyEVChargingNeedsResponse that = (NotifyEVChargingNeedsResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("isValid", validate())
                .toString();
    }

}
