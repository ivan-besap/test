package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para la actualización del estado de una reserva.
 *
 * <p>Representa la respuesta enviada por la estación de carga para confirmar
 * la recepción y el procesamiento de la solicitud de actualización del estado de una reserva.
 */
public class ReservationStatusUpdateResponse extends Confirmation {

    /** Datos personalizados específicos del mensaje. */
    @Nullable
    private CustomData customData;

    /**
     * Constructor de la clase ReservationStatusUpdateResponse.
     */
    public ReservationStatusUpdateResponse() {}

    /**
     * Obtiene los datos personalizados asociados a la respuesta.
     *
     * @return Objeto de tipo CustomData o null si no está presente.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados para la respuesta.
     *
     * @param customData Objeto de tipo CustomData que contiene información adicional personalizada.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "El campo customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Valida si el campo customData es correcto.
     *
     * @param customData Objeto de tipo CustomData a validar.
     * @return {@code true} si el campo customData es válido o null, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la respuesta.
     *
     * @param customData Objeto de tipo CustomData que contiene información adicional personalizada.
     * @return La instancia actual de la clase para encadenar métodos.
     */
    public ReservationStatusUpdateResponse withCustomData(@Nullable CustomData customData) {
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
        ReservationStatusUpdateResponse that = (ReservationStatusUpdateResponse) o;
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
