package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * UnpublishFirmwareRequest
 *
 * <p>Representa una solicitud para despublicar un firmware previamente publicado en el sistema.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class UnpublishFirmwareRequest extends RequestWithId {
    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * El checksum MD5 del archivo completo de firmware como una cadena hexadecimal de 32 caracteres.
     */
    private String checksum;

    /**
     * Constructor para la clase UnpublishFirmwareRequest.
     *
     * @param checksum El checksum MD5 del archivo de firmware como una cadena hexadecimal de 32
     *     caracteres.
     */
    public UnpublishFirmwareRequest(String checksum) {
        setChecksum(checksum);
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
    public UnpublishFirmwareRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el checksum MD5 del archivo completo de firmware.
     *
     * @return Checksum MD5 como una cadena hexadecimal de 32 caracteres.
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Establece el checksum MD5 del archivo completo de firmware.
     *
     * @param checksum Checksum MD5 como una cadena hexadecimal de 32 caracteres.
     */
    public void setChecksum(String checksum) {
        if (!isValidChecksum(checksum)) {
            throw new PropertyConstraintException(checksum, "checksum is invalid");
        }
        this.checksum = checksum;
    }

    /**
     * Valida si el checksum proporcionado es válido.
     *
     * @param checksum El checksum a validar.
     * @return {@code true} si es válido, {@code false} de lo contrario.
     */
    private boolean isValidChecksum(String checksum) {
        return checksum != null && checksum.length() == 32;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidChecksum(checksum);
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnpublishFirmwareRequest that = (UnpublishFirmwareRequest) o;
        return Objects.equals(customData, that.customData) && Objects.equals(checksum, that.checksum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, checksum);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("checksum", checksum)
                .add("isValid", validate())
                .toString();
    }
}
