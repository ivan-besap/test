package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Enums.PublishFirmwareStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * PublishFirmwareStatusNotificationRequest
 *
 * Notificación del estado de la publicación del firmware.
 * Indica el progreso del proceso de publicación y, opcionalmente, proporciona ubicaciones y detalles.
 * OCPP 2.0.1 FINAL
 */
public class PublishFirmwareStatusNotificationRequest extends RequestWithId {

    /** Datos personalizados opcionales */
    @Nullable
    private CustomData customData;

    /** Estado del progreso de la publicación del firmware */
    private PublishFirmwareStatusEnum status;

    /** URIs opcionales requeridos si el estado es Published */
    @Nullable
    private String[] location;

    /** ID de la solicitud original que activó esta acción */
    @Nullable
    private Integer requestId;

    /**
     * Constructor principal.
     *
     * @param status Estado del progreso de la publicación del firmware.
     */
    public PublishFirmwareStatusNotificationRequest(PublishFirmwareStatusEnum status) {
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

    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "CustomData inválido.");
        }
        this.customData = customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de PublishFirmwareStatusNotificationRequest.
     */
    public PublishFirmwareStatusNotificationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del progreso de la publicación del firmware.
     *
     * @return Estado del progreso de la publicación del firmware.
     */
    public PublishFirmwareStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del progreso de la publicación del firmware.
     *
     * @param status Estado del progreso de la publicación del firmware.
     */
    public void setStatus(PublishFirmwareStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Configura el estado del progreso de la publicación del firmware.
     *
     * @param status Estado del progreso de la publicación del firmware.
     * @return La instancia actualizada.
     */
    private boolean isValidStatus(PublishFirmwareStatusEnum status) {
        return status != null;
    }

    /**
     * Configura el estado del progreso de la publicación del firmware.
     *
     *
     * @return La instancia actualizada.
     */
    @Nullable
    public String[] getLocation() {
        return location;
    }

    public void setLocation(@Nullable String[] location) {
        if (!isValidLocation(location)) {
            throw new PropertyConstraintException(location, "Ubicación inválida.");
        }
        this.location = location;
    }

    /**
     * Establece la ubicación del firmware.
     *
     * @param location Ubicación del firmware.
     */
    private boolean isValidLocation(@Nullable String[] location) {
        return location == null
                || (location.length >= 1 && Arrays.stream(location).allMatch(item -> item.length() <= 512));
    }

    /**
     * Agrega la ubicación del firmware.
     *
     * @param location Ubicación del firmware.
     * @return La instancia actual de PublishFirmwareStatusNotificationRequest.
     */
    public PublishFirmwareStatusNotificationRequest withLocation(@Nullable String[] location) {
        setLocation(location);
        return this;
    }

    /**
     * Obtiene el ID de la solicitud original que activó esta acción.
     *
     * @return ID de la solicitud original.
     */
    @Nullable
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el ID de la solicitud original que activó esta acción.
     *
     * @param requestId ID de la solicitud original.
     */
    public void setRequestId(@Nullable Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Agrega el ID de la solicitud original que activó esta acción.
     *
     * @param requestId ID de la solicitud original.
     * @return La instancia actual de PublishFirmwareStatusNotificationRequest.
     */
    public PublishFirmwareStatusNotificationRequest withRequestId(@Nullable Integer requestId) {
        setRequestId(requestId);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidLocation(location);
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PublishFirmwareStatusNotificationRequest that = (PublishFirmwareStatusNotificationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Arrays.equals(location, that.location)
                && Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, Arrays.hashCode(location), requestId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("location", location)
                .add("requestId", requestId)
                .add("isValid", validate())
                .toString();
    }
}
