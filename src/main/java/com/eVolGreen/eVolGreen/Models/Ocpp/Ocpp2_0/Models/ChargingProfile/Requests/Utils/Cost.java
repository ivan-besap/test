package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.CostKindEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Costo
 *
 * <p>Esta clase representa un costo asociado a una tarifa, incluyendo el tipo de costo,
 * la cantidad estimada o real y un multiplicador que define el valor final.
 */
public class Cost {

    /** Datos personalizados relacionados con el costo. */
    @Nullable
    private CustomData customData;

    /**
     * Tipo de costo.
     *
     * <p>Define el tipo de costo al que se refiere el atributo "amount" (cantidad). Por ejemplo, puede ser un costo por kWh.
     */
    private CostKindEnum costKind;

    /**
     * Cantidad estimada o real.
     *
     * <p>Indica el costo estimado o real asociado al tipo de costo definido.
     */
    private Integer amount;

    /**
     * Multiplicador de la cantidad.
     *
     * <p>Define el exponente en base 10 para calcular el valor final del costo como:
     * `amount * 10^amountMultiplier`. Los valores permitidos están en el rango de -3 a 3.
     */
    @Nullable private Integer amountMultiplier;

    /**
     * Constructor de la clase `Cost`.
     *
     * @param costKind Tipo de costo asociado.
     * @param amount Cantidad estimada o real del costo.
     */
    public Cost(CostKindEnum costKind, Integer amount) {
        setCostKind(costKind);
        setAmount(amount);
    }

    /**
     * Obtiene los datos personalizados relacionados con el costo.
     *
     * @return Datos personalizados o `null` si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con el costo.
     *
     * @param customData Datos personalizados o `null` si no están definidos.
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
     * @param customData Datos personalizados a validar.
     * @return `true` si los datos son válidos, `false` en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados relacionados con el costo.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de `Cost` para encadenar métodos.
     */
    public Cost withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de costo al que se refiere la cantidad.
     *
     * @return Tipo de costo.
     */
    public CostKindEnum getCostKind() {
        return costKind;
    }

    /**
     * Establece el tipo de costo al que se refiere la cantidad.
     *
     * @param costKind Tipo de costo.
     */
    public void setCostKind(CostKindEnum costKind) {
        if (!isValidCostKind(costKind)) {
            throw new PropertyConstraintException(costKind, "El tipo de costo no es válido.");
        }
        this.costKind = costKind;
    }

    private boolean isValidCostKind(CostKindEnum costKind) {
        return costKind != null;
    }

    /**
     * Obtiene la cantidad estimada o real del costo.
     *
     * @return Cantidad del costo.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Establece la cantidad estimada o real del costo.
     *
     * @param amount Cantidad del costo.
     */
    public void setAmount(Integer amount) {
        if (!isValidAmount(amount)) {
            throw new PropertyConstraintException(amount, "La cantidad no es válida.");
        }
        this.amount = amount;
    }

    private boolean isValidAmount(Integer amount) {
        return amount != null;
    }

    /**
     * Obtiene el multiplicador de la cantidad.
     *
     * @return Multiplicador de la cantidad o `null` si no está definido.
     */
    @Nullable
    public Integer getAmountMultiplier() {
        return amountMultiplier;
    }

    /**
     * Establece el multiplicador de la cantidad.
     *
     * <p>El valor final del costo se calcula como `amount * 10^amountMultiplier`.
     *
     * @param amountMultiplier Multiplicador de la cantidad.
     */
    public void setAmountMultiplier(@Nullable Integer amountMultiplier) {
        this.amountMultiplier = amountMultiplier;
    }

    /**
     * Añade el multiplicador de la cantidad.
     *
     * @param amountMultiplier Multiplicador de la cantidad.
     * @return Esta instancia de `Cost` para encadenar métodos.
     */
    public Cost withAmountMultiplier(@Nullable Integer amountMultiplier) {
        setAmountMultiplier(amountMultiplier);
        return this;
    }

    /**
     * Valida si los atributos de la clase son correctos.
     *
     * @return `true` si los atributos son válidos, `false` en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidCostKind(costKind) && isValidAmount(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cost that = (Cost) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(costKind, that.costKind)
                && Objects.equals(amount, that.amount)
                && Objects.equals(amountMultiplier, that.amountMultiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, costKind, amount, amountMultiplier);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("costKind", costKind)
                .add("amount", amount)
                .add("amountMultiplier", amountMultiplier)
                .add("isValid", validate())
                .toString();
    }
}
