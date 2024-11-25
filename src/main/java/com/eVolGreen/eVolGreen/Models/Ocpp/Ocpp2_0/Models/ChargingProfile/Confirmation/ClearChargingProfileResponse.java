package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums.ClearChargingProfileStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al borrado de perfil de carga.
 *
 * Representa la respuesta a la solicitud de borrado de un perfil de carga en una estación de carga.
 */
public class ClearChargingProfileResponse extends Confirmation {

    /**
     * Datos personalizados adicionales para la respuesta.
     */
    @Nullable
    private CustomData customData;

    /**
     * Indica si la estación de carga pudo ejecutar la solicitud de borrado.
     */
    private ClearChargingProfileStatusEnum status;

    /**
     * Información adicional sobre el estado.
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase ClearChargingProfileResponse.
     *
     * @param status Estado de ejecución de la solicitud.
     */
    public ClearChargingProfileResponse(ClearChargingProfileStatusEnum status) {
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
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados adicionales de forma fluida.
     *
     * @param customData Datos personalizados adicionales.
     * @return Instancia actualizada.
     */
    public ClearChargingProfileResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud de borrado.
     *
     * @return Estado de la solicitud.
     */
    public ClearChargingProfileStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de borrado.
     *
     * @param status Estado de la solicitud.
     */
    public void setStatus(ClearChargingProfileStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si el estado es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(ClearChargingProfileStatusEnum status) {
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
            throw new PropertyConstraintException(statusInfo, "Información de estado inválida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return {@code true} si la información es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Establece información adicional sobre el estado de forma fluida.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Instancia actualizada.
     */
    public ClearChargingProfileResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        ClearChargingProfileResponse that = (ClearChargingProfileResponse) o;
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
