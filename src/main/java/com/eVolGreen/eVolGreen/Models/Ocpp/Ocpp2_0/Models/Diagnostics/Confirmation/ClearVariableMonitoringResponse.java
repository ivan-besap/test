package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils.ClearMonitoringResult;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Respuesta para la solicitud de borrado de monitorización de variables.
 *
 * <p>Esta respuesta contiene los resultados del intento de borrar monitores de variables específicos.
 * Es parte del protocolo OCPP 2.0.1.</p>
 */
public class ClearVariableMonitoringResponse extends Confirmation {

    /**
     * Datos personalizados.
     *
     * <p>Permite incluir atributos adicionales específicos de la implementación.</p>
     */
    @Nullable
    private CustomData customData;

    /**
     * Resultados del borrado de los monitores.
     *
     * <p>Lista que contiene los resultados de cada monitor procesado.</p>
     */
    private ClearMonitoringResult[] clearMonitoringResult;

    /**
     * Constructor de la clase ClearVariableMonitoringResponse.
     *
     * @param clearMonitoringResult Lista de resultados del borrado de monitores.
     */
    public ClearVariableMonitoringResponse(ClearMonitoringResult[] clearMonitoringResult) {
        setClearMonitoringResult(clearMonitoringResult);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Los datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Los datos personalizados.
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
     * @param customData Los datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Método fluido para establecer los datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @return La instancia actual de la respuesta.
     */
    public ClearVariableMonitoringResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los resultados del borrado de monitores.
     *
     * @return Lista de resultados del borrado de monitores.
     */
    public ClearMonitoringResult[] getClearMonitoringResult() {
        return clearMonitoringResult;
    }

    /**
     * Establece los resultados del borrado de monitores.
     *
     * @param clearMonitoringResult Lista de resultados del borrado de monitores.
     * @throws PropertyConstraintException si la lista es nula o contiene elementos inválidos.
     */
    public void setClearMonitoringResult(ClearMonitoringResult[] clearMonitoringResult) {
        if (!isValidClearMonitoringResult(clearMonitoringResult)) {
            throw new PropertyConstraintException(
                    clearMonitoringResult, "La lista de resultados de borrado es inválida.");
        }
        this.clearMonitoringResult = clearMonitoringResult;
    }

    /**
     * Verifica si la lista de resultados de borrado es válida.
     *
     * @param clearMonitoringResult Lista de resultados a verificar.
     * @return {@code true} si la lista es válida, de lo contrario {@code false}.
     */
    private boolean isValidClearMonitoringResult(ClearMonitoringResult[] clearMonitoringResult) {
        return clearMonitoringResult != null
                && clearMonitoringResult.length >= 1
                && Arrays.stream(clearMonitoringResult).allMatch(ClearMonitoringResult::validate);
    }

    /**
     * Valida la respuesta.
     *
     * @return {@code true} si la respuesta es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidClearMonitoringResult(clearMonitoringResult);
    }

    /**
     * Compara esta respuesta con otro objeto.
     *
     * @param o El objeto a comparar.
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
        ClearVariableMonitoringResponse that = (ClearVariableMonitoringResponse) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(clearMonitoringResult, that.clearMonitoringResult);
    }

    /**
     * Calcula el código hash de la respuesta.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(clearMonitoringResult));
    }

    /**
     * Genera una representación en cadena de la respuesta.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("clearMonitoringResult", clearMonitoringResult)
                .add("isValid", validate())
                .toString();
    }
}
