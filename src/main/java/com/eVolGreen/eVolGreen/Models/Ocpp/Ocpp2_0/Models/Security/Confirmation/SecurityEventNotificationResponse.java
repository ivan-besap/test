package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la Notificación de Evento de Seguridad
 *
 * <p>Clase que representa la respuesta a una notificación de evento de seguridad enviada por una
 * estación de carga.
 */
public class SecurityEventNotificationResponse extends Confirmation {

    /** Datos personalizados relacionados con la respuesta del evento de seguridad. */
    @Nullable
    private CustomData customData;

    /** Constructor para la clase SecurityEventNotificationResponse. */
    public SecurityEventNotificationResponse() {}

    /**
     * Obtiene los datos personalizados relacionados con la respuesta del evento de seguridad.
     *
     * @return Datos personalizados relacionados con la respuesta.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados relacionados con la respuesta del evento de seguridad.
     *
     * @param customData Datos personalizados relacionados con la respuesta.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados relacionados con la respuesta.
     *
     * @param customData Datos personalizados relacionados con la respuesta.
     * @return Esta instancia actualizada.
     */
    public SecurityEventNotificationResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecurityEventNotificationResponse that = (SecurityEventNotificationResponse) o;
        return Objects.equals(customData, that.customData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
