package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums.GetCertificateStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la solicitud de estado de un certificado utilizando OCSP.
 *
 * <p>Proporciona información sobre si la estación de carga pudo recuperar el estado del certificado
 * mediante OCSP (Online Certificate Status Protocol), junto con detalles adicionales opcionales.
 */
public class GetCertificateStatusResponse extends Confirmation {

    /** Datos personalizados adicionales relacionados con la respuesta. */
    @Nullable
    private CustomData customData;

    /** Indica si la estación de carga pudo recuperar el estado del certificado OCSP. */
    private GetCertificateStatusEnum status;

    /** Información adicional sobre el estado de la respuesta. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Clase OCSPResponse definida en IETF RFC 6960. Codificada en DER (como se especifica en IETF
     * RFC 6960), y luego en base64. Puede omitirse solo si el estado no es Accepted.
     */
    @Nullable
    private String ocspResult;

    /**
     * Constructor para la clase GetCertificateStatusResponse.
     *
     * @param status Indica si la estación de carga pudo recuperar el estado del certificado OCSP.
     */
    public GetCertificateStatusResponse(GetCertificateStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados adicionales relacionados con la respuesta.
     *
     * @return Objeto CustomData con información personalizada, o {@code null} si no está configurado.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales relacionados con la respuesta.
     *
     * @param customData Objeto CustomData con información personalizada, puede ser {@code null}.
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
     * @param customData Objeto CustomData con información personalizada.
     * @return Esta instancia de GetCertificateStatusResponse.
     */
    public GetCertificateStatusResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la recuperación del certificado OCSP.
     *
     * @return Enumeración GetCertificateStatusEnum indicando el estado de la recuperación.
     */
    public GetCertificateStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la recuperación del certificado OCSP.
     *
     * @param status Enumeración GetCertificateStatusEnum indicando el estado de la recuperación.
     * @throws PropertyConstraintException Si el estado proporcionado es nulo.
     */
    public void setStatus(GetCertificateStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status Enumeración GetCertificateStatusEnum a validar.
     * @return {@code true} si el estado es válido, {@code false} de lo contrario.
     */
    private boolean isValidStatus(GetCertificateStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Objeto StatusInfo con información adicional, o {@code null} si no está configurado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Objeto StatusInfo con información adicional.
     * @throws PropertyConstraintException Si la información adicional no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional proporcionada es válida.
     *
     * @param statusInfo Objeto StatusInfo a validar.
     * @return {@code true} si la información adicional es válida o es nula, {@code false} de lo contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Establece información adicional sobre el estado y devuelve esta instancia.
     *
     * @param statusInfo Objeto StatusInfo con información adicional.
     * @return Esta instancia de GetCertificateStatusResponse.
     */
    public GetCertificateStatusResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Obtiene el resultado OCSP codificado en base64.
     *
     * @return Cadena codificada en base64 con el resultado OCSP, o {@code null} si no está configurado.
     */
    @Nullable
    public String getOcspResult() {
        return ocspResult;
    }

    /**
     * Establece el resultado OCSP codificado en base64.
     *
     * @param ocspResult Cadena codificada en base64 con el resultado OCSP.
     * @throws PropertyConstraintException Si el resultado OCSP no es válido.
     */
    public void setOcspResult(@Nullable String ocspResult) {
        if (!isValidOcspResult(ocspResult)) {
            throw new PropertyConstraintException(ocspResult, "ocspResult is invalid");
        }
        this.ocspResult = ocspResult;
    }

    /**
     * Valida si el resultado OCSP proporcionado es válido.
     *
     * @param ocspResult Cadena a validar.
     * @return {@code true} si el resultado es válido o es nulo, {@code false} de lo contrario.
     */
    private boolean isValidOcspResult(@Nullable String ocspResult) {
        return ocspResult == null || ocspResult.length() <= 5500;
    }

    /**
     * Establece el resultado OCSP y devuelve esta instancia.
     *
     * @param ocspResult Cadena codificada en base64 con el resultado OCSP.
     * @return Esta instancia de GetCertificateStatusResponse.
     */
    public GetCertificateStatusResponse withOcspResult(@Nullable String ocspResult) {
        setOcspResult(ocspResult);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo)
                && isValidOcspResult(ocspResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetCertificateStatusResponse that = (GetCertificateStatusResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(ocspResult, that.ocspResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo, ocspResult);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("ocspResult", ocspResult)
                .add("isValid", validate())
                .toString();
    }
}
