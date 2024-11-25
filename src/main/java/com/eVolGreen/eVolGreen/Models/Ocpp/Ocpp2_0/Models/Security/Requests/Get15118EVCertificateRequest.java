package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.CertificateActionEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener un certificado 15118 EV.
 * Este mensaje permite a la estación de carga gestionar certificados relacionados con el vehículo eléctrico (EV).
 */
public class Get15118EVCertificateRequest extends RequestWithId {

    /** Datos personalizados para extensiones específicas. */
    @Nullable
    private CustomData customData;

    /**
     * Versión del esquema 15118 actualmente utilizada en la sesión entre el EV y la estación de carga.
     * Necesaria para que el CSMS pueda interpretar el flujo EXI.
     */
    private String iso15118SchemaVersion;

    /** Define si el certificado debe ser instalado o actualizado. */
    private CertificateActionEnum action;

    /** Solicitud cruda de instalación de certificado enviada desde el EV, codificada en Base64. */
    private String exiRequest;

    /**
     * Constructor de la clase Get15118EVCertificateRequest.
     *
     * @param iso15118SchemaVersion Versión del esquema 15118 utilizado en la sesión.
     * @param action Acción requerida sobre el certificado (instalar o actualizar).
     * @param exiRequest Solicitud de instalación de certificado en formato EXI, codificada en Base64.
     */
    public Get15118EVCertificateRequest(
            String iso15118SchemaVersion, CertificateActionEnum action, String exiRequest) {
        setIso15118SchemaVersion(iso15118SchemaVersion);
        setAction(action);
        setExiRequest(exiRequest);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Instancia de {@link CustomData} o {@code null} si no hay datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta solicitud.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Instancia de {@link CustomData} a validar.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asocia datos personalizados con esta solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de {@link Get15118EVCertificateRequest}.
     */
    public Get15118EVCertificateRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la versión del esquema 15118 utilizado en la sesión.
     *
     * @return Versión del esquema 15118.
     */
    public String getIso15118SchemaVersion() {
        return iso15118SchemaVersion;
    }

    /**
     * Establece la versión del esquema 15118 utilizado en la sesión.
     *
     * @param iso15118SchemaVersion Versión del esquema 15118.
     * @throws PropertyConstraintException Si la versión del esquema es inválida.
     */
    public void setIso15118SchemaVersion(String iso15118SchemaVersion) {
        if (!isValidIso15118SchemaVersion(iso15118SchemaVersion)) {
            throw new PropertyConstraintException(iso15118SchemaVersion, "iso15118SchemaVersion es inválido");
        }
        this.iso15118SchemaVersion = iso15118SchemaVersion;
    }

    /**
     * Valida la versión del esquema 15118.
     *
     * @param iso15118SchemaVersion Versión del esquema a validar.
     * @return {@code true} si es válida, {@code false} de lo contrario.
     */
    private boolean isValidIso15118SchemaVersion(String iso15118SchemaVersion) {
        return iso15118SchemaVersion != null && iso15118SchemaVersion.length() <= 50;
    }

    /**
     * Obtiene la acción a realizar sobre el certificado.
     *
     * @return Acción del certificado (instalar o actualizar).
     */
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    /**
     * Establece la acción a realizar sobre el certificado.
     *
     * @param action Acción a realizar.
     * @throws PropertyConstraintException Si la acción es inválida.
     */
    public void setAction(CertificateActionEnum action) {
        if (!isValidAction(action)) {
            throw new PropertyConstraintException(action, "action es inválido");
        }
        this.action = action;
    }

    /**
     * Valida la acción a realizar sobre el certificado.
     *
     * @param action Acción a validar.
     * @return {@code true} si es válida, {@code false} de lo contrario.
     */
    private boolean isValidAction(CertificateActionEnum action) {
        return action != null;
    }

    /**
     * Obtiene la solicitud EXI enviada desde el EV.
     *
     * @return Solicitud EXI codificada en Base64.
     */
    public String getExiRequest() {
        return exiRequest;
    }

    /**
     * Establece la solicitud EXI enviada desde el EV.
     *
     * @param exiRequest Solicitud EXI codificada en Base64.
     * @throws PropertyConstraintException Si la solicitud EXI es inválida.
     */
    public void setExiRequest(String exiRequest) {
        if (!isValidExiRequest(exiRequest)) {
            throw new PropertyConstraintException(exiRequest, "exiRequest es inválido");
        }
        this.exiRequest = exiRequest;
    }

    /**
     * Valida la solicitud EXI enviada desde el EV.
     *
     * @param exiRequest Solicitud EXI a validar.
     * @return {@code true} si es válida, {@code false} de lo contrario.
     */
    private boolean isValidExiRequest(String exiRequest) {
        return exiRequest != null && exiRequest.length() <= 5600;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidIso15118SchemaVersion(iso15118SchemaVersion)
                && isValidAction(action)
                && isValidExiRequest(exiRequest);
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Get15118EVCertificateRequest that = (Get15118EVCertificateRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(iso15118SchemaVersion, that.iso15118SchemaVersion)
                && Objects.equals(action, that.action)
                && Objects.equals(exiRequest, that.exiRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, iso15118SchemaVersion, action, exiRequest);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("iso15118SchemaVersion", iso15118SchemaVersion)
                .add("action", action)
                .add("exiRequest", exiRequest)
                .add("isValid", validate())
                .toString();
    }
}
