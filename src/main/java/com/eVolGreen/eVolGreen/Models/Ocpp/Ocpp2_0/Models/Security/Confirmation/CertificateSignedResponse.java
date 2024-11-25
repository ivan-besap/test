package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums.CertificateSignedStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al proceso de firma de certificado.
 *
 * <p>Este mensaje confirma si la solicitud de firma de certificado fue aceptada o rechazada
 * por el CSMS.
 *
 * <p>Parte del perfil de seguridad de OCPP 2.0.1.
 */
public class CertificateSignedResponse extends Confirmation {

    /**
     * Datos personalizados para extensibilidad.
     */
    @Nullable
    private CustomData customData;

    /**
     * Indica si la firma del certificado fue aceptada o rechazada.
     */
    private CertificateSignedStatusEnum status;

    /**
     * Información adicional sobre el estado.
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase CertificateSignedResponse.
     *
     * @param status El estado del proceso de firma del certificado (Aceptado o Rechazado).
     */
    public CertificateSignedResponse(CertificateSignedStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Una instancia de {@link CustomData} o {@code null} si no se ha establecido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Una instancia de {@link CustomData}.
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
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si son válidos o {@code null}, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Una instancia de {@link CustomData}.
     * @return Esta instancia de {@link CertificateSignedResponse}.
     */
    public CertificateSignedResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del proceso de firma del certificado.
     *
     * @return El estado del proceso (Aceptado o Rechazado).
     */
    public CertificateSignedStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del proceso de firma del certificado.
     *
     * @param status El estado (Aceptado o Rechazado).
     */
    public void setStatus(CertificateSignedStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status,"El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Agrega el estado del proceso de firma del certificado.
     *
     * @param status El estado (Aceptado o Rechazado).
     * @return Esta instancia de {@link CertificateSignedResponse}.
     */
    private boolean isValidStatus(CertificateSignedStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Una instancia de {@link StatusInfo}, o {@code null}.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Una instancia de {@link StatusInfo}.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información de estado es válida.
     *
     * @param statusInfo La información de estado a validar.
     * @return {@code true} si es válida o {@code null}, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Una instancia de {@link StatusInfo}.
     * @return Esta instancia de {@link CertificateSignedResponse}.
     */
    public CertificateSignedResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && status != null && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateSignedResponse that = (CertificateSignedResponse) o;
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
                .add("esVálido", validate())
                .toString();
    }
}
