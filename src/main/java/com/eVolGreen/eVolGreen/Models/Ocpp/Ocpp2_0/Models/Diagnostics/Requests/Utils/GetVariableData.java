package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.AttributeEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Clase que contiene los parámetros para la solicitud de GetVariables.
 *
 * <p>Se utiliza para especificar un componente y variable junto con el tipo de atributo para recuperar su valor.
 */
public class GetVariableData {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Tipo de atributo cuyo valor se solicita. Si está ausente, se asume por defecto el tipo
     * "Actual".
     */
    @Nullable
    private AttributeEnum attributeType;

    /** Componente físico o lógico. */
    private Component component;

    /** Clave de referencia a una variable del componente. */
    private Variable variable;

    /**
     * Constructor de la clase GetVariableData.
     *
     * @param component Componente físico o lógico.
     * @param variable Clave de referencia a una variable del componente.
     */
    public GetVariableData(Component component, Variable variable) {
        setComponent(component);
        setVariable(variable);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
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
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual.
     */
    public GetVariableData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de atributo cuyo valor se solicita.
     *
     * @return Tipo de atributo. Por defecto, retorna el tipo "Actual".
     */
    public AttributeEnum getAttributeType() {
        return attributeType != null ? attributeType : AttributeEnum.Actual;
    }

    /**
     * Establece el tipo de atributo cuyo valor se solicita.
     *
     * @param attributeType Tipo de atributo.
     */
    public void setAttributeType(@Nullable AttributeEnum attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * Agrega el tipo de atributo cuyo valor se solicita.
     *
     * @param attributeType Tipo de atributo.
     * @return La instancia actual.
     */
    public GetVariableData withAttributeType(@Nullable AttributeEnum attributeType) {
        setAttributeType(attributeType);
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
            throw new PropertyConstraintException(component, "component es inválido");
        }
        this.component = component;
    }

    /**
     * Verifica si el componente es válido.
     *
     * @param component Componente físico o lógico.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la clave de referencia a una variable del componente.
     *
     * @return Clave de referencia a una variable.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la clave de referencia a una variable del componente.
     *
     * @param variable Clave de referencia a una variable.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "variable es inválida");
        }
        this.variable = variable;
    }

    /**
     * Verifica si la variable es válida.
     *
     * @param variable Clave de referencia a una variable.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
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
        GetVariableData that = (GetVariableData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(attributeType, that.attributeType)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, attributeType, component, variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("attributeType", attributeType)
                .add("component", component)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
