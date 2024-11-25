package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Confirmation.Enums.TriggerMessageStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta de TriggerMessage
 *
 * <p>Clase que representa la respuesta al mensaje TriggerMessage en el protocolo OCPP 2.0.1. Este mensaje indica si la estación de carga enviará la notificación solicitada.
 */
public class TriggerMessageResponse extends Confirmation {
    /** Datos personalizados */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga enviará o no la notificación solicitada. */
    private TriggerMessageStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor para la clase TriggerMessageResponse.
     *
     * @param status Indica si la estación de carga enviará o no la notificación solicitada.
     */
    public TriggerMessageResponse(TriggerMessageStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
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
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Instancia actualizada de {@code TriggerMessageResponse}.
     */
    public TriggerMessageResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de si la estación de carga enviará la notificación solicitada.
     *
     * @return Estado de la notificación solicitada.
     */
    public TriggerMessageStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de si la estación de carga enviará la notificación solicitada.
     *
     * @param status Estado de la notificación solicitada.
     */
    public void setStatus(TriggerMessageStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si el estado es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(TriggerMessageStatusEnum status) {
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
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información adicional a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Instancia actualizada de {@code TriggerMessageResponse}.
     */
    public TriggerMessageResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        TriggerMessageResponse that = (TriggerMessageResponse) o;
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
