package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.Enums.DisplayMessageStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a una solicitud para mostrar un mensaje en una estación de carga.
 */
public class SetDisplayMessageResponse extends Confirmation {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga puede mostrar el mensaje. */
    private DisplayMessageStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor de la clase SetDisplayMessageResponse.
     *
     * @param status Indica si la estación de carga puede mostrar el mensaje.
     */
    public SetDisplayMessageResponse(DisplayMessageStatusEnum status) {
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return La instancia actual de la respuesta.
     */
    public SetDisplayMessageResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la estación de carga puede mostrar el mensaje.
     *
     * @return Estado de la capacidad de la estación de carga para mostrar el mensaje.
     */
    public DisplayMessageStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la estación de carga puede mostrar el mensaje.
     *
     * @param status Estado de la capacidad de la estación de carga para mostrar el mensaje.
     */
    public void setStatus(DisplayMessageStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(DisplayMessageStatusEnum status) {
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
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información de estado proporcionada es válida.
     *
     * @param statusInfo Información de estado a validar.
     * @return {@code true} si la información es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La instancia actual de la respuesta.
     */
    public SetDisplayMessageResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        SetDisplayMessageResponse that = (SetDisplayMessageResponse) o;
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
