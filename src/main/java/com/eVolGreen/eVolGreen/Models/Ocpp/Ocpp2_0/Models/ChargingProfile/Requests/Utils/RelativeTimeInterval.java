package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Intervalo de Tiempo Relativo
 *
 * <p>Representa un intervalo de tiempo definido en relación al momento actual (NOW). Incluye un punto de inicio y, opcionalmente, una duración.
 */
public class RelativeTimeInterval {

    /** Datos personalizados asociados al intervalo de tiempo relativo. */
    @Nullable
    private CustomData customData;

    /**
     * Inicio del intervalo.
     *
     * <p>Define el punto de inicio del intervalo, expresado en segundos desde el momento actual
     * (NOW).
     */
    private Integer start;

    /**
     * Duración del intervalo.
     *
     * <p>Especifica la duración del intervalo en segundos. Este valor es opcional.
     */
    @Nullable private Integer duration;

    /**
     * Constructor de la clase `RelativeTimeInterval`.
     *
     * @param start Inicio del intervalo, en segundos desde el momento actual (NOW).
     */
    public RelativeTimeInterval(Integer start) {
        setStart(start);
    }

    /**
     * Obtiene los datos personalizados asociados al intervalo.
     *
     * @return Datos personalizados o `null` si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al intervalo.
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
     * @return `true` si los datos personalizados son válidos, `false` en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados al intervalo.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia del intervalo para encadenar métodos.
     */
    public RelativeTimeInterval withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el inicio del intervalo en segundos desde el momento actual (NOW).
     *
     * @return Inicio del intervalo.
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Establece el inicio del intervalo en segundos desde el momento actual (NOW).
     *
     * @param start Inicio del intervalo.
     */
    public void setStart(Integer start) {
        if (!isValidStart(start)) {
            throw new PropertyConstraintException(start, "El inicio del intervalo no es válido.");
        }
        this.start = start;
    }

    private boolean isValidStart(Integer start) {
        return start != null;
    }

    /**
     * Obtiene la duración del intervalo en segundos.
     *
     * @return Duración del intervalo o `null` si no está definida.
     */
    @Nullable
    public Integer getDuration() {
        return duration;
    }

    /**
     * Establece la duración del intervalo en segundos.
     *
     * @param duration Duración del intervalo.
     */
    public void setDuration(@Nullable Integer duration) {
        this.duration = duration;
    }

    /**
     * Añade la duración al intervalo en segundos.
     *
     * @param duration Duración del intervalo.
     * @return Esta instancia del intervalo para encadenar métodos.
     */
    public RelativeTimeInterval withDuration(@Nullable Integer duration) {
        setDuration(duration);
        return this;
    }

    /**
     * Valida si todas las propiedades del intervalo son correctas.
     *
     * @return `true` si todas las propiedades son válidas, `false` en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidStart(start);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RelativeTimeInterval that = (RelativeTimeInterval) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(start, that.start)
                && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, start, duration);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("start", start)
                .add("duration", duration)
                .add("isValid", validate())
                .toString();
    }
}
