package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums.InstallCertificateStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la solicitud de instalación de certificado.
 * Contiene información sobre el estado del intento de instalación.
 */
public class InstallCertificateResponse extends Confirmation {
    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la instalación del certificado fue exitosa. */
    private InstallCertificateStatusEnum status;

    /** Información adicional sobre el estado de la instalación. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor para la clase InstallCertificateResponse.
     *
     * @param status Estado que indica si la instalación fue exitosa.
     */
    public InstallCertificateResponse(InstallCertificateStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados con la respuesta.
     *
     * @return Datos personalizados, o null si no están presentes.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la respuesta.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados proporcionados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return true si los datos personalizados son válidos, de lo contrario false.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados y devuelve la respuesta actualizada.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de la respuesta.
     */
    public InstallCertificateResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la instalación del certificado.
     *
     * @return Estado de la instalación.
     */
    public InstallCertificateStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la instalación del certificado.
     *
     * @param status Estado de la instalación.
     */
    public void setStatus(InstallCertificateStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return true si el estado es válido, de lo contrario false.
     */
    private boolean isValidStatus(InstallCertificateStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la instalación.
     *
     * @return Información adicional, o null si no está presente.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la instalación.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información del estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información a validar.
     * @return true si la información es válida, de lo contrario false.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado y devuelve la respuesta actualizada.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La instancia actualizada de la respuesta.
     */
    public InstallCertificateResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
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
        InstallCertificateResponse that = (InstallCertificateResponse) o;
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
