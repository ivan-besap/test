package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.HeartbeatRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Sistema Central al Punto de Carga en respuesta a una {@link HeartbeatRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code currentTime}: La hora actual del Sistema Central (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ZonedDateTime now = ZonedDateTime.now();
 *     HeartbeatConfirmation confirmacion = new HeartbeatConfirmation(now);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "heartbeatResponse")
public class HeartbeatConfirmation extends Confirmation {

    @NotNull(message = "currentTime es obligatorio")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", shape = JsonFormat.Shape.STRING)
    private ZonedDateTime currentTime;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #HeartbeatConfirmation(ZonedDateTime)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public HeartbeatConfirmation() {}

    /**
     * Construye una nueva instancia de HeartbeatConfirmation con la hora actual especificada.
     *
     * @param currentTime la hora actual del Sistema Central.
     */
    public HeartbeatConfirmation(ZonedDateTime currentTime) {
        setCurrentTime(currentTime);
    }

    /**
     * Obtiene la hora actual del Sistema Central.
     *
     * @return ZonedDateTime, la hora actual.
     */
    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    /**
     * Establece la hora actual del Sistema Central.
     *
     * @param currentTime ZonedDateTime, la hora actual.
     */
    @XmlElement
    public void setCurrentTime(ZonedDateTime currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Valida que el campo currentTime no sea nulo.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return currentTime != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeartbeatConfirmation that = (HeartbeatConfirmation) o;
        return Objects.equals(currentTime, that.currentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentTime);
    }

    @Override
    public String toString() {
        return "HeartbeatConfirmation{" +
                "currentTime=" + currentTime +
                ", isValid=" + validate() +
                '}';
    }
}