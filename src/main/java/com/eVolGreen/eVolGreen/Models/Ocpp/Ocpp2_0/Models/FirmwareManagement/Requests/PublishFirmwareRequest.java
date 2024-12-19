package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * PublishFirmwareRequest
 *
 * Solicitud para publicar firmware en la estación de carga.
 * OCPP 2.0.1 FINAL
 */
public class PublishFirmwareRequest extends RequestWithId {

    /**
     * Datos personalizados opcionales
     */
    @Nullable
    private CustomData customData;

    /**
     * URI donde se puede descargar el firmware
     */
    private String location;

    /**
     * Número de intentos de descarga antes de rendirse (opcional)
     */
    @Nullable
    private Integer retries;

    /**
     * Suma de verificación MD5 del firmware
     */
    private String checksum;

    /**
     * Identificador único de la solicitud
     */
    private Integer requestId;

    /**
     * Intervalo entre reintentos en segundos (opcional)
     */
    @Nullable
    private Integer retryInterval;

    /**
     * Constructor de la clase PublishFirmwareRequest.
     *
     * @param location  URI del firmware.
     * @param checksum  Suma MD5 del archivo de firmware.
     * @param requestId Identificador único de la solicitud.
     */
    public PublishFirmwareRequest(String location, String checksum, Integer requestId) {
        setLocation(location);
        setChecksum(checksum);
        setRequestId(requestId);
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
            throw new PropertyConstraintException(customData, "CustomData inválido.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados y retorna esta instancia.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public PublishFirmwareRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (!isValidLocation(location)) {
            throw new PropertyConstraintException(location, "La ubicación debe ser una URI válida de hasta 512 caracteres.");
        }
        this.location = location;
    }

    /**
     * Verifica si la ubicación es válida.
     *
     * @param location Ubicación a validar.
     * @return {@code true} si es válida o {@code false} en caso contrario.
     */
    private boolean isValidLocation(String location) {
        return location != null && location.length() <= 512;
    }

    public Integer getRetries() {
        return retries;
    }

    /**
     * Establece el número de reintentos.
     *
     * @param retries Número de reintentos.
     */
    public void setRetries(@Nullable Integer retries) {
        this.retries = retries;
    }

    /**
     * Agrega el número de reintentos y retorna esta instancia.
     *
     * @param retries Número de reintentos.
     * @return Esta instancia.
     */
    public PublishFirmwareRequest withRetries(@Nullable Integer retries) {
        setRetries(retries);
        return this;
    }

    /**
     * Obtiene la suma de verificación MD5 del firmware.
     *
     * @return Suma de verificación MD5.
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Establece la suma de verificación MD5 del firmware.
     *
     * @param checksum Suma de verificación MD5.
     */
    public void setChecksum(String checksum) {
        if (!isValidChecksum(checksum)) {
            throw new PropertyConstraintException(checksum, "El checksum debe tener exactamente 32 caracteres.");
        }
        this.checksum = checksum;
    }

    /**
     * Verifica si la suma de verificación MD5 es válida.
     *
     * @param checksum Suma de verificación MD5 a validar.
     * @return {@code true} si es válida o {@code false} en caso contrario.
     */
    private boolean isValidChecksum(String checksum) {
        return checksum != null && checksum.length() <= 32;
    }

    /**
     * Obtiene el identificador único de la solicitud.
     *
     * @return Identificador de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador único de la solicitud.
     *
     * @param requestId Identificador de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId is invalid");
        }
        this.requestId = requestId;
    }

    /**
     * Verifica si el identificador de la solicitud es válido.
     *
     * @param requestId Identificador a validar.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Establece el intervalo entre reintentos.
     *
     */
    @Nullable
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Establece el intervalo entre reintentos.
     *
     * @param retryInterval Intervalo entre reintentos.
     */
    public void setRetryInterval(@Nullable Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * Agrega el intervalo entre reintentos y retorna esta instancia.
     *
     * @param retryInterval Intervalo entre reintentos.
     * @return Esta instancia.
     */
    public PublishFirmwareRequest withRetryInterval(@Nullable Integer retryInterval) {
        setRetryInterval(retryInterval);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidLocation(location)
                && isValidChecksum(checksum)
                && isValidRequestId(requestId);
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
        PublishFirmwareRequest that = (PublishFirmwareRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(location, that.location)
                && Objects.equals(retries, that.retries)
                && Objects.equals(checksum, that.checksum)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(retryInterval, that.retryInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, location, retries, checksum, requestId, retryInterval);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("location", location)
                .add("retries", retries)
                .add("checksum", checksum)
                .add("requestId", requestId)
                .add("retryInterval", retryInterval)
                .add("isValid", validate())
                .toString();
    }
}
