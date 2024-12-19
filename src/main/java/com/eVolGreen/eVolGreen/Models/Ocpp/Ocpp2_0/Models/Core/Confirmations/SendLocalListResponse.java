package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.SendLocalListStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la actualización de la lista local de autorizaciones.
 *
 * <p>Este mensaje se envía en respuesta a un mensaje de solicitud para actualizar la lista local de autorizaciones en una estación de carga.
 */
public class SendLocalListResponse extends Confirmation {

    /** Información personalizada asociada a la respuesta. */
    @Nullable
    private CustomData customData;

    /**
     * Indica si la estación de carga ha recibido y aplicado correctamente la actualización de la
     * lista local de autorizaciones.
     */
    private SendLocalListStatusEnum status;

    /** Información adicional sobre el estado de la operación. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase SendLocalListResponse.
     *
     * @param status Indica si la estación de carga ha recibido y aplicado correctamente la
     *               actualización de la lista local de autorizaciones.
     */
    public SendLocalListResponse(SendLocalListStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene la información personalizada asociada a la respuesta.
     *
     * @return Información personalizada o {@code null} si no está definida.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece información personalizada para la respuesta.
     *
     * @param customData Información personalizada a asociar.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "La información personalizada no es válida.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si la información personalizada es válida.
     *
     * @param customData Información personalizada a validar.
     * @return {@code true} si es válida, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega información personalizada a la respuesta.
     *
     * @param customData Información personalizada a agregar.
     * @return Esta instancia de la clase para encadenar métodos.
     */
    public SendLocalListResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la operación de actualización de la lista local.
     *
     * @return Estado de la operación.
     */
    public SendLocalListStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la operación de actualización de la lista local.
     *
     * @param status Estado de la operación.
     */
    public void setStatus(SendLocalListStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado de la operación es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido, {@code false} de lo contrario.
     */
    private boolean isValidStatus(SendLocalListStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la operación.
     *
     * @return Información adicional o {@code null} si no está definida.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la operación.
     *
     * @param statusInfo Información adicional a asociar.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional del estado es válida.
     *
     * @param statusInfo Información adicional a validar.
     * @return {@code true} si es válida, {@code false} de lo contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado de la operación.
     *
     * @param statusInfo Información adicional a agregar.
     * @return Esta instancia de la clase para encadenar métodos.
     */
    public SendLocalListResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        SendLocalListResponse that = (SendLocalListResponse) o;
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
