package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Información adicional sobre el estado.
 *
 * <p>Esta clase contiene detalles adicionales sobre el estado, incluyendo un código predefinido y
 * texto adicional para proporcionar más información.
 */
public class StatusInfo {

    /** Datos personalizados asociados con la información de estado. */
    @Nullable
    private CustomData customData;

    /**
     * Código predefinido para el motivo del estado proporcionado.
     *
     * <p>El texto no distingue entre mayúsculas y minúsculas.
     */
    private String reasonCode;

    /** Información adicional en forma de texto para detallar el motivo del estado. */
    @Nullable private String additionalInfo;

    /**
     * Constructor de la clase StatusInfo.
     *
     * @param reasonCode Código predefinido para el motivo del estado.
     */
    public StatusInfo(String reasonCode) {
        setReasonCode(reasonCode);
    }

    /** @return Datos personalizados asociados con la información de estado. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados con la información de estado.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /** Valida si los datos personalizados son válidos. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /** @return Código predefinido para el motivo del estado. */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Establece un código predefinido para el motivo del estado.
     *
     * @param reasonCode Código del motivo del estado.
     */
    public void setReasonCode(String reasonCode) {
        if (!isValidReasonCode(reasonCode)) {
            throw new PropertyConstraintException(reasonCode, "El código del motivo no es válido");
        }
        this.reasonCode = reasonCode;
    }

    /** Valida si el código del motivo es válido. */
    private boolean isValidReasonCode(String reasonCode) {
        return reasonCode != null && reasonCode.length() <= 20;
    }

    /** @return Información adicional en forma de texto para detallar el motivo del estado. */
    @Nullable
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Establece información adicional en forma de texto para detallar el motivo del estado.
     *
     * @param additionalInfo Información adicional.
     */
    public void setAdditionalInfo(@Nullable String additionalInfo) {
        if (!isValidAdditionalInfo(additionalInfo)) {
            throw new PropertyConstraintException(
                    additionalInfo, "La información adicional no es válida");
        }
        this.additionalInfo = additionalInfo;
    }

    /** Valida si la información adicional es válida. */
    private boolean isValidAdditionalInfo(@Nullable String additionalInfo) {
        return additionalInfo == null || additionalInfo.length() <= 512;
    }

    /**
     * Valida si todos los datos de la información de estado son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidReasonCode(reasonCode)
                && isValidAdditionalInfo(additionalInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StatusInfo that = (StatusInfo) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(reasonCode, that.reasonCode)
                && Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, reasonCode, additionalInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("reasonCode", reasonCode)
                .add("additionalInfo", additionalInfo)
                .add("isValid", validate())
                .toString();
    }
}
