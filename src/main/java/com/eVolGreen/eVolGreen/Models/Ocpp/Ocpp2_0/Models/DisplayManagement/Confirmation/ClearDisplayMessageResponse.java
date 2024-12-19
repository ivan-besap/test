package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.Enums.ClearMessageStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la solicitud de eliminación de un mensaje mostrado en la estación de carga.
 *
 * Representa la respuesta a una solicitud para eliminar un mensaje específico de la estación
 * de carga, indicando si la operación fue exitosa o no.
 */
public class ClearDisplayMessageResponse extends Confirmation {

    /**
     * Datos personalizados para atributos adicionales.
     */
    @Nullable
    private CustomData customData;

    /**
     * Indica si la estación de carga pudo eliminar el mensaje solicitado.
     */
    private ClearMessageStatusEnum status;

    /**
     * Información adicional sobre el estado del mensaje.
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase ClearDisplayMessageResponse.
     *
     * @param status Indica si la estación de carga pudo eliminar el mensaje solicitado.
     */
    public ClearDisplayMessageResponse(ClearMessageStatusEnum status) {
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de la respuesta.
     */
    public ClearDisplayMessageResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la eliminación del mensaje.
     *
     * @return Estado de la eliminación del mensaje.
     */
    public ClearMessageStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la eliminación del mensaje.
     *
     * @param status Estado de la eliminación del mensaje.
     */
    public void setStatus(ClearMessageStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status Estado de la eliminación del mensaje.
     * @return {@code true} si el estado es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(ClearMessageStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado del mensaje.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado del mensaje.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información del estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return {@code true} si la información es válida, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Establece información adicional sobre el estado de forma fluida.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia de la respuesta.
     */
    public ClearDisplayMessageResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Método de validación para la respuesta.
     *
     * @return {@code true} si la respuesta es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearDisplayMessageResponse that = (ClearDisplayMessageResponse) o;
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
