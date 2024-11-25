package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.Enums.CancelReservationStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * CancelReservationResponse
 *
 * <p>Representa la respuesta a la solicitud de cancelación de una reserva en el protocolo OCPP 2.0.1.
 */
public class CancelReservationResponse extends Confirmation {

    /** Datos personalizados adicionales para la respuesta. */
    @Nullable
    private CustomData customData;

    /** Indica el resultado del intento de cancelación de la reserva por parte del CSMS. */
    private CancelReservationStatusEnum status;

    /** Información adicional sobre el estado de la respuesta. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase CancelReservationResponse.
     *
     * @param status El resultado del intento de cancelación de la reserva.
     */
    public CancelReservationResponse(CancelReservationStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados adicionales de la respuesta.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales de la respuesta.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados adicionales son válidos.
     *
     * @param customData Los datos personalizados adicionales a verificar.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales a la respuesta.
     *
     * @param customData Datos personalizados adicionales.
     * @return La respuesta actualizada.
     */
    public CancelReservationResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el resultado del intento de cancelación de la reserva por parte del CSMS.
     *
     * @return Resultado de la cancelación de la reserva.
     */
    public CancelReservationStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el resultado del intento de cancelación de la reserva por parte del CSMS.
     *
     * @param status Resultado de la cancelación de la reserva.
     */
    public void setStatus(CancelReservationStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status no es válido");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status El estado a verificar.
     * @return {@code true} si el estado es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(CancelReservationStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo no es válido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional del estado es válida.
     *
     * @param statusInfo La información adicional del estado a verificar.
     * @return {@code true} si es válida; de lo contrario, {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La respuesta actualizada.
     */
    public CancelReservationResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida los datos de la respuesta.
     *
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo);
    }

    /**
     * Compara esta instancia con otro objeto para verificar la igualdad.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario, {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CancelReservationResponse that = (CancelReservationResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    /**
     * Calcula el hash de esta instancia.
     *
     * @return El hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo);
    }

    /**
     * Genera una representación en forma de cadena de esta respuesta.
     *
     * @return Una cadena que representa esta instancia.
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
