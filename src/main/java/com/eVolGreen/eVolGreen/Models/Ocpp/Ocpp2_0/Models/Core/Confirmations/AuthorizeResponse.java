package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.AuthorizeCertificateStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.IdTokenInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;


/**
 * Representa la respuesta a una solicitud de autorización en el protocolo OCPP 2.0.1.
 *
 * <p>Proporciona información sobre el estado del token de identificación, datos personalizados y
 * el estado del certificado utilizado para la autorización.
 */
public class AuthorizeResponse extends Confirmation {

    /** Datos personalizados asociados con la respuesta. */
    @Nullable
    private CustomData customData;

    /**
     * Información del token de identificación.
     *
     * <p>Incluye el estado del identificador. Se recomienda no detener la carga para un token que
     * expire durante la carga, ya que la fecha de expiración solo se utiliza con fines de
     * almacenamiento en caché. Si no se proporciona una fecha de expiración, el estado no tiene
     * fecha de fin.
     */
    private IdTokenInfo idTokenInfo;

    /**
     * Estado del certificado utilizado para la autorización.
     *
     * <pre>
     * - Si todos los certificados son válidos: devuelve 'Accepted'.
     * - Si uno de los certificados ha sido revocado: devuelve 'CertificateRevoked'.
     * </pre>
     */
    @Nullable
    private AuthorizeCertificateStatusEnum certificateStatus;

    /**
     * Constructor de la clase AuthorizeResponse.
     *
     * @param idTokenInfo Información sobre el estado del token de identificación.
     */
    public AuthorizeResponse(IdTokenInfo idTokenInfo) {
        setIdTokenInfo(idTokenInfo);
    }

    /** @return Datos personalizados asociados con la respuesta. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados con la respuesta.
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
     * Agrega datos personalizados asociados con la respuesta.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de AuthorizeResponse.
     */
    public AuthorizeResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return Información sobre el estado del token de identificación. */
    public IdTokenInfo getIdTokenInfo() {
        return idTokenInfo;
    }

    /**
     * Establece información sobre el estado del token de identificación.
     *
     * @param idTokenInfo Información sobre el estado del token.
     */
    public void setIdTokenInfo(IdTokenInfo idTokenInfo) {
        if (!isValidIdTokenInfo(idTokenInfo)) {
            throw new PropertyConstraintException(idTokenInfo, "La información del token no es válida");
        }
        this.idTokenInfo = idTokenInfo;
    }

    /**
     * Verifica si la información del token de identificación es válida.
     *
     * @param idTokenInfo Información del token a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidIdTokenInfo(IdTokenInfo idTokenInfo) {
        return idTokenInfo != null && idTokenInfo.validate();
    }

    /** @return Estado del certificado utilizado para la autorización. */
    @Nullable
    public AuthorizeCertificateStatusEnum getCertificateStatus() {
        return certificateStatus;
    }

    /**
     * Establece el estado del certificado utilizado para la autorización.
     *
     * @param certificateStatus Estado del certificado.
     */
    public void setCertificateStatus(@Nullable AuthorizeCertificateStatusEnum certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    /**
     * Agrega el estado del certificado utilizado para la autorización.
     *
     * @param certificateStatus Estado del certificado.
     * @return La instancia actualizada de AuthorizeResponse.
     */
    public AuthorizeResponse withCertificateStatus(
            @Nullable AuthorizeCertificateStatusEnum certificateStatus) {
        setCertificateStatus(certificateStatus);
        return this;
    }

    /**
     * Valida si los datos de la respuesta son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidIdTokenInfo(idTokenInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizeResponse that = (AuthorizeResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(idTokenInfo, that.idTokenInfo)
                && Objects.equals(certificateStatus, that.certificateStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, idTokenInfo, certificateStatus);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("idTokenInfo", idTokenInfo)
                .add("certificateStatus", certificateStatus)
                .add("isValid", validate())
                .toString();
    }
}
