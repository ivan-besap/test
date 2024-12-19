package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.SetNetworkProfileStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al intento de configurar un perfil de red en una estación de carga.
 * Contiene el resultado de la operación y detalles adicionales sobre el estado.
 */
public class SetNetworkProfileResponse extends Confirmation {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Estado del resultado de la operación de configuración del perfil de red. */
    private SetNetworkProfileStatusEnum status;

    /** Información adicional acerca del estado del resultado. */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase SetNetworkProfileResponse.
     *
     * @param status Estado del resultado de la operación.
     */
    public SetNetworkProfileResponse(SetNetworkProfileStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Objeto de datos personalizados, o {@code null} si no está definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Objeto de datos personalizados.
     * @throws PropertyConstraintException Si los datos proporcionados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos o {@code null}, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados adicionales.
     *
     * @param customData Objeto de datos personalizados.
     * @return Esta instancia para facilitar encadenamiento de métodos.
     */
    public SetNetworkProfileResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado del resultado de la operación.
     *
     * @return Estado de la operación como un valor de {@link SetNetworkProfileStatusEnum}.
     */
    public SetNetworkProfileStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del resultado de la operación.
     *
     * @param status Estado de la operación.
     * @throws PropertyConstraintException Si el estado proporcionado es inválido.
     */
    public void setStatus(SetNetworkProfileStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado proporcionado no es válido.");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado no es nulo, {@code false} en caso contrario.
     */
    private boolean isValidStatus(SetNetworkProfileStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional sobre el estado, o {@code null} si no está definida.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param statusInfo Objeto con información adicional.
     * @throws PropertyConstraintException Si la información proporcionada es inválida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param statusInfo Información a validar.
     * @return {@code true} si es válida o {@code null}, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado.
     *
     * @param statusInfo Objeto con información adicional.
     * @return Esta instancia para facilitar encadenamiento de métodos.
     */
    public SetNetworkProfileResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida la instancia actual.
     *
     * @return {@code true} si la instancia es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status) && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetNetworkProfileResponse that = (SetNetworkProfileResponse) o;
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
