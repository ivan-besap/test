package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.Enums.GenericDeviceModelStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta a una solicitud de informe base (GetBaseReportRequest) en OCPP 2.0.1.
 *
 * <p>Esta clase contiene información sobre si la estación de carga puede aceptar la solicitud
 * y detalles adicionales sobre el estado de la misma.</p>
 */
public class GetBaseReportResponse extends Confirmation {

    /** Datos personalizados específicos del implementador. */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la estación de carga puede aceptar la solicitud. */
    private GenericDeviceModelStatusEnum status;

    /** Información adicional sobre el estado de la respuesta. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase GetBaseReportResponse.
     *
     * @param status Estado que indica si la estación de carga puede aceptar la solicitud.
     */
    public GetBaseReportResponse(GenericDeviceModelStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados proporcionados por el implementador.
     *
     * @return Datos personalizados o {@code null} si no se han especificado.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados específicos del implementador.
     *
     * @param customData Datos personalizados a establecer.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados específicos del implementador.
     *
     * @param customData Datos personalizados a agregar.
     * @return Esta instancia de GetBaseReportResponse para encadenamiento.
     */
    public GetBaseReportResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la estación de carga puede aceptar la solicitud.
     *
     * @return Estado de la respuesta.
     */
    public GenericDeviceModelStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la estación de carga puede aceptar la solicitud.
     *
     * @param status Estado a establecer.
     * @throws PropertyConstraintException Si el estado no es válido.
     */
    public void setStatus(GenericDeviceModelStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(GenericDeviceModelStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Información adicional o {@code null} si no se ha especificado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Información adicional a establecer.
     * @throws PropertyConstraintException Si la información adicional no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información adicional a validar.
     * @return {@code true} si la información adicional es válida; de lo contrario, {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Información adicional a agregar.
     * @return Esta instancia de GetBaseReportResponse para encadenamiento.
     */
    public GetBaseReportResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        GetBaseReportResponse that = (GetBaseReportResponse) o;
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
