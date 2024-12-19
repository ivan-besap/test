package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa información adicional relacionada con un identificador principal
 * utilizado para autorización en OCPP.
 *
 * <p>Incluye un identificador adicional y su tipo, que puede ser acordado entre
 * las partes involucradas en la implementación.
 */
public class AdditionalInfo {

    /** Datos personalizados asociados con la información adicional. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador adicional relacionado con la autorización.
     * Puede contener valores como un UUID u otros formatos según el contexto.
     */
    private String additionalIdToken;

    /**
     * Tipo del identificador adicional. Este es un tipo personalizado
     * que debe ser acordado por todas las partes involucradas.
     */
    private String type;

    /**
     * Constructor para la clase AdditionalInfo.
     *
     * @param additionalIdToken Identificador adicional relacionado con la autorización.
     * @param type Tipo del identificador adicional, acordado entre las partes involucradas.
     */
    public AdditionalInfo(String additionalIdToken, String type) {
        setAdditionalIdToken(additionalIdToken);
        setType(type);
    }

    /**
     * Obtiene los datos personalizados asociados.
     *
     * @return Datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados.
     *
     * @param customData Datos personalizados a asociar.
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
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al objeto actual.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de {@link AdditionalInfo}.
     */
    public AdditionalInfo withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador adicional relacionado con la autorización.
     *
     * @return Identificador adicional en formato de cadena.
     */
    public String getAdditionalIdToken() {
        return additionalIdToken;
    }

    /**
     * Establece el identificador adicional relacionado con la autorización.
     *
     * @param additionalIdToken Identificador adicional a establecer.
     */
    public void setAdditionalIdToken(String additionalIdToken) {
        if (!isValidAdditionalIdToken(additionalIdToken)) {
            throw new PropertyConstraintException(
                    additionalIdToken, "El identificador adicional no es válido.");
        }
        this.additionalIdToken = additionalIdToken;
    }

    /**
     * Verifica si el identificador adicional es válido.
     *
     * @param additionalIdToken Identificador a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidAdditionalIdToken(String additionalIdToken) {
        return additionalIdToken != null && additionalIdToken.length() <= 36;
    }

    /**
     * Obtiene el tipo del identificador adicional.
     *
     * @return Tipo del identificador adicional en formato de cadena.
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el tipo del identificador adicional.
     *
     * @param type Tipo del identificador adicional a establecer.
     */
    public void setType(String type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "El tipo de identificador no es válido.");
        }
        this.type = type;
    }

    /**
     * Verifica si el tipo del identificador adicional es válido.
     *
     * @param type Tipo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidType(String type) {
        return type != null && type.length() <= 50;
    }

    /**
     * Valida que todos los campos del objeto cumplen con los requisitos establecidos.
     *
     * @return {@code true} si todos los campos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAdditionalIdToken(additionalIdToken)
                && isValidType(type);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdditionalInfo that = (AdditionalInfo) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(additionalIdToken, that.additionalIdToken)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, additionalIdToken, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("additionalIdToken", additionalIdToken)
                .add("type", type)
                .add("isValid", validate())
                .toString();
    }
}
