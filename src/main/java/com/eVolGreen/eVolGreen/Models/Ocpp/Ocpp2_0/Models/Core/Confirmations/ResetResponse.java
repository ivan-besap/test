package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.ResetStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta de una solicitud de reinicio (Reset) en una estación de carga.
 *
 * <p>Proporciona información sobre si la estación de carga puede realizar el reinicio solicitado
 * y detalles adicionales relacionados con el estado de la operación.
 *
 * <p>Esta clase forma parte de la especificación OCPP 2.0.1.
 */
public class ResetResponse extends Confirmation {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la estación de carga puede realizar el reinicio. */
    private ResetStatusEnum status;

    /** Información adicional sobre el estado del reinicio. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase ResetResponse.
     *
     * @param status Estado que indica si la estación de carga puede realizar el reinicio.
     */
    public ResetResponse(ResetStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Los datos personalizados adicionales o {@code null} si no están presentes.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Los datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Los datos personalizados a verificar.
     * @return {@code true} si son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Los datos personalizados adicionales.
     * @return La instancia actual de ResetResponse.
     */
    public ResetResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del reinicio.
     *
     * @return El estado del reinicio.
     */
    public ResetStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del reinicio.
     *
     * @param status El estado del reinicio.
     */
    public void setStatus(ResetStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado del reinicio no es válido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado del reinicio es válido.
     *
     * @param status El estado a verificar.
     * @return {@code true} si es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(ResetStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado del reinicio.
     *
     * @return La información adicional sobre el estado o {@code null} si no está presente.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado del reinicio.
     *
     * @param statusInfo La información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(
                    statusInfo, "La información adicional del estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional sobre el estado es válida.
     *
     * @param statusInfo La información adicional a verificar.
     * @return {@code true} si es válida; de lo contrario, {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado del reinicio.
     *
     * @param statusInfo La información adicional sobre el estado.
     * @return La instancia actual de ResetResponse.
     */
    public ResetResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        ResetResponse that = (ResetResponse) o;
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
