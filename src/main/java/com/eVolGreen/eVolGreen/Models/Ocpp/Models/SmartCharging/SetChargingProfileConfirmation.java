package com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central en respuesta a una {@link SetChargingProfileRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code status}: Indica si la solicitud de perfil de carga fue aceptada o rechazada (obligatorio).
 *
 * <p>Los posibles valores para {@code status} incluyen:
 * <ul>
 *   <li>{@code ACCEPTED} - El perfil de carga fue aceptado.</li>
 *   <li>{@code REJECTED} - El perfil de carga fue rechazado.</li>
 *   <li>{@code NOT_SUPPORTED} - El perfil de carga no es soportado.</li>
 * </ul>
 * </p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     SetChargingProfileConfirmation confirmacion = new SetChargingProfileConfirmation(ChargingProfileStatus.ACCEPTED);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "setChargingProfileResponse")
public class SetChargingProfileConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private ChargingProfileStatus status;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #SetChargingProfileConfirmation(ChargingProfileStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public SetChargingProfileConfirmation() {}

    /**
     * Construye una nueva instancia de SetChargingProfileConfirmation con el estado especificado.
     *
     * @param status el estado de la operación.
     */
    public SetChargingProfileConfirmation(ChargingProfileStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la operación de cambio del perfil de carga.
     *
     * @return {@link ChargingProfileStatus}, el estado de la operación.
     */
    public ChargingProfileStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la operación de cambio del perfil de carga.
     *
     * @param status {@link ChargingProfileStatus}, el estado de la operación.
     */
    @XmlElement
    public void setStatus(ChargingProfileStatus status) {
        this.status = status;
    }

    /**
     * Valida si los datos de la confirmación son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return this.status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetChargingProfileConfirmation that = (SetChargingProfileConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "SetChargingProfileConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}