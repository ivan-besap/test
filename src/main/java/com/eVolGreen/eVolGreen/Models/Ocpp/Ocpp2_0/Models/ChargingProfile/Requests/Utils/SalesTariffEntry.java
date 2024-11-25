package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Entrada de Tarifa de Ventas (Sales Tariff Entry)
 *
 * <p>Representa una entrada individual dentro de una tarifa de ventas. Define el intervalo de tiempo relativo, el nivel de precio y los costos asociados al consumo en un período específico.
 */
public class SalesTariffEntry {

    /** Datos personalizados asociados a esta entrada de tarifa. */
    @Nullable
    private CustomData customData;

    /**
     * Intervalo de tiempo relativo.
     *
     * <p>Define el período de tiempo específico en relación con el inicio de la tarifa en el que esta entrada es válida.
     */
    private RelativeTimeInterval relativeTimeInterval;

    /**
     * Nivel de precio de esta entrada de tarifa.
     *
     * <p>Pequeños valores indican precios más económicos, mientras que valores grandes indican precios más altos. Corresponde al nivel de precio configurado en `NumEPriceLevels`.
     */
    @Nullable private Integer ePriceLevel;

    /**
     * Costos asociados al consumo.
     *
     * <p>Define los costos por unidad de energía o tiempo aplicables durante el intervalo especificado.
     */
    @Nullable private ConsumptionCost[] consumptionCost;

    /**
     * Constructor para la clase `SalesTariffEntry`.
     *
     * @param relativeTimeInterval Intervalo de tiempo relativo en el que esta entrada es válida.
     */
    public SalesTariffEntry(RelativeTimeInterval relativeTimeInterval) {
        setRelativeTimeInterval(relativeTimeInterval);
    }

    /**
     * Obtiene los datos personalizados asociados a esta entrada de tarifa.
     *
     * @return Datos personalizados, o `null` si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a esta entrada de tarifa.
     *
     * @param customData Datos personalizados, o `null` si no están definidos.
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
     * Obtiene el intervalo de tiempo relativo en el que esta entrada es válida.
     *
     * @return Intervalo de tiempo relativo.
     */
    public RelativeTimeInterval getRelativeTimeInterval() {
        return relativeTimeInterval;
    }

    /**
     * Establece el intervalo de tiempo relativo en el que esta entrada es válida.
     *
     * @param relativeTimeInterval Intervalo de tiempo relativo.
     */
    public void setRelativeTimeInterval(RelativeTimeInterval relativeTimeInterval) {
        if (!isValidRelativeTimeInterval(relativeTimeInterval)) {
            throw new PropertyConstraintException(
                    relativeTimeInterval, "El intervalo de tiempo relativo no es válido.");
        }
        this.relativeTimeInterval = relativeTimeInterval;
    }

    private boolean isValidRelativeTimeInterval(RelativeTimeInterval relativeTimeInterval) {
        return relativeTimeInterval != null && relativeTimeInterval.validate();
    }

    /**
     * Obtiene el nivel de precio de esta entrada de tarifa.
     *
     * @return Nivel de precio, o `null` si no está definido.
     */
    @Nullable
    public Integer getEPriceLevel() {
        return ePriceLevel;
    }

    /**
     * Establece el nivel de precio de esta entrada de tarifa.
     *
     * @param ePriceLevel Nivel de precio, o `null` si no está definido.
     */
    public void setEPriceLevel(@Nullable Integer ePriceLevel) {
        if (!isValidEPriceLevel(ePriceLevel)) {
            throw new PropertyConstraintException(ePriceLevel, "El nivel de precio no es válido.");
        }
        this.ePriceLevel = ePriceLevel;
    }

    private boolean isValidEPriceLevel(@Nullable Integer ePriceLevel) {
        return ePriceLevel == null || ePriceLevel >= 0;
    }

    /**
     * Agrega el nivel de precio de esta entrada de tarifa.
     *
     * @param ePriceLevel Nivel de precio, o `null` si no está definido.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public SalesTariffEntry withEPriceLevel(@Nullable Integer ePriceLevel) {
        setEPriceLevel(ePriceLevel);
        return this;
    }

    /**
     * Obtiene los costos asociados al consumo durante este período.
     *
     * @return Arreglo de costos de consumo, o `null` si no están definidos.
     */
    @Nullable
    public ConsumptionCost[] getConsumptionCost() {
        return consumptionCost;
    }

    /**
     * Establece los costos asociados al consumo durante este período.
     *
     * @param consumptionCost Arreglo de costos de consumo, o `null` si no están definidos.
     */
    public void setConsumptionCost(@Nullable ConsumptionCost[] consumptionCost) {
        if (!isValidConsumptionCost(consumptionCost)) {
            throw new PropertyConstraintException(consumptionCost, "Los costos de consumo no son válidos.");
        }
        this.consumptionCost = consumptionCost;
    }

    private boolean isValidConsumptionCost(@Nullable ConsumptionCost[] consumptionCost) {
        return consumptionCost == null
                || (consumptionCost.length >= 1
                && consumptionCost.length <= 3
                && Arrays.stream(consumptionCost).allMatch(ConsumptionCost::validate));
    }

    /**
     * Valida las propiedades de la entrada de tarifa.
     *
     * @return `true` si todas las propiedades son válidas, `false` en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidRelativeTimeInterval(relativeTimeInterval)
                && isValidEPriceLevel(ePriceLevel)
                && isValidConsumptionCost(consumptionCost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SalesTariffEntry that = (SalesTariffEntry) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(relativeTimeInterval, that.relativeTimeInterval)
                && Objects.equals(ePriceLevel, that.ePriceLevel)
                && Arrays.equals(consumptionCost, that.consumptionCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, relativeTimeInterval, ePriceLevel, Arrays.hashCode(consumptionCost));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("relativeTimeInterval", relativeTimeInterval)
                .add("ePriceLevel", ePriceLevel)
                .add("consumptionCost", consumptionCost)
                .add("isValid", validate())
                .toString();
    }
}
