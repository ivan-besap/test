package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Utils.Firmware;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * UpdateFirmwareRequest
 *
 * <p>Clase que representa una solicitud para actualizar el firmware de una estación de carga. Define los parámetros necesarios para la actualización, como los intentos de descarga, el intervalo de reintentos y la información del firmware.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class UpdateFirmwareRequest extends RequestWithId {
    @Nullable
    private CustomData customData;

    /**
     * Número de intentos que la estación de carga debe realizar para descargar el firmware antes de abandonar. Si no se especifica, queda a discreción de la estación de carga.
     */
    @Nullable
    private Integer retries;

    /**
     * Intervalo en segundos después del cual se puede realizar un reintento de descarga. Si no se especifica, queda a discreción de la estación de carga.
     */
    @Nullable
    private Integer retryInterval;

    /**
     * Identificador único de la solicitud.
     */
    private Integer requestId;

    /**
     * Información del firmware a ser actualizado en la estación de carga.
     */
    private Firmware firmware;

    /**
     * Constructor para la clase UpdateFirmwareRequest.
     *
     * @param requestId Identificador de la solicitud.
     * @param firmware Información del firmware para la actualización.
     */
    public UpdateFirmwareRequest(Integer requestId, Firmware firmware) {
        setRequestId(requestId);
        setFirmware(firmware);
    }

    /**
     * Devuelve los datos personalizados adjuntos.
     *
     * @return CustomData adjunta.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados para la solicitud.
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
     * Establece datos personalizados adicionales de forma fluida.
     *
     * @param customData Datos personalizados adicionales.
     * @return Instancia actual de UpdateFirmwareRequest.
     */
    public UpdateFirmwareRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Devuelve el número de reintentos configurados.
     *
     * @return Número de intentos de reintento.
     */
    @Nullable
    public Integer getRetries() {
        return retries;
    }

    /**
     * Configura el número máximo de reintentos para la descarga.
     *
     * @param retries Número máximo de intentos.
     */
    public void setRetries(@Nullable Integer retries) {
        this.retries = retries;
    }

    /**
     * Establece el número de reintentos de forma fluida.
     *
     * @param retries Número de reintentos.
     * @return Instancia actual de UpdateFirmwareRequest.
     */
    public UpdateFirmwareRequest withRetries(@Nullable Integer retries) {
        setRetries(retries);
        return this;
    }

    /**
     * Devuelve el intervalo en segundos entre reintentos.
     *
     * @return Intervalo en segundos.
     */
    @Nullable
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Configura el intervalo en segundos entre reintentos.
     *
     * @param retryInterval Intervalo en segundos.
     */
    public void setRetryInterval(@Nullable Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * Establece el intervalo de reintentos de forma fluida.
     *
     * @param retryInterval Intervalo en segundos.
     * @return Instancia actual de UpdateFirmwareRequest.
     */
    public UpdateFirmwareRequest withRetryInterval(@Nullable Integer retryInterval) {
        setRetryInterval(retryInterval);
        return this;
    }

    /**
     * Devuelve el identificador único de la solicitud.
     *
     * @return Identificador de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador único de la solicitud.
     *
     * @param requestId Identificador único.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId is invalid");
        }
        this.requestId = requestId;
    }

    /**
     * Valida si el identificador de la solicitud es válido.
     *
     * @param requestId Identificador de la solicitud a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Devuelve la información del firmware para la actualización.
     *
     * @return Información del firmware.
     */
    public Firmware getFirmware() {
        return firmware;
    }

    /**
     * Configura la información del firmware para la actualización.
     *
     * @param firmware Información del firmware.
     */
    public void setFirmware(Firmware firmware) {
        if (!isValidFirmware(firmware)) {
            throw new PropertyConstraintException(firmware, "firmware is invalid");
        }
        this.firmware = firmware;
    }

    /**
     * Valida si la información del firmware es válida.
     *
     * @param firmware Información del firmware a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidFirmware(Firmware firmware) {
        return firmware != null && firmware.validate();
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidRequestId(requestId)
                && isValidFirmware(firmware);
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
        UpdateFirmwareRequest that = (UpdateFirmwareRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(retries, that.retries)
                && Objects.equals(retryInterval, that.retryInterval)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(firmware, that.firmware);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, retries, retryInterval, requestId, firmware);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("retries", retries)
                .add("retryInterval", retryInterval)
                .add("requestId", requestId)
                .add("firmware", firmware)
                .add("isValid", validate())
                .toString();
    }
}

