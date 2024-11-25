package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Enums.FirmwareStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para notificar el estado del firmware en una estación de carga.
 * Este mensaje puede ser utilizado para informar sobre el progreso de una instalación
 * de firmware.
 */
public class FirmwareStatusNotificationRequest extends RequestWithId {

    @Nullable
    private CustomData customData;

    private FirmwareStatusEnum status;

    @Nullable
    private Integer requestId;

    /**
     * Constructor para la clase FirmwareStatusNotificationRequest.
     *
     * @param status El estado de progreso de la instalación del firmware.
     */
    public FirmwareStatusNotificationRequest(FirmwareStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados a esta solicitud.
     *
     * @return Los datos personalizados, si están presentes; de lo contrario, null.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta solicitud.
     *
     * @param customData Los datos personalizados a asignar.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Los datos personalizados a verificar.
     * @return true si los datos son válidos o null; de lo contrario, false.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a esta solicitud.
     *
     * @param customData Los datos personalizados a agregar.
     * @return Esta misma instancia de FirmwareStatusNotificationRequest.
     */
    public FirmwareStatusNotificationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de progreso de la instalación del firmware.
     *
     * @return El estado actual de la instalación del firmware.
     */
    public FirmwareStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de progreso de la instalación del firmware.
     *
     * @param status El estado a asignar.
     * @throws PropertyConstraintException Si el estado proporcionado no es válido.
     */
    public void setStatus(FirmwareStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status El estado a verificar.
     * @return true si el estado no es null; de lo contrario, false.
     */
    private boolean isValidStatus(FirmwareStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene el ID de la solicitud que inició esta actualización de firmware.
     *
     * @return El ID de la solicitud, si está presente; de lo contrario, null.
     */
    @Nullable
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el ID de la solicitud que inició esta actualización de firmware.
     *
     * @param requestId El ID de la solicitud a asignar.
     */
    public void setRequestId(@Nullable Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Agrega el ID de la solicitud a esta notificación.
     *
     * @param requestId El ID de la solicitud a agregar.
     * @return Esta misma instancia de FirmwareStatusNotificationRequest.
     */
    public FirmwareStatusNotificationRequest withRequestId(@Nullable Integer requestId) {
        setRequestId(requestId);
        return this;
    }

    /**
     * Valida si todos los campos obligatorios de esta solicitud son correctos.
     *
     * @return true si la solicitud es válida; de lo contrario, false.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción activa.
     *
     * @return false, ya que no está relacionada con transacciones.
     */
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
        FirmwareStatusNotificationRequest that = (FirmwareStatusNotificationRequest) o;
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
