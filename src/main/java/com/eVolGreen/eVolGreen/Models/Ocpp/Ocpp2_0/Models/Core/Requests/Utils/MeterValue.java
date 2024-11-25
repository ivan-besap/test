package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representa un valor medido en un punto de carga.
 * <p>
 * Contiene una colección de valores medidos en un momento específico del tiempo,
 * acompañados de información adicional opcional.
 */
public class MeterValue {

    /** Datos personalizados asociados al valor medido. */
    @Nullable
    private CustomData customData;

    /**
     * Colección de valores muestreados.
     * <p>
     * Cada valor muestreado puede incluir campos opcionales adicionales. Por defecto, se interpreta
     * como una lectura de registro de energía activa importada en unidades de Wh (Watt-hora).
     */
    private SampledValue[] sampledValue;

    /**
     * Marca de tiempo.
     * <p>
     * Indica el momento exacto en que se tomaron los valores medidos.
     */
    private ZonedDateTime timestamp;

    /**
     * Constructor de la clase MeterValue.
     *
     * @param sampledValue Colección de valores muestreados.
     * @param timestamp Marca de tiempo de los valores medidos.
     */
    public MeterValue(SampledValue[] sampledValue, ZonedDateTime timestamp) {
        setSampledValue(sampledValue);
        setTimestamp(timestamp);
    }

    /**
     * Obtiene los datos personalizados asociados al valor medido.
     *
     * @return Un objeto {@link CustomData} con los datos personalizados, o {@code null} si no se han establecido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al valor medido.
     *
     * @param customData Un objeto {@link CustomData} con los datos personalizados.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
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
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asocia datos personalizados y devuelve esta instancia.
     *
     * @param customData Un objeto {@link CustomData} con los datos personalizados.
     * @return Esta instancia de {@link MeterValue}.
     */
    public MeterValue withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la colección de valores muestreados.
     *
     * @return Un arreglo de {@link SampledValue} con los valores muestreados.
     */
    public SampledValue[] getSampledValue() {
        return sampledValue;
    }

    /**
     * Establece la colección de valores muestreados.
     *
     * @param sampledValue Un arreglo de {@link SampledValue} con los valores muestreados.
     * @throws PropertyConstraintException Si la colección de valores muestreados no es válida.
     */
    public void setSampledValue(SampledValue[] sampledValue) {
        if (!isValidSampledValue(sampledValue)) {
            throw new PropertyConstraintException(sampledValue, "Los valores muestreados no son válidos.");
        }
        this.sampledValue = sampledValue;
    }

    /**
     * Verifica si la colección de valores muestreados es válida.
     *
     * @param sampledValue La colección de valores muestreados a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidSampledValue(SampledValue[] sampledValue) {
        return sampledValue != null
                && sampledValue.length >= 1
                && Arrays.stream(sampledValue).allMatch(item -> item.validate());
    }

    /**
     * Obtiene la marca de tiempo de los valores medidos.
     *
     * @return Un objeto {@link ZonedDateTime} que representa la marca de tiempo.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la marca de tiempo de los valores medidos.
     *
     * @param timestamp Un objeto {@link ZonedDateTime} que representa la marca de tiempo.
     * @throws PropertyConstraintException Si la marca de tiempo no es válida.
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        if (!isValidTimestamp(timestamp)) {
            throw new PropertyConstraintException(timestamp, "La marca de tiempo no es válida.");
        }
        this.timestamp = timestamp;
    }

    /**
     * Verifica si la marca de tiempo es válida.
     *
     * @param timestamp La marca de tiempo a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidTimestamp(ZonedDateTime timestamp) {
        return timestamp != null;
    }

    /**
     * Valida la instancia actual.
     *
     * @return {@code true} si la instancia es válida, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidSampledValue(sampledValue)
                && isValidTimestamp(timestamp);
    }

    /**
     * Compara esta instancia con otro objeto para determinar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MeterValue that = (MeterValue) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(sampledValue, that.sampledValue)
                && Objects.equals(timestamp, that.timestamp);
    }

    /**
     * Calcula el código hash para esta instancia.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(sampledValue), timestamp);
    }

    /**
     * Devuelve una representación en forma de cadena de esta instancia.
     *
     * @return Una cadena que representa esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("sampledValue", sampledValue)
                .add("timestamp", timestamp)
                .add("isValid", validate())
                .toString();
    }
}
