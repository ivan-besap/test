package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums.ChargingProfileStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la solicitud de establecer un perfil de carga.
 *
 * <p>Indica si la estación de carga pudo procesar la solicitud exitosamente. No garantiza que
 * el programa de carga se siga exactamente, ya que pueden existir otras restricciones que la
 * estación de carga deba considerar.
 */
public class SetChargingProfileResponse extends Confirmation {
    /** Datos personalizados adicionales (opcional). */
    @Nullable
    private CustomData customData;

    /**
     * Indica si la estación de carga pudo procesar la solicitud exitosamente. Esto no garantiza
     * que el programa de carga se siga exactamente, ya que pueden existir restricciones adicionales.
     */
    private ChargingProfileStatusEnum status;

    /** Información adicional sobre el estado (opcional). */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor de la clase SetChargingProfileResponse.
     *
     * @param status Indica si la estación de carga pudo procesar la solicitud exitosamente.
     */
    public SetChargingProfileResponse(ChargingProfileStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura los datos personalizados y devuelve la instancia actual.
     *
     * @param customData Datos personalizados adicionales.
     * @return La instancia actual de la respuesta.
     */
    public SetChargingProfileResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la solicitud fue procesada exitosamente.
     *
     * @return Estado de la solicitud.
     */
    public ChargingProfileStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la solicitud fue procesada exitosamente.
     *
     * @param status Estado de la solicitud.
     */
    public void setStatus(ChargingProfileStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado es correcto.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(ChargingProfileStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional del estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional del estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información del estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es correcta.
     *
     * @param statusInfo Información adicional del estado a validar.
     * @return {@code true} si la información es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Configura información adicional sobre el estado y devuelve la instancia actual.
     *
     * @param statusInfo Información adicional del estado.
     * @return La instancia actual de la respuesta.
     */
    public SetChargingProfileResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        SetChargingProfileResponse that = (SetChargingProfileResponse) o;
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
