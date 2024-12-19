package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Clase que representa datos del informe relacionados con componentes, variables y sus características.
 * <p>
 * Esta clase se utiliza en el contexto de OCPP 2.0.1 para proporcionar información detallada sobre
 * componentes físicos o lógicos, variables asociadas, sus atributos y características inmutables.
 * </p>
 */
public class ReportData {

    /** Datos personalizados asociados al informe. */
    @Nullable
    private CustomData customData;

    /** Componente físico o lógico al que se refiere este informe. */
    private Component component;

    /** Clave de referencia a una variable asociada al componente. */
    private Variable variable;

    /** Atributos de la variable especificada. */
    private VariableAttribute[] variableAttribute;

    /** Parámetros inmutables asociados a la variable. */
    @Nullable
    private VariableCharacteristics variableCharacteristics;

    /**
     * Constructor de la clase ReportData.
     *
     * @param component Componente físico o lógico al que se refiere este informe.
     * @param variable Clave de referencia a una variable asociada al componente.
     * @param variableAttribute Atributos de la variable especificada.
     */
    public ReportData(Component component, Variable variable, VariableAttribute[] variableAttribute) {
        setComponent(component);
        setVariable(variable);
        setVariableAttribute(variableAttribute);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public ReportData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el componente físico o lógico asociado.
     *
     * @return Componente físico o lógico.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente físico o lógico asociado.
     *
     * @param component Componente físico o lógico.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "component es inválido.");
        }
        this.component = component;
    }

    /**
     * Valida el componente especificado.
     *
     * @param component Componente a validar.
     * @return {@code true} si el componente es válido, de lo contrario {@code false}.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la variable asociada al componente.
     *
     * @return Variable asociada.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la variable asociada al componente.
     *
     * @param variable Variable asociada.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "variable es inválida.");
        }
        this.variable = variable;
    }

    /**
     * Valida la variable especificada.
     *
     * @param variable Variable a validar.
     * @return {@code true} si la variable es válida, de lo contrario {@code false}.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Obtiene los atributos de la variable.
     *
     * @return Atributos de la variable.
     */
    public VariableAttribute[] getVariableAttribute() {
        return variableAttribute;
    }

    /**
     * Establece los atributos de la variable.
     *
     * @param variableAttribute Atributos de la variable.
     */
    public void setVariableAttribute(VariableAttribute[] variableAttribute) {
        if (!isValidVariableAttribute(variableAttribute)) {
            throw new PropertyConstraintException(variableAttribute, "variableAttribute es inválido.");
        }
        this.variableAttribute = variableAttribute;
    }

    /**
     * Valida los atributos de la variable.
     *
     * @param variableAttribute Atributos a validar.
     * @return {@code true} si los atributos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidVariableAttribute(VariableAttribute[] variableAttribute) {
        return variableAttribute != null
                && variableAttribute.length >= 1
                && variableAttribute.length <= 4
                && Arrays.stream(variableAttribute).allMatch(VariableAttribute::validate);
    }

    /**
     * Obtiene las características inmutables de la variable.
     *
     * @return Características inmutables, o {@code null} si no están definidas.
     */
    @Nullable
    public VariableCharacteristics getVariableCharacteristics() {
        return variableCharacteristics;
    }

    /**
     * Establece las características inmutables de la variable.
     *
     * @param variableCharacteristics Características inmutables.
     */
    public void setVariableCharacteristics(@Nullable VariableCharacteristics variableCharacteristics) {
        if (!isValidVariableCharacteristics(variableCharacteristics)) {
            throw new PropertyConstraintException(variableCharacteristics, "variableCharacteristics es inválido.");
        }
        this.variableCharacteristics = variableCharacteristics;
    }

    /**
     * Valida las características inmutables de la variable.
     *
     * @param variableCharacteristics Características a validar.
     * @return {@code true} si las características son válidas, de lo contrario {@code false}.
     */
    private boolean isValidVariableCharacteristics(@Nullable VariableCharacteristics variableCharacteristics) {
        return variableCharacteristics == null || variableCharacteristics.validate();
    }

    /**
     * Añade características inmutables de la variable.
     *
     * @param variableCharacteristics Características inmutables.
     * @return Esta instancia.
     */
    public ReportData withVariableCharacteristics(@Nullable VariableCharacteristics variableCharacteristics) {
        setVariableCharacteristics(variableCharacteristics);
        return this;
    }

    /**
     * Valida esta instancia de ReportData.
     *
     * @return {@code true} si todos los campos son válidos, de lo contrario {@code false}.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidComponent(component)
                && isValidVariable(variable)
                && isValidVariableAttribute(variableAttribute)
                && isValidVariableCharacteristics(variableCharacteristics);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportData that = (ReportData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable)
                && Arrays.equals(variableAttribute, that.variableAttribute)
                && Objects.equals(variableCharacteristics, that.variableCharacteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                component,
                variable,
                Arrays.hashCode(variableAttribute),
                variableCharacteristics);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("component", component)
                .add("variable", variable)
                .add("variableAttribute", variableAttribute)
                .add("variableCharacteristics", variableCharacteristics)
                .add("isValid", validate())
                .toString();
    }
}
