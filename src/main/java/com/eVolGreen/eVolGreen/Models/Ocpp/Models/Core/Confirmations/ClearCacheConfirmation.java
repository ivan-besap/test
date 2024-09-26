package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.ClearCacheStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el punto de carga al sistema central
 * tras procesar la solicitud de limpieza de caché (ClearCacheRequest).
 * Esta confirmación indica si la solicitud de limpieza de caché fue aceptada y ejecutada.
 */
@XmlRootElement(name = "clearCacheResponse")
public class ClearCacheConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private ClearCacheStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #ClearCacheConfirmation(ClearCacheStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ClearCacheConfirmation() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado de la limpieza de caché.
     */
    public ClearCacheConfirmation(ClearCacheStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la limpieza de caché.
     * Aceptado si el punto de carga ha ejecutado la solicitud, de lo contrario rechazado.
     *
     * @return Estado de la limpieza de caché como {@link ClearCacheStatus}.
     */
    public ClearCacheStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la limpieza de caché.
     * Aceptado si el punto de carga ha ejecutado la solicitud, de lo contrario rechazado.
     *
     * @param status Estado de la limpieza de caché. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(ClearCacheStatus status) {
        this.status = status;
    }

    /**
     * Obtiene el estado de la limpieza de caché.
     *
     * @return Estado de la limpieza de caché como {@link ClearCacheStatus}.
     * @deprecated Use {@link #getStatus()} en su lugar.
     */
    @Deprecated
    public ClearCacheStatus objStatus() {
        return status;
    }

    /**
     * Valida que el campo obligatorio status esté presente.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearCacheConfirmation that = (ClearCacheConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "ClearCacheConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}