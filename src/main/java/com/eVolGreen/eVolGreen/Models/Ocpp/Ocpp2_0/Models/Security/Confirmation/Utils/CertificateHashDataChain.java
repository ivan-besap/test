package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.Utils.CertificateHashData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.GetCertificateIdUseEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa una cadena de datos hash de certificados.
 *
 * <p>Incluye información sobre el certificado principal, el tipo de certificado,
 * datos personalizados opcionales y datos hash de certificados secundarios (si existen).
 */
public class CertificateHashDataChain {

    /** Datos personalizados opcionales */
    @Nullable
    private CustomData customData;

    /** Datos hash del certificado principal */
    private CertificateHashData certificateHashData;

    /** Tipo del certificado solicitado */
    private GetCertificateIdUseEnum certificateType;

    /** Datos hash de los certificados secundarios en la cadena */
    @Nullable
    private CertificateHashData[] childCertificateHashData;

    /**
     * Constructor de la clase CertificateHashDataChain.
     *
     * @param certificateHashData Datos hash del certificado principal.
     * @param certificateType Tipo del certificado solicitado.
     */
    public CertificateHashDataChain(
            CertificateHashData certificateHashData, GetCertificateIdUseEnum certificateType) {
        setCertificateHashData(certificateHashData);
        setCertificateType(certificateType);
    }

    /**
     * Obtiene los datos personalizados opcionales.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados opcionales.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    public CertificateHashDataChain withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los datos hash del certificado principal.
     *
     * @return Datos hash del certificado principal.
     */
    public CertificateHashData getCertificateHashData() {
        return certificateHashData;
    }

    /**
     * Establece los datos hash del certificado principal.
     *
     * @param certificateHashData Datos hash del certificado principal.
     */
    public void setCertificateHashData(CertificateHashData certificateHashData) {
        if (!isValidCertificateHashData(certificateHashData)) {
            throw new PropertyConstraintException(
                    certificateHashData, "certificateHashData no es válido");
        }
        this.certificateHashData = certificateHashData;
    }

    private boolean isValidCertificateHashData(CertificateHashData certificateHashData) {
        return certificateHashData != null && certificateHashData.validate();
    }

    /**
     * Obtiene el tipo del certificado solicitado.
     *
     * @return Tipo del certificado solicitado.
     */
    public GetCertificateIdUseEnum getCertificateType() {
        return certificateType;
    }

    /**
     * Establece el tipo del certificado solicitado.
     *
     * @param certificateType Tipo del certificado solicitado.
     */
    public void setCertificateType(GetCertificateIdUseEnum certificateType) {
        if (!isValidCertificateType(certificateType)) {
            throw new PropertyConstraintException(certificateType, "certificateType no es válido");
        }
        this.certificateType = certificateType;
    }

    private boolean isValidCertificateType(GetCertificateIdUseEnum certificateType) {
        return certificateType != null;
    }

    /**
     * Obtiene los datos hash de los certificados secundarios.
     *
     * @return Datos hash de los certificados secundarios.
     */
    @Nullable
    public CertificateHashData[] getChildCertificateHashData() {
        return childCertificateHashData;
    }

    /**
     * Establece los datos hash de los certificados secundarios.
     *
     * @param childCertificateHashData Datos hash de los certificados secundarios.
     */
    public void setChildCertificateHashData(
            @Nullable CertificateHashData[] childCertificateHashData) {
        if (!isValidChildCertificateHashData(childCertificateHashData)) {
            throw new PropertyConstraintException(
                    childCertificateHashData, "childCertificateHashData no es válido");
        }
        this.childCertificateHashData = childCertificateHashData;
    }

    private boolean isValidChildCertificateHashData(
            @Nullable CertificateHashData[] childCertificateHashData) {
        return childCertificateHashData == null
                || (childCertificateHashData.length >= 1
                && childCertificateHashData.length <= 4
                && Arrays.stream(childCertificateHashData).allMatch(CertificateHashData::validate));
    }

    public CertificateHashDataChain withChildCertificateHashData(
            @Nullable CertificateHashData[] childCertificateHashData) {
        setChildCertificateHashData(childCertificateHashData);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidCertificateHashData(certificateHashData)
                && isValidCertificateType(certificateType)
                && isValidChildCertificateHashData(childCertificateHashData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateHashDataChain that = (CertificateHashDataChain) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(certificateHashData, that.certificateHashData)
                && Objects.equals(certificateType, that.certificateType)
                && Arrays.equals(childCertificateHashData, that.childCertificateHashData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                certificateHashData,
                certificateType,
                Arrays.hashCode(childCertificateHashData));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("certificateHashData", certificateHashData)
                .add("certificateType", certificateType)
                .add("childCertificateHashData", childCertificateHashData)
                .add("isValid", validate())
                .toString();
    }
}
