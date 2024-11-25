package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.ClearCacheStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * ClearCacheResponse
 *
 * Respuesta a la solicitud de limpieza de la memoria caché en una estación de carga.
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class ClearCacheResponse extends Confirmation {

    /** Datos personalizados que permiten incluir información adicional en la respuesta. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga ejecutó correctamente la solicitud de limpieza de la caché. */
    private ClearCacheStatusEnum status;

    /** Información adicional sobre el estado de la solicitud. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase ClearCacheResponse.
     *
     * @param status Estado de la ejecución de la solicitud. Debe ser una enumeración {@link ClearCacheStatusEnum}.
     */
    public ClearCacheResponse(ClearCacheStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Objeto {@link CustomData} que contiene los datos personalizados, o {@code null} si no se especifican.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados de manera fluida.
     *
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @return La instancia actual de {@link ClearCacheResponse}.
     */
    public ClearCacheResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud de limpieza de la caché.
     *
     * @return Enumeración {@link ClearCacheStatusEnum} que indica el estado de la solicitud.
     */
    public ClearCacheStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de limpieza de la caché.
     *
     * @param status Enumeración {@link ClearCacheStatusEnum} que indica el estado de la solicitud.
     * @throws PropertyConstraintException si el estado es nulo.
     */
    public void setStatus(ClearCacheStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si el estado es válido, {@code false} de lo contrario.
     */
    private boolean isValidStatus(ClearCacheStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la solicitud.
     *
     * @return Objeto {@link StatusInfo} con información adicional, o {@code null} si no se especifica.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la solicitud.
     *
     * @param statusInfo Objeto {@link StatusInfo} con información adicional.
     * @throws PropertyConstraintException si la información adicional no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "Información adicional inválida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Objeto {@link StatusInfo} con información adicional.
     * @return {@code true} si la información es válida, {@code false} de lo contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Asigna información adicional de manera fluida.
     *
     * @param statusInfo Objeto {@link StatusInfo} con información adicional.
     * @return La instancia actual de {@link ClearCacheResponse}.
     */
    public ClearCacheResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida la respuesta verificando que todos los campos requeridos sean válidos.
     *
     * @return {@code true} si la respuesta es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearCacheResponse that = (ClearCacheResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo);
    }

    /**
     * Representación en cadena de la respuesta ClearCacheResponse.
     *
     * @return Una cadena con los valores de los atributos de la clase.
     */
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
