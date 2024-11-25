package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.OCSPRequestData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


/**
 * Representa una solicitud de autorización en el protocolo OCPP 2.0.1.
 *
 * <p>Esta clase encapsula los datos necesarios para validar la autorización de un usuario o
 * dispositivo en una estación de carga. Incluye soporte para datos personalizados, tokens de
 * identificación y certificados digitales.
 */
public class AuthorizeRequest extends RequestWithId {

    /** Datos personalizados para la solicitud. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador insensible a mayúsculas y minúsculas utilizado para la autorización, que soporta
     * múltiples formas de identificación.
     */
    private IdToken idToken;

    /** Certificado X.509 presentado por el vehículo eléctrico, codificado en formato PEM. */
    @Nullable private String certificate;

    /** Datos de hash de certificados iso15118. */
    @Nullable private OCSPRequestData[] iso15118CertificateHashData;

    /**
     * Constructor de la clase AuthorizeRequest.
     *
     * @param idToken Identificador utilizado para la autorización.
     */
    public AuthorizeRequest(IdToken idToken) {
        setIdToken(idToken);
    }

    /** @return Datos personalizados para la solicitud. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la solicitud.
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
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return Instancia actualizada de AuthorizeRequest.
     */
    public AuthorizeRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return Identificador utilizado para la autorización. */
    public IdToken getIdToken() {
        return idToken;
    }

    /**
     * Establece el identificador utilizado para la autorización.
     *
     * @param idToken Identificador.
     */
    public void setIdToken(IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "El token de identificación no es válido");
        }
        this.idToken = idToken;
    }

    /**
     * Verifica si el token de identificación es válido.
     *
     * @param idToken Token a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidIdToken(IdToken idToken) {
        return idToken != null && idToken.validate();
    }

    /** @return Certificado X.509 presentado por el vehículo eléctrico. */
    @Nullable
    public String getCertificate() {
        return certificate;
    }

    /**
     * Establece el certificado X.509 presentado por el vehículo eléctrico.
     *
     * @param certificate Certificado codificado en formato PEM.
     */
    public void setCertificate(@Nullable String certificate) {
        if (!isValidCertificate(certificate)) {
            throw new PropertyConstraintException(certificate, "El certificado no es válido");
        }
        this.certificate = certificate;
    }

    /**
     * Verifica si el certificado es válido.
     *
     * @param certificate Certificado a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidCertificate(@Nullable String certificate) {
        return certificate == null || certificate.length() <= 5500;
    }

    /**
     * Agrega un certificado X.509 a la solicitud.
     *
     * @param certificate Certificado en formato PEM.
     * @return Instancia actualizada de AuthorizeRequest.
     */
    public AuthorizeRequest withCertificate(@Nullable String certificate) {
        setCertificate(certificate);
        return this;
    }

    /** @return Datos de hash de certificados iso15118. */
    @Nullable
    public OCSPRequestData[] getIso15118CertificateHashData() {
        return iso15118CertificateHashData;
    }

    /**
     * Establece los datos de hash de certificados iso15118.
     *
     * @param iso15118CertificateHashData Arreglo de datos de hash.
     */
    public void setIso15118CertificateHashData(
            @Nullable OCSPRequestData[] iso15118CertificateHashData) {
        if (!isValidIso15118CertificateHashData(iso15118CertificateHashData)) {
            throw new PropertyConstraintException(
                    iso15118CertificateHashData, "Los datos de hash no son válidos");
        }
        this.iso15118CertificateHashData = iso15118CertificateHashData;
    }

    /**
     * Verifica si los datos de hash son válidos.
     *
     * @param iso15118CertificateHashData Datos de hash a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidIso15118CertificateHashData(
            @Nullable OCSPRequestData[] iso15118CertificateHashData) {
        return iso15118CertificateHashData == null
                || (iso15118CertificateHashData.length >= 1
                && iso15118CertificateHashData.length <= 4
                && Arrays.stream(iso15118CertificateHashData).allMatch(item -> item.validate()));
    }

    /**
     * Agrega datos de hash de certificados iso15118.
     *
     * @param iso15118CertificateHashData Datos de hash.
     * @return Instancia actualizada de AuthorizeRequest.
     */
    public AuthorizeRequest withIso15118CertificateHashData(
            @Nullable OCSPRequestData[] iso15118CertificateHashData) {
        setIso15118CertificateHashData(iso15118CertificateHashData);
        return this;
    }

    /** Valida si la solicitud contiene datos correctos. */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidIdToken(idToken)
                && isValidCertificate(certificate)
                && isValidIso15118CertificateHashData(iso15118CertificateHashData);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code true} si la solicitud está relacionada con una transacción, {@code false} en caso contrario.
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
     * Compara este objeto con otro para determinar si son iguales.
     *
     * @param o El objeto a comparar.
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
        AuthorizeRequest that = (AuthorizeRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(idToken, that.idToken)
                && Objects.equals(certificate, that.certificate)
                && Arrays.equals(iso15118CertificateHashData, that.iso15118CertificateHashData);
    }

    /**
     * Calcula el código hash para este objeto.
     *
     * @return Un valor entero que representa el código hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                customData, idToken, certificate, Arrays.hashCode(iso15118CertificateHashData));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("idToken", idToken)
                .add("certificate", certificate)
                .add("iso15118CertificateHashData", iso15118CertificateHashData)
                .add("isValid", validate())
                .toString();
    }
}
