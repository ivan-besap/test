package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.ConfigurationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el punto de carga al sistema central
 * tras intentar cambiar una configuración.
 * Esta confirmación indica si el cambio de configuración ha sido aceptado.
 */
@XmlRootElement(name = "changeConfigurationResponse")
public class ChangeConfigurationConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private ConfigurationStatus status;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #ChangeConfigurationConfirmation(ConfigurationStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ChangeConfigurationConfirmation() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado de la configuración tras el intento de cambio.
     */
    public ChangeConfigurationConfirmation(ConfigurationStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la configuración tras el intento de cambio.
     *
     * @return Estado de la configuración como {@link ConfigurationStatus}.
     */
    public ConfigurationStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la configuración tras el intento de cambio.
     * Este estado indica si el cambio de configuración ha sido aceptado.
     *
     * @param status Estado de la configuración. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(ConfigurationStatus status) {
        this.status = status;
    }

    /**
     * Obtiene el estado de la configuración tras el intento de cambio.
     *
     * @return Estado de la configuración como {@link ConfigurationStatus}.
     * @deprecated Use {@link #getStatus()} en su lugar.
     */
    @Deprecated
    public ConfigurationStatus objStatus() {
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
        ChangeConfigurationConfirmation that = (ChangeConfigurationConfirmation) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "ChangeConfigurationConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}