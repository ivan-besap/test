package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Parámetros de Carga en Corriente Continua (DC)
 *
 * <p>Representa los parámetros relacionados con la carga en corriente continua de un vehículo eléctrico.
 */
public class DCChargingParameters {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /**
     * Corriente máxima (amperios) soportada por el vehículo eléctrico.
     *
     * <p>Incluye la capacidad del cable.
     */
    private Integer evMaxCurrent;

    /**
     * Voltaje máximo soportado por el vehículo eléctrico.
     */
    private Integer evMaxVoltage;

    /**
     * Cantidad de energía solicitada (en Wh).
     *
     * <p>Incluye la energía necesaria para el preacondicionamiento.
     */
    @Nullable private Integer energyAmount;

    /**
     * Potencia máxima (en W) soportada por el vehículo eléctrico.
     *
     * <p>Requerido para la carga en corriente continua.
     */
    @Nullable private Integer evMaxPower;

    /**
     * Estado de carga (SoC) actual de la batería (porcentaje).
     *
     * <p>Representa la cantidad de energía disponible en la batería.
     */
    @Nullable private Integer stateOfCharge;

    /**
     * Capacidad de la batería del vehículo eléctrico (en Wh).
     */
    @Nullable private Integer evEnergyCapacity;

    /**
     * Porcentaje de SoC que el vehículo considera como batería completamente cargada.
     *
     * <p>Valores posibles: 0 - 100.
     */
    @Nullable private Integer fullSoC;

    /**
     * Porcentaje de SoC que el vehículo considera como el fin del proceso de carga rápida.
     *
     * <p>Valores posibles: 0 - 100.
     */
    @Nullable private Integer bulkSoC;

