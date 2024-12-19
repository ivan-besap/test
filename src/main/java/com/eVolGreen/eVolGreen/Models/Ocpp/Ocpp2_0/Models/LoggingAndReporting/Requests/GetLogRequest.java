package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums.LogEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Utils.LogParameters;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener registros de log desde una estación de carga.
 *
 * <p>Incluye parámetros como el tipo de log, configuración de reintentos y el intervalo entre
 * intentos.
 */
public class GetLogRequest extends RequestWithId {

    /** Datos personalizados opcionales asociados a la solicitud. */
    @Nullable
    private CustomData customData;

    /** Parámetros de configuración del log solicitado. */
    private LogParameters log;

    /** Tipo de log que debe enviarse desde la estación de carga. */
    private LogEnum logType;

    /** Identificador único de la solicitud. */
    private Integer requestId;

    /**
     * Número de intentos que la estación de carga debe realizar para subir el log antes de abandonar.
     */
    @Nullable
    private Integer retries;

    /**
     * Intervalo en segundos después del cual se puede realizar un nuevo intento en caso de fallo.
     */
    @Nullable
    private Integer retryInterval;

    /**
     * Constructor de la clase GetLogRequest.
     *
     * @param log Parámetros de configuración del log.
     * @param logType Tipo de log que debe enviarse.
     * @param requestId Identificador único de la solicitud.
     */
    public GetLogRequest(LogParameters log, LogEnum logType, Integer requestId) {
        setLog(log);
        setLogType(logType);
        setRequestId(requestId);
    }

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a la solicitud.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Obtiene los parámetros de configuración del log solicitado.
     *
     * @return Parámetros del log.
     */
    public LogParameters getLog() {
        return log;
    }

    /**
     * Establece los parámetros de configuración del log solicitado.
     *
     * @param log Parámetros del log.
     */
    public void setLog(LogParameters log) {
        if (!isValidLog(log)) {
            throw new PropertyConstraintException(log, "Los parámetros del log no son válidos");
        }
        this.log = log;
    }

    /**
     * Verifica si los parámetros del log son válidos.
     *
     * @param log Parámetros del log a validar.
     * @return {@code true} si los parámetros son válidos, {@code false} en caso contrario.
     */
    private boolean isValidLog(LogParameters log) {
        return log != null && log.validate();
    }

    /**
     * Obtiene el tipo de log que debe enviarse desde la estación de carga.
     *
     * @return Tipo de log.
     */
    public LogEnum getLogType() {
        return logType;
    }

    /**
     * Establece el tipo de log que debe enviarse desde la estación de carga.
     *
     * @param logType Tipo de log.
     */
    public void setLogType(LogEnum logType) {
        if (!isValidLogType(logType)) {
            throw new PropertyConstraintException(logType, "El tipo de log no es válido");
        }
        this.logType = logType;
    }

    /**
     * Verifica si el tipo de log es válido.
     *
     * @param logType Tipo de log a validar.
     * @return {@code true} si el tipo es válido, {@code false} en caso contrario.
     */
    private boolean isValidLogType(LogEnum logType) {
        return logType != null;
    }

    /**
     * Obtiene el identificador único de la solicitud.
     *
     * @return Identificador de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador único de la solicitud.
     *
     * @param requestId Identificador de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "El identificador de la solicitud no es válido");
        }
        this.requestId = requestId;
    }

    /**
     * Verifica si el identificador de la solicitud es válido.
     *
     * @param requestId Identificador a validar.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene el número de intentos permitidos para subir el log.
     *
     * @return Número de intentos.
     */
    @Nullable
    public Integer getRetries() {
        return retries;
    }

    /**
     * Establece el número de intentos permitidos para subir el log.
     *
     * @param retries Número de intentos.
     */
    public void setRetries(@Nullable Integer retries) {
        this.retries = retries;
    }

    /**
     * Método fluido para establecer el número de intentos permitidos para subir el log.
     *
     * @param retries Número de intentos.
     * @return La instancia actual de la solicitud.
     */
    public GetLogRequest withRetries(@Nullable Integer retries) {
        setRetries(retries);
        return this;
    }

    /**
     * Obtiene el intervalo en segundos entre intentos.
     *
     * @return Intervalo entre intentos.
     */
    @Nullable
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Establece el intervalo en segundos entre intentos.
     *
     * @param retryInterval Intervalo entre intentos.
     */
    public void setRetryInterval(@Nullable Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * Método fluido para establecer el intervalo en segundos entre intentos.
     *
     * @param retryInterval Intervalo entre intentos.
     * @return La instancia actual de la solicitud.
     */
    public GetLogRequest withRetryInterval(@Nullable Integer retryInterval) {
        setRetryInterval(retryInterval);
        return this;
    }

    /**
     * Valida la solicitud para garantizar que cumple con los requisitos necesarios.
     *
     * @return {@code true} si la solicitud es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidLog(log)
                && isValidLogType(logType)
                && isValidRequestId(requestId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("log", log)
                .add("logType", logType)
                .add("requestId", requestId)
                .add("retries", retries)
                .add("retryInterval", retryInterval)
                .add("isValid", validate())
                .toString();
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }
}
