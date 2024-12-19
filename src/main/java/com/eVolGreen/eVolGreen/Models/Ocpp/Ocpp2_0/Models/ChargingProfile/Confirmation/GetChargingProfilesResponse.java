package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums.GetChargingProfileStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al mensaje GetChargingProfiles.
 *
 * <p>Define si la estación de carga puede procesar la solicitud y enviar mensajes
 * ReportChargingProfilesRequest.
 */
public class GetChargingProfilesResponse extends Confirmation {

    /** Datos personalizados específicos de la implementación. */
    @Nullable
    private CustomData customData;

    /**
     * Estado que indica si la estación de carga puede procesar esta solicitud y enviará los mensajes
     * ReportChargingProfilesRequest correspondientes.
     */
    private GetChargingProfileStatusEnum status;

    /** Información adicional sobre el estado de la solicitud. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase GetChargingProfilesResponse.
     *
     * @param status Estado que indica si la estación de carga puede procesar esta solicitud.
     */
    public GetChargingProfilesResponse(GetChargingProfileStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados específicos de la implementación.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados específicos de la implementación.
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
     * Valida si los datos personalizados proporcionados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura y devuelve esta instancia con los datos personalizados establecidos.
     *
     * @param customData Datos personalizados a establecer.
     * @return Esta instancia.
     */
    public GetChargingProfilesResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la respuesta, que indica si la estación de carga puede procesar la
     * solicitud.
     *
     * @return Estado de la respuesta.
     */
    public GetChargingProfileStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la respuesta, que indica si la estación de carga puede procesar la
     * solicitud.
     *
     * @param status Estado de la respuesta.
     * @throws PropertyConstraintException Si el estado proporcionado no es válido.
     */
    public void setStatus(GetChargingProfileStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(GetChargingProfileStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Información adicional o {@code null} si no está definida.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @throws PropertyConstraintException Si la información proporcionada no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información a validar.
     * @return {@code true} si la información es válida; de lo contrario, {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Configura y devuelve esta instancia con la información adicional sobre el estado establecida.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return Esta instancia.
     */
    public GetChargingProfilesResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        GetChargingProfilesResponse that = (GetChargingProfilesResponse) o;
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
