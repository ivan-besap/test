package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa una respuesta a una solicitud de notificación de estado en OCPP 2.0.1.
 *
 * <p>La clase StatusNotificationResponse indica al CSMS (Central System Management System)
 * que la solicitud de notificación de estado fue procesada exitosamente.
 */
public class StatusNotificationResponse extends Confirmation {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Constructor por defecto para la clase StatusNotificationResponse. */
    public StatusNotificationResponse() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Configura los datos personalizados.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de StatusNotificationResponse.
     */
    public StatusNotificationResponse withCustomData(@Nullable CustomData customData) {
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
        StatusNotificationResponse that = (StatusNotificationResponse) o;
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
