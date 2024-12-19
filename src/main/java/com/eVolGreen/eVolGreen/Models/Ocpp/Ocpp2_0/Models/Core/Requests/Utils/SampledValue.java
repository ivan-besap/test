package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.LocationEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.MeasurandEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.PhaseEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.ReadingContextEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa un valor muestreado de un medidor.
 * <p>
 * Cada valor muestreado puede incluir campos opcionales para proporcionar información adicional.
 * Por defecto, se interpreta como una lectura del registro de energía activa importada en unidades de Wh (Watt-hora).
 */
public class SampledValue {

    /** Datos personalizados asociados al valor muestreado. */
    @Nullable
    private CustomData customData;

    /**
     * El valor medido.
     * <p>
     * Representa la medición registrada en el momento especificado.
     */
    private Double value;

    /**
     * Contexto de la lectura.
     * <p>
     * Define si la lectura es un inicio, final o un muestreo periódico. Valor por defecto: "Sample.Periodic".
     */
    @Nullable
    private ReadingContextEnum context;

    /**
     * Tipo de medición realizada.
     * <p>
     * Por defecto: "Energy.Active.Import.Register".
     */
    @Nullable
    private MeasurandEnum measurand;

    /**
     * Fase de la medición.
     * <p>
     * Indica cómo se interpreta el valor medido, por ejemplo, entre L1 y neutro (L1-N).
     * Si está ausente, el valor se interpreta como un valor general.
     */
    @Nullable
    private PhaseEnum phase;

    /**
     * Ubicación de la medición.
     * <p>
     * Especifica dónde se tomó el valor medido. Por defecto: "Outlet".
     */
    @Nullable
    private LocationEnum location;

    /** Versión firmada del valor del medidor. */
    @Nullable
    private SignedMeterValue signedMeterValue;

    /** Unidad de medida con un multiplicador opcional. */
    @Nullable
    private UnitOfMeasure unitOfMeasure;

    /**
     * Constructor de la clase SampledValue.
     *
     * @param value El valor medido.
     */
    public SampledValue(Double value) {
        setValue(value);
    }

    /**
     * Obtiene los datos personalizados asociados al valor muestreado.
     *
     * @return Un objeto {@link CustomData} con los datos personalizados, o {@code null} si no se han establecido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al valor muestreado.
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
     * Obtiene el valor medido.
     *
     * @return El valor medido.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Establece el valor medido.
     *
     * @param value El valor medido.
     * @throws PropertyConstraintException Si el valor es nulo.
     */
    public void setValue(Double value) {
        if (!isValidValue(value)) {
            throw new PropertyConstraintException(value, "El valor medido no es válido.");
        }
        this.value = value;
    }

    /**
     * Verifica si el valor medido es válido.
     *
     * @param value El valor medido a validar.
     * @return {@code true} si el valor es válido, {@code false} en caso contrario.
     */
    private boolean isValidValue(Double value) {
        return value != null;
    }

    /**
     * Obtiene el contexto de la lectura.
     *
     * @return El contexto de la lectura. Si no se establece, retorna el valor por defecto "Sample.Periodic".
     */
    public ReadingContextEnum getContext() {
        return context != null ? context : ReadingContextEnum.SamplePeriodic;
    }

    /**
     * Establece el contexto de la lectura.
     *
     * @param context El contexto de la lectura.
     */
    public void setContext(@Nullable ReadingContextEnum context) {
        this.context = context;
    }

    /**
     * Verifica si el contexto de la lectura es válido.
     *
     * @param context El contexto de la lectura a validar.
     * @return {@code true} si el contexto es válido, {@code false} en caso contrario.
     */
    public SampledValue withContext(@Nullable ReadingContextEnum context) {
        setContext(context);
        return this;
    }

    /**
     * Obtiene el tipo de medición realizada.
     *
     * @return El tipo de medición. Si no se establece, retorna el valor por defecto "Energy.Active.Import.Register".
     */
    public MeasurandEnum getMeasurand() {
        return measurand != null ? measurand : MeasurandEnum.EnergyActiveImportRegister;
    }

    /**
     * Establece el tipo de medición realizada.
     *
     * @param measurand El tipo de medición.
     */
    public void setMeasurand(@Nullable MeasurandEnum measurand) {
        this.measurand = measurand;
    }

    /**
     * Establece el tipo de medición realizada.
     *
     * @param measurand El tipo de medición.
     * @return La instancia actual de {@link SampledValue}.
     */
    public SampledValue withMeasurand(@Nullable MeasurandEnum measurand) {
        setMeasurand(measurand);
        return this;
    }

    /**
     * Obtiene la fase de la medición.
     *
     * @return La fase de la medición, o {@code null} si no se ha definido.
     */
    @Nullable
    public PhaseEnum getPhase() {
        return phase;
    }

