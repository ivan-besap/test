package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.Enums.ReserveNowStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta al intento de realizar una reserva con "ReserveNow".
 *
 * <p>Incluye el estado de la operación y detalles adicionales opcionales sobre el resultado.
 */
public class ReserveNowResponse extends Confirmation {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Indica si la reserva fue exitosa o fallida. */
    private ReserveNowStatusEnum status;

    /** Información adicional sobre el estado de la operación. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase ReserveNowResponse.
     *
     * @param status Indica si la reserva fue exitosa o fallida.
     */
    public ReserveNowResponse(ReserveNowStatusEnum status) {
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
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si son válidos; {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia de la clase.
     */
    public ReserveNowResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la operación.
     *
     * @return Estado de la operación.
     */
    public ReserveNowStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la operación.
     *
     * @param status Estado de la operación.
     */
    public void setStatus(ReserveNowStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status El estado a validar.
     * @return {@code true} si es válido; {@code false} en caso contrario.
     */
    private boolean isValidStatus(ReserveNowStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la operación.
     *
     * @return Información adicional sobre el estado de la operación.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la operación.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(
                    statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información de estado es válida.
     *
     * @param statusInfo La información de estado a validar.
     * @return {@code true} si es válida; {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado de la operación.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia de la clase.
     */
    public ReserveNowResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        ReserveNowResponse that = (ReserveNowResponse) o;
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

