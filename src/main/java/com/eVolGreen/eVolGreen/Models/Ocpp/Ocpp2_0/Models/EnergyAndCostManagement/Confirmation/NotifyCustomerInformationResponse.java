package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * NotifyCustomerInformationResponse
 *
 * <p>Respuesta a una solicitud de notificación de información del cliente.
 *
 * <p>Este mensaje es parte de la especificación OCPP 2.0.1.
 */
public class NotifyCustomerInformationResponse extends Confirmation {
    /** Datos personalizados específicos para el mensaje. */
    @Nullable
    private CustomData customData;

    /** Constructor para la clase NotifyCustomerInformationResponse. */
    public NotifyCustomerInformationResponse() {}

    /**
     * Obtiene los datos personalizados específicos para el mensaje.
     *
     * @return Datos personalizados
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados específicos para el mensaje.
     *
     * @param customData Datos personalizados
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
     * @param customData Datos personalizados a validar
     * @return {@code true} si son válidos, {@code false} en caso contrario
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados específicos para el mensaje.
     *
     * @param customData Datos personalizados
     * @return La instancia actual
     */
    public NotifyCustomerInformationResponse withCustomData(@Nullable CustomData customData) {
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
        NotifyCustomerInformationResponse that = (NotifyCustomerInformationResponse) o;
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
