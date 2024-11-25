package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums.GetInstalledCertificateStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Utils.CertificateHashDataChain;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Respuesta al mensaje GetInstalledCertificateIdsRequest.
 *
 * <p>Indica si la estación de carga pudo procesar la solicitud y proporciona la lista de
 * certificados instalados en caso de éxito.
 */
public class GetInstalledCertificateIdsResponse extends Confirmation {

    @Nullable
    private CustomData customData;

    private GetInstalledCertificateStatusEnum status;

    @Nullable
    private StatusInfo statusInfo;

    @Nullable
    private CertificateHashDataChain[] certificateHashDataChain;

    /**
     * Constructor de la clase GetInstalledCertificateIdsResponse.
     *
     * @param status Estado indicando si la estación de carga pudo procesar la solicitud.
     */
    public GetInstalledCertificateIdsResponse(GetInstalledCertificateStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene datos personalizados.
     *
     * @return Objeto CustomData o null si no se ha configurado.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Configura datos personalizados.
     *
     * @param customData Objeto CustomData.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si el objeto CustomData es válido.
     *
     * @param customData Objeto CustomData a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Objeto CustomData.
     * @return La instancia actual.
     */
    public GetInstalledCertificateIdsResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la respuesta.
     *
     * @return Estado de la respuesta.
     */
    public GetInstalledCertificateStatusEnum getStatus() {
        return status;
    }

    /**
     * Configura el estado de la respuesta.
     *
     * @param status Estado de la respuesta.
     */
    public void setStatus(GetInstalledCertificateStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status no es válido");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(GetInstalledCertificateStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Objeto StatusInfo o null si no se ha configurado.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Configura información adicional sobre el estado.
     *
     * @param statusInfo Objeto StatusInfo.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo no es válido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional del estado es válida.
     *
     * @param statusInfo Objeto StatusInfo a verificar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado.
     *
     * @param statusInfo Objeto StatusInfo.
     * @return La instancia actual.
     */
    public GetInstalledCertificateIdsResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Obtiene la lista de certificados instalados.
     *
     * @return Array de objetos CertificateHashDataChain o null si no se ha configurado.
     */
    @Nullable
    public CertificateHashDataChain[] getCertificateHashDataChain() {
        return certificateHashDataChain;
    }

    /**
     * Configura la lista de certificados instalados.
     *
     * @param certificateHashDataChain Array de objetos CertificateHashDataChain.
     */
    public void setCertificateHashDataChain(
            @Nullable CertificateHashDataChain[] certificateHashDataChain) {
        if (!isValidCertificateHashDataChain(certificateHashDataChain)) {
            throw new PropertyConstraintException(
                    certificateHashDataChain, "certificateHashDataChain no es válido");
        }
        this.certificateHashDataChain = certificateHashDataChain;
    }

    /**
     * Verifica si la lista de certificados es válida.
     *
     * @param certificateHashDataChain Lista de certificados a verificar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidCertificateHashDataChain(
            @Nullable CertificateHashDataChain[] certificateHashDataChain) {
        return certificateHashDataChain == null
                || (certificateHashDataChain.length >= 1
                && Arrays.stream(certificateHashDataChain).allMatch(item -> item.validate()));
    }

    /**
     * Añade la lista de certificados instalados.
     *
     * @param certificateHashDataChain Array de objetos CertificateHashDataChain.
     * @return La instancia actual.
     */
    public GetInstalledCertificateIdsResponse withCertificateHashDataChain(
            @Nullable CertificateHashDataChain[] certificateHashDataChain) {
        setCertificateHashDataChain(certificateHashDataChain);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo)
                && isValidCertificateHashDataChain(certificateHashDataChain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetInstalledCertificateIdsResponse that = (GetInstalledCertificateIdsResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo)
                && Arrays.equals(certificateHashDataChain, that.certificateHashDataChain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, status, statusInfo, Arrays.hashCode(certificateHashDataChain));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("certificateHashDataChain", certificateHashDataChain)
                .add("isValid", validate())
                .toString();
    }
}
