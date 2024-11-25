package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al límite de carga notificado
 *
 * <p>Clase que representa la respuesta al mensaje NotifyChargingLimit.
 */
public class NotifyChargingLimitResponse extends Confirmation {
    /** Datos personalizados opcionales */
    @Nullable
    private CustomData customData;

    /** Constructor de la clase NotifyChargingLimitResponse */
    public NotifyChargingLimitResponse() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!esCustomDataValido(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados
     * @return {@code true} si son válidos, {@code false} si no
     */
    private boolean esCustomDataValido(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados
     * @return esta instancia
     */
    public NotifyChargingLimitResponse conCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida la instancia actual.
     *
     * @return {@code true} si todos los campos son válidos, {@code false} si no
     */
    @Override
    public boolean validate() {
        return esCustomDataValido(customData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyChargingLimitResponse that = (NotifyChargingLimitResponse) o;
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
                .add("esValido", validate())
                .toString();
    }
}
