package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa la solicitud enviada desde el Sistema Central al Punto de Carga para actualizar el firmware.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <p>Los campos obligatorios son {@code location} (la URI donde se debe descargar el firmware) y {@code retrieveDate} (fecha y hora para comenzar la descarga).
 * Otros campos opcionales incluyen los intentos de reintento y el intervalo entre esos intentos.</p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     UpdateFirmwareRequest solicitud = new UpdateFirmwareRequest("https://ejemplo.com/firmware", ZonedDateTime.now());
 *     solicitud.setRetries(3);
 *     solicitud.setRetryInterval(60);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al punto de carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
@XmlType(propOrder = {"location", "retries", "retrieveDate", "retryInterval"})
public class UpdateFirmwareRequest extends RequestWithId {

    @NotNull(message = "El campo location es obligatorio")
    @JsonProperty("location")
    private String location;

    @NotNull(message = "El campo retrieveDate es obligatorio")
    @JsonProperty("retrieveDate")
    private ZonedDateTime retrieveDate;

    @Positive(message = "retries debe ser mayor que 0")
    @JsonProperty("retries")
    private Integer retries;

    @Positive(message = "retryInterval debe ser mayor que 0")
    @JsonProperty("retryInterval")
    private Integer retryInterval;

    /**
     * Constructor por defecto.
     * @deprecated use {@link #UpdateFirmwareRequest(String, ZonedDateTime)} para asegurar que se establezcan los campos requeridos
     */
    @Deprecated
    public UpdateFirmwareRequest() {}

    /**
     * Construye una nueva solicitud UpdateFirmwareRequest con los campos obligatorios.
     *
     * @param location la URI de la ubicación del firmware; no debe ser nulo.
     * @param retrieveDate la fecha y hora para comenzar la descarga; no debe ser nulo.
     */
    public UpdateFirmwareRequest(String location, ZonedDateTime retrieveDate) {
        setLocation(location);
        setRetrieveDate(retrieveDate);
    }

    /**
     * Obtiene la URI de la ubicación del firmware.
     *
     * @return la URI de la ubicación del firmware.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Establece la URI de la ubicación del firmware.
     *
     * @param location la URI de la ubicación del firmware; no debe ser nulo.
     */
    @XmlElement
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Obtiene la fecha y hora para comenzar la descarga del firmware.
     *
     * @return la fecha y hora para comenzar la descarga.
     */
    public ZonedDateTime getRetrieveDate() {
        return retrieveDate;
    }

    /**
     * Establece la fecha y hora para comenzar la descarga del firmware.
     *
     * @param retrieveDate la fecha y hora para comenzar la descarga; no debe ser nulo.
     */
    @XmlElement
    public void setRetrieveDate(ZonedDateTime retrieveDate) {
        this.retrieveDate = retrieveDate;
    }

    /**
     * Obtiene el número de intentos de descarga del firmware.
     *
     * @return el número de intentos de descarga.
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * Establece el número de intentos de descarga del firmware.
     *
     * @param retries el número de intentos de descarga; debe ser mayor que 0.
     * @throws IllegalArgumentException si retries es menor o igual a 0.
     */
    @XmlElement
    public void setRetries(Integer retries) {
        if (retries != null && retries <= 0) {
            throw new IllegalArgumentException("retries debe ser mayor que 0");
        }
        this.retries = retries;
    }

    /**
     * Obtiene el intervalo de tiempo entre intentos de descarga.
     *
     * @return el intervalo de tiempo entre intentos en segundos.
     */
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Establece el intervalo de tiempo entre intentos de descarga.
     *
     * @param retryInterval el intervalo de tiempo entre intentos en segundos; debe ser mayor que 0.
     * @throws IllegalArgumentException si retryInterval es menor o igual a 0.
     */
    @XmlElement
    public void setRetryInterval(Integer retryInterval) {
        if (retryInterval != null && retryInterval <= 0) {
            throw new IllegalArgumentException("retryInterval debe ser mayor que 0");
        }
        this.retryInterval = retryInterval;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    /**
     * Valida la solicitud para asegurar que los campos obligatorios no sean nulos.
     *
     * @return {@code true} si la solicitud es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return location != null && retrieveDate != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateFirmwareRequest that = (UpdateFirmwareRequest) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(retrieveDate, that.retrieveDate) &&
                Objects.equals(retries, that.retries) &&
                Objects.equals(retryInterval, that.retryInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, retrieveDate, retries, retryInterval);
    }

    @Override
    public String toString() {
        return "UpdateFirmwareRequest{" +
                "location='" + location + '\'' +
                ", retrieveDate=" + retrieveDate +
                ", retries=" + retries +
                ", retryInterval=" + retryInterval +
                ", isValid=" + validate() +
                '}';
    }
}