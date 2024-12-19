package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa una combinación de un componente físico o lógico y una variable asociada, así como
 * sus características.
 *
 * <p>Se utiliza en el estándar OCPP 2.0.1 para definir elementos de configuración y monitoreo.
 */
public class ComponentVariable {

    /** Datos personalizados para el componente y la variable. */
    @Nullable
    private CustomData customData;

    /** Componente físico o lógico. */
    private Component component;

    /** Variable asociada al componente. */
    @Nullable
    private Variable variable;

    /**
     * Constructor principal para la clase.
     *
     * @param component Componente físico o lógico.
     */
    public ComponentVariable(Component component) {
        setComponent(component);
    }

    /**
     * Obtiene los datos personalizados asociados al componente-variable.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al componente-variable.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException si los datos no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados al componente-variable.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada.
     */
    public ComponentVariable withCustomData(@Nullable CustomData customData) {
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
     * @throws PropertyConstraintException si el componente no es válido.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "El componente no es válido");
        }
        this.component = component;
    }

    /**
     * Verifica si el componente es válido.
     *
     * @param component Componente a validar.
     * @return {@code true} si el componente es válido o {@code false} en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la variable asociada al componente.
     *
     * @return Variable asociada o {@code null} si no está definida.
     */
    @Nullable
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la variable asociada al componente.
     *
     * @param variable Variable asociada.
     * @throws PropertyConstraintException si la variable no es válida.
     */
    public void setVariable(@Nullable Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "La variable no es válida");
        }
        this.variable = variable;
    }

    /**
     * Verifica si la variable asociada es válida.
     *
     * @param variable Variable a validar.
     * @return {@code true} si la variable es válida o {@code false} en caso contrario.
     */
    private boolean isValidVariable(@Nullable Variable variable) {
        return variable == null || variable.validate();
    }

    /**
     * Añade una variable al componente.
     *
     * @param variable Variable asociada.
     * @return La instancia actualizada.
     */
    public ComponentVariable withVariable(@Nullable Variable variable) {
        setVariable(variable);
        return this;
    }

    /**
     * Verifica la validez de toda la configuración del componente-variable.
     *
     * @return {@code true} si todos los elementos son válidos o {@code false} en caso contrario.
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
        ComponentVariable that = (ComponentVariable) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, component, variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("component", component)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