    /**
     * Constructor para la clase DCChargingParameters.
     *
     * @param evMaxCurrent Corriente máxima soportada por el vehículo eléctrico.
     * @param evMaxVoltage Voltaje máximo soportado por el vehículo eléctrico.
     */
    public DCChargingParameters(Integer evMaxCurrent, Integer evMaxVoltage) {
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
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la corriente máxima soportada por el vehículo eléctrico.
     *
     * @return Corriente máxima soportada (amperios).
     */
    public Integer getEvMaxCurrent() {
        return evMaxCurrent;
    }

    /**
     * Establece la corriente máxima soportada por el vehículo eléctrico.
     *
     * @param evMaxCurrent Corriente máxima soportada (amperios).
     */
    public void setEvMaxCurrent(Integer evMaxCurrent) {
        if (!isValidEvMaxCurrent(evMaxCurrent)) {
            throw new PropertyConstraintException(evMaxCurrent, "Corriente máxima inválida.");
        }
        this.evMaxCurrent = evMaxCurrent;
    }

    /**
     * Verifica si la corriente máxima soportada por el vehículo eléctrico es válida.
     *
     * @param evMaxCurrent Corriente máxima soportada.
     * @return {@code true} si es válida o {@code false} en caso contrario.
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
     * Verifica si el voltaje máximo soportado por el vehículo eléctrico es válido.
     *
     * @param evMaxVoltage Voltaje máximo soportado.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidEvMaxVoltage(Integer evMaxVoltage) {
        return evMaxVoltage != null;
    }

    /**
     * Obtiene la cantidad de energía solicitada (en Wh).
     *
     * @return Cantidad de energía solicitada.
     */
    @Nullable
    public Integer getEnergyAmount() {
        return energyAmount;
    }

    /**
     * Establece la cantidad de energía solicitada (en Wh).
     *
     * @param energyAmount Cantidad de energía solicitada.
     */
    public void setEnergyAmount(@Nullable Integer energyAmount) {
        this.energyAmount = energyAmount;
    }

    /**
     * Añade la cantidad de energía solicitada (en Wh).
     *
     * @param energyAmount Cantidad de energía solicitada.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withEnergyAmount(@Nullable Integer energyAmount) {
        setEnergyAmount(energyAmount);
        return this;
    }

    /**
     * Obtiene la potencia máxima soportada por el vehículo eléctrico (en W).
     *
     * @return Potencia máxima soportada.
     */
    @Nullable
    public Integer getEvMaxPower() {
        return evMaxPower;
    }

    /**
     * Establece la potencia máxima soportada por el vehículo eléctrico (en W).
     *
     * @param evMaxPower Potencia máxima soportada.
     */
    public void setEvMaxPower(@Nullable Integer evMaxPower) {
        this.evMaxPower = evMaxPower;
    }

    /**
     * Añade la potencia máxima soportada por el vehículo eléctrico (en W).
     *
     * @param evMaxPower Potencia máxima soportada.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withEvMaxPower(@Nullable Integer evMaxPower) {
        setEvMaxPower(evMaxPower);
        return this;
    }

    /**
     * Obtiene el estado de carga (SoC) actual de la batería (porcentaje).
     *
     * @return Estado de carga actual de la batería.
     */
    @Nullable
    public Integer getStateOfCharge() {
        return stateOfCharge;
    }

    /**
     * Establece el estado de carga (SoC) actual de la batería (porcentaje).
     *
     * @param stateOfCharge Estado de carga actual de la batería.
     */
    public void setStateOfCharge(@Nullable Integer stateOfCharge) {
        if (!isValidStateOfCharge(stateOfCharge)) {
            throw new PropertyConstraintException(stateOfCharge, "Estado de carga inválido.");
        }
        this.stateOfCharge = stateOfCharge;
    }

    /**
     * Añade el estado de carga (SoC) actual de la batería (porcentaje).
     *
     * @param stateOfCharge Estado de carga actual de la batería.
     * @return La instancia actualizada.
     */
    private boolean isValidStateOfCharge(@Nullable Integer stateOfCharge) {
        return stateOfCharge == null || (stateOfCharge >= 0 && stateOfCharge <= 100);
    }

    /**
     * Añade el estado de carga (SoC) actual de la batería (porcentaje).
     *
     * @param stateOfCharge Estado de carga actual de la batería.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withStateOfCharge(@Nullable Integer stateOfCharge) {
        setStateOfCharge(stateOfCharge);
        return this;
    }

    /**
     * Obtiene la capacidad de la batería del vehículo eléctrico (en Wh).
     *
     * @return Capacidad de la batería.
     */
    @Nullable
    public Integer getEvEnergyCapacity() {
        return evEnergyCapacity;
    }

    /**
     * Establece la capacidad de la batería del vehículo eléctrico (en Wh).
     *
     * @param evEnergyCapacity Capacidad de la batería.
     */
    public void setEvEnergyCapacity(@Nullable Integer evEnergyCapacity) {
        this.evEnergyCapacity = evEnergyCapacity;
    }

    /**
     * Añade la capacidad de la batería del vehículo eléctrico (en Wh).
     *
     * @param evEnergyCapacity Capacidad de la batería.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withEvEnergyCapacity(@Nullable Integer evEnergyCapacity) {
        setEvEnergyCapacity(evEnergyCapacity);
        return this;
    }

    /**
     * Obtiene el porcentaje de SoC que el vehículo considera como batería completamente cargada.
     *
     * @return Porcentaje de SoC para batería completamente cargada.
     */
    @Nullable
    public Integer getFullSoC() {
        return fullSoC;
    }

    /**
     * Establece el porcentaje de SoC que el vehículo considera como batería completamente cargada.
     *
     * @param fullSoC Porcentaje de SoC para batería completamente cargada.
     */
    public void setFullSoC(@Nullable Integer fullSoC) {
        if (!isValidFullSoC(fullSoC)) {
            throw new PropertyConstraintException(fullSoC, "Porcentaje de carga completo inválido.");
        }
        this.fullSoC = fullSoC;
    }

    /**
     * Verifica si el porcentaje de SoC que el vehículo considera como batería completamente cargada es válido.
     *
     * @param fullSoC Porcentaje de SoC para batería completamente cargada.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidFullSoC(@Nullable Integer fullSoC) {
        return fullSoC == null || (fullSoC >= 0 && fullSoC <= 100);
    }

    /**
     * Añade el porcentaje de SoC que el vehículo considera como batería completamente cargada.
     *
     * @param fullSoC Porcentaje de SoC para batería completamente cargada.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withFullSoC(@Nullable Integer fullSoC) {
        setFullSoC(fullSoC);
        return this;
    }

    /**
     * Obtiene el porcentaje de SoC que el vehículo considera como fin del proceso de carga rápida.
     *
     * @return Porcentaje de SoC para fin del proceso de carga rápida.
     */
    @Nullable
    public Integer getBulkSoC() {
        return bulkSoC;
    }

    /**
     * Establece el porcentaje de SoC que el vehículo considera como fin del proceso de carga rápida.
     *
     * @param bulkSoC Porcentaje de SoC para fin del proceso de carga rápida.
     */
    public void setBulkSoC(@Nullable Integer bulkSoC) {
        if (!isValidBulkSoC(bulkSoC)) {
            throw new PropertyConstraintException(bulkSoC, "Porcentaje de carga rápida inválido.");
        }
        this.bulkSoC = bulkSoC;
    }

    /**
     * Verifica si el porcentaje de SoC que el vehículo considera como fin del proceso de carga rápida es válido.
     *
     * @param bulkSoC Porcentaje de SoC para fin del proceso de carga rápida.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidBulkSoC(@Nullable Integer bulkSoC) {
        return bulkSoC == null || (bulkSoC >= 0 && bulkSoC <= 100);
    }

    /**
     * Añade el porcentaje de SoC que el vehículo considera como fin del proceso de carga rápida.
     *
     * @param bulkSoC Porcentaje de SoC para fin del proceso de carga rápida.
     * @return La instancia actualizada.
     */
    public DCChargingParameters withBulkSoC(@Nullable Integer bulkSoC) {
        setBulkSoC(bulkSoC);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEvMaxCurrent(evMaxCurrent)
                && isValidEvMaxVoltage(evMaxVoltage)
                && isValidStateOfCharge(stateOfCharge)
                && isValidFullSoC(fullSoC)
                && isValidBulkSoC(bulkSoC);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DCChargingParameters that = (DCChargingParameters) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evMaxCurrent, that.evMaxCurrent)
                && Objects.equals(evMaxVoltage, that.evMaxVoltage)
                && Objects.equals(energyAmount, that.energyAmount)
                && Objects.equals(evMaxPower, that.evMaxPower)
                && Objects.equals(stateOfCharge, that.stateOfCharge)
                && Objects.equals(evEnergyCapacity, that.evEnergyCapacity)
                && Objects.equals(fullSoC, that.fullSoC)
                && Objects.equals(bulkSoC, that.bulkSoC);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                evMaxCurrent,
                evMaxVoltage,
                energyAmount,
                evMaxPower,
                stateOfCharge,
                evEnergyCapacity,
                fullSoC,
                bulkSoC);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evMaxCurrent", evMaxCurrent)
                .add("evMaxVoltage", evMaxVoltage)
                .add("energyAmount", energyAmount)
                .add("evMaxPower", evMaxPower)
                .add("stateOfCharge", stateOfCharge)
                .add("evEnergyCapacity", evEnergyCapacity)
                .add("fullSoC", fullSoC)
                .add("bulkSoC", bulkSoC)
                .add("isValid", validate())
                .toString();
    }
}

