package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils.GetVariableResult;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * GetVariablesResponse
 *
 * <p>Clase que encapsula la respuesta a una solicitud de GetVariables.
 *
 * <p>Definición según OCPP 2.0.1 FINAL.
 */
public class GetVariablesResponse extends Confirmation {

    /** Información personalizada asociada con la respuesta. */
    @Nullable
    private CustomData customData;

    /** Resultados de la solicitud de GetVariables. */
    private GetVariableResult[] getVariableResult;

    /**
     * Constructor de la clase GetVariablesResponse.
     *
     * @param getVariableResult Resultados de la solicitud de GetVariables.
     */
    public GetVariablesResponse(GetVariableResult[] getVariableResult) {
        setGetVariableResult(getVariableResult);
    }

    /**
     * Obtiene la información personalizada asociada.
     *
     * @return Información personalizada.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece la información personalizada asociada.
     *
     * @param customData Información personalizada.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura los datos personalizados y devuelve la instancia actual.
     *
     * @param customData Información personalizada.
     * @return Esta instancia.
     */
    public GetVariablesResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los resultados de la solicitud de GetVariables.
     *
     * @return Resultados de la solicitud.
     */
    public GetVariableResult[] getGetVariableResult() {
        return getVariableResult;
    }

    /**
     * Configura los resultados de la solicitud de GetVariables.
     *
     * @param getVariableResult Resultados de la solicitud.
     */
    public void setGetVariableResult(GetVariableResult[] getVariableResult) {
        if (!isValidGetVariableResult(getVariableResult)) {
            throw new PropertyConstraintException(getVariableResult, "getVariableResult is invalid");
        }
        this.getVariableResult = getVariableResult;
    }

    /**
     * Verifica si los resultados de GetVariables son válidos.
     *
     * @param getVariableResult Resultados a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidGetVariableResult(GetVariableResult[] getVariableResult) {
        return getVariableResult != null
                && getVariableResult.length >= 1
                && Arrays.stream(getVariableResult).allMatch(GetVariableResult::validate);
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidGetVariableResult(getVariableResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetVariablesResponse that = (GetVariablesResponse) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(getVariableResult, that.getVariableResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(getVariableResult));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("getVariableResult", getVariableResult)
                .add("isValid", validate())
                .toString();
    }
}
