package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.DataTransferStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta de una solicitud de transferencia de datos entre la estación de carga y el sistema central.
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class DataTransferResponse extends Confirmation {

    /** Datos personalizados definidos por el proveedor o cliente. */
    @Nullable
    private CustomData customData;

    /** Éxito o fallo en la transferencia de datos. */
    private DataTransferStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable private StatusInfo statusInfo;

    /** Datos en respuesta a la solicitud, sin longitud o formato especificado. */
    @Nullable private Object data;

    /**
     * Constructor para la clase DataTransferResponse.
     *
     * @param status Estado del éxito o fallo de la transferencia de datos.
     */
    public DataTransferResponse(DataTransferStatusEnum status) {
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

    /**
     * Establece datos personalizados.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia para encadenamiento.
     */
    public DataTransferResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de éxito o fallo de la transferencia de datos.
     *
     * @return Estado de la transferencia de datos.
     */
    public DataTransferStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de éxito o fallo de la transferencia de datos.
     *
     * @param status Estado de la transferencia de datos.
     */
    public void setStatus(DataTransferStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status es inválido");
        }
        this.status = status;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional del estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional del estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo es inválido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional del estado.
     * @return Esta instancia para encadenamiento.
     */
    public DataTransferResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Obtiene los datos de respuesta a la solicitud.
     *
     * @return Datos de respuesta.
     */
    @Nullable
    public Object getData() {
        return data;
    }

    /**
     * Establece los datos de respuesta a la solicitud.
     *
     * @param data Datos de respuesta.
     */
    public void setData(@Nullable Object data) {
        this.data = data;
    }

    /**
     * Agrega los datos de respuesta a la solicitud.
     *
     * @param data Datos de respuesta.
     * @return Esta instancia para encadenamiento.
     */
    public DataTransferResponse withData(@Nullable Object data) {
        setData(data);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataTransferResponse that = (DataTransferResponse) o;
        return Objects.equals(customData, that.customData)
                && status == that.status
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("data", data)
                .add("isValid", validate())
                .toString();
    }

    // Métodos privados de validación
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    private boolean isValidStatus(DataTransferStatusEnum status) {
        return status != null;
    }

    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }
}
