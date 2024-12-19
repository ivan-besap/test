package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a la solicitud de límite de carga eliminado.
 *
 * <p>Representa la confirmación de que se ha procesado correctamente una solicitud para eliminar
 * un límite de carga. Este mensaje es parte del protocolo OCPP 2.0.1.</p>
 */
public class ClearedChargingLimitResponse extends Confirmation {
    /**
     * Datos personalizados.
     *
     * <p>Permite incluir atributos adicionales específicos de la implementación.</p>
     */
    @Nullable
    private CustomData customData;

    /**
     * Constructor de la clase ClearedChargingLimitResponse.
     */
    public ClearedChargingLimitResponse() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Los datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Los datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Método fluido para establecer datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @return La instancia actual de la respuesta.
     */
    public ClearedChargingLimitResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida la respuesta.
     *
     * @return {@code true} si la respuesta es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Verifica la igualdad entre esta respuesta y otro objeto.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, de lo contrario {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClearedChargingLimitResponse that = (ClearedChargingLimitResponse) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Calcula el código hash de la respuesta.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Genera una representación en cadena de la respuesta.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
