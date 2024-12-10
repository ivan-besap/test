package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils;

import javax.validation.constraints.NotNull;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * Representa un valor muestreado en OCPP 1.6.
 * Es parte de la información adicional que puede ser enviada en las transacciones.
 */
public class SampledValue {

    @NotNull(message = "El valor es obligatorio")
    @JsonProperty("value")
    private String value;

    @JsonProperty("context")
    private SampledValueContext context = SampledValueContext.Sample_Periodic; // Valor por defecto

    @JsonProperty("format")
    private SampledValueFormat format = SampledValueFormat.Raw; // Valor por defecto

    @JsonProperty("measurand")
    private SampledValueMeasurand measurand = SampledValueMeasurand.Energy_Active_Import_Register; // Valor por defecto

    @JsonProperty("phase")
    private String phase;

    @JsonProperty("location")
    private SampledValueLocation location = SampledValueLocation.Outlet; // Valor por defecto

    @JsonProperty("unit")
    private SampledValueUnit unit;

    // Constructor por defecto
    public SampledValue() {}

    // Constructor con campo obligatorio
    public SampledValue(String value) {
        this.value = value;
    }

    // Getters y setters con anotaciones
    public String getValue() {
        return value;
    }

    @XmlElement
    public void setValue(String value) {
        this.value = value;
    }

    public SampledValueContext getContext() {
        return context;
    }

    @XmlElement
    public void setContext(SampledValueContext context) {
        this.context = context;
    }

    public SampledValueFormat getFormat() {
        return format;
    }

    @XmlElement
    public void setFormat(SampledValueFormat format) {
        this.format = format;
    }

    public SampledValueMeasurand getMeasurand() {
        return measurand;
    }

    @XmlElement
    public void setMeasurand(SampledValueMeasurand measurand) {
        this.measurand = measurand;
    }

    public String getPhase() {
        return phase;
    }

    @XmlElement
    public void setPhase(String phase) {
        this.phase = phase;
    }

    public SampledValueLocation getLocation() {
        return location;
    }

    @XmlElement
    public void setLocation(SampledValueLocation location) {
        this.location = location;
    }

    public SampledValueUnit getUnit() {
        return unit;
    }

    @XmlElement
    public void setUnit(SampledValueUnit unit) {
        this.unit = unit;
    }

    // Método de validación
    public boolean validate() {
        return value != null;
    }

    // Sobrescritura de equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampledValue that = (SampledValue) o;
        return Objects.equals(value, that.value) &&
                context == that.context &&
                format == that.format &&
                measurand == that.measurand &&
                Objects.equals(phase, that.phase) &&
                location == that.location &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, context, format, measurand, phase, location, unit);
    }

    @Override
    public String toString() {
        return "SampledValue{" +
                "value='" + value + '\'' +
                ", context=" + context +
                ", format=" + format +
                ", measurand=" + measurand +
                ", phase=" + phase +
                ", location=" + location +
                ", unit=" + unit +
                '}';
    }
}
