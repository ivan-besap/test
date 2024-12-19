package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.Enums.RequestStartStopStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la solicitud de detener una transacción en la estación de carga.
 *
 * <p>Este mensaje confirma si la estación de carga acepta o rechaza la solicitud de detener una transacción activa.
 * <p>Versión: OCPP 2.0.1 FINAL
 */
public class RequestStopTransactionResponse extends Confirmation {

    /** Datos personalizados opcionales relacionados con la respuesta. */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la estación de carga acepta la solicitud de detener la transacción. */
    private RequestStartStopStatusEnum status;

    /** Información adicional sobre el estado de la respuesta. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase RequestStopTransactionResponse.
     *
     * @param status Estado que indica si la estación de carga acepta la solicitud de detener la transacción.
     */
    public RequestStopTransactionResponse(RequestStartStopStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados relacionados con la respuesta.
     *
     * @return Los datos personalizados o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con la respuesta.
     *
     * @param customData Los datos personalizados que se desean establecer.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados proporcionados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return true si son válidos, de lo contrario false.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados y devuelve la instancia actual.
     *
     * @param customData Los datos personalizados que se desean establecer.
     * @return La instancia actual de RequestStopTransactionResponse.
     */
    public RequestStopTransactionResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la estación de carga acepta la solicitud de detener la transacción.
     *
     * @return El estado de la respuesta.
     */
    public RequestStartStopStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la estación de carga acepta la solicitud de detener la transacción.
     *
     * @param status El estado de la respuesta.
     */
    public void setStatus(RequestStartStopStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status El estado a validar.
     * @return true si es válido, de lo contrario false.
     */
    private boolean isValidStatus(RequestStartStopStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Información adicional o null si no está definida.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo La información adicional que se desea establecer.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional proporcionada es válida.
     *
     * @param statusInfo La información adicional a validar.
     * @return true si es válida, de lo contrario false.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Asigna información adicional sobre el estado y devuelve la instancia actual.
     *
     * @param statusInfo La información adicional que se desea establecer.
     * @return La instancia actual de RequestStopTransactionResponse.
     */
    public RequestStopTransactionResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        RequestStopTransactionResponse that = (RequestStopTransactionResponse) o;
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
