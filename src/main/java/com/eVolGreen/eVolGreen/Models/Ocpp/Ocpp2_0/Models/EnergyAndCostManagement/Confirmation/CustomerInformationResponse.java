package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.Enums.CustomerInformationStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la solicitud de información del cliente.
 *
 * <p>Esta clase representa la respuesta a una solicitud para obtener o borrar información
 * de un cliente, indicando si fue aceptada y proporcionando detalles adicionales.</p>
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class CustomerInformationResponse extends Confirmation {

    /** Datos personalizados adicionales */
    @Nullable
    private CustomData customData;

    /** Estado de la solicitud */
    private CustomerInformationStatusEnum status;

    /** Información adicional sobre el estado */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase CustomerInformationResponse.
     *
     * @param status Estado de la solicitud.
     */
    public CustomerInformationResponse(CustomerInformationStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados adicionales.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales a la respuesta.
     *
     * @param customData Datos personalizados adicionales.
     * @return La respuesta actualizada.
     */
    public CustomerInformationResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado de la solicitud.
     *
     * @return Estado de la solicitud.
     */
    public CustomerInformationStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud.
     *
     * @param status Estado de la solicitud.
     */
    public void setStatus(CustomerInformationStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status,"El estado no puede ser nulo.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado de la solicitud.
     * @return {@code true} si el estado es válido; de lo contrario, {@code false}.
     */
    private boolean isValidStatus(CustomerInformationStatusEnum status) {
        return status != null;
    }

    /** Obtener información adicional sobre el estado. */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "Información de estado inválida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información de estado es válida.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return {@code true} si la información es válida; de lo contrario, {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional sobre el estado.
     * @return La respuesta actualizada.
     */
    public CustomerInformationResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInformationResponse that = (CustomerInformationResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo);
    }

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
