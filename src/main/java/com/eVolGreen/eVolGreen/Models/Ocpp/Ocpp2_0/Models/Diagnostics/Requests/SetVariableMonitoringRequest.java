package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.SetMonitoringData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para configurar el monitoreo de variables en la estación de carga.
 *
 * <p>Esta clase permite establecer datos de monitoreo para una o más variables en la estación de
 * carga.
 */
public class SetVariableMonitoringRequest extends RequestWithId {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Parámetros del monitoreo de variables a configurar. */
    private SetMonitoringData[] setMonitoringData;

    /**
     * Constructor para la clase SetVariableMonitoringRequest.
     *
     * @param setMonitoringData Parámetros del monitoreo de variables a configurar.
     */
    public SetVariableMonitoringRequest(SetMonitoringData[] setMonitoringData) {
        setSetMonitoringData(setMonitoringData);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Objeto de tipo {@link CustomData} que contiene información personalizada.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Objeto de tipo {@link CustomData} con información personalizada.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Objeto de tipo {@link CustomData} a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece datos personalizados adicionales de forma fluida.
     *
     * @param customData Objeto de tipo {@link CustomData} con información personalizada.
     * @return Instancia actualizada de {@link SetVariableMonitoringRequest}.
     */
    public SetVariableMonitoringRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los parámetros de monitoreo de variables.
     *
     * @return Array de objetos {@link SetMonitoringData} que contiene los parámetros del monitoreo.
     */
    public SetMonitoringData[] getSetMonitoringData() {
        return setMonitoringData;
    }

    /**
     * Establece los parámetros de monitoreo de variables.
     *
     * @param setMonitoringData Array de objetos {@link SetMonitoringData} que contiene los
     *     parámetros del monitoreo.
     * @throws PropertyConstraintException si los parámetros no son válidos.
     */
    public void setSetMonitoringData(SetMonitoringData[] setMonitoringData) {
        if (!isValidSetMonitoringData(setMonitoringData)) {
            throw new PropertyConstraintException(
                    setMonitoringData, "Los parámetros de monitoreo no son válidos.");
        }
        this.setMonitoringData = setMonitoringData;
    }

    /**
     * Verifica si los parámetros de monitoreo son válidos.
     *
     * @param setMonitoringData Array de objetos {@link SetMonitoringData} a validar.
     * @return {@code true} si los parámetros son válidos, {@code false} en caso contrario.
     */
    private boolean isValidSetMonitoringData(SetMonitoringData[] setMonitoringData) {
        return setMonitoringData != null
                && setMonitoringData.length >= 1
                && Arrays.stream(setMonitoringData).allMatch(SetMonitoringData::validate);
    }

    /**
     * Valida la solicitud para asegurarse de que todos los datos requeridos son válidos.
     *
     * @return {@code true} si la solicitud es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidSetMonitoringData(setMonitoringData);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false} ya que esta solicitud no está relacionada con una transacción.
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
        SetVariableMonitoringRequest that = (SetVariableMonitoringRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(setMonitoringData, that.setMonitoringData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(setMonitoringData));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("setMonitoringData", setMonitoringData)
                .add("isValid", validate())
                .toString();
    }
}
