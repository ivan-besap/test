package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Configuración de parámetros para la generación de logs.
 *
 * <p>Permite especificar la ubicación remota donde se almacenarán los logs, así como los límites de tiempo para filtrar las entradas de logs.
 */
public class LogParameters {

    /** Información personalizada adicional */
    @Nullable
    private CustomData customData;

    /** URL donde se almacenarán los logs en el sistema remoto */
    private String remoteLocation;

    /** Fecha y hora más antigua de las entradas de logs a incluir */
    @Nullable
    private ZonedDateTime oldestTimestamp;

    /** Fecha y hora más reciente de las entradas de logs a incluir */
    @Nullable
    private ZonedDateTime latestTimestamp;

    /**
     * Constructor de la clase LogParameters.
     *
     * @param remoteLocation URL del sistema remoto donde se almacenarán los logs.
     */
    public LogParameters(String remoteLocation) {
        setRemoteLocation(remoteLocation);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de LogParameters.
     */
    public LogParameters withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la URL remota donde se almacenarán los logs.
     *
     * @return URL remota.
     */
    public String getRemoteLocation() {
        return remoteLocation;
    }

    /**
     * Establece la URL remota donde se almacenarán los logs.
     *
     * @param remoteLocation URL remota.
     */
    public void setRemoteLocation(String remoteLocation) {
        if (!isValidRemoteLocation(remoteLocation)) {
            throw new PropertyConstraintException(remoteLocation, "remoteLocation no es válida");
        }
        this.remoteLocation = remoteLocation;
    }

    /**
     * Valida la URL remota.
     *
     * @param remoteLocation URL remota a validar.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidRemoteLocation(String remoteLocation) {
        return remoteLocation != null && remoteLocation.length() <= 512;
    }

    /**
     * Obtiene la fecha y hora más antigua de las entradas de logs a incluir.
     *
     * @return Fecha y hora más antigua.
     */
    @Nullable
    public ZonedDateTime getOldestTimestamp() {
        return oldestTimestamp;
    }

    /**
     * Establece la fecha y hora más antigua de las entradas de logs a incluir.
     *
     * @param oldestTimestamp Fecha y hora más antigua.
     */
    public void setOldestTimestamp(@Nullable ZonedDateTime oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    /**
     * Agrega la fecha y hora más antigua de las entradas de logs a incluir.
     *
     * @param oldestTimestamp Fecha y hora más antigua.
     * @return La instancia actual de LogParameters.
     */
    public LogParameters withOldestTimestamp(@Nullable ZonedDateTime oldestTimestamp) {
        setOldestTimestamp(oldestTimestamp);
        return this;
    }

    /**
     * Obtiene la fecha y hora más reciente de las entradas de logs a incluir.
     *
     * @return Fecha y hora más reciente.
     */
    @Nullable
    public ZonedDateTime getLatestTimestamp() {
        return latestTimestamp;
    }

    /**
     * Establece la fecha y hora más reciente de las entradas de logs a incluir.
     *
     * @param latestTimestamp Fecha y hora más reciente.
     */
    public void setLatestTimestamp(@Nullable ZonedDateTime latestTimestamp) {
        this.latestTimestamp = latestTimestamp;
    }

    /**
     * Agrega la fecha y hora más reciente de las entradas de logs a incluir.
     *
     * @param latestTimestamp Fecha y hora más reciente.
     * @return La instancia actual de LogParameters.
     */
    public LogParameters withLatestTimestamp(@Nullable ZonedDateTime latestTimestamp) {
        setLatestTimestamp(latestTimestamp);
        return this;
    }

    /**
     * Valida los parámetros de log.
     *
     * @return {@code true} si todos los parámetros son válidos, de lo contrario {@code false}.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidRemoteLocation(remoteLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogParameters that = (LogParameters) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(remoteLocation, that.remoteLocation)
                && Objects.equals(oldestTimestamp, that.oldestTimestamp)
                && Objects.equals(latestTimestamp, that.latestTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, remoteLocation, oldestTimestamp, latestTimestamp);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("remoteLocation", remoteLocation)
                .add("oldestTimestamp", oldestTimestamp)
                .add("latestTimestamp", latestTimestamp)
                .add("isValid", validate())
                .toString();
    }
}
