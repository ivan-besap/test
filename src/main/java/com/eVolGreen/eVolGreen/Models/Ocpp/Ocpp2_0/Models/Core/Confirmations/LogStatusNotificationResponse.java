package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la notificación del estado de carga de registros (LogStatusNotification).
 * <p>
 * Este mensaje se utiliza para confirmar la recepción de una notificación de estado
 * relacionada con la carga de registros en el sistema.
 */
public class LogStatusNotificationResponse extends Confirmation {

    /** Datos personalizados asociados a la respuesta. */
    @Nullable
    private CustomData customData;

    /**
     * Constructor de la clase LogStatusNotificationResponse.
     */
    public LogStatusNotificationResponse() {}

    /**
     * Obtiene los datos personalizados asociados a esta respuesta.
     *
     * @return Un objeto {@link CustomData} que contiene los datos personalizados, o {@code null}
     *         si no se han establecido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta respuesta.
     *
     * @param customData Un objeto {@link CustomData} con los datos personalizados.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asocia datos personalizados a esta respuesta y devuelve la instancia actual.
     *
     * @param customData Un objeto {@link CustomData} con los datos personalizados.
     * @return Esta instancia de {@link LogStatusNotificationResponse}.
     */
    public LogStatusNotificationResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida la instancia actual de la respuesta.
     *
     * @return {@code true} si la instancia es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Compara esta instancia con otro objeto para determinar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogStatusNotificationResponse that = (LogStatusNotificationResponse) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Calcula el código hash para esta instancia.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Devuelve una representación en forma de cadena de esta instancia.
     *
     * @return Una cadena que representa esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
