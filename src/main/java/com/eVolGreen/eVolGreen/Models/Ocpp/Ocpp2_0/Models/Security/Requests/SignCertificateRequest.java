package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.CertificateSigningUseEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para firmar un certificado.
 *
 * <p>Permite a la estación de carga enviar una CSR (Certificate Signing Request) al CSMS para
 * obtener un certificado firmado, cumpliendo con el estándar OCPP 2.0.1.
 */
public class SignCertificateRequest extends RequestWithId {
    /** Datos personalizados específicos de la implementación. */
    @Nullable
    private CustomData customData;

    /**
     * Solicitud de firma de certificado en formato CSR (Certificate Signing Request) codificada en
     * PEM. De acuerdo con el estándar RFC 2986.
     */
    private String csr;

    /**
     * Tipo de certificado que se solicita firmar. Si se omite, se asume que el certificado será
     * utilizado tanto para conexiones ISO 15118 como para la conexión entre la estación de carga y el
     * CSMS.
     */
    @Nullable private CertificateSigningUseEnum certificateType;

    /**
     * Constructor de la clase SignCertificateRequest.
     *
     * @param csr Solicitud de firma de certificado en formato CSR.
     */
    public SignCertificateRequest(String csr) {
        setCsr(csr);
    }

    /**
     * Obtiene datos personalizados específicos de la implementación.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados específicos de la implementación.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia actualizada.
     */
    public SignCertificateRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la solicitud CSR codificada en PEM.
     *
     * @return CSR codificado en PEM.
     */
    public String getCsr() {
        return csr;
    }

    /**
     * Establece la solicitud CSR codificada en PEM.
     *
     * @param csr CSR codificado en PEM.
     */
    public void setCsr(String csr) {
        if (!isValidCsr(csr)) {
            throw new PropertyConstraintException(csr, "CSR inválido");
        }
        this.csr = csr;
    }

    /**
     * Verifica si la CSR es válida.
     *
     * @param csr CSR a verificar.
     * @return {@code true} si la CSR es válida, {@code false} en caso contrario.
     */
    private boolean isValidCsr(String csr) {
        return csr != null && csr.length() <= 5500;
    }

    /**
     * Obtiene el tipo de certificado a firmar.
     *
     * @return Tipo de certificado.
     */
    @Nullable
    public CertificateSigningUseEnum getCertificateType() {
        return certificateType;
    }

    /**
     * Establece el tipo de certificado a firmar.
     *
     * @param certificateType Tipo de certificado.
     */
    public void setCertificateType(@Nullable CertificateSigningUseEnum certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Agrega el tipo de certificado a la solicitud.
     *
     * @param certificateType Tipo de certificado.
     * @return Esta instancia actualizada.
     */
    public SignCertificateRequest withCertificateType(
            @Nullable CertificateSigningUseEnum certificateType) {
        setCertificateType(certificateType);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidCsr(csr);
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
        SignCertificateRequest that = (SignCertificateRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(csr, that.csr)
                && Objects.equals(certificateType, that.certificateType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, csr, certificateType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("csr", csr)
                .add("certificateType", certificateType)
                .add("isValid", validate())
                .toString();
    }
}
