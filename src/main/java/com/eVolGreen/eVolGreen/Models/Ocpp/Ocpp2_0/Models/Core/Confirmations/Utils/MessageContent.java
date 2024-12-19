package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.MessageFormatEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa los detalles de un mensaje que será mostrado en una estación de carga.
 *
 * <p>Incluye información sobre el formato, el idioma y el contenido del mensaje.
 */
public class MessageContent {

    /** Datos personalizados asociados al mensaje. */
    @Nullable
    private CustomData customData;

    /**
     * Formato del mensaje.
     *
     * <p>Especifica el formato en el que el mensaje será mostrado.
     */
    private MessageFormatEnum format;

    /**
     * Código de idioma del mensaje.
     *
     * <p>Contiene un código de idioma definido en la norma [RFC5646].
     */
    @Nullable
    private String language;

    /**
     * Contenido del mensaje.
     *
     * <p>El texto del mensaje que será mostrado en la estación de carga.
     */
    private String content;

    /**
     * Constructor para la clase MessageContent.
     *
     * @param format Formato del mensaje.
     * @param content Contenido del mensaje.
     */
    public MessageContent(MessageFormatEnum format, String content) {
        setFormat(format);
        setContent(content);
    }

    /** @return Datos personalizados asociados al mensaje. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados al mensaje.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al mensaje.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de MessageContent.
     */
    public MessageContent withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return Formato del mensaje. */
    public MessageFormatEnum getFormat() {
        return format;
    }

    /**
     * Establece el formato del mensaje.
     *
     * @param format Formato del mensaje.
     */
    public void setFormat(MessageFormatEnum format) {
        if (!isValidFormat(format)) {
            throw new PropertyConstraintException(format, "El formato no es válido");
        }
        this.format = format;
    }

    /**
     * Verifica si el formato del mensaje es válido.
     *
     * @param format Formato a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidFormat(MessageFormatEnum format) {
        return format != null;
    }

    /** @return Código de idioma del mensaje. */
    @Nullable
    public String getLanguage() {
        return language;
    }

    /**
     * Establece el código de idioma del mensaje.
     *
     * @param language Código de idioma.
     */
    public void setLanguage(@Nullable String language) {
        if (!isValidLanguage(language)) {
            throw new PropertyConstraintException(language, "El código de idioma no es válido");
        }
        this.language = language;
    }

    /**
     * Verifica si el código de idioma del mensaje es válido.
     *
     * @param language Código de idioma a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidLanguage(@Nullable String language) {
        return language == null || language.length() <= 8;
    }

    /**
     * Agrega un código de idioma al mensaje.
     *
     * @param language Código de idioma.
     * @return La instancia actualizada de MessageContent.
     */
    public MessageContent withLanguage(@Nullable String language) {
        setLanguage(language);
        return this;
    }

    /** @return Contenido del mensaje. */
    public String getContent() {
        return content;
    }

    /**
     * Establece el contenido del mensaje.
     *
     * @param content Contenido del mensaje.
     */
    public void setContent(String content) {
        if (!isValidContent(content)) {
            throw new PropertyConstraintException(content, "El contenido no es válido");
        }
        this.content = content;
    }

    /**
     * Verifica si el contenido del mensaje es válido.
     *
     * @param content Contenido a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidContent(String content) {
        return content != null && content.length() <= 512;
    }

    /**
     * Valida si todos los datos del mensaje son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidFormat(format)
                && isValidLanguage(language)
                && isValidContent(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageContent that = (MessageContent) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(format, that.format)
                && Objects.equals(language, that.language)
                && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, format, language, content);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("format", format)
                .add("language", language)
                .add("content", content)
                .add("isValid", validate())
                .toString();
    }
}
