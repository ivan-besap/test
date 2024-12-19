package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Validation.OCPPSecurityExtDatatypes;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Clase que define los parámetros de los logs que serán enviados o solicitados
 * en el sistema OCPP de eVolGreen.
 */
public class LogParametersType {

    private static final transient Validator remoteLocationValidator =
            new ValidatorBuilder()
                    .addRule(OCPPSecurityExtDatatypes.string512())
                    .setRequired(true)
                    .build();

    private String remoteLocation;
    private ZonedDateTime oldestTimestamp;
    private ZonedDateTime latestTimestamp;

    /**
     * Obtiene la URL del sistema remoto donde se almacenará el log.
     *
     * @return String
     */
    public String getRemoteLocation() {
        return remoteLocation;
    }

    /**
     * Establece la URL del sistema remoto donde se almacenará el log.
     *
     * @param remoteLocation String
     */
    public void setRemoteLocation(String remoteLocation) {
        remoteLocationValidator.validate(remoteLocation);
        this.remoteLocation = remoteLocation;
    }

    /**
     * Obtiene la fecha y hora de la información de logs más antigua a incluir en los diagnósticos.
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getOldestTimestamp() {
        return oldestTimestamp;
    }

    /**
     * Establece la fecha y hora de la información de logs más antigua a incluir en los diagnósticos.
     *
     * @param oldestTimestamp ZonedDateTime
     */
    public void setOldestTimestamp(ZonedDateTime oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    /**
     * Obtiene la fecha y hora de la información de logs más reciente a incluir en los diagnósticos.
     *
     * @return ZonedDateTime
     */
    public ZonedDateTime getLatestTimestamp() {
        return latestTimestamp;
    }

    /**
     * Establece la fecha y hora de la información de logs más reciente a incluir en los diagnósticos.
     *
     * @param latestTimestamp ZonedDateTime
     */
    public void setLatestTimestamp(ZonedDateTime latestTimestamp) {
        this.latestTimestamp = latestTimestamp;
    }

    /**
     * Valida los parámetros del log.
     *
     * @return boolean
     */
    public boolean validate() {
        return remoteLocationValidator.safeValidate(remoteLocation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogParametersType that = (LogParametersType) o;
        return Objects.equals(remoteLocation, that.remoteLocation) &&
                Objects.equals(oldestTimestamp, that.oldestTimestamp) &&
                Objects.equals(latestTimestamp, that.latestTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remoteLocation, oldestTimestamp, latestTimestamp);
    }

    @Override
    public String toString() {
        return "LogParametersType{" +
                "remoteLocation='" + remoteLocation + '\'' +
                ", oldestTimestamp=" + oldestTimestamp +
                ", latestTimestamp=" + latestTimestamp +
                '}';
    }
}
