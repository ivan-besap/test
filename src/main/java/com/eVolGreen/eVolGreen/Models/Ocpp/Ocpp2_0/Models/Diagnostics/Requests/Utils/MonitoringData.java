package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Clase que contiene los parámetros de una solicitud de monitoreo de variables (SetVariableMonitoring).
 */
public class MonitoringData {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Componente físico o lógico. */
    private Component component;

    /** Clave de referencia para una variable asociada a un componente. */
    private Variable variable;

    /** Configuración de monitoreo para una variable. */
    private VariableMonitoring[] variableMonitoring;

    /**
     * Constructor de la clase MonitoringData.
     *
     * @param component Componente físico o lógico.
     * @param variable Clave de referencia para una variable asociada a un componente.
     * @param variableMonitoring Configuración de monitoreo para una variable.
     */
    public MonitoringData(
            Component component, Variable variable, VariableMonitoring[] variableMonitoring) {
        setComponent(component);
        setVariable(variable);
        setVariableMonitoring(variableMonitoring);
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
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida que los datos personalizados sean válidos.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia.
     */
    public MonitoringData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el componente físico o lógico.
     *
     * @return Componente físico o lógico.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente físico o lógico.
     *
     * @param component Componente físico o lógico.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "El componente es inválido.");
        }
        this.component = component;
    }

    /**
     * Valida que el componente sea válido.
     *
     * @param component Componente.
     * @return {@code true} si el componente es válido, {@code false} en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la clave de referencia para una variable asociada a un componente.
     *
     * @return Clave de referencia para una variable.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la clave de referencia para una variable asociada a un componente.
     *
     * @param variable Clave de referencia para una variable.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "La variable es inválida.");
        }
        this.variable = variable;
    }

    /**
     * Valida que la variable sea válida.
     *
     * @param variable Variable.
     * @return {@code true} si la variable es válida, {@code false} en caso contrario.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Obtiene la configuración de monitoreo para una variable.
     *
     * @return Configuración de monitoreo para una variable.
     */
    public VariableMonitoring[] getVariableMonitoring() {
        return variableMonitoring;
    }

    /**
     * Establece la configuración de monitoreo para una variable.
     *
     * @param variableMonitoring Configuración de monitoreo para una variable.
     */
    public void setVariableMonitoring(VariableMonitoring[] variableMonitoring) {
        if (!isValidVariableMonitoring(variableMonitoring)) {
            throw new PropertyConstraintException(variableMonitoring, "La configuración de monitoreo es inválida.");
        }
        this.variableMonitoring = variableMonitoring;
    }

    /**
     * Valida que la configuración de monitoreo sea válida.
     *
     * @param variableMonitoring Configuración de monitoreo.
     * @return {@code true} si la configuración es válida, {@code false} en caso contrario.
     */
    private boolean isValidVariableMonitoring(VariableMonitoring[] variableMonitoring) {
        return variableMonitoring != null
                && variableMonitoring.length >= 1
                && Arrays.stream(variableMonitoring).allMatch(VariableMonitoring::validate);
    }

    /**
     * Valida todos los atributos de esta clase.
     *
     * @return {@code true} si todos los atributos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidComponent(component)
                && isValidVariable(variable)
                && isValidVariableMonitoring(variableMonitoring);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MonitoringData that = (MonitoringData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable)
                && Arrays.equals(variableMonitoring, that.variableMonitoring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, component, variable, Arrays.hashCode(variableMonitoring));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("component", component)
                .add("variable", variable)
                .add("variableMonitoring", variableMonitoring)
                .add("isValid", validate())
                .toString();
    }
}
