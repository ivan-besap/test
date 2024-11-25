package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta para el mensaje PublishFirmwareStatusNotification.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class PublishFirmwareStatusNotificationResponse extends Confirmation {

    /** Información adicional personalizada. */
    @Nullable
    private CustomData customData;

    /**
     * Constructor vacío para la clase PublishFirmwareStatusNotificationResponse.
     */
    public PublishFirmwareStatusNotificationResponse() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Instancia de {@link CustomData} o {@code null}.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Asigna datos personalizados.
     *
     * @param customData Instancia de {@link CustomData}.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Instancia de {@link CustomData} a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados utilizando un patrón de diseño fluido.
     *
     * @param customData Instancia de {@link CustomData}.
     * @return La instancia actual de {@link PublishFirmwareStatusNotificationResponse}.
     */
    public PublishFirmwareStatusNotificationResponse withCustomData(@Nullable CustomData customData) {
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
        PublishFirmwareStatusNotificationResponse that = (PublishFirmwareStatusNotificationResponse) o;
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
