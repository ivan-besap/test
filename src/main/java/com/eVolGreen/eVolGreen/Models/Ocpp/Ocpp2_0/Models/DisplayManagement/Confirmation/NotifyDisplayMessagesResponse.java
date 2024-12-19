package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta de notificación de mensajes para mostrar.
 *
 * <p>Definido en OCPP 2.0.1 FINAL.
 */
public class NotifyDisplayMessagesResponse extends Confirmation {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Constructor para la clase NotifyDisplayMessagesResponse. */
    public NotifyDisplayMessagesResponse() {}

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales, si están presentes.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia de {@code NotifyDisplayMessagesResponse}.
     */
    public NotifyDisplayMessagesResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida si esta respuesta es válida.
     *
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Comprueba si dos instancias de NotifyDisplayMessagesResponse son iguales.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyDisplayMessagesResponse that = (NotifyDisplayMessagesResponse) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Calcula el código hash para esta instancia.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Devuelve una representación en cadena de esta instancia.
     *
     * @return Cadena que representa esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
