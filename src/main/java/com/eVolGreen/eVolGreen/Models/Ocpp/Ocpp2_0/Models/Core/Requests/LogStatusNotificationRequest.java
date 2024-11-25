package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.UploadLogStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa una solicitud de notificación de estado de registro en OCPP.
 */
public class LogStatusNotificationRequest extends RequestWithId {

    /** Información adicional opcional relacionada con la solicitud. */
    @Nullable
    private CustomData customData;

    /** Estado actual de la carga del registro. */
    private UploadLogStatusEnum status;

    /**
     * Identificador de la solicitud original en GetLogRequest que inició la carga del registro.
     * Este campo es obligatorio, a menos que el mensaje sea activado por TriggerMessageRequest y no haya una carga de registro en curso.
     */
    @Nullable private Integer requestId;

    /**
     * Constructor de la clase LogStatusNotificationRequest.
     *
     * @param status Estado actual de la carga del registro.
     */
    public LogStatusNotificationRequest(UploadLogStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados con esta solicitud.
     *
     * @return Objeto CustomData, o null si no se proporciona.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta solicitud.
     *
     * @param customData Objeto CustomData a asignar.
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
     * @param customData Objeto CustomData a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Objeto CustomData a asignar.
     * @return La instancia actual de LogStatusNotificationRequest.
     */
    public LogStatusNotificationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado actual de la carga del registro.
     *
     * @return El estado actual de tipo UploadLogStatusEnum.
     */
    public UploadLogStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado actual de la carga del registro.
     *
     * @param status Estado de tipo UploadLogStatusEnum.
     */
    public void setStatus(UploadLogStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado proporcionado no es válido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado de tipo UploadLogStatusEnum a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidStatus(UploadLogStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene el identificador de la solicitud original.
     *
     * @return Identificador de la solicitud, o null si no está establecido.
     */
    @Nullable
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador de la solicitud original.
     *
     * @param requestId Identificador de tipo Integer.
     */
    public void setRequestId(@Nullable Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Agrega el identificador de la solicitud original a la solicitud actual.
     *
     * @param requestId Identificador de tipo Integer.
     * @return La instancia actual de LogStatusNotificationRequest.
     */
    public LogStatusNotificationRequest withRequestId(@Nullable Integer requestId) {
        setRequestId(requestId);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status);
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
        LogStatusNotificationRequest that = (LogStatusNotificationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, requestId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("requestId", requestId)
                .add("isValid", validate())
                .toString();
    }
}
