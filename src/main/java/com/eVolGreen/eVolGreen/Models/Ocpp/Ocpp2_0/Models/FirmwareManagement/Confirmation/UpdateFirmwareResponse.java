package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.Enums.UpdateFirmwareStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * UpdateFirmwareResponse
 *
 * <p>Clase que representa la respuesta de una estación de carga a una solicitud de actualización de firmware.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class UpdateFirmwareResponse extends Confirmation {

    /** Datos personalizados adicionales (opcional). */
    @Nullable
    private CustomData customData;

    /**
     * Estado que indica si la estación de carga pudo aceptar la solicitud de actualización de firmware.
     */
    private UpdateFirmwareStatusEnum status;

    /**
     * Información adicional sobre el estado (opcional).
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor principal para la clase UpdateFirmwareResponse.
     *
     * @param status Indica si la estación de carga aceptó la solicitud.
     */
    public UpdateFirmwareResponse(UpdateFirmwareStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene datos personalizados adicionales.
     *
     * @return Datos personalizados (opcional).
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados adicionales.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia actualizada.
     */
    public UpdateFirmwareResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud de actualización.
     *
     * @return Estado de la solicitud.
     */
    public UpdateFirmwareStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de actualización.
     *
     * @param status Estado de la solicitud.
     */
    public void setStatus(UpdateFirmwareStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(UpdateFirmwareStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado (opcional).
     *
     * @return Información adicional.
     */
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
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional.
     * @return Esta instancia actualizada.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional.
     * @return Esta instancia actualizada.
     */
    public UpdateFirmwareResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida si todos los datos de la respuesta son válidos.
     *
     * @return {@code true} si los datos son válidos.
     */
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
        UpdateFirmwareResponse that = (UpdateFirmwareResponse) o;
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
