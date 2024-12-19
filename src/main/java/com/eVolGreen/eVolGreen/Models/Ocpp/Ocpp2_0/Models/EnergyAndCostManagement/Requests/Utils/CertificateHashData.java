package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.HashAlgorithmEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Datos Hash del Certificado
 *
 * <p>Esta clase representa los datos hash asociados a un certificado, incluyendo información
 * sobre el algoritmo de hash utilizado, los valores hash del nombre del emisor, la clave del emisor
 * y el número de serie del certificado.</p>
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class CertificateHashData {

    /** Datos personalizados adicionales */
    @Nullable
    private CustomData customData;

    /** Algoritmo utilizado para los hashes proporcionados */
    private HashAlgorithmEnum hashAlgorithm;

    /** Valor hash del nombre distinguido (DN) del emisor */
    private String issuerNameHash;

    /** Valor hash de la clave pública del emisor */
    private String issuerKeyHash;

    /** Número de serie del certificado */
    private String serialNumber;

    /**
     * Constructor de la clase CertificateHashData.
     *
     * @param hashAlgorithm Algoritmo utilizado para los hashes proporcionados.
     * @param issuerNameHash Valor hash del nombre distinguido del emisor.
     * @param issuerKeyHash Valor hash de la clave pública del emisor.
     * @param serialNumber Número de serie del certificado.
     */
    public CertificateHashData(
            HashAlgorithmEnum hashAlgorithm,
            String issuerNameHash,
            String issuerKeyHash,
            String serialNumber) {
        setHashAlgorithm(hashAlgorithm);
        setIssuerNameHash(issuerNameHash);
        setIssuerKeyHash(issuerKeyHash);
        setSerialNumber(serialNumber);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
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
     * Asigna datos personalizados de manera fluida.
     *
     * @param customData Datos personalizados adicionales.
     * @return La instancia actual de {@link CertificateHashData}.
     */
    public CertificateHashData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el algoritmo de hash utilizado.
     *
     * @return Algoritmo de hash utilizado.
     */
    public HashAlgorithmEnum getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Establece el algoritmo de hash utilizado.
     *
     * @param hashAlgorithm Algoritmo de hash utilizado.
     */
    public void setHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
        if (!isValidHashAlgorithm(hashAlgorithm)) {
            throw new PropertyConstraintException(hashAlgorithm,"El algoritmo de hash no puede ser nulo.");
        }
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Verifica si el algoritmo de hash proporcionado es válido.
     *
     * @param hashAlgorithm El algoritmo de hash a validar.
     * @return {@code true} si el algoritmo de hash es válido, de lo contrario {@code false}.
     */
    private boolean isValidHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
        return hashAlgorithm != null;
    }

    /**
     * Asigna el algoritmo de hash de manera fluida.
     *
     * @param hashAlgorithm El algoritmo de hash a establecer.
     * @return La instancia actual de {@link CertificateHashData}.
     */
    public CertificateHashData withHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
        setHashAlgorithm(hashAlgorithm);
        return this;
    }

    /**
     * Obtiene el valor hash del nombre del emisor.
     *
     * @return Valor hash del nombre del emisor.
     */
    public String getIssuerNameHash() {
        return issuerNameHash;
    }

    /**
     * Establece el valor hash del nombre del emisor.
     *
     * @param issuerNameHash Valor hash del nombre del emisor.
     */
    public void setIssuerNameHash(String issuerNameHash) {
        if (!isValidIssuerNameHash(issuerNameHash)) {
            throw new PropertyConstraintException(issuerNameHash, "Hash del nombre del emisor inválido.");
        }
        this.issuerNameHash = issuerNameHash;
    }

    /**
     * Verifica si el valor hash del nombre del emisor es válido.
     *
     * @param issuerNameHash El valor hash del nombre del emisor a validar.
     * @return {@code true} si el valor es válido, de lo contrario {@code false}.
     */
    private boolean isValidIssuerNameHash(String issuerNameHash) {
        return issuerNameHash != null && issuerNameHash.length() <= 128;
    }

    /** Obtiene el valor hash de la clave pública del emisor. */
    public String getIssuerKeyHash() {
        return issuerKeyHash;
    }

    /**
     * Establece el valor hash de la clave pública del emisor.
     *
     * @param issuerKeyHash Valor hash de la clave pública del emisor.
     */
    public void setIssuerKeyHash(String issuerKeyHash) {
        if (!isValidIssuerKeyHash(issuerKeyHash)) {
            throw new PropertyConstraintException(issuerKeyHash, "Hash de la clave del emisor inválido.");
        }
        this.issuerKeyHash = issuerKeyHash;
    }

    /**
     * Verifica si el valor hash de la clave pública del emisor es válido.
     *
     * @param issuerKeyHash El valor hash de la clave pública del emisor a validar.
     * @return {@code true} si el valor es válido, de lo contrario {@code false}.
     */
    private boolean isValidIssuerKeyHash(String issuerKeyHash) {
        return issuerKeyHash != null && issuerKeyHash.length() <= 128;
    }

    /**Obtiene el número de serie del certificado. */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Establece el número de serie del certificado.
     *
     * @param serialNumber Número de serie del certificado.
     */
    public void setSerialNumber(String serialNumber) {
        if (!isValidSerialNumber(serialNumber)) {
            throw new PropertyConstraintException(serialNumber, "Número de serie inválido.");
        }
        this.serialNumber = serialNumber;
    }

    /**
     * Verifica si el número de serie del certificado es válido.
     *
     * @param serialNumber El número de serie del certificado a validar.
     * @return {@code true} si el número de serie es válido, de lo contrario {@code false}.
     */
    private boolean isValidSerialNumber(String serialNumber) {
        return serialNumber != null && serialNumber.length() <= 40;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidHashAlgorithm(hashAlgorithm)
                && isValidIssuerNameHash(issuerNameHash)
                && isValidIssuerKeyHash(issuerKeyHash)
                && isValidSerialNumber(serialNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateHashData that = (CertificateHashData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(hashAlgorithm, that.hashAlgorithm)
                && Objects.equals(issuerNameHash, that.issuerNameHash)
                && Objects.equals(issuerKeyHash, that.issuerKeyHash)
                && Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, hashAlgorithm, issuerNameHash, issuerKeyHash, serialNumber);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("hashAlgorithm", hashAlgorithm)
                .add("issuerNameHash", issuerNameHash)
                .add("issuerKeyHash", issuerKeyHash)
                .add("serialNumber", serialNumber)
                .add("isValid", validate())
                .toString();
    }
}
