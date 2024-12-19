package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.Enums.UnpublishFirmwareStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * UnpublishFirmwareResponse
 *
 * <p>Representa la respuesta a una solicitud para despublicar un firmware previamente publicado en
 * el sistema.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class UnpublishFirmwareResponse extends Confirmation {
    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Indica si el controlador local (Local Controller) logró despublicar el firmware.
     */
    private UnpublishFirmwareStatusEnum status;

    /**
     * Constructor para la clase UnpublishFirmwareResponse.
     *
     * @param status Estado que indica si el controlador local despublicó el firmware con éxito.
     */
    public UnpublishFirmwareResponse(UnpublishFirmwareStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados opcionales.
     *
     * @return Datos personalizados opcionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados opcionales.
     *
     * @param customData Datos personalizados opcionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados opcionales.
     *
     * @param customData Datos personalizados opcionales.
     * @return Esta instancia con los datos personalizados actualizados.
     */
    public UnpublishFirmwareResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si el controlador local despublicó el firmware con éxito.
     *
     * @return Estado de la operación de despublicación.
     */
    public UnpublishFirmwareStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si el controlador local despublicó el firmware con éxito.
     *
     * @param status Estado de la operación de despublicación.
     */
    public void setStatus(UnpublishFirmwareStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Valida si el estado proporcionado es válido.
     *
     * @param status El estado a validar.
     * @return {@code true} si el estado es válido, {@code false} de lo contrario.
     */
    private boolean isValidStatus(UnpublishFirmwareStatusEnum status) {
        return status != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidStatus(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnpublishFirmwareResponse that = (UnpublishFirmwareResponse) o;
        return Objects.equals(customData, that.customData) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("isValid", validate())
                .toString();
    }
}
