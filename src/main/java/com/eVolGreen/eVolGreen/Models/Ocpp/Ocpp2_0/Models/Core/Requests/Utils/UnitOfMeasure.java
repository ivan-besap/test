package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa una unidad de medida con un multiplicador opcional.
 * <p>
 * La unidad puede ser una medida estandarizada definida en las especificaciones OCPP o una
 * unidad personalizada. El multiplicador define un exponente base 10 para ajustar los valores
 * medidos.
 */
public class UnitOfMeasure {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Unidad de medida del valor. Por defecto, "Wh" si la medida predeterminada es un tipo de
     * energía. Este campo debe usar un valor de la lista de Unidades de Medida Estandarizadas
     * definida en OCPP Parte 2 Anexos. Si no existe una unidad aplicable en esa lista, se puede usar
     * una unidad personalizada.
     */
    @Nullable private String unit;

    /**
     * Multiplicador que representa el exponente en base 10. Por ejemplo, un multiplicador de 3
     * significa 10 elevado a la potencia 3. El valor predeterminado es 0.
     */
    @Nullable private Integer multiplier;

    /** Constructor por defecto de la clase UnitOfMeasure. */
    public UnitOfMeasure() {}

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados.
     * @return Este objeto.
     */
    public UnitOfMeasure withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la unidad de medida del valor.
     *
     * @return Unidad de medida (por defecto "Wh").
     */
    public String getUnit() {
        return unit != null ? unit : "Wh";
    }

    /**
     * Establece la unidad de medida del valor.
     *
     * @param unit Unidad de medida.
     */
    public void setUnit(@Nullable String unit) {
        if (!isValidUnit(unit)) {
            throw new PropertyConstraintException(unit, "Unidad de medida inválida.");
        }
        this.unit = unit;
    }

    private boolean isValidUnit(@Nullable String unit) {
        return unit == null || unit.length() <= 20;
    }

    /**
     * Establece la unidad de medida del valor.
     *
     * @param unit Unidad de medida.
     * @return Este objeto.
     */
    public UnitOfMeasure withUnit(@Nullable String unit) {
        setUnit(unit);
        return this;
    }

    /**
     * Obtiene el multiplicador para ajustar los valores medidos.
     *
     * @return Multiplicador (por defecto 0).
     */
    public Integer getMultiplier() {
        return multiplier != null ? multiplier : 0;
    }

    /**
     * Establece el multiplicador para ajustar los valores medidos.
     *
     * @param multiplier Multiplicador (exponente base 10).
     */
    public void setMultiplier(@Nullable Integer multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Establece el multiplicador para ajustar los valores medidos.
     *
     * @param multiplier Multiplicador (exponente base 10).
     * @return Este objeto.
     */
    public UnitOfMeasure withMultiplier(@Nullable Integer multiplier) {
        setMultiplier(multiplier);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData) && isValidUnit(unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnitOfMeasure that = (UnitOfMeasure) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(unit, that.unit)
                && Objects.equals(multiplier, that.multiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, unit, multiplier);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("unit", unit)
                .add("multiplier", multiplier)
                .add("isValid", validate())
                .toString();
    }
}
