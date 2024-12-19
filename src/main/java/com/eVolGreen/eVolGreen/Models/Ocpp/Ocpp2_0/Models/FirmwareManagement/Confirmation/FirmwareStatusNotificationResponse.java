package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a una notificación sobre el estado del firmware enviada por la estación de carga.
 * Esta clase representa la confirmación del servidor a dicha notificación.
 */
public class FirmwareStatusNotificationResponse extends Confirmation {

    /** Datos personalizados que pueden incluir información adicional para extensiones específicas. */
    @Nullable
    private CustomData customData;

    /** Constructor por defecto para la clase FirmwareStatusNotificationResponse. */
    public FirmwareStatusNotificationResponse() {}

    /**
     * Obtiene los datos personalizados asociados a esta respuesta.
     *
     * @return Instancia de {@link CustomData} o {@code null} si no hay datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta respuesta.
     *
     * @param customData Instancia de {@link CustomData} que contiene información adicional.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Instancia de {@link CustomData} para validar.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asocia datos personalizados con esta respuesta.
     *
     * @param customData Instancia de {@link CustomData} que contiene información adicional.
     * @return Esta misma instancia de {@link FirmwareStatusNotificationResponse}.
     */
    public FirmwareStatusNotificationResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida el estado de la respuesta para garantizar que todos los datos son válidos.
     *
     * @return {@code true} si la respuesta es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Verifica la igualdad entre esta instancia y otro objeto.
     *
     * @param o Objeto con el que se comparará.
     * @return {@code true} si los objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FirmwareStatusNotificationResponse that = (FirmwareStatusNotificationResponse) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Calcula el código hash de esta instancia.
     *
     * @return Valor hash basado en los atributos de la clase.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Representa esta respuesta como una cadena de texto para depuración y registro.
     *
     * @return Cadena que contiene los valores de los atributos de la respuesta.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
