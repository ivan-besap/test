package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.Enums.LogStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al mensaje GetLogRequest.
 *
 * <p>Indica si la estación de carga pudo procesar la solicitud de obtención de logs y proporciona información adicional sobre el estado, como el nombre del archivo generado.
 */
public class GetLogResponse extends Confirmation {

    /** Información personalizada adicional. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga pudo procesar la solicitud. */
    private LogStatusEnum status;

    /** Información adicional sobre el estado de la respuesta. */
    @Nullable private StatusInfo statusInfo;

    /**
     * Nombre del archivo de log que será subido. Este campo no estará presente si no hay información
     * de logs disponible.
     */
    @Nullable private String filename;

    /**
     * Constructor de la clase GetLogResponse.
     *
     * @param status Indica si la estación de carga pudo procesar la solicitud.
     */
    public GetLogResponse(LogStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene la información personalizada adicional.
     *
     * @return Información personalizada adicional.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece la información personalizada adicional.
     *
     * @param customData Información personalizada adicional.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Valida la información personalizada adicional.
     *
     * @param customData Información personalizada adicional a validar.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega la información personalizada adicional.
     *
     * @param customData Información personalizada adicional.
     * @return Esta instancia de GetLogResponse.
     */
    public GetLogResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud de logs.
     *
     * @return Estado de la solicitud de logs.
     */
    public LogStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de logs.
     *
     * @param status Estado de la solicitud de logs.
     */
    public void setStatus(LogStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status no es válido");
        }
        this.status = status;
    }

    /**
     * Valida el estado de la solicitud.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(LogStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la solicitud.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la solicitud.
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
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia de GetLogResponse.
     */
    public GetLogResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida la información adicional sobre el estado.
     *
     * @param statusInfo Información adicional a validar.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Obtiene el nombre del archivo de logs que será subido.
     *
     * @return Nombre del archivo de logs.
     */
    @Nullable
    public String getFilename() {
        return filename;
    }

    /**
     * Establece el nombre del archivo de logs que será subido.
     *
     * @param filename Nombre del archivo de logs.
     */
    public void setFilename(@Nullable String filename) {
        if (!isValidFilename(filename)) {
            throw new PropertyConstraintException(filename, "filename no es válido");
        }
        this.filename = filename;
    }

    /**
     * Agrega el nombre del archivo de logs.
     *
     * @param filename Nombre del archivo de logs.
     * @return Esta instancia de GetLogResponse.
     */
    public GetLogResponse withFilename(@Nullable String filename) {
        setFilename(filename);
        return this;
    }

    /**
     * Valida el nombre del archivo de logs.
     *
     * @param filename Nombre a validar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidFilename(@Nullable String filename) {
        return filename == null || filename.length() <= 255;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo)
                && isValidFilename(filename);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetLogResponse that = (GetLogResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo, filename);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("filename", filename)
                .add("isValid", validate())
                .toString();
    }
}
