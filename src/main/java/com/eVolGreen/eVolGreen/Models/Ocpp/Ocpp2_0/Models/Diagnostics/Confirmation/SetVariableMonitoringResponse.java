package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils.SetMonitoringResult;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa la respuesta al mensaje de solicitud SetVariableMonitoring.
 *
 * <p>Este mensaje contiene los resultados de la solicitud de monitoreo de variables específicas
 * enviadas a la estación de carga.
 */
public class SetVariableMonitoringResponse extends Confirmation {

    /** Datos personalizados adicionales para extender la información de la respuesta. */
    @Nullable
    private CustomData customData;

    /**
     * Resultados de la solicitud SetVariableMonitoring.
     *
     * <p>Incluye información detallada sobre los resultados de cada variable monitoreada.
     */
    private SetMonitoringResult[] setMonitoringResult;

    /**
     * Constructor para la clase SetVariableMonitoringResponse.
     *
     * @param setMonitoringResult Arreglo de resultados de la solicitud SetVariableMonitoring.
     */
    public SetVariableMonitoringResponse(SetMonitoringResult[] setMonitoringResult) {
        setSetMonitoringResult(setMonitoringResult);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Objeto {@link CustomData} con información adicional o {@code null} si no está definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Objeto {@link CustomData} con información adicional.
     * @throws PropertyConstraintException si los datos no son válidos.
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
     * @param customData Objeto {@link CustomData} a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece datos personalizados adicionales de forma fluida.
     *
     * @param customData Objeto {@link CustomData} con información adicional.
     * @return Instancia actualizada de {@link SetVariableMonitoringResponse}.
     */
    public SetVariableMonitoringResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los resultados de la solicitud SetVariableMonitoring.
     *
     * @return Arreglo de objetos {@link SetMonitoringResult} que representan los resultados.
     */
    public SetMonitoringResult[] getSetMonitoringResult() {
        return setMonitoringResult;
    }

    /**
     * Establece los resultados de la solicitud SetVariableMonitoring.
     *
     * @param setMonitoringResult Arreglo de objetos {@link SetMonitoringResult}.
     * @throws PropertyConstraintException si los resultados no son válidos.
     */
    public void setSetMonitoringResult(SetMonitoringResult[] setMonitoringResult) {
        if (!isValidSetMonitoringResult(setMonitoringResult)) {
            throw new PropertyConstraintException(
                    setMonitoringResult, "Los resultados del monitoreo no son válidos.");
        }
        this.setMonitoringResult = setMonitoringResult;
    }

    /**
     * Valida si los resultados de monitoreo son válidos.
     *
     * @param setMonitoringResult Arreglo de objetos {@link SetMonitoringResult} a validar.
     * @return {@code true} si los resultados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidSetMonitoringResult(SetMonitoringResult[] setMonitoringResult) {
        return setMonitoringResult != null
                && setMonitoringResult.length >= 1
                && Arrays.stream(setMonitoringResult).allMatch(SetMonitoringResult::validate);
    }

    /**
     * Valida la respuesta.
     *
     * @return {@code true} si todos los datos en la respuesta son válidos, {@code false} en caso
     *     contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidSetMonitoringResult(setMonitoringResult);
    }

    /**
     * Compara este objeto con otro para verificar la igualdad.
     *
     * @param o Objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetVariableMonitoringResponse that = (SetVariableMonitoringResponse) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(setMonitoringResult, that.setMonitoringResult);
    }

    /**
     * Genera un hash para este objeto.
     *
     * @return Valor hash basado en los atributos del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(setMonitoringResult));
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     *
     * @return Cadena que representa los datos del objeto.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("setMonitoringResult", setMonitoringResult)
                .add("isValid", validate())
                .toString();
    }
}
