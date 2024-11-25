package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Firmware
 *
 * <p>Clase que representa los detalles de un firmware que puede ser cargado o actualizado en una estación de carga.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class Firmware {
    /**
     * Datos personalizados adicionales.
     */
    @Nullable
    private CustomData customData;

    /**
     * URI que define el origen del firmware.
     */
    private String location;

    /**
     * Fecha y hora en la que se debe recuperar el firmware.
     */
    private ZonedDateTime retrieveDateTime;

    /**
     * Fecha y hora en la que se debe instalar el firmware (opcional).
     */
    @Nullable
    private ZonedDateTime installDateTime;

    /**
     * Certificado con el que se firmó el firmware. Codificado en formato PEM.
     */
    @Nullable
    private String signingCertificate;

    /**
     * Firma del firmware codificada en Base64 (opcional).
     */
    @Nullable
    private String signature;

    /**
     * Constructor principal de la clase Firmware.
     *
     * @param location URI del firmware.
     * @param retrieveDateTime Fecha y hora de recuperación.
     */
    public Firmware(String location, ZonedDateTime retrieveDateTime) {
        setLocation(location);
        setRetrieveDateTime(retrieveDateTime);
    }

    /**
     * Obtiene datos personalizados adicionales.
     *
     * @return Datos personalizados (opcional).
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados adicionales.
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
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia actualizada.
     */
    public Firmware withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la URI del firmware.
     *
     * @return URI del firmware.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Establece la URI del firmware.
     *
     * @param location URI del firmware.
     */
    public void setLocation(String location) {
        if (!isValidLocation(location)) {
            throw new PropertyConstraintException(location, "location is invalid");
        }
        this.location = location;
    }

    /**
     * Valida si la URI es válida.
     *
     * @param location URI a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidLocation(String location) {
        return location != null && location.length() <= 512;
    }

    /**
     * Obtiene la fecha y hora en la que se debe recuperar el firmware.
     *
     * @return Fecha y hora de recuperación.
     */
    public ZonedDateTime getRetrieveDateTime() {
        return retrieveDateTime;
    }

    /**
     * Establece la fecha y hora en la que se debe recuperar el firmware.
     *
     * @param retrieveDateTime Fecha y hora de recuperación.
     */
    public void setRetrieveDateTime(ZonedDateTime retrieveDateTime) {
        if (!isValidRetrieveDateTime(retrieveDateTime)) {
            throw new PropertyConstraintException(retrieveDateTime, "retrieveDateTime is invalid");
        }
        this.retrieveDateTime = retrieveDateTime;
    }

    /**
     * Valida si la fecha y hora de recuperación son válidas.
     *
     * @param retrieveDateTime Fecha y hora a validar.
     * @return {@code true} si son válidas, {@code false} en caso contrario.
     */
    private boolean isValidRetrieveDateTime(ZonedDateTime retrieveDateTime) {
        return retrieveDateTime != null;
    }

    /**
     * Obtiene la fecha y hora en la que se debe instalar el firmware (opcional).
     *
     * @return Fecha y hora de instalación.
     */
    @Nullable
    public ZonedDateTime getInstallDateTime() {
        return installDateTime;
    }

    /**
     * Establece la fecha y hora en la que se debe instalar el firmware.
     *
     * @param installDateTime Fecha y hora de instalación.
     */
    public void setInstallDateTime(@Nullable ZonedDateTime installDateTime) {
        this.installDateTime = installDateTime;
    }

    /**
     * Añade la fecha y hora en la que se debe instalar el firmware.
     *
     * @param installDateTime Fecha y hora de instalación.
     * @return Esta instancia actualizada.
     */
    public Firmware withInstallDateTime(@Nullable ZonedDateTime installDateTime) {
        setInstallDateTime(installDateTime);
        return this;
    }

    /**
     * Obtiene el certificado de firma del firmware.
     *
     * @return Certificado de firma.
     */
    @Nullable
    public String getSigningCertificate() {
        return signingCertificate;
    }

    /**
     * Establece el certificado de firma del firmware.
     *
     * @param signingCertificate Certificado en formato PEM.
     */
    public void setSigningCertificate(@Nullable String signingCertificate) {
        if (!isValidSigningCertificate(signingCertificate)) {
            throw new PropertyConstraintException(signingCertificate, "signingCertificate is invalid");
        }
        this.signingCertificate = signingCertificate;
    }

    /**
     * Valida si el certificado de firma es válido.
     *
     * @param signingCertificate Certificado a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidSigningCertificate(@Nullable String signingCertificate) {
        return signingCertificate == null || signingCertificate.length() <= 5500;
    }

    /**
     * Obtiene la firma del firmware codificada en Base64.
     *
     * @return Firma del firmware.
     */
    @Nullable
    public String getSignature() {
        return signature;
    }

    /**
     * Establece la firma del firmware codificada en Base64.
     *
     * @param signature Firma del firmware.
     */
    public void setSignature(@Nullable String signature) {
        if (!isValidSignature(signature)) {
            throw new PropertyConstraintException(signature, "signature is invalid");
        }
        this.signature = signature;
    }

    /**
     * Valida si la firma es válida.
     *
     * @param signature Firma a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidSignature(@Nullable String signature) {
        return signature == null || signature.length() <= 800;
    }

    /**
     * Añade la firma del firmware.
     *
     * @return Esta instancia actualizada.
     */
    public Firmware withSignature(@Nullable String signature) {
        setSignature(signature);
        return this;
    }


    /**
     * Valida si los datos proporcionados son válidos.
     *
     * @return {@code true} si todos los campos son válidos.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidLocation(location)
                && isValidRetrieveDateTime(retrieveDateTime)
                && isValidSigningCertificate(signingCertificate)
                && isValidSignature(signature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Firmware that = (Firmware) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(location, that.location)
                && Objects.equals(retrieveDateTime, that.retrieveDateTime)
                && Objects.equals(installDateTime, that.installDateTime)
                && Objects.equals(signingCertificate, that.signingCertificate)
                && Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, location, retrieveDateTime, installDateTime, signingCertificate, signature);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("location", location)
                .add("retrieveDateTime", retrieveDateTime)
                .add("installDateTime", installDateTime)
                .add("signingCertificate", signingCertificate)
                .add("signature", signature)
                .add("isValid", validate())
                .toString();
    }
}
