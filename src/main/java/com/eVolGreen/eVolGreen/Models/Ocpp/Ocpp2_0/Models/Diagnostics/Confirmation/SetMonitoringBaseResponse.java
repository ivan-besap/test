package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.Enums.GenericDeviceModelStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Clase que representa la respuesta a la solicitud para configurar la base de monitoreo.
 *
 * <p>Esta respuesta indica si la estación de carga fue capaz de procesar y aceptar la solicitud para configurar la base de monitoreo.
 */
public class SetMonitoringBaseResponse extends Confirmation {

    /** Datos personalizados asociados con esta respuesta. */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la estación de carga aceptó la solicitud. */
    private GenericDeviceModelStatusEnum status;

    /** Información adicional sobre el estado del procesamiento. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase SetMonitoringBaseResponse.
     *
     * @param status Estado que indica si la estación de carga aceptó la solicitud.
     */
    public SetMonitoringBaseResponse(GenericDeviceModelStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados con esta respuesta.
     *
     * @return Datos personalizados o {@code null} si no se proporcionaron.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Configura los datos personalizados en esta respuesta.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a esta respuesta.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual para encadenamiento.
     */
    public SetMonitoringBaseResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la estación de carga aceptó la solicitud.
     *
     * @return Estado del procesamiento de la solicitud.
     */
    public GenericDeviceModelStatusEnum getStatus() {
        return status;
    }

    /**
     * Configura el estado que indica si la estación de carga aceptó la solicitud.
     *
     * @param status Estado del procesamiento de la solicitud.
     */
    public void setStatus(GenericDeviceModelStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida el estado de la solicitud.
     *
     * @param status El estado a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(GenericDeviceModelStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado del procesamiento.
     *
     * @return Información adicional sobre el estado o {@code null} si no se proporcionó.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Configura información adicional sobre el estado del procesamiento.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida la información adicional del estado.
     *
     * @param statusInfo La información de estado a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La instancia actual para encadenamiento.
     */
    public SetMonitoringBaseResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        SetMonitoringBaseResponse that = (SetMonitoringBaseResponse) o;
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
