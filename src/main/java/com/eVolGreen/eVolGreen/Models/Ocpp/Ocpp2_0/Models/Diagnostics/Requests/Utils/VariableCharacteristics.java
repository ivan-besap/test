package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.DataEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Características fijas de solo lectura de una variable.
 * <p>
 * Esta clase describe los atributos y límites de las variables que forman parte del protocolo OCPP.
 * </p>
 */
public class VariableCharacteristics {

    /** Datos personalizados asociados a esta variable. */
    @Nullable
    private CustomData customData;

    /** Unidad de medida de la variable, requerida si el valor transmitido incluye una unidad. */
    @Nullable private String unit;

    /** Tipo de dato de esta variable (por ejemplo, Integer, String, Boolean). */
    private DataEnum dataType;

    /** Valor mínimo posible para esta variable. */
    @Nullable private Double minLimit;

    /**
     * Valor máximo posible para esta variable. Si la variable es de tipo String, OptionList,
     * SequenceList o MemberList, este campo define la longitud máxima del valor (en formato CSV).
     */
    @Nullable private Double maxLimit;

    /**
     * Lista de valores permitidos para la variable si es de tipo OptionList, MemberList o SequenceList.
     * <p>
     * Ejemplo:
     * <ul>
     *   <li>OptionList: La variable debe contener un solo valor de esta lista.</li>
     *   <li>MemberList: La variable puede ser un conjunto (desordenado) de valores válidos.</li>
     *   <li>SequenceList: La variable puede ser un conjunto ordenado de valores válidos.</li>
     * </ul>
     * Este campo es una cadena separada por comas.
     */
    @Nullable private String valuesList;

    /** Indicador que señala si esta variable admite monitoreo. */
    private Boolean supportsMonitoring;

    /**
     * Constructor para la clase `VariableCharacteristics`.
     *
     * @param dataType Tipo de dato de la variable.
     * @param supportsMonitoring Indicador de soporte de monitoreo.
     */
    public VariableCharacteristics(DataEnum dataType, Boolean supportsMonitoring) {
        setDataType(dataType);
        setSupportsMonitoring(supportsMonitoring);
    }

    // Métodos Getters, Setters y Validadores con documentación

    /**
     * Obtiene los datos personalizados de esta variable.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta variable.
     *
     * @param customData Datos personalizados.
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
     * Agrega datos personalizados a esta variable.
     *
     * @param customData Datos personalizados.
     * @return Instancia actual de la clase.
     */
    public VariableCharacteristics withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la unidad de medida asociada a la variable.
     *
     * @return Unidad de medida o {@code null} si no está definida.
     */
    @Nullable
    public String getUnit() {
        return unit;
    }

    /**
     * Establece la unidad de medida de la variable.
     *
     * @param unit Unidad de medida.
     */
    public void setUnit(@Nullable String unit) {
        if (!isValidUnit(unit)) {
            throw new PropertyConstraintException(unit, "La unidad no es válida.");
        }
        this.unit = unit;
    }

    private boolean isValidUnit(@Nullable String unit) {
        return unit == null || unit.length() <= 16;
    }

    /**
     * Agrega una unidad de medida a la variable.
     *
     * @param unit Unidad de medida.
     * @return Instancia actual de la clase.
     */
    public VariableCharacteristics withUnit(@Nullable String unit) {
        setUnit(unit);
        return this;
    }

    /**
     * Obtiene el tipo de dato de la variable.
     *
     * @return Tipo de dato.
     */
    public DataEnum getDataType() {
        return dataType;
    }

    /**
     * Establece el tipo de dato de la variable.
     *
     * @param dataType Tipo de dato.
     */
    public void setDataType(DataEnum dataType) {
        if (!isValidDataType(dataType)) {
            throw new PropertyConstraintException(dataType, "dataType is invalid");
        }
        this.dataType = dataType;
    }

    /**
     * Agrega un tipo de dato a la variable.
     *
     * @param dataType Tipo de dato.
     * @return Instancia actual de la clase.
     */
    private boolean isValidDataType(DataEnum dataType) {
        return dataType != null;
    }

    /**
     * Obtiene el valor mínimo permitido para la variable.
     *
     * @return Valor mínimo o {@code null} si no está definido.
     */
    @Nullable
    public Double getMinLimit() {
        return minLimit;
    }

