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
 * Solicitud de Certificado Firmado
 *
 * Representa una solicitud para enviar un certificado X.509 firmado al CSMS.
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class CertificateSignedRequest extends RequestWithId {
    /** Datos personalizados para atributos adicionales */
    @Nullable
    private CustomData customData;

    /**
     * El certificado X.509 firmado en formato PEM codificado.
     * Puede incluir certificados sub CA, siguiendo el orden de la cadena de certificados comenzando desde el certificado hoja.
     */
    private String certificateChain;

    /**
     * Especifica el tipo de certificado. Si se omite, el certificado se aplica tanto
     * a la conexión Estación de Carga-CSMS como a la conexión 15118 (si está implementada).
     */
    @Nullable
    private CertificateSigningUseEnum certificateType;

    /**
     * Constructor de la clase CertificateSignedRequest.
     *
     * @param certificateChain El certificado X.509 firmado en formato PEM.
     */
    public CertificateSignedRequest(String certificateChain) {
        setCertificateChain(certificateChain);
    }

    /** Obtiene los datos personalizados. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /** Establece los datos personalizados. */
    public void setCustomData(@Nullable CustomData customData) {
        if (customData != null && !customData.validate()) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /** Valida los datos personalizados. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /** Establece los datos personalizados de forma fluida. */
    public CertificateSignedRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** Obtiene la cadena del certificado. */
    public String getCertificateChain() {
        return certificateChain;
    }

    /** Establece la cadena del certificado. */
    public void setCertificateChain(String certificateChain) {
        if (!isValidCertificateChain(certificateChain)) {
            throw new PropertyConstraintException(certificateChain, "La cadena del certificado no es válida.");
        }
        this.certificateChain = certificateChain;
    }

    /** Valida la cadena del certificado. */
    private boolean isValidCertificateChain(String certificateChain) {
        return certificateChain != null && certificateChain.length() <= 10000;
    }

    /** Obtiene el tipo de certificado. */
    @Nullable
    public CertificateSigningUseEnum getCertificateType() {
        return certificateType;
    }

    /** Establece el tipo de certificado. */
    public void setCertificateType(@Nullable CertificateSigningUseEnum certificateType) {
        this.certificateType = certificateType;
    }

    /** Establece el tipo de certificado de forma fluida. */
    public CertificateSignedRequest withCertificateType(@Nullable CertificateSigningUseEnum certificateType) {
        setCertificateType(certificateType);
        return this;
    }

    /** Valida la entidad. */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidCertificateChain(certificateChain);
    }

    /** Indica si la solicitud está relacionada con una transacción. */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    /**
     * Retorna la acción asociada con esta solicitud.
     */
    @Override
    public String getAction() {
        return "";
    }

    /** Obtiene el índice de la sesión, si corresponde. */
    @Override
    public UUID getSessionIndex() {
        return null;
    }

    /** Compara la igualdad de esta instancia con otro objeto. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateSignedRequest that = (CertificateSignedRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(certificateChain, that.certificateChain)
                && Objects.equals(certificateType, that.certificateType);
    }

    /** Genera el código hash para esta instancia. */
    @Override
    public int hashCode() {
        return Objects.hash(customData, certificateChain, certificateType);
    }

    /** Representación en cadena de la entidad. */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("certificateChain", certificateChain)
                .add("certificateType", certificateType)
                .add("isValid", validate())
                .toString();
    }
}
