package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Parámetros de Carga en Corriente Alterna (AC)
 *
 * <p>Representa los parámetros relacionados con la carga en corriente alterna de un vehículo eléctrico.
 */
public class ACChargingParameters {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Cantidad de energía solicitada (en Wh).
     *
     * <p>Incluye la energía necesaria para el preacondicionamiento.
     */
    private Integer energyAmount;

    /**
     * Corriente mínima (amperios) soportada por el vehículo eléctrico (por fase).
     */
    private Integer evMinCurrent;

    /**
     * Corriente máxima (amperios) soportada por el vehículo eléctrico (por fase).
     *
     * <p>Incluye la capacidad del cable.
     */
    private Integer evMaxCurrent;

    /**
     * Voltaje máximo soportado por el vehículo eléctrico.
     */
    private Integer evMaxVoltage;

    /**
     * Constructor para la clase ACChargingParameters.
     *
     * @param energyAmount Cantidad de energía solicitada (en Wh).
     * @param evMinCurrent Corriente mínima soportada por el vehículo eléctrico (por fase).
     * @param evMaxCurrent Corriente máxima soportada por el vehículo eléctrico (por fase).
     * @param evMaxVoltage Voltaje máximo soportado por el vehículo eléctrico.
     */
    public ACChargingParameters(
            Integer energyAmount, Integer evMinCurrent, Integer evMaxCurrent, Integer evMaxVoltage) {
        setEnergyAmount(energyAmount);
        setEvMinCurrent(evMinCurrent);
        setEvMaxCurrent(evMaxCurrent);
        setEvMaxVoltage(evMaxVoltage);
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
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
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
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia de la clase.
     */
    public ACChargingParameters withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la cantidad de energía solicitada (en Wh).
     *
     * @return Cantidad de energía solicitada.
     */
    public Integer getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Establece la cantidad de energía solicitada (en Wh).
     *
     * @param energyAmount Cantidad de energía solicitada.
     */
    public void setEnergyAmount(Integer energyAmount) {
        if (!isValidEnergyAmount(energyAmount)) {
            throw new PropertyConstraintException(energyAmount, "Cantidad de energía inválida.");
        }
        this.energyAmount = energyAmount;
    }

    /**
     * Valida si la cantidad de energía solicitada es válida.
     *
     * @param energyAmount Cantidad de energía a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidEnergyAmount(Integer energyAmount) {
        return energyAmount != null;
    }

    /**
     * Obtiene la corriente mínima soportada por el vehículo eléctrico (por fase).
     *
     * @return Corriente mínima soportada.
     */
    public Integer getEvMinCurrent() {
        return evMinCurrent;
    }

    /**
     * Establece la corriente mínima soportada por el vehículo eléctrico (por fase).
     *
     * @param evMinCurrent Corriente mínima soportada.
     */
    public void setEvMinCurrent(Integer evMinCurrent) {
        if (!isValidEvMinCurrent(evMinCurrent)) {
            throw new PropertyConstraintException(evMinCurrent, "Corriente mínima inválida.");
        }
        this.evMinCurrent = evMinCurrent;
    }

    /**
     * Valida si la corriente mínima es válida.
     *
     * @param evMinCurrent Corriente mínima a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidEvMinCurrent(Integer evMinCurrent) {
        return evMinCurrent != null;
    }

    /**
     * Obtiene la corriente máxima soportada por el vehículo eléctrico (por fase).
     *
     * @return Corriente máxima soportada.
     */
    public Integer getEvMaxCurrent() {
        return evMaxCurrent;
    }

    /**
     * Establece la corriente máxima soportada por el vehículo eléctrico (por fase).
     *
     * @param evMaxCurrent Corriente máxima soportada.
     */
    public void setEvMaxCurrent(Integer evMaxCurrent) {
        if (!isValidEvMaxCurrent(evMaxCurrent)) {
            throw new PropertyConstraintException(evMaxCurrent, "Corriente máxima inválida.");
        }
        this.evMaxCurrent = evMaxCurrent;
    }

    /**
     * Valida si la corriente máxima es válida.
     *
     * @param evMaxCurrent Corriente máxima a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidEvMaxCurrent(Integer evMaxCurrent) {
        return evMaxCurrent != null;
    }

    /**
     * Obtiene el voltaje máximo soportado por el vehículo eléctrico.
     *
     * @return Voltaje máximo soportado.
     */
    public Integer getEvMaxVoltage() {
        return evMaxVoltage;
    }

    /**
     * Establece el voltaje máximo soportado por el vehículo eléctrico.
     *
     * @param evMaxVoltage Voltaje máximo soportado.
     */
    public void setEvMaxVoltage(Integer evMaxVoltage) {
        if (!isValidEvMaxVoltage(evMaxVoltage)) {
            throw new PropertyConstraintException(evMaxVoltage, "Voltaje máximo inválido.");
        }
        this.evMaxVoltage = evMaxVoltage;
    }

    /**
     * Valida si el voltaje máximo es válido.
     *
     * @param evMaxVoltage Voltaje máximo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvMaxVoltage(Integer evMaxVoltage) {
        return evMaxVoltage != null;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEnergyAmount(energyAmount)
                && isValidEvMinCurrent(evMinCurrent)
                && isValidEvMaxCurrent(evMaxCurrent)
                && isValidEvMaxVoltage(evMaxVoltage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ACChargingParameters that = (ACChargingParameters) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(energyAmount, that.energyAmount)
                && Objects.equals(evMinCurrent, that.evMinCurrent)
                && Objects.equals(evMaxCurrent, that.evMaxCurrent)
                && Objects.equals(evMaxVoltage, that.evMaxVoltage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, energyAmount, evMinCurrent, evMaxCurrent, evMaxVoltage);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("energyAmount", energyAmount)
                .add("evMinCurrent", evMinCurrent)
                .add("evMaxCurrent", evMaxCurrent)
                .add("evMaxVoltage", evMaxVoltage)
                .add("isValid", validate())
                .toString();
    }
}