    /**
     * Establece el valor mínimo permitido para la variable.
     *
     * @param minLimit Valor mínimo.
     */
    public void setMinLimit(@Nullable Double minLimit) {
        this.minLimit = minLimit;
    }

    /**
     * Agrega un valor mínimo a la variable.
     *
     * @param minLimit Valor mínimo.
     * @return Instancia actual de la clase.
     */
    public VariableCharacteristics withMinLimit(@Nullable Double minLimit) {
        setMinLimit(minLimit);
        return this;
    }

    /**
     * Obtiene el valor máximo permitido para la variable.
     *
     * @return Valor máximo o {@code null} si no está definido.
     */
    @Nullable
    public Double getMaxLimit() {
        return maxLimit;
    }

    /**
     * Establece el valor máximo permitido para la variable.
     *
     * @param maxLimit Valor máximo.
     */
    public void setMaxLimit(@Nullable Double maxLimit) {
        this.maxLimit = maxLimit;
    }

    /**
     * Agrega un valor máximo a la variable.
     *
     * @param maxLimit Valor máximo.
     * @return Instancia actual de la clase.
     */
    public VariableCharacteristics withMaxLimit(@Nullable Double maxLimit) {
        setMaxLimit(maxLimit);
        return this;
    }

    /**
     * Obtiene la lista de valores válidos para la variable.
     *
     * @return Lista de valores válidos o {@code null} si no está definida.
     */
    @Nullable
    public String getValuesList() {
        return valuesList;
    }

    /**
     * Establece la lista de valores válidos para la variable.
     *
     * @param valuesList Lista de valores separados por comas.
     */
    public void setValuesList(@Nullable String valuesList) {
        if (!isValidValuesList(valuesList)) {
            throw new PropertyConstraintException(valuesList, "La lista de valores no es válida.");
        }
        this.valuesList = valuesList;
    }

    private boolean isValidValuesList(@Nullable String valuesList) {
        return valuesList == null || valuesList.length() <= 1000;
    }

    /**
     * Agrega una lista de valores válidos a la variable.
     *
     * @param valuesList Lista de valores separados por comas.
     * @return Instancia actual de la clase.
     */
    public VariableCharacteristics withValuesList(@Nullable String valuesList) {
        setValuesList(valuesList);
        return this;
    }

    /**
     * Indica si la variable admite monitoreo.
     *
     * @return {@code true} si admite monitoreo, de lo contrario {@code false}.
     */
    public Boolean getSupportsMonitoring() {
        return supportsMonitoring;
    }

    /**
     * Establece si la variable admite monitoreo.
     *
     * @param supportsMonitoring Indicador de soporte de monitoreo.
     */
    public void setSupportsMonitoring(Boolean supportsMonitoring) {
        if (!isValidSupportsMonitoring(supportsMonitoring)) {
            throw new PropertyConstraintException(supportsMonitoring, "supportsMonitoring is invalid");
        }
        this.supportsMonitoring = supportsMonitoring;
    }

    /**
     * Agrega un indicador de soporte de monitoreo a la variable.
     *
     * @param supportsMonitoring Indicador de soporte de monitoreo.
     * @return Instancia actual de la clase.
     */
    private boolean isValidSupportsMonitoring(Boolean supportsMonitoring) {
        return supportsMonitoring != null;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidUnit(unit)
                && isValidDataType(dataType)
                && isValidValuesList(valuesList)
                && isValidSupportsMonitoring(supportsMonitoring);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VariableCharacteristics that = (VariableCharacteristics) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(unit, that.unit)
                && Objects.equals(dataType, that.dataType)
                && Objects.equals(minLimit, that.minLimit)
                && Objects.equals(maxLimit, that.maxLimit)
                && Objects.equals(valuesList, that.valuesList)
                && Objects.equals(supportsMonitoring, that.supportsMonitoring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, unit, dataType, minLimit, maxLimit, valuesList, supportsMonitoring);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("unit", unit)
                .add("dataType", dataType)
                .add("minLimit", minLimit)
                .add("maxLimit", maxLimit)
                .add("valuesList", valuesList)
                .add("supportsMonitoring", supportsMonitoring)
                .add("isValid", validate())
                .toString();
    }
}
