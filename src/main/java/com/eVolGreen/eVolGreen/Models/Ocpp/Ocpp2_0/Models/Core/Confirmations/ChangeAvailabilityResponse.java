package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.ChangeAvailabilityStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta a una solicitud de cambio de disponibilidad de la estación de carga.
 * <p>
 * Proporciona información sobre si la estación de carga puede realizar el cambio solicitado,
 * así como detalles adicionales sobre el estado.
 * </p>
 */
public class ChangeAvailabilityResponse extends Confirmation {

    /** Datos personalizados para atributos adicionales. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga puede realizar el cambio de disponibilidad. */
    private ChangeAvailabilityStatusEnum status;

    /** Información adicional sobre el estado del cambio. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase ChangeAvailabilityResponse.
     *
     * @param status Indica si la estación de carga puede realizar el cambio de disponibilidad.
     */
    public ChangeAvailabilityResponse(ChangeAvailabilityStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados o {@code null}.
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
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados de manera fluida.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual para encadenar métodos.
     */
    public ChangeAvailabilityResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del cambio de disponibilidad.
     *
     * @return Estado del cambio.
     */
    public ChangeAvailabilityStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del cambio de disponibilidad.
     *
     * @param status Estado del cambio.
     */
    public void setStatus(ChangeAvailabilityStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(ChangeAvailabilityStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional o {@code null}.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información de estado es válida.
     *
     * @param statusInfo Información de estado a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional de manera fluida.
     *
     * @param statusInfo Información adicional.
     * @return La instancia actual para encadenar métodos.
     */
    public ChangeAvailabilityResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida si la respuesta es consistente.
     *
     * @return {@code true} si la respuesta es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    /**
     * Compara esta instancia con otro objeto.
     *
     * @param o Objeto a comparar.
     * @return {@code true} si son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeAvailabilityResponse that = (ChangeAvailabilityResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    /**
     * Genera el código hash de la instancia.
     *
     * @return Código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo);
    }

    /**
     * Representación en cadena de la instancia.
     *
     * @return Representación en cadena.
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
