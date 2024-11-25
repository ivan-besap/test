package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa un valor de medidor firmado digitalmente.
 * <p>
 * Incluye datos firmados, métodos de codificación y firma, y una clave pública opcional para
 * garantizar la integridad y autenticidad de los valores medidos.
 */
public class SignedMeterValue {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Datos del medidor en formato Base64 firmados digitalmente.
     * Puede contener información adicional como marcas de tiempo y referencias al cliente.
     */
    private String signedMeterData;

    /** Método utilizado para generar la firma digital. */
    private String signingMethod;

    /**
     * Método utilizado para codificar los valores del medidor antes de aplicar el algoritmo de firma
     * digital.
     */
    private String encodingMethod;

    /**
     * Clave pública en formato Base64.
     * Enviada opcionalmente dependiendo de la configuración de la variable
     * `PublicKeyWithSignedMeterValue`.
     */
    private String publicKey;

    /**
     * Constructor de la clase SignedMeterValue.
     *
     * @param signedMeterData Datos firmados digitalmente en formato Base64.
     * @param signingMethod Método utilizado para crear la firma digital.
     * @param encodingMethod Método utilizado para codificar los valores antes de la firma.
     * @param publicKey Clave pública opcional en formato Base64.
     */
    public SignedMeterValue(
            String signedMeterData, String signingMethod, String encodingMethod, String publicKey) {
        setSignedMeterData(signedMeterData);
        setSigningMethod(signingMethod);
        setEncodingMethod(encodingMethod);
        setPublicKey(publicKey);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados.
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
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura los datos personalizados y devuelve la instancia actual.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public SignedMeterValue withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los datos firmados del medidor.
     *
     * @return Datos firmados en formato Base64.
     */
    public String getSignedMeterData() {
        return signedMeterData;
    }

    /**
     * Establece los datos firmados del medidor.
     *
     * @param signedMeterData Datos firmados en formato Base64.
     */
    public void setSignedMeterData(String signedMeterData) {
        if (!isValidSignedMeterData(signedMeterData)) {
            throw new PropertyConstraintException(signedMeterData, "Datos firmados inválidos.");
        }
        this.signedMeterData = signedMeterData;
    }

    /**
     * Verifica si los datos firmados del medidor son válidos.
     *
     * @param signedMeterData Datos firmados a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidSignedMeterData(String signedMeterData) {
        return signedMeterData != null && signedMeterData.length() <= 2500;
    }

    /**
     * Obtiene el método utilizado para generar la firma digital.
     *
     * @return Método de firma.
     */
    public String getSigningMethod() {
        return signingMethod;
    }

    /**
     * Establece el método utilizado para generar la firma digital.
     *
     * @param signingMethod Método de firma.
     */
    public void setSigningMethod(String signingMethod) {
        if (!isValidSigningMethod(signingMethod)) {
            throw new PropertyConstraintException(signingMethod, "Método de firma inválido.");
        }
        this.signingMethod = signingMethod;
    }

    private boolean isValidSigningMethod(String signingMethod) {
        return signingMethod != null && signingMethod.length() <= 50;
    }

    /**
     * Obtiene el método utilizado para codificar los valores antes de la firma.
     *
     * @return Método de codificación.
     */
    public String getEncodingMethod() {
        return encodingMethod;
    }

    /**
     * Establece el método utilizado para codificar los valores antes de la firma.
     *
     * @param encodingMethod Método de codificación.
     */
    public void setEncodingMethod(String encodingMethod) {
        if (!isValidEncodingMethod(encodingMethod)) {
            throw new PropertyConstraintException(encodingMethod, "Método de codificación inválido.");
        }
        this.encodingMethod = encodingMethod;
    }

    /**
     * Verifica si el método de codificación es válido.
     *
     * @param encodingMethod Método de codificación a validar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidEncodingMethod(String encodingMethod) {
        return encodingMethod != null && encodingMethod.length() <= 50;
    }

    /**
     * Obtiene la clave pública utilizada para verificar la firma.
     *
     * @return Clave pública en formato Base64.
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Establece la clave pública utilizada para verificar la firma.
     *
     * @param publicKey Clave pública en formato Base64.
     */
    public void setPublicKey(String publicKey) {
        if (!isValidPublicKey(publicKey)) {
            throw new PropertyConstraintException(publicKey, "Clave pública inválida.");
        }
        this.publicKey = publicKey;
    }

    /**
     * Verifica si la clave pública es válida.
     *
     * @param publicKey Clave pública a validar.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidPublicKey(String publicKey) {
        return publicKey != null && publicKey.length() <= 2500;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidSignedMeterData(signedMeterData)
                && isValidSigningMethod(signingMethod)
                && isValidEncodingMethod(encodingMethod)
                && isValidPublicKey(publicKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SignedMeterValue that = (SignedMeterValue) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(signedMeterData, that.signedMeterData)
                && Objects.equals(signingMethod, that.signingMethod)
                && Objects.equals(encodingMethod, that.encodingMethod)
                && Objects.equals(publicKey, that.publicKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, signedMeterData, signingMethod, encodingMethod, publicKey);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("signedMeterData", signedMeterData)
                .add("signingMethod", signingMethod)
                .add("encodingMethod", encodingMethod)
                .add("publicKey", publicKey)
                .add("isValid", validate())
                .toString();
    }
}
