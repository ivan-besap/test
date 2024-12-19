package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SignedUpdateFirmwareRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Validation.OCPPSecurityExtDatatypes;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Validation.StringMaxLengthValidationRule;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa una copia del firmware que puede ser cargada o actualizada en el Charge Point.
 * Esta clase se utiliza en {@link SignedUpdateFirmwareRequest}.
 */
public class FirmwareType {

    private static final transient Validator locationValidator =
            new ValidatorBuilder()
                    .addRule(OCPPSecurityExtDatatypes.string512())
                    .setRequired(true)
                    .build();

    private static final transient Validator signingCertificateValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(5500))
                    .setRequired(true)
                    .build();

    private static final transient Validator signatureValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(800))
                    .setRequired(true)
                    .build();

    private String location;
    private ZonedDateTime retrieveDateTime;
    private ZonedDateTime installDateTime;
    private String signingCertificate;
    private String signature;

    /**
     * Obtiene la URI que define el origen del firmware.
     *
     * @return string[0..512]
     */
    public String getLocation() {
        return location;
    }

    /**
     * Establece la URI que define el origen del firmware.
     *
     * @param location string[0..512]
     */
    public void setLocation(String location) {
        locationValidator.validate(location);
        this.location = location;
    }

    /**
     * Obtiene la fecha y hora en que se debe recuperar el firmware.
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getRetrieveDateTime() {
        return retrieveDateTime;
    }

    /**
     * Establece la fecha y hora en que se debe recuperar el firmware.
     *
     * @param retrieveDateTime ZonedDateTime
     */
    public void setRetrieveDateTime(ZonedDateTime retrieveDateTime) {
        this.retrieveDateTime = retrieveDateTime;
    }

    /**
     * Obtiene la fecha y hora en que se debe instalar el firmware.
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getInstallDateTime() {
        return installDateTime;
    }

    /**
     * Establece la fecha y hora en que se debe instalar el firmware.
     *
     * @param installDateTime ZonedDateTime
     */
    public void setInstallDateTime(ZonedDateTime installDateTime) {
        this.installDateTime = installDateTime;
    }

    /**
     * Obtiene el certificado con el que fue firmado el firmware (PEM codificado).
     *
     * @return string[0..5500]
     */
    public String getSigningCertificate() {
        return signingCertificate;
    }

    /**
     * Establece el certificado con el que fue firmado el firmware (PEM codificado).
     *
     * @param signingCertificate string[0..5500]
     */
    public void setSigningCertificate(String signingCertificate) {
        signingCertificateValidator.validate(signingCertificate);
        this.signingCertificate = signingCertificate;
    }

    /**
     * Obtiene la firma del firmware (codificada en Base64).
     *
     * @return string[0..800]
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Establece la firma del firmware (codificada en Base64).
     *
     * @param signature string[0..800]
     */
    public void setSignature(String signature) {
        signatureValidator.validate(signature);
        this.signature = signature;
    }

    /**
     * Valida que los campos requeridos estén correctamente configurados.
     *
     * @return true si es válido; false en caso contrario.
     */
    public boolean validate() {
        return locationValidator.safeValidate(location)
                && retrieveDateTime != null
                && signingCertificateValidator.safeValidate(signingCertificate)
                && signatureValidator.safeValidate(signature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirmwareType that = (FirmwareType) o;
        return Objects.equals(location, that.location)
                && Objects.equals(retrieveDateTime, that.retrieveDateTime)
                && Objects.equals(installDateTime, that.installDateTime)
                && Objects.equals(signingCertificate, that.signingCertificate)
                && Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, retrieveDateTime, installDateTime, signingCertificate, signature);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("location", location)
                .add("retrieveDateTime", retrieveDateTime)
                .add("installDateTime", installDateTime)
                .add("signingCertificate", signingCertificate)
                .add("signature", signature)
                .toString();
    }
}
