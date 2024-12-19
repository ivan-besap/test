package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.Enums.GetDisplayMessagesStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al mensaje GetDisplayMessagesRequest.
 *
 * <p>Indica si la estación de carga tiene mensajes de pantalla que coincidan con los criterios
 * solicitados en el mensaje de solicitud.
 */
public class GetDisplayMessagesResponse extends Confirmation {
    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Estado que indica si la estación de carga tiene mensajes de pantalla que coincidan con los
     * criterios solicitados.
     */
    private GetDisplayMessagesStatusEnum status;

    /** Información adicional sobre el estado de la respuesta. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase GetDisplayMessagesResponse.
     *
     * @param status Estado indicando si la estación de carga tiene mensajes de pantalla que coincidan.
     */
    public GetDisplayMessagesResponse(GetDisplayMessagesStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados opcionales.
     *
     * @return Datos personalizados, si están disponibles.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados opcionales.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura los datos personalizados y retorna la instancia actual.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public GetDisplayMessagesResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la respuesta.
     *
     * @return Estado indicando si hay mensajes que coinciden con los criterios.
     */
    public GetDisplayMessagesStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la respuesta.
     *
     * @param status Estado indicando si hay mensajes que coinciden con los criterios.
     */
    public void setStatus(GetDisplayMessagesStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(GetDisplayMessagesStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Información adicional sobre el estado, si está disponible.
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
            throw new PropertyConstraintException(statusInfo, "statusInfo no es válido.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información de estado es válida.
     *
     * @param statusInfo Información de estado a validar.
     * @return {@code true} si es válida; de lo contrario, {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Configura la información de estado y retorna la instancia actual.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia.
     */
    public GetDisplayMessagesResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDisplayMessagesResponse that = (GetDisplayMessagesResponse) o;
        return Objects.equals(customData, that.customData) &&
                Objects.equals(status, that.status) &&
                Objects.equals(statusInfo, that.statusInfo);
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
