package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums.Iso15118EVCertificateStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la solicitud de certificado 15118 EV.
 *
 * <p>Esta clase representa la respuesta que envía el sistema en relación con la solicitud de instalación o actualización de un certificado 15118 EV.</p>
 */
public class Get15118EVCertificateResponse extends Confirmation {

    /** Datos personalizados asociados con la solicitud */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la solicitud fue procesada correctamente */
    private Iso15118EVCertificateStatusEnum status;

    /** Información adicional sobre el estado del proceso */
    @Nullable
    private StatusInfo statusInfo;

    /** Respuesta EXI cruda para el EV, codificada en Base64 */
    private String exiResponse;

    /**
     * Constructor principal de la clase.
     *
     * @param status Estado que indica si la solicitud fue procesada correctamente.
     * @param exiResponse Respuesta EXI cruda para el EV, codificada en Base64.
     */
    public Get15118EVCertificateResponse(Iso15118EVCertificateStatusEnum status, String exiResponse) {
        setStatus(status);
        setExiResponse(exiResponse);
    }

    /**
     * Obtiene los datos personalizados de la solicitud.
     *
     * @return Objeto {@link CustomData} con los datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados de la solicitud.
     *
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @throws PropertyConstraintException Si los datos proporcionados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Objeto {@link CustomData} a validar.
     * @return {@code true} si los datos son válidos; {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la respuesta.
     *
     * @param customData Objeto {@link CustomData} a añadir.
     * @return La instancia actual del objeto.
     */
    public Get15118EVCertificateResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud.
     *
     * @return El estado de la solicitud representado por {@link Iso15118EVCertificateStatusEnum}.
     */
    public Iso15118EVCertificateStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud.
     *
     * @param status Estado de la solicitud.
     * @throws PropertyConstraintException Si el estado proporcionado no es válido.
     */
    public void setStatus(Iso15118EVCertificateStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status es inválido");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido; {@code false} en caso contrario.
     */
    private boolean isValidStatus(Iso15118EVCertificateStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado del proceso.
     *
     * @return Objeto {@link StatusInfo} con información adicional.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado del proceso.
     *
     * @param statusInfo Objeto {@link StatusInfo} con la información adicional.
     * @throws PropertyConstraintException Si la información proporcionada no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo es inválido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información adicional es válida.
     *
     * @param statusInfo Información adicional a validar.
     * @return {@code true} si la información es válida; {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado del proceso.
     *
     * @param statusInfo Información adicional a añadir.
     * @return La instancia actual del objeto.
     */
    public Get15118EVCertificateResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Obtiene la respuesta EXI cruda para el EV, codificada en Base64.
     *
     * @return Cadena codificada en Base64 con la respuesta EXI.
     */
    public String getExiResponse() {
        return exiResponse;
    }

    /**
     * Establece la respuesta EXI cruda para el EV, codificada en Base64.
     *
     * @param exiResponse Cadena codificada en Base64 con la respuesta EXI.
     * @throws PropertyConstraintException Si la respuesta proporcionada no es válida.
     */
    public void setExiResponse(String exiResponse) {
        if (!isValidExiResponse(exiResponse)) {
            throw new PropertyConstraintException(exiResponse, "exiResponse es inválido");
        }
        this.exiResponse = exiResponse;
    }

    /**
     * Verifica si la respuesta EXI es válida.
     *
     * @param exiResponse Respuesta EXI a validar.
     * @return {@code true} si la respuesta es válida; {@code false} en caso contrario.
     */
    private boolean isValidExiResponse(String exiResponse) {
        return exiResponse != null && exiResponse.length() <= 5600;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo)
                && isValidExiResponse(exiResponse);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Get15118EVCertificateResponse that = (Get15118EVCertificateResponse) o;
        return Objects.equals(customData, that.customData)
                && status == that.status
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(exiResponse, that.exiResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo, exiResponse);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("exiResponse", exiResponse)
                .add("isValid", validate())
                .toString();
    }
}
