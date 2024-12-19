package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.IdTokenEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Representa un identificador insensible a mayúsculas y minúsculas utilizado para la autorización,
 * con soporte para diferentes tipos de identificación y datos adicionales.
 *
 * <p>Este identificador puede contener el ID oculto de una etiqueta RFID, un UUID u otros formatos
 * definidos según el contexto.
 */
public class IdToken {

    /** Datos personalizados asociados al identificador. */
    @Nullable
    private CustomData customData;

    /**
     * Información adicional asociada al identificador, utilizada para la autorización en múltiples
     * formatos.
     */
    @Nullable
    private AdditionalInfo[] additionalInfo;

    /**
     * Identificador insensible a mayúsculas y minúsculas. Puede contener valores como el ID oculto de
     * una etiqueta RFID o un UUID.
     */
    private String idToken;

    /** Tipo de identificador, especificado mediante una enumeración. */
    private IdTokenEnum type;

    /**
     * Constructor de la clase IdToken.
     *
     * @param idToken Identificador insensible a mayúsculas y minúsculas. Puede contener valores como
     *     un UUID o un ID de etiqueta RFID.
     * @param type Tipo de identificador definido mediante la enumeración {@link IdTokenEnum}.
     */
    public IdToken(String idToken, IdTokenEnum type) {
        setIdToken(idToken);
        setType(type);
    }

    /**
     * Obtiene los datos personalizados asociados al identificador.
     *
     * @return Datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al identificador.
     *
     * @param customData Datos personalizados que se quieren asociar.
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
     * @param customData Datos personalizados que se quieren validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al identificador.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de {@link IdToken}.
     */
    public IdToken withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene información adicional asociada al identificador.
     *
     * @return Arreglo de información adicional, o {@code null} si no está definido.
     */
    @Nullable
    public AdditionalInfo[] getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Establece información adicional asociada al identificador.
     *
     * @param additionalInfo Arreglo de información adicional que se quiere asociar.
     */
    public void setAdditionalInfo(@Nullable AdditionalInfo[] additionalInfo) {
        if (!isValidAdditionalInfo(additionalInfo)) {
            throw new PropertyConstraintException(
                    additionalInfo, "La información adicional no es válida.");
        }
        this.additionalInfo = additionalInfo;
    }

    /**
     * Verifica si la información adicional es válida.
     *
     * @param additionalInfo Arreglo de información adicional que se quiere validar.
     * @return {@code true} si la información adicional es válida, {@code false} en caso contrario.
     */
    private boolean isValidAdditionalInfo(@Nullable AdditionalInfo[] additionalInfo) {
        return additionalInfo == null
                || (additionalInfo.length >= 1
                && Arrays.stream(additionalInfo).allMatch(item -> item.validate()));
    }

    /**
     * Agrega información adicional al identificador.
     *
     * @param additionalInfo Arreglo de información adicional.
     * @return La instancia actual de {@link IdToken}.
     */
    public IdToken withAdditionalInfo(@Nullable AdditionalInfo[] additionalInfo) {
        setAdditionalInfo(additionalInfo);
        return this;
    }

    /**
     * Obtiene el identificador insensible a mayúsculas y minúsculas.
     *
     * @return El identificador en formato de cadena de texto.
     */
    public String getIdToken() {
        return idToken;
    }

    /**
     * Establece el identificador insensible a mayúsculas y minúsculas.
     *
     * @param idToken Identificador que se quiere establecer.
     */
    public void setIdToken(String idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "El identificador no es válido.");
        }
        this.idToken = idToken;
    }

    /**
     * Verifica si el identificador es válido.
     *
     * @param idToken Identificador que se quiere validar.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidIdToken(String idToken) {
        return idToken != null && idToken.length() <= 36;
    }

    /**
     * Obtiene el tipo de identificador.
     *
     * @return Tipo de identificador definido en {@link IdTokenEnum}.
     */
    public IdTokenEnum getType() {
        return type;
    }

    /**
     * Establece el tipo de identificador.
     *
     * @param type Tipo de identificador a establecer.
     */
    public void setType(IdTokenEnum type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "El tipo de identificador no es válido.");
        }
        this.type = type;
    }

    /**
     * Verifica si el tipo de identificador es válido.
     *
     * @param type Tipo de identificador a validar.
     * @return {@code true} si el tipo de identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidType(IdTokenEnum type) {
        return type != null;
    }

    /**
     * Valida que todos los campos del objeto cumplen con los requisitos establecidos.
     *
     * @return {@code true} si el objeto es válido, de lo contrario {@code false}.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAdditionalInfo(additionalInfo)
                && isValidIdToken(idToken)
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
        IdToken that = (IdToken) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(additionalInfo, that.additionalInfo)
                && Objects.equals(idToken, that.idToken)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(additionalInfo), idToken, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("additionalInfo", additionalInfo)
                .add("idToken", idToken)
                .add("type", type)
                .add("isValid", validate())
                .toString();
    }
}
