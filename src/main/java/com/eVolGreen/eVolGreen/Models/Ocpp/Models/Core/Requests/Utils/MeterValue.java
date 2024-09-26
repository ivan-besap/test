package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Validatable;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representa una colección de valores muestreados en OCPP 1.6.
 * Todos los valores muestreados dentro de un MeterValue tienen la misma marca de tiempo.
 * Esta clase implementa {@link Validatable} para mantener la consistencia con otras clases validables en el sistema.
 *
 * <p>Los campos obligatorios son {@code timestamp} (la marca de tiempo de los valores muestreados) y
 * {@code sampledValue} (un array de valores muestreados).</p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     SampledValue[] sampledValues = new SampledValue[]{
 *         new SampledValue("Energy.Active.Import.Register", "kWh", "0.5"),
 *         new SampledValue("Power.Active.Import", "W", "11000")
 *     };
 *     MeterValue meterValue = new MeterValue(ZonedDateTime.now(), sampledValues);
 *
 *     if (meterValue.validate()) {
 *         System.out.println("MeterValue válido: " + meterValue);
 *         // Procesar el MeterValue
 *     } else {
 *         System.out.println("MeterValue inválido");
 *     }
 * </pre>
 */
@XmlRootElement
@XmlType(propOrder = {"timestamp", "sampledValue"})
public class MeterValue implements Validatable {

    @NotNull(message = "timestamp es obligatorio")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = JsonFormat.Shape.STRING)
    private ZonedDateTime timestamp;

    @NotNull(message = "sampledValue es obligatorio")
    @Size(min = 1, message = "sampledValue debe contener al menos un elemento")
    private SampledValue[] sampledValue;

    /**
     * Constructor por defecto.
     * @deprecated use {@link #MeterValue(ZonedDateTime, SampledValue[])} para asegurar que se establezcan los campos requeridos
     */
    @Deprecated
    public MeterValue() {}

    /**
     * Construye un nuevo MeterValue con los campos obligatorios.
     *
     * @param timestamp la marca de tiempo de los valores muestreados; no debe ser nulo.
     * @param sampledValue el array de valores muestreados; no debe ser nulo ni vacío.
     */
    public MeterValue(ZonedDateTime timestamp, SampledValue[] sampledValue) {
        setTimestamp(timestamp);
        setSampledValue(sampledValue);
    }

    /**
     * Obtiene la marca de tiempo de los valores muestreados.
     *
     * @return la marca de tiempo.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la marca de tiempo de los valores muestreados.
     *
     * @param timestamp la marca de tiempo a establecer; no debe ser nulo.
     */
    @XmlElement
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el array de valores muestreados.
     *
     * @return el array de valores muestreados.
     */
    public SampledValue[] getSampledValue() {
        return sampledValue;
    }

    /**
     * Establece el array de valores muestreados.
     *
     * @param sampledValue el array de valores muestreados a establecer; no debe ser nulo ni vacío.
     */
    @XmlElement
    public void setSampledValue(SampledValue[] sampledValue) {
        this.sampledValue = sampledValue;
    }

    /**
     * Valida que los campos obligatorios no sean nulos y que el array de valores muestreados no esté vacío.
     *
     * @return {@code true} si el MeterValue es válido; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return timestamp != null && sampledValue != null && sampledValue.length > 0 &&
                Arrays.stream(sampledValue).allMatch(SampledValue::validate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeterValue that = (MeterValue) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Arrays.equals(sampledValue, that.sampledValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, Arrays.hashCode(sampledValue));
    }

    @Override
    public String toString() {
        return "MeterValue{" +
                "timestamp=" + timestamp +
                ", sampledValue=" + Arrays.toString(sampledValue) +
                ", isValid=" + validate() +
                '}';
    }
}