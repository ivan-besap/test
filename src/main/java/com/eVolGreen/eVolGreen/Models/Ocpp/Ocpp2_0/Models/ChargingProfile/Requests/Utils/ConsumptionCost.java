package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Costo de Consumo
 *
 * <p>Representa un bloque de consumo que define los costos asociados para un rango de valores de consumo.
 */
public class ConsumptionCost {

    /** Datos personalizados asociados al costo de consumo. */
    @Nullable
    private CustomData customData;

    /**
     * Valor inicial del bloque de consumo.
     *
     * <p>Define el nivel más bajo de consumo que marca el inicio de este bloque. El intervalo del
     * bloque se extiende hasta el inicio del siguiente intervalo.
     */
    private Double startValue;

    /**
     * Lista de costos asociados al bloque de consumo.
     *
     * <p>Define los costos aplicables en este bloque de consumo. Puede incluir múltiples costos dependiendo de las condiciones.
     */
    private Cost[] cost;

    /**
     * Constructor de la clase `ConsumptionCost`.
     *
     * @param startValue Valor inicial del bloque de consumo.
     * @param cost Lista de costos asociados al bloque.
     */
    public ConsumptionCost(Double startValue, Cost[] cost) {
        setStartValue(startValue);
        setCost(cost);
    }

    /**
     * Obtiene los datos personalizados asociados al costo de consumo.
     *
     * @return Datos personalizados o `null` si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al costo de consumo.
     *
     * @param customData Datos personalizados o `null` si no están definidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados al costo de consumo.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de `ConsumptionCost` para encadenar métodos.
     */
    public ConsumptionCost withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el valor inicial del bloque de consumo.
     *
     * @return Valor inicial del bloque.
     */
    public Double getStartValue() {
        return startValue;
    }

    /**
     * Establece el valor inicial del bloque de consumo.
     *
     * @param startValue Valor inicial del bloque.
     */
    public void setStartValue(Double startValue) {
        if (!isValidStartValue(startValue)) {
            throw new PropertyConstraintException(startValue, "El valor inicial no es válido.");
        }
        this.startValue = startValue;
    }

    private boolean isValidStartValue(Double startValue) {
        return startValue != null;
    }

    /**
     * Obtiene la lista de costos asociados al bloque de consumo.
     *
     * @return Lista de costos.
     */
    public Cost[] getCost() {
        return cost;
    }

    /**
     * Establece la lista de costos asociados al bloque de consumo.
     *
     * @param cost Lista de costos.
     */
    public void setCost(Cost[] cost) {
        if (!isValidCost(cost)) {
            throw new PropertyConstraintException(cost, "La lista de costos no es válida.");
        }
        this.cost = cost;
    }

    private boolean isValidCost(Cost[] cost) {
        return cost != null
                && cost.length >= 1
                && cost.length <= 3
                && Arrays.stream(cost).allMatch(item -> item.validate());
    }

    /**
     * Valida si todas las propiedades del costo de consumo son correctas.
     *
     * @return `true` si todas las propiedades son válidas, `false` en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidStartValue(startValue) && isValidCost(cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsumptionCost that = (ConsumptionCost) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(startValue, that.startValue)
                && Arrays.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, startValue, Arrays.hashCode(cost));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("startValue", startValue)
                .add("cost", cost)
                .add("isValid", validate())
                .toString();
    }
}
