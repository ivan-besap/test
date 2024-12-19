package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.HashAlgorithmEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa los datos necesarios para realizar una solicitud OCSP (Online Certificate Status Protocol)
 * en el contexto del protocolo OCPP 2.0.1.
 *
 * <p>Incluye información sobre algoritmos de hash, valores hash de certificados, números de serie y
 * la URL del respondedor OCSP.
 */
public class OCSPRequestData {

    /** Datos personalizados para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Algoritmo de hash utilizado. */
    private HashAlgorithmEnum hashAlgorithm;

    /** Valor hash del nombre del emisor (Issuer DN - Distinguished Name). */
    private String issuerNameHash;

    /** Valor hash de la clave pública del emisor. */
    private String issuerKeyHash;

    /** Número de serie del certificado. */
    private String serialNumber;

    /** URL del respondedor OCSP (insensible a mayúsculas/minúsculas). */
    private String responderURL;

    /**
     * Constructor para la clase OCSPRequestData.
     *
     * @param hashAlgorithm Algoritmo de hash utilizado.
     * @param issuerNameHash Valor hash del nombre del emisor (Issuer DN).
     * @param issuerKeyHash Valor hash de la clave pública del emisor.
     * @param serialNumber Número de serie del certificado.
     * @param responderURL URL del respondedor OCSP.
     */
    public OCSPRequestData(
            HashAlgorithmEnum hashAlgorithm,
            String issuerNameHash,
            String issuerKeyHash,
            String serialNumber,
            String responderURL) {
        setHashAlgorithm(hashAlgorithm);
        setIssuerNameHash(issuerNameHash);
        setIssuerKeyHash(issuerKeyHash);
        setSerialNumber(serialNumber);
        setResponderURL(responderURL);
    }

    /** @return Datos personalizados para la solicitud. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados para la solicitud.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de OCSPRequestData.
     */
    public OCSPRequestData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return Algoritmo de hash utilizado. */
    public HashAlgorithmEnum getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Establece el algoritmo de hash utilizado.
     *
     * @param hashAlgorithm Algoritmo de hash.
     */
    public void setHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
        if (!isValidHashAlgorithm(hashAlgorithm)) {
            throw new PropertyConstraintException(hashAlgorithm, "El algoritmo de hash no es válido");
        }
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Verifica si el algoritmo de hash es válido.
     *
     * @param hashAlgorithm Algoritmo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidHashAlgorithm(HashAlgorithmEnum hashAlgorithm) {
        return hashAlgorithm != null;
    }

    /** @return Valor hash del nombre del emisor. */
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
            throw new PropertyConstraintException(issuerNameHash, "El hash del nombre del emisor no es válido");
        }
        this.issuerNameHash = issuerNameHash;
    }

    /**
     * Verifica si el valor hash del nombre del emisor es válido.
     *
     * @param issuerNameHash Valor hash a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidIssuerNameHash(String issuerNameHash) {
        return issuerNameHash != null && issuerNameHash.length() <= 128;
    }

    /** @return Valor hash de la clave pública del emisor. */
    public String getIssuerKeyHash() {
        return issuerKeyHash;
    }

    /**
     * Establece el valor hash de la clave pública del emisor.
     *
     * @param issuerKeyHash Valor hash de la clave pública.
     */
    public void setIssuerKeyHash(String issuerKeyHash) {
        if (!isValidIssuerKeyHash(issuerKeyHash)) {
            throw new PropertyConstraintException(issuerKeyHash, "El hash de la clave pública no es válido");
        }
        this.issuerKeyHash = issuerKeyHash;
    }

    /**
     * Verifica si el valor hash de la clave pública del emisor es válido.
     *
     * @param issuerKeyHash Valor hash a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidIssuerKeyHash(String issuerKeyHash) {
        return issuerKeyHash != null && issuerKeyHash.length() <= 128;
    }

    /** @return Número de serie del certificado. */
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
            throw new PropertyConstraintException(serialNumber, "El número de serie no es válido");
        }
        this.serialNumber = serialNumber;
    }

    /**
     * Verifica si el número de serie del certificado es válido.
     *
     * @param serialNumber Número de serie a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidSerialNumber(String serialNumber) {
        return serialNumber != null && serialNumber.length() <= 40;
    }

    /** @return URL del respondedor OCSP. */
    public String getResponderURL() {
        return responderURL;
    }

    /**
     * Establece la URL del respondedor OCSP.
     *
     * @param responderURL URL del respondedor OCSP.
     */
    public void setResponderURL(String responderURL) {
        if (!isValidResponderURL(responderURL)) {
            throw new PropertyConstraintException(responderURL, "La URL del respondedor no es válida");
        }
        this.responderURL = responderURL;
    }

    /**
     * Verifica si la URL del respondedor OCSP es válida.
     *
     * @param responderURL URL a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidResponderURL(String responderURL) {
        return responderURL != null && responderURL.length() <= 512;
    }

    /**
     * Valida si todos los datos de la solicitud son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidHashAlgorithm(hashAlgorithm)
                && isValidIssuerNameHash(issuerNameHash)
                && isValidIssuerKeyHash(issuerKeyHash)
                && isValidSerialNumber(serialNumber)
                && isValidResponderURL(responderURL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OCSPRequestData that = (OCSPRequestData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(hashAlgorithm, that.hashAlgorithm)
                && Objects.equals(issuerNameHash, that.issuerNameHash)
                && Objects.equals(issuerKeyHash, that.issuerKeyHash)
                && Objects.equals(serialNumber, that.serialNumber)
                && Objects.equals(responderURL, that.responderURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, hashAlgorithm, issuerNameHash, issuerKeyHash, serialNumber, responderURL);
    }

    @Override
    public String toString() {
        return "OCSPRequestData{" +
                "customData=" + customData +
                ", hashAlgorithm=" + hashAlgorithm +
                ", issuerNameHash='" + issuerNameHash + '\'' +
                ", issuerKeyHash='" + issuerKeyHash + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", responderURL='" + responderURL + '\'' +
                '}';
    }
}