    /**
     * Establece la fase de la medición.
     *
     * @param phase La fase de la medición.
     */
    public void setPhase(@Nullable PhaseEnum phase) {
        this.phase = phase;
    }

    /**
     * Establece la fase de la medición.
     *
     * @param phase La fase de la medición.
     * @return La instancia actual de {@link SampledValue}.
     */
    public SampledValue withPhase(@Nullable PhaseEnum phase) {
        setPhase(phase);
        return this;
    }

    /**
     * Obtiene la ubicación de la medición.
     *
     * @return La ubicación de la medición. Si no se establece, retorna "Outlet".
     */
    public LocationEnum getLocation() {
        return location != null ? location : LocationEnum.Outlet;
    }

    /**
     * Establece la ubicación de la medición.
     *
     * @param location La ubicación de la medición.
     */
    public void setLocation(@Nullable LocationEnum location) {
        this.location = location;
    }

    /**
     * Establece la ubicación de la medición.
     *
     * @param location La ubicación de la medición.
     * @return La instancia actual de {@link SampledValue}.
     */
    public SampledValue withLocation(@Nullable LocationEnum location) {
        setLocation(location);
        return this;
    }

    /**
     * Obtiene la versión firmada del valor medido.
     *
     * @return La versión firmada del valor medido, o {@code null} si no se ha definido.
     */
    @Nullable
    public SignedMeterValue getSignedMeterValue() {
        return signedMeterValue;
    }

    /**
     * Establece la versión firmada del valor medido.
     *
     * @param signedMeterValue La versión firmada del valor medido.
     * @throws PropertyConstraintException Si la versión firmada no es válida.
     */
    public void setSignedMeterValue(@Nullable SignedMeterValue signedMeterValue) {
        if (!isValidSignedMeterValue(signedMeterValue)) {
            throw new PropertyConstraintException(signedMeterValue, "El valor firmado no es válido.");
        }
        this.signedMeterValue = signedMeterValue;
    }

    /**
     * Verifica si la versión firmada del valor medido es válida.
     *
     * @param signedMeterValue La versión firmada del valor medido a validar.
     * @return {@code true} si la versión firmada es válida, {@code false} en caso contrario.
     */
    private boolean isValidSignedMeterValue(@Nullable SignedMeterValue signedMeterValue) {
        return signedMeterValue == null || signedMeterValue.validate();
    }

    /**
     * Establece la versión firmada del valor medido.
     *
     * @param signedMeterValue La versión firmada del valor medido.
     * @return La instancia actual de {@link SampledValue}.
     */
    public SampledValue withSignedMeterValue(@Nullable SignedMeterValue signedMeterValue) {
        setSignedMeterValue(signedMeterValue);
        return this;
    }

    /**
     * Obtiene la unidad de medida asociada.
     *
     * @return La unidad de medida, o {@code null} si no se ha definido.
     */
    @Nullable
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Establece la unidad de medida asociada.
     *
     * @param unitOfMeasure La unidad de medida.
     * @throws PropertyConstraintException Si la unidad de medida no es válida.
     */
    public void setUnitOfMeasure(@Nullable UnitOfMeasure unitOfMeasure) {
        if (!isValidUnitOfMeasure(unitOfMeasure)) {
            throw new PropertyConstraintException(unitOfMeasure, "La unidad de medida no es válida.");
        }
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Verifica si la unidad de medida es válida.
     *
     * @param unitOfMeasure La unidad de medida a validar.
     * @return {@code true} si la unidad de medida es válida, {@code false} en caso contrario.
     */
    private boolean isValidUnitOfMeasure(@Nullable UnitOfMeasure unitOfMeasure) {
        return unitOfMeasure == null || unitOfMeasure.validate();
    }

    /**
     * Establece la unidad de medida asociada.
     *
     * @param unitOfMeasure La unidad de medida.
     * @return La instancia actual de {@link SampledValue}.
     */
    public SampledValue withUnitOfMeasure(@Nullable UnitOfMeasure unitOfMeasure) {
        setUnitOfMeasure(unitOfMeasure);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidValue(value)
                && isValidSignedMeterValue(signedMeterValue)
                && isValidUnitOfMeasure(unitOfMeasure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SampledValue that = (SampledValue) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(value, that.value)
                && Objects.equals(context, that.context)
                && Objects.equals(measurand, that.measurand)
                && Objects.equals(phase, that.phase)
                && Objects.equals(location, that.location)
                && Objects.equals(signedMeterValue, that.signedMeterValue)
                && Objects.equals(unitOfMeasure, that.unitOfMeasure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, value, context, measurand, phase, location, signedMeterValue, unitOfMeasure);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("value", value)
                .add("context", context)
                .add("measurand", measurand)
                .add("phase", phase)
                .add("location", location)
                .add("signedMeterValue", signedMeterValue)
                .add("unitOfMeasure", unitOfMeasure)
                .add("isValid", validate())
                .toString();
    }
}
