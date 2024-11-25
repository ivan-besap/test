package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.SetVariableData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para establecer valores en variables específicas en la estación de carga.
 *
 * <p>
 * Representa una solicitud en OCPP 2.0.1 para modificar el estado o los parámetros de
 * operación definidos por las variables de la estación.
 */
public class SetVariablesRequest extends RequestWithId {

    /** Datos personalizados adicionales para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Lista de variables que se desean modificar. */
    private SetVariableData[] setVariableData;

    /**
     * Constructor para la clase SetVariablesRequest.
     *
     * @param setVariableData Lista de variables a modificar.
     */
    public SetVariablesRequest(SetVariableData[] setVariableData) {
        setSetVariableData(setVariableData);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia de SetVariablesRequest.
     */
    public SetVariablesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la lista de variables que se desean modificar.
     *
     * @return Lista de variables a modificar.
     */
    public SetVariableData[] getSetVariableData() {
        return setVariableData;
    }

    /**
     * Establece la lista de variables que se desean modificar.
     *
     * @param setVariableData Lista de variables a modificar.
     */
    public void setSetVariableData(SetVariableData[] setVariableData) {
        if (!isValidSetVariableData(setVariableData)) {
            throw new PropertyConstraintException(setVariableData, "La lista de variables no es válida");
        }
        this.setVariableData = setVariableData;
    }

    /**
     * Valida si la lista de variables es válida.
     *
     * @param setVariableData Lista de variables a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidSetVariableData(SetVariableData[] setVariableData) {
        return setVariableData != null
                && setVariableData.length >= 1
                && Arrays.stream(setVariableData).allMatch(SetVariableData::validate);
    }

    /**
     * Valida si la solicitud es consistente y válida.
     *
     * @return {@code true} si la solicitud es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidSetVariableData(setVariableData);
    }

    /**
     * Indica si la solicitud está relacionada con una transacción activa.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con transacciones.
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
        SetVariablesRequest that = (SetVariablesRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(setVariableData, that.setVariableData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(setVariableData));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("setVariableData", setVariableData)
                .add("isValid", validate())
                .toString();
    }
}

