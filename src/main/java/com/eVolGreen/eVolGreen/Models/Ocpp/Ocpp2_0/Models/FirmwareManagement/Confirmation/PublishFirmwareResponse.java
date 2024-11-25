package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums.GenericStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * PublishFirmwareResponse
 *
 * Respuesta al publicar un firmware.
 * Indica si la solicitud fue aceptada y proporciona información adicional sobre el estado.
 * OCPP 2.0.1 FINAL
 */
public class PublishFirmwareResponse extends Confirmation {

    /** Datos personalizados opcionales */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la solicitud fue aceptada */
    private GenericStatusEnum status;

    /** Información adicional sobre el estado (opcional) */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor de la clase PublishFirmwareResponse.
     *
     * @param status Estado que indica si la solicitud fue aceptada.
     */
    public PublishFirmwareResponse(GenericStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados a la respuesta.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }


    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "CustomData inválido.");
        }
        this.customData = customData;
    }

    /**
     * Establece los datos personalizados asociados a la respuesta.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException si los datos no son válidos.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la respuesta.
     *
     * @param customData Datos personalizados.
     * @return La respuesta actualizada.
     */
    public PublishFirmwareResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la solicitud fue aceptada.
     *
     * @return Estado de la solicitud.
     */
    public GenericStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la solicitud fue aceptada.
     *
     * @param status Estado de la solicitud.
     * @throws PropertyConstraintException si el estado es nulo.
     */
    public void setStatus(GenericStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si el estado es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(GenericStatusEnum status) {
        return status != null;
    }

    /**
     * Establece el estado que indica si la solicitud fue aceptada.
     *
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece la información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @throws PropertyConstraintException si la información adicional no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "StatusInfo inválido.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información adicional a verificar.
     * @return {@code true} si la información es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado a la respuesta.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La respuesta actualizada.
     */
    public PublishFirmwareResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        PublishFirmwareResponse that = (PublishFirmwareResponse) o;
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
