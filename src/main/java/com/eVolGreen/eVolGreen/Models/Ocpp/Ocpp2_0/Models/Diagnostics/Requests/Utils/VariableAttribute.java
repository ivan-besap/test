package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.AttributeEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MutabilityEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Clase que representa los datos de un atributo de una variable en el protocolo OCPP.
 * <p>
 * Utilizada para describir atributos específicos de una variable, incluyendo el tipo, valor,
 * mutabilidad y características adicionales.
 * </p>
 */
public class VariableAttribute {

    /** Datos personalizados asociados al atributo. */
    @Nullable
    private CustomData customData;

    /** Tipo de atributo: Actual, MinSet, MaxSet, etc. Por defecto es Actual. */
    @Nullable
    private AttributeEnum type;

    /**
     * Valor del atributo. Puede omitirse si la mutabilidad está configurada como 'WriteOnly'.
     * <p>
     * El tamaño máximo del valor puede estar limitado por la configuración
     * {@code ReportingValueSize} definida en el sistema.
     * </p>
     */
    @Nullable
    private String value;

    /** Mutabilidad del atributo. Por defecto es ReadWrite. */
    @Nullable
    private MutabilityEnum mutability;

    /**
     * Indica si el valor es persistente entre reinicios o apagados del sistema. Por defecto es {@code false}.
     */
    @Nullable
    private Boolean persistent;

    /**
     * Indica si el valor nunca será modificado por la estación de carga en tiempo de ejecución. Por defecto es {@code false}.
     */
    @Nullable
    private Boolean constant;

    /** Constructor predeterminado para la clase VariableAttribute. */
    public VariableAttribute() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Los datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Los datos personalizados a asociar.
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
     * @param customData Los datos a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Los datos personalizados a asociar.
     * @return Esta instancia.
     */
    public VariableAttribute withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de atributo.
     *
     * @return Tipo de atributo o {@code Actual} por defecto si no está definido.
     */
    public AttributeEnum getType() {
        return type != null ? type : AttributeEnum.Actual;
    }

    /**
     * Establece el tipo de atributo.
     *
     * @param type Tipo de atributo.
     */
    public void setType(@Nullable AttributeEnum type) {
        this.type = type;
    }

    /**
     * Añade el tipo de atributo.
     *
     * @param type Tipo de atributo.
     * @return Esta instancia.
     */
    public VariableAttribute withType(@Nullable AttributeEnum type) {
        setType(type);
        return this;
    }

    /**
     * Obtiene el valor del atributo.
     *
     * @return Valor del atributo o {@code null} si no está definido.
     */
    @Nullable
    public String getValue() {
        return value;
    }

    /**
     * Establece el valor del atributo.
     *
     * @param value Valor del atributo.
     */
    public void setValue(@Nullable String value) {
        if (!isValidValue(value)) {
            throw new PropertyConstraintException(value, "El valor es inválido.");
        }
        this.value = value;
    }

    /**
     * Valida el valor del atributo.
     *
     * @param value Valor a validar.
     * @return {@code true} si el valor es válido, de lo contrario {@code false}.
     */
    private boolean isValidValue(@Nullable String value) {
        return value == null || value.length() <= 2500;
    }

    /**
     * Añade el valor del atributo.
     *
     * @param value Valor del atributo.
     * @return Esta instancia.
     */
    public VariableAttribute withValue(@Nullable String value) {
        setValue(value);
        return this;
    }

    /**
     * Obtiene la mutabilidad del atributo.
     *
     * @return Mutabilidad del atributo o {@code ReadWrite} por defecto si no está definida.
     */
    public MutabilityEnum getMutability() {
        return mutability != null ? mutability : MutabilityEnum.ReadWrite;
    }

    /**
     * Establece la mutabilidad del atributo.
     *
     * @param mutability Mutabilidad del atributo.
     */
    public void setMutability(@Nullable MutabilityEnum mutability) {
        this.mutability = mutability;
    }

    /**
     * Añade la mutabilidad del atributo.
     *
     * @param mutability Mutabilidad del atributo.
     * @return Esta instancia.
     */
    public VariableAttribute withMutability(@Nullable MutabilityEnum mutability) {
        setMutability(mutability);
        return this;
    }

    /**
     * Obtiene si el valor es persistente.
     *
     * @return {@code true} si el valor es persistente, de lo contrario {@code false}.
     */
    public Boolean getPersistent() {
        return persistent != null ? persistent : false;
    }

    /**
     * Establece si el valor es persistente.
     *
     * @param persistent {@code true} para hacer el valor persistente.
     */
    public void setPersistent(@Nullable Boolean persistent) {
        this.persistent = persistent;
    }

    /**
     * Añade la persistencia del valor.
     *
     * @param persistent {@code true} para hacer el valor persistente.
     * @return Esta instancia.
     */
    public VariableAttribute withPersistent(@Nullable Boolean persistent) {
        setPersistent(persistent);
        return this;
    }

    /**
     * Obtiene si el valor es constante.
     *
     * @return {@code true} si el valor es constante, de lo contrario {@code false}.
     */
    public Boolean getConstant() {
        return constant != null ? constant : false;
    }

    /**
     * Establece si el valor es constante.
     *
     * @param constant {@code true} para hacer el valor constante.
     */
    public void setConstant(@Nullable Boolean constant) {
        this.constant = constant;
    }

    /**
     * Añade la constancia del valor.
     *
     * @param constant {@code true} para hacer el valor constante.
     * @return Esta instancia.
     */
    public VariableAttribute withConstant(@Nullable Boolean constant) {
        setConstant(constant);
        return this;
    }

    /**
     * Valida esta instancia.
     *
     * @return {@code true} si todos los campos son válidos, de lo contrario {@code false}.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableAttribute that = (VariableAttribute) o;
        return Objects.equals(customData, that.customData) &&
                Objects.equals(type, that.type) &&
                Objects.equals(value, that.value) &&
                Objects.equals(mutability, that.mutability) &&
                Objects.equals(persistent, that.persistent) &&
                Objects.equals(constant, that.constant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, type, value, mutability, persistent, constant);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("type", type)
                .add("value", value)
                .add("mutability", mutability)
                .add("persistent", persistent)
                .add("constant", constant)
                .add("isValid", validate())
                .toString();
    }
}
