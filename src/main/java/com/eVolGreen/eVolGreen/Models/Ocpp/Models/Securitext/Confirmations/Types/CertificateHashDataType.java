package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation.OCPPSecurityExtDatatypes;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation.StringMaxLengthValidationRule;

import java.util.Objects;

/**
 * Clase que representa los datos de hash de un certificado en el sistema OCPP.
 * <p>
 * Esta clase contiene la información relevante sobre el hash del nombre del emisor,
 * la clave del emisor y el número de serie del certificado.
 * </p>
 *
 * <p><b>Validaciones:</b></p>
 * <ul>
 *     <li>El {@code issuerNameHash} y {@code issuerKeyHash} deben ser cadenas de longitud máxima de 128 caracteres.</li>
 *     <li>El {@code serialNumber} debe tener una longitud máxima de 40 caracteres.</li>
 * </ul>
 */
public class CertificateHashDataType {

    private static final transient Validator identifierString128Validator =
            new ValidatorBuilder()
                    .addRule(OCPPSecurityExtDatatypes.identifierString())
                    .addRule(new StringMaxLengthValidationRule(128))
                    .setRequired(true)
                    .build();

    private static final transient Validator serialNumberValidator =
            new ValidatorBuilder()
                    .addRule(OCPPSecurityExtDatatypes.string40())
                    .setRequired(true)
                    .build();

    private HashAlgorithmEnumType hashAlgorithm;
    private String issuerNameHash;
    private String issuerKeyHash;
    private String serialNumber;

    /**
     * Obtiene el algoritmo de hash utilizado.
     *
     * @return {@link HashAlgorithmEnumType} el tipo de algoritmo de hash.
     */
    public HashAlgorithmEnumType getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Establece el algoritmo de hash utilizado.
     *
     * @param hashAlgorithm el algoritmo de hash que se utiliza.
     */
    public void setHashAlgorithm(HashAlgorithmEnumType hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Obtiene el valor hash del nombre del emisor.
     *
     * @return una cadena con la representación hash del nombre del emisor.
     */
    public String getIssuerNameHash() {
        return issuerNameHash;
    }

    /**
     * Establece el valor hash del nombre del emisor.
     *
     * @param issuerNameHash cadena hash del nombre del emisor.
     */
    public void setIssuerNameHash(String issuerNameHash) {
        identifierString128Validator.validate(issuerNameHash);
        this.issuerNameHash = issuerNameHash;
    }

    /**
     * Obtiene el valor hash de la clave pública del emisor.
     *
     * @return una cadena con la representación hash de la clave pública del emisor.
     */
    public String getIssuerKeyHash() {
        return issuerKeyHash;
    }

    /**
     * Establece el valor hash de la clave pública del emisor.
     *
     * @param issuerKeyHash cadena hash de la clave pública del emisor.
     */
    public void setIssuerKeyHash(String issuerKeyHash) {
        identifierString128Validator.validate(issuerKeyHash);
        this.issuerKeyHash = issuerKeyHash;
    }

    /**
     * Obtiene el número de serie del certificado.
     *
     * @return una cadena con el número de serie del certificado.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Establece el número de serie del certificado.
     *
     * @param serialNumber el número de serie del certificado.
     */
    public void setSerialNumber(String serialNumber) {
        serialNumberValidator.validate(serialNumber);
        this.serialNumber = serialNumber;
    }

    /**
     * Valida los datos del hash del certificado.
     *
     * @return {@code true} si todos los datos son válidos, {@code false} de lo contrario.
     */
    public boolean validate() {
        return hashAlgorithm != null
                && identifierString128Validator.safeValidate(issuerNameHash)
                && identifierString128Validator.safeValidate(issuerKeyHash)
                && serialNumberValidator.safeValidate(serialNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateHashDataType that = (CertificateHashDataType) o;
        return Objects.equals(hashAlgorithm, that.hashAlgorithm) &&
                Objects.equals(issuerNameHash, that.issuerNameHash) &&
                Objects.equals(issuerKeyHash, that.issuerKeyHash) &&
                Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashAlgorithm, issuerNameHash, issuerKeyHash, serialNumber);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("hashAlgorithm", hashAlgorithm)
                .add("issuerNameHash", issuerNameHash)
                .add("issuerKeyHash", issuerKeyHash)
                .add("serialNumber", serialNumber)
                .add("isValid", validate())
                .toString();
    }
}
