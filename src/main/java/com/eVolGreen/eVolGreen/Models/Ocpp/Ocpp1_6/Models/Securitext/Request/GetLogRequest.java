package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.LogEnumType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.LogParametersType;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener un log desde un punto de carga.
 * La solicitud especifica el tipo de log, el número de intentos, el intervalo de reintento y la ubicación de envío.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     GetLogRequest request = new GetLogRequest(LogEnumType.Diagnostic, 123, new LogParametersType());
 *     request.setRetries(3);
 *     request.setRetryInterval(60);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class GetLogRequest extends RequestWithId {

    private LogEnumType logType;
    private Integer requestId;
    private Integer retries;
    private Integer retryInterval;
    private LogParametersType log;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private GetLogRequest() {
    }

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param logType   El tipo de log que el punto de carga debe enviar.
     * @param requestId El identificador de la solicitud.
     * @param log       Parámetros del log que especifican el log solicitado y su ubicación.
     */
    public GetLogRequest(LogEnumType logType, Integer requestId, LogParametersType log) {
        setLogType(logType);
        setRequestId(requestId);
        setLog(log);
    }

    /**
     * Obtiene el tipo de archivo de log que el punto de carga debe enviar.
     *
     * @return {@link LogEnumType} El tipo de log.
     */
    public LogEnumType getLogType() {
        return logType;
    }

    /**
     * Establece el tipo de archivo de log que el punto de carga debe enviar.
     *
     * @param logType El tipo de log a enviar.
     */
    public void setLogType(LogEnumType logType) {
        this.logType = logType;
    }

    /**
     * Obtiene el identificador de esta solicitud.
     *
     * @return El identificador de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador de esta solicitud.
     *
     * @param requestId El identificador de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Obtiene la cantidad de intentos que el punto de carga debe realizar para subir el log antes de abandonar.
     *
     * @return El número de reintentos.
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * Establece la cantidad de intentos que el punto de carga debe realizar para subir el log antes de abandonar.
     * Este campo es opcional.
     *
     * @param retries El número de reintentos.
     */
    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    /**
     * Obtiene el intervalo en segundos después del cual puede intentarse un nuevo reintento.
     *
     * @return El intervalo de reintento en segundos.
     */
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Establece el intervalo en segundos después del cual puede intentarse un nuevo reintento.
     * Este campo es opcional.
     *
     * @param retryInterval El intervalo de reintento en segundos.
     */
    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * Obtiene los parámetros del log que especifican el log solicitado y su ubicación.
     *
     * @return {@link LogParametersType} Parámetros del log.
     */
    public LogParametersType getLog() {
        return log;
    }

    /**
     * Establece los parámetros del log que especifican el log solicitado y su ubicación.
     *
     * @param log {@link LogParametersType} Parámetros del log.
     */
    public void setLog(LogParametersType log) {
        this.log = log;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con una transacción.
     */
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

    /**
     * Valida si la solicitud es válida, asegurando que los campos obligatorios no sean nulos y que los parámetros del log sean válidos.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return logType != null && requestId != null && log != null && log.validate();
    }

    /**
     * Compara si dos solicitudes de log son equivalentes.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetLogRequest that = (GetLogRequest) o;
        return Objects.equals(logType, that.logType) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(retries, that.retries) &&
                Objects.equals(retryInterval, that.retryInterval) &&
                Objects.equals(log, that.log);
    }

    /**
     * Genera un código hash único basado en el tipo de log, el ID de la solicitud y los parámetros del log.
     *
     * @return El valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(logType, requestId, retries, retryInterval, log);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de obtención de log.
     *
     * @return Una cadena que representa la solicitud, incluyendo el tipo de log y los parámetros.
     */
    @Override
    public String toString() {
        return "GetLogRequest{" +
                "logType=" + logType +
                ", requestId=" + requestId +
                ", retries=" + retries +
                ", retryInterval=" + retryInterval +
                ", log=" + log +
                ", isValid=" + validate() +
                '}';
    }
}
