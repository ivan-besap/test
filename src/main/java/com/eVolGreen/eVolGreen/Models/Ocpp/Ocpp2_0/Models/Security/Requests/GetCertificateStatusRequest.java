package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.OCSPRequestData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener el estado de un certificado mediante OCSP (Online Certificate Status Protocol).
 *
 * <p>Esta clase define los datos requeridos para realizar la solicitud al CSMS para validar el estado
 * de un certificado digital, proporcionando información específica como los datos del OCSP.</p>
 */
public class GetCertificateStatusRequest extends RequestWithId {

    /** Datos personalizados adicionales relacionados con la solicitud. */
    @Nullable
    private CustomData customData;

    /** Datos de la solicitud OCSP requeridos para validar el estado del certificado. */
    private OCSPRequestData ocspRequestData;

    /**
     * Constructor para la clase GetCertificateStatusRequest.
     *
     * @param ocspRequestData Datos de la solicitud OCSP necesarios para validar el estado del certificado.
     */
    public GetCertificateStatusRequest(OCSPRequestData ocspRequestData) {
        setOcspRequestData(ocspRequestData);
    }

    /**
     * Obtiene los datos personalizados relacionados con la solicitud.
     *
     * @return Objeto CustomData que contiene información personalizada, o {@code null} si no está configurado.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con la solicitud.
     *
     * @param customData Objeto CustomData que contiene información personalizada, puede ser {@code null}.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados proporcionados son válidos.
     *
     * @param customData Objeto CustomData a validar.
     * @return {@code true} si los datos personalizados son válidos o son nulos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados y devuelve esta instancia.
     *
     * @param customData Objeto CustomData que contiene información personalizada.
     * @return Esta instancia de GetCertificateStatusRequest.
     */
    public GetCertificateStatusRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los datos de la solicitud OCSP necesarios para validar el certificado.
     *
     * @return Objeto OCSPRequestData que contiene la información necesaria para la validación.
     */
    public OCSPRequestData getOcspRequestData() {
        return ocspRequestData;
    }

    /**
     * Establece los datos de la solicitud OCSP necesarios para validar el certificado.
     *
     * @param ocspRequestData Objeto OCSPRequestData que contiene la información necesaria para la validación.
     * @throws PropertyConstraintException Si los datos de la solicitud OCSP no son válidos.
     */
    public void setOcspRequestData(OCSPRequestData ocspRequestData) {
        if (!isValidOcspRequestData(ocspRequestData)) {
            throw new PropertyConstraintException(ocspRequestData, "ocspRequestData is invalid");
        }
        this.ocspRequestData = ocspRequestData;
    }

    /**
     * Valida si los datos de la solicitud OCSP proporcionados son válidos.
     *
     * @param ocspRequestData Objeto OCSPRequestData a validar.
     * @return {@code true} si los datos de la solicitud son válidos, {@code false} de lo contrario.
     */
    private boolean isValidOcspRequestData(OCSPRequestData ocspRequestData) {
        return ocspRequestData != null && ocspRequestData.validate();
    }

    /**
     * Valida la instancia actual de GetCertificateStatusRequest.
     *
     * @return {@code true} si la instancia es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidOcspRequestData(ocspRequestData);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción en curso.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con transacciones.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return null;
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    /**
     * Compara esta instancia con otro objeto.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetCertificateStatusRequest that = (GetCertificateStatusRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(ocspRequestData, that.ocspRequestData);
    }

    /**
     * Calcula el hash code de la instancia.
     *
     * @return Valor hash code calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, ocspRequestData);
    }

    /**
     * Devuelve una representación en formato String de la instancia.
     *
     * @return Cadena con los valores de los atributos y el estado de validación.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("ocspRequestData", ocspRequestData)
                .add("isValid", validate())
                .toString();
    }
}
