package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.InstallCertificateUseEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para instalar un certificado en la estación de carga. Incluye el tipo de certificado y
 * el certificado codificado en formato PEM.
 */
public class InstallCertificateRequest extends RequestWithId {

    /** Datos personalizados asociados a la solicitud. */
    @Nullable
    private CustomData customData;

    /** Tipo de certificado que se enviará. */
    private InstallCertificateUseEnum certificateType;

    /** Certificado X.509 codificado en formato PEM. */
    private String certificate;

    /**
     * Constructor para la clase InstallCertificateRequest.
     *
     * @param certificateType Tipo de certificado que se enviará.
     * @param certificate Certificado X.509 codificado en formato PEM.
     */
    public InstallCertificateRequest(InstallCertificateUseEnum certificateType, String certificate) {
        setCertificateType(certificateType);
        setCertificate(certificate);
    }

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Objeto CustomData o {@code null} si no se han definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a la solicitud.
     *
     * @param customData Datos personalizados o {@code null}.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
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
     * @param customData Objeto CustomData a verificar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Objeto CustomData.
     * @return La instancia actual de InstallCertificateRequest.
     */
    public InstallCertificateRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de certificado que se enviará.
     *
     * @return Tipo de certificado como InstallCertificateUseEnum.
     */
    public InstallCertificateUseEnum getCertificateType() {
        return certificateType;
    }

    /**
     * Establece el tipo de certificado que se enviará.
     *
     * @param certificateType Tipo de certificado.
     * @throws PropertyConstraintException si el tipo de certificado no es válido.
     */
    public void setCertificateType(InstallCertificateUseEnum certificateType) {
        if (!isValidCertificateType(certificateType)) {
            throw new PropertyConstraintException(certificateType, "El tipo de certificado no es válido.");
        }
        this.certificateType = certificateType;
    }

    /**
     * Verifica si el tipo de certificado es válido.
     *
     * @param certificateType Tipo de certificado a verificar.
     * @return {@code true} si el tipo de certificado es válido, {@code false} en caso contrario.
     */
    private boolean isValidCertificateType(InstallCertificateUseEnum certificateType) {
        return certificateType != null;
    }

    /**
     * Obtiene el certificado X.509 codificado en formato PEM.
     *
     * @return Certificado como una cadena de texto.
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * Establece el certificado X.509 codificado en formato PEM.
     *
     * @param certificate Certificado codificado en formato PEM.
     * @throws PropertyConstraintException si el certificado no es válido.
     */
    public void setCertificate(String certificate) {
        if (!isValidCertificate(certificate)) {
            throw new PropertyConstraintException(certificate, "El certificado no es válido.");
        }
        this.certificate = certificate;
    }

    /**
     * Verifica si el certificado es válido.
     *
     * @param certificate Certificado a verificar.
     * @return {@code true} si el certificado es válido, {@code false} en caso contrario.
     */
    private boolean isValidCertificate(String certificate) {
        return certificate != null && certificate.length() <= 5500;
    }

    /**
     * Valida si esta solicitud cumple con los requisitos establecidos.
     *
     * @return {@code true} si la solicitud es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidCertificateType(certificateType)
                && isValidCertificate(certificate);
    }

    /**
     * Determina si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false} ya que no está relacionada con una transacción.
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

    /**
     * Compara esta instancia con otro objeto.
     *
     * @param o Objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InstallCertificateRequest that = (InstallCertificateRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(certificateType, that.certificateType)
                && Objects.equals(certificate, that.certificate);
    }

    /**
     * Calcula el código hash de esta instancia.
     *
     * @return Código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, certificateType, certificate);
    }

    /**
     * Devuelve una representación en texto de esta solicitud.
     *
     * @return Cadena que representa el objeto.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("certificateType", certificateType)
                .add("certificate", certificate)
                .add("isValid", validate())
                .toString();
    }
}
