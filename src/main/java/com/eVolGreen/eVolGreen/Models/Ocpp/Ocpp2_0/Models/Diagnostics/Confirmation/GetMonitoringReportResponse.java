package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.Enums.GenericDeviceModelStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la solicitud de un reporte de monitoreo.
 *
 * <p>Define si la estación de carga pudo aceptar la solicitud de un reporte de monitoreo y
 * proporciona información adicional relacionada con el estado.
 */
public class GetMonitoringReportResponse extends Confirmation {

    /** Datos personalizados para extensiones específicas. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga pudo aceptar la solicitud. */
    private GenericDeviceModelStatusEnum status;

    /** Información adicional sobre el estado del proceso. */
    @Nullable private StatusInfo statusInfo;

    /**
     * Constructor de la clase GetMonitoringReportResponse.
     *
     * @param status Estado que indica si la estación de carga pudo aceptar la solicitud.
     */
    public GetMonitoringReportResponse(GenericDeviceModelStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene datos personalizados.
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
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura datos personalizados y retorna esta instancia.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public GetMonitoringReportResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la solicitud fue aceptada.
     *
     * @return Estado de la solicitud.
     */
    public GenericDeviceModelStatusEnum getStatus() {
        return status;
    }

    /**
     * Configura el estado que indica si la solicitud fue aceptada.
     *
     * @param status Estado de la solicitud.
     */
    public void setStatus(GenericDeviceModelStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Valida si el estado es correcto.
     *
     * @param status Estado a validar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(GenericDeviceModelStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Configura información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información de estado es correcta.
     *
     * @param statusInfo Información a validar.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Configura información adicional sobre el estado y retorna esta instancia.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia.
     */
    public GetMonitoringReportResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        GetMonitoringReportResponse that = (GetMonitoringReportResponse) o;
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
