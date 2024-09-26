package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa la solicitud enviada desde el Sistema Central al Punto de Carga para obtener los diagnósticos.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code location}: Directorio donde se subirán los archivos de diagnóstico (obligatorio).
 * - {@code retries}: Número de reintentos (opcional).
 * - {@code retryInterval}: Intervalo entre reintentos en segundos (opcional).
 * - {@code startTime}: Fecha y hora de inicio para incluir en el diagnóstico (opcional).
 * - {@code stopTime}: Fecha y hora de fin para incluir en el diagnóstico (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     GetDiagnosticsRequest solicitud = new GetDiagnosticsRequest("https://ejemplo.com/diagnostics");
 *     solicitud.setRetries(3);
 *     solicitud.setRetryInterval(60);
 *     solicitud.setStartTime(ZonedDateTime.now().minusDays(1));
 *     solicitud.setStopTime(ZonedDateTime.now());
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Punto de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
@XmlType(propOrder = {"location", "startTime", "stopTime", "retries", "retryInterval"})
public class GetDiagnosticsRequest extends RequestWithId {

    @NotNull(message = "El campo location es obligatorio")
    @JsonProperty("location")
    private String location;

    @JsonProperty("retries")
    private Integer retries;

    @JsonProperty("retryInterval")
    private Integer retryInterval;

    @JsonProperty("startTime")
    private ZonedDateTime startTime;

    @JsonProperty("stopTime")
    private ZonedDateTime stopTime;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #GetDiagnosticsRequest(String)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public GetDiagnosticsRequest() {}

    /**
     * Construye una nueva solicitud GetDiagnosticsRequest con el campo {@code location} obligatorio.
     *
     * @param location la ubicación donde se subirán los archivos de diagnóstico; no debe ser nulo.
     */
    public GetDiagnosticsRequest(String location) {
        setLocation(location);
    }

    /**
     * Obtiene la ubicación donde se subirán los archivos de diagnóstico.
     *
     * @return String, el directorio de destino.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Establece la ubicación donde se subirán los archivos de diagnóstico.
     *
     * @param location String, el directorio de destino. No debe ser nulo.
     */
    @XmlElement
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Obtiene el número de reintentos.
     *
     * @return Integer, el número de reintentos.
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * Establece el número de reintentos.
     *
     * @param retries Integer, el número de reintentos.
     */
    @XmlElement
    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    /**
     * Obtiene el intervalo entre reintentos en segundos.
     *
     * @return Integer, el intervalo en segundos.
     */
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Establece el intervalo entre reintentos en segundos.
     *
     * @param retryInterval Integer, el intervalo en segundos.
     */
    @XmlElement
    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * Obtiene la fecha y hora de inicio para incluir en el diagnóstico.
     *
     * @return ZonedDateTime, la fecha y hora de inicio.
     */
    public ZonedDateTime getStartTime() {
        return startTime;
    }

    /**
     * Establece la fecha y hora de inicio para incluir en el diagnóstico.
     *
     * @param startTime ZonedDateTime, la fecha y hora de inicio.
     */
    @XmlElement
    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Obtiene la fecha y hora de fin para incluir en el diagnóstico.
     *
     * @return ZonedDateTime, la fecha y hora de fin.
     */
    public ZonedDateTime getStopTime() {
        return stopTime;
    }

    /**
     * Establece la fecha y hora de fin para incluir en el diagnóstico.
     *
     * @param stopTime ZonedDateTime, la fecha y hora de fin.
     */
    @XmlElement
    public void setStopTime(ZonedDateTime stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    /**
     * Valida la solicitud para asegurar que el campo {@code location} no sea nulo.
     *
     * @return {@code true} si la solicitud es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return location != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDiagnosticsRequest that = (GetDiagnosticsRequest) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(retries, that.retries) &&
                Objects.equals(retryInterval, that.retryInterval) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(stopTime, that.stopTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, retries, retryInterval, startTime, stopTime);
    }

    @Override
    public String toString() {
        return "GetDiagnosticsRequest{" +
                "location='" + location + '\'' +
                ", retries=" + retries +
                ", retryInterval=" + retryInterval +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", isValid=" + validate() +
                '}';
    }
}