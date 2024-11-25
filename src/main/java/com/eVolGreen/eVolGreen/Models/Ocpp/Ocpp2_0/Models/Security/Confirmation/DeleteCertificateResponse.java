package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums.DeleteCertificateStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la solicitud de eliminación de un certificado.
 *
 * <p>Este mensaje es enviado por la estación de carga para indicar si puede procesar la solicitud
 * de eliminación del certificado especificado.
 */
public class DeleteCertificateResponse extends Confirmation {

    /** Información adicional personalizada que se puede incluir en la respuesta. */
    @Nullable
    private CustomData customData;

    /** Estado que indica si la estación de carga puede procesar la solicitud. */
    private DeleteCertificateStatusEnum status;

    /** Información adicional sobre el estado. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor que inicializa el estado de la respuesta.
     *
     * @param status Estado que indica si la estación de carga puede procesar la solicitud.
     */
    public DeleteCertificateResponse(DeleteCertificateStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados incluidos en la respuesta.
     *
     * @return Datos personalizados o {@code null} si no se proporcionaron.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados que se incluirán en la respuesta.
     *
     * @param customData Datos personalizados adicionales.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados proporcionados son correctos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la respuesta.
     *
     * @param customData Datos personalizados a incluir.
     * @return La instancia actualizada de la respuesta.
     */
    public DeleteCertificateResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud procesada por la estación de carga.
     *
     * @return Estado de la solicitud.
     */
    public DeleteCertificateStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la estación de carga puede procesar la solicitud.
     *
     * @param status Estado de la solicitud.
     * @throws PropertyConstraintException Si el estado proporcionado es inválido.
     */
    public void setStatus(DeleteCertificateStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status es inválido");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es correcto.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(DeleteCertificateStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la solicitud.
     *
     * @return Información adicional sobre el estado, o {@code null} si no se proporcionó.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la solicitud.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @throws PropertyConstraintException Si la información proporcionada es inválida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo es inválido");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es correcta.
     *
     * @param statusInfo Información a validar.
     * @return {@code true} si la información es válida, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado de la solicitud.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La instancia actualizada de la respuesta.
     */
    public DeleteCertificateResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida si todos los campos de la respuesta son correctos.
     *
     * @return {@code true} si todos los campos son válidos, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    /**
     * Compara esta instancia con otro objeto.
     *
     * @param o El objeto con el cual comparar.
     * @return {@code true} si ambos objetos son iguales, de lo contrario {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteCertificateResponse that = (DeleteCertificateResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    /**
     * Genera un código hash para esta instancia.
     *
     * @return Código hash de la instancia.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo);
    }

    /**
     * Convierte esta instancia en una representación de cadena legible.
     *
     * @return Una representación de cadena de esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("isValid", validate())
                .toString();
    }
}
