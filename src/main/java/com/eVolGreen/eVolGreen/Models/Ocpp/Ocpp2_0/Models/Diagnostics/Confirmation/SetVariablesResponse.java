package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils.SetVariableResult;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Respuesta a la solicitud de establecimiento de variables en una estación de carga.
 *
 * <p>Proporciona información sobre el resultado de intentar establecer valores en las variables.
 */
public class SetVariablesResponse extends Confirmation {
    /** Datos personalizados adicionales. */
    @Nullable private CustomData customData;

    /** Resultados de los intentos de establecer variables. */
    private SetVariableResult[] setVariableResult;

    /**
     * Constructor de la clase SetVariablesResponse.
     *
     * @param setVariableResult Resultados de los intentos de establecer variables.
     */
    public SetVariablesResponse(SetVariableResult[] setVariableResult) {
        setSetVariableResult(setVariableResult);
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Instancia actual de SetVariablesResponse.
     */
    public SetVariablesResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los resultados de los intentos de establecer variables.
     *
     * @return Resultados de los intentos de establecer variables.
     */
    public SetVariableResult[] getSetVariableResult() {
        return setVariableResult;
    }

    /**
     * Establece los resultados de los intentos de establecer variables.
     *
     * @param setVariableResult Resultados de los intentos de establecer variables.
     */
    public void setSetVariableResult(SetVariableResult[] setVariableResult) {
        if (!isValidSetVariableResult(setVariableResult)) {
            throw new PropertyConstraintException(setVariableResult, "Los resultados no son válidos.");
        }
        this.setVariableResult = setVariableResult;
    }

    /**
     * Valida si los resultados son válidos.
     *
     * @param setVariableResult Resultados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidSetVariableResult(SetVariableResult[] setVariableResult) {
        return setVariableResult != null
                && setVariableResult.length >= 1
                && Arrays.stream(setVariableResult).allMatch(SetVariableResult::validate);
    }

    /**
     * Valida si la respuesta es válida.
     *
     * @return {@code true} si la respuesta es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidSetVariableResult(setVariableResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetVariablesResponse that = (SetVariablesResponse) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(setVariableResult, that.setVariableResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(setVariableResult));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("setVariableResult", setVariableResult)
                .add("isValid", validate())
                .toString();
    }
}
