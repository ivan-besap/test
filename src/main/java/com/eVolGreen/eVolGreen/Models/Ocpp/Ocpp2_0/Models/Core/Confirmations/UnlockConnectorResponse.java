package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.UnlockStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * UnlockConnectorResponse
 *
 * <p>Respuesta para la solicitud de desbloqueo de un conector.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class UnlockConnectorResponse extends Confirmation {
    /** Datos personalizados de la respuesta. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga ha desbloqueado el conector. */
    private UnlockStatusEnum status;

    /** Información adicional sobre el estado del desbloqueo. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase UnlockConnectorResponse.
     *
     * @param status Indica si la estación de carga ha desbloqueado el conector.
     */
    public UnlockConnectorResponse(UnlockStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados de la respuesta.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados de la respuesta.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la respuesta.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de UnlockConnectorResponse.
     */
    public UnlockConnectorResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del desbloqueo del conector.
     *
     * @return Estado del desbloqueo.
     */
    public UnlockStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del desbloqueo del conector.
     *
     * @param status Estado del desbloqueo.
     */
    public void setStatus(UnlockStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status es inválido");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(UnlockStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado del desbloqueo.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado del desbloqueo.
     *
     * @param statusInfo Información adicional.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo es inválido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional del estado es válida.
     *
     * @param statusInfo Información a verificar.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado del desbloqueo.
     *
     * @param statusInfo Información adicional.
     * @return Esta instancia de UnlockConnectorResponse.
     */
    public UnlockConnectorResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        UnlockConnectorResponse that = (UnlockConnectorResponse) o;
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
