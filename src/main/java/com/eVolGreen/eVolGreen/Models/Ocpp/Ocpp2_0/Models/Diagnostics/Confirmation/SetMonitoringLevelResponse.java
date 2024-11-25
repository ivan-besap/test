package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums.GenericStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta a una solicitud de configuración del nivel de monitoreo en la estación de carga.
 *
 * <p>Indica si la estación de carga pudo aceptar la solicitud y proporciona información adicional sobre el estado.
 */
public class SetMonitoringLevelResponse extends Confirmation {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Estado de aceptación de la solicitud por parte de la estación de carga. */
    private GenericStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor de la clase SetMonitoringLevelResponse.
     *
     * @param status Estado de aceptación de la solicitud.
     */
    public SetMonitoringLevelResponse(GenericStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados o {@code null}.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados o {@code null}.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados no válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos; {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia actualizada.
     */
    public SetMonitoringLevelResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de aceptación de la solicitud.
     *
     * @return Estado de aceptación de la solicitud.
     */
    public GenericStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de aceptación de la solicitud.
     *
     * @param status Estado de aceptación de la solicitud.
     */
    public void setStatus(GenericStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "Estado no válido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido; {@code false} en caso contrario.
     */
    private boolean isValidStatus(GenericStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional sobre el estado o {@code null}.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado o {@code null}.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "Información de estado no válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información de estado a validar.
     * @return {@code true} si es válida; {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia actualizada.
     */
    public SetMonitoringLevelResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetMonitoringLevelResponse that = (SetMonitoringLevelResponse) o;
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
