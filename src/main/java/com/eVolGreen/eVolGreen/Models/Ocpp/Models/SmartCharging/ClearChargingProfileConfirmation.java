package com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada como respuesta a una solicitud {@link ClearChargingProfileRequest}.
 * Esta confirmación indica el estado del proceso de eliminación de perfiles de carga.
 *
 * <b>Campos:</b>
 * - {@code status}: Estado de la operación de eliminación del perfil de carga.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ClearChargingProfileConfirmation confirmation = new ClearChargingProfileConfirmation(ClearChargingProfileStatus.Accepted);
 *
 *     if (confirmation.validate()) {
 *         // Procesar la confirmación
 *         System.out.println("Estado de la operación: " + confirmation.getStatus());
 *     } else {
 *         // Manejar error de validación
 *         System.out.println("La confirmación no es válida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "clearChargingProfileResponse")
public class ClearChargingProfileConfirmation extends Confirmation {

    @NotNull(message = "El campo status es obligatorio")
    @JsonProperty("status")
    private ClearChargingProfileStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #ClearChargingProfileConfirmation(ClearChargingProfileStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ClearChargingProfileConfirmation() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado de la operación de eliminación del perfil de carga.
     */
    public ClearChargingProfileConfirmation(ClearChargingProfileStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la operación de eliminación del perfil de carga.
     *
     * @return Estado de la operación como {@link ClearChargingProfileStatus}.
     */
    public ClearChargingProfileStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la operación de eliminación del perfil de carga.
     * Este estado indica el éxito o fracaso de la operación.
     *
     * @param status Estado de la operación. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(ClearChargingProfileStatus status) {
        this.status = status;
    }

    /**
     * Valida que el campo obligatorio status esté presente.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return this.status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearChargingProfileConfirmation that = (ClearChargingProfileConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "ClearChargingProfileConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}