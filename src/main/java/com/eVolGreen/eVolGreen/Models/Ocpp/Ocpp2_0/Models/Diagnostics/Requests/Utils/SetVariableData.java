package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.AttributeEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa los datos necesarios para establecer valores en una variable específica en la estación de carga.
 */
public class SetVariableData {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Tipo de atributo: Actual, Target, MinSet, MaxSet. Por defecto es Actual si se omite. */
    @Nullable
    private AttributeEnum attributeType;

    /**
     * Valor que se asignará al atributo de la variable.
     * <p>
     * El tamaño máximo del valor puede ser limitado por la variable de configuración
     * ConfigurationVariable.ConfigurationValueSize.
     */
    private String attributeValue;

    /** Componente físico o lógico al que pertenece la variable. */
    private Component component;

    /** Clave de referencia para identificar la variable dentro del componente. */
    private Variable variable;

    /**
     * Constructor de la clase SetVariableData.
     *
     * @param attributeValue Valor que se asignará al atributo de la variable.
     * @param component Componente físico o lógico al que pertenece la variable.
     * @param variable Clave de referencia de la variable.
     */
    public SetVariableData(String attributeValue, Component component, Variable variable) {
        setAttributeValue(attributeValue);
        setComponent(component);
        setVariable(variable);
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
     * @return Instancia actual de SetVariableData.
     */
    public SetVariableData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de atributo.
     *
     * @return Tipo de atributo: Actual, Target, MinSet, MaxSet. Por defecto es Actual si se omite.
     */
    public AttributeEnum getAttributeType() {
        return attributeType != null ? attributeType : AttributeEnum.Actual;
    }

    /**
     * Establece el tipo de atributo.
     *
     * @param attributeType Tipo de atributo: Actual, Target, MinSet, MaxSet.
     */
    public void setAttributeType(@Nullable AttributeEnum attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * Añade el tipo de atributo.
     *
     * @param attributeType Tipo de atributo: Actual, Target, MinSet, MaxSet.
     * @return Instancia actual de SetVariableData.
     */
    public SetVariableData withAttributeType(@Nullable AttributeEnum attributeType) {
        setAttributeType(attributeType);
        return this;
    }

    /**
     * Obtiene el valor que se asignará al atributo de la variable.
     *
     * @return Valor del atributo.
     */
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * Establece el valor que se asignará al atributo de la variable.
     *
     * @param attributeValue Valor del atributo.
     */
    public void setAttributeValue(String attributeValue) {
        if (!isValidAttributeValue(attributeValue)) {
            throw new PropertyConstraintException(attributeValue, "El valor del atributo no es válido.");
        }
        this.attributeValue = attributeValue;
    }

    /**
     * Valida si el valor del atributo es válido.
     *
     * @param attributeValue Valor del atributo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidAttributeValue(String attributeValue) {
        return attributeValue != null && attributeValue.length() <= 1000;
    }

    /**
     * Obtiene el componente físico o lógico al que pertenece la variable.
     *
     * @return Componente de la variable.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente físico o lógico al que pertenece la variable.
     *
     * @param component Componente de la variable.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "El componente no es válido.");
        }
        this.component = component;
    }

    /**
     * Valida si el componente es válido.
     *
     * @param component Componente a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la clave de referencia de la variable dentro del componente.
     *
     * @return Clave de la variable.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la clave de referencia de la variable dentro del componente.
     *
     * @param variable Clave de la variable.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "La variable no es válida.");
        }
        this.variable = variable;
    }

    /**
     * Valida si la variable es válida.
     *
     * @param variable Variable a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Valida si la configuración de SetVariableData es válida.
     *
     * @return {@code true} si la configuración es válida, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAttributeValue(attributeValue)
                && isValidComponent(component)
                && isValidVariable(variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetVariableData that = (SetVariableData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(attributeType, that.attributeType)
                && Objects.equals(attributeValue, that.attributeValue)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, attributeType, attributeValue, component, variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("attributeType", attributeType)
                .add("attributeValue", attributeValue)
                .add("component", component)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
