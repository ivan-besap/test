package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.Types.CertificateUseEnumType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation.StringMaxLengthValidationRule;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para instalar un certificado en el punto de carga.
 * El mensaje incluye el tipo de certificado y el certificado en sí, codificado en formato PEM.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     InstallCertificateRequest request = new InstallCertificateRequest(CertificateUseEnumType.CentralSystem, "PEM_ENCODED_CERTIFICATE");
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class InstallCertificateRequest extends RequestWithId {

    private static final transient Validator certificateValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(5500))
                    .setRequired(true)
                    .build();

    private CertificateUseEnumType certificateType;
    private String certificate;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private InstallCertificateRequest() {
    }

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param certificateType Tipo de certificado. Ver {@link #setCertificateType(CertificateUseEnumType)}
     * @param certificate     Certificado codificado en PEM. Ver {@link #setCertificate(String)}
     */
    public InstallCertificateRequest(CertificateUseEnumType certificateType, String certificate) {
        setCertificateType(certificateType);
        setCertificate(certificate);
    }

    /**
     * Indica el tipo de certificado que se está enviando.
     *
     * @return {@link CertificateUseEnumType} Tipo de certificado.
     */
    public CertificateUseEnumType getCertificateType() {
        return certificateType;
    }

    /**
     * Requerido. Indica el tipo de certificado que se está enviando.
     *
     * @param certificateType Tipo de certificado.
     */
    public void setCertificateType(CertificateUseEnumType certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Certificado codificado en formato PEM X.509.
     *
     * @return Certificado codificado como cadena.
     */
    public String getCertificate() {
        return certificate;
    }

    /**
     * Requerido. Certificado codificado en formato PEM X.509.
     *
     * @param certificate Certificado como cadena de máximo 5500 caracteres.
     */
    public void setCertificate(String certificate) {
        certificateValidator.validate(certificate);
        this.certificate = certificate;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción. En este caso, no lo está.
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
     * Valida la solicitud, asegurando que los campos requeridos sean válidos.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return certificateType != null && certificateValidator.safeValidate(certificate);
    }

    /**
     * Compara si dos solicitudes de instalación de certificado son equivalentes.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstallCertificateRequest that = (InstallCertificateRequest) o;
        return Objects.equals(certificateType, that.certificateType)
                && Objects.equals(certificate, that.certificate);
    }

    /**
     * Genera un código hash único basado en el tipo de certificado y el certificado.
     *
     * @return El valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(certificateType, certificate);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de instalación de certificado.
     *
     * @return Una cadena que representa la solicitud, incluyendo el tipo de certificado y el certificado en sí.
     */
    @Override
    public String toString() {
        return "InstallCertificateRequest{" +
                "certificateType=" + certificateType +
                ", certificate='" + certificate + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}
