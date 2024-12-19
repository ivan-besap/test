package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.GetCertificateIdUseEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener los IDs de los certificados instalados.
 *
 * <p>Permite especificar el tipo de certificados a consultar, devolviendo todos los tipos si no se
 * especifica.
 */
public class GetInstalledCertificateIdsRequest extends RequestWithId {

    /** Datos personalizados. */
    @Nullable
    private CustomData customData;

    /**
     * Tipos de certificados solicitados. Si no se especifica, se devuelven todos los tipos de
     * certificados.
     */
    @Nullable private GetCertificateIdUseEnum[] certificateType;

    /** Constructor vacío para la clase GetInstalledCertificateIdsRequest. */
    public GetInstalledCertificateIdsRequest() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
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
     * Valida los datos personalizados proporcionados.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al objeto de solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de la solicitud.
     */
    public GetInstalledCertificateIdsRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los tipos de certificados solicitados.
     *
     * @return Un arreglo con los tipos de certificados solicitados, o null si no se especifican.
     */
    @Nullable
    public GetCertificateIdUseEnum[] getCertificateType() {
        return certificateType;
    }

    /**
     * Establece los tipos de certificados solicitados.
     *
     * @param certificateType Tipos de certificados solicitados.
     */
    public void setCertificateType(@Nullable GetCertificateIdUseEnum[] certificateType) {
        if (!isValidCertificateType(certificateType)) {
            throw new PropertyConstraintException(certificateType, "Los tipos de certificados no son válidos");
        }
        this.certificateType = certificateType;
    }

    /**
     * Valida el arreglo de tipos de certificados proporcionados.
     *
     * @param certificateType Arreglo con los tipos de certificados a validar.
     * @return {@code true} si el arreglo es válido, de lo contrario {@code false}.
     */
    private boolean isValidCertificateType(@Nullable GetCertificateIdUseEnum[] certificateType) {
        return certificateType == null || (certificateType.length >= 1);
    }

    /**
     * Agrega los tipos de certificados solicitados al objeto de solicitud.
     *
     * @param certificateType Tipos de certificados solicitados.
     * @return La instancia actual de la solicitud.
     */
    public GetInstalledCertificateIdsRequest withCertificateType(
            @Nullable GetCertificateIdUseEnum[] certificateType) {
        setCertificateType(certificateType);
        return this;
    }

    /**
     * Valida la solicitud actual.
     *
     * @return {@code true} si la solicitud es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidCertificateType(certificateType);
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con una transacción.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetInstalledCertificateIdsRequest that = (GetInstalledCertificateIdsRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(certificateType, that.certificateType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(certificateType));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("certificateType", certificateType)
                .add("isValid", validate())
                .toString();
    }
}
