package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.IdToken;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums.AuthorizationStatusEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representa información sobre el estado de un identificador en el protocolo OCPP 2.0.1.
 *
 * <p>Proporciona detalles sobre el token de identificación, como estado, fecha de expiración,
 * prioridades de carga y mensajes personalizados para estaciones de carga.
 */
public class IdTokenInfo {

    /** Datos personalizados asociados al identificador. */
    @Nullable private CustomData customData;

    /**
     * Estado actual del identificador.
     *
     * <p>Indica si el token está autorizado o si hay restricciones asociadas.
     */
    private AuthorizationStatusEnum status;

    /**
     * Fecha y hora de expiración del token.
     *
     * <p>Después de esta fecha, el token debe considerarse inválido. Se utiliza principalmente para
     * almacenamiento en caché.
     */
    @Nullable
    private ZonedDateTime cacheExpiryDateTime;

    /**
     * Prioridad de carga desde el punto de vista empresarial.
     *
     * <p>El rango es de -9 a 9, donde valores más altos indican una mayor prioridad. El valor por
     * defecto es 0.
     */
    @Nullable
    private Integer chargingPriority;

    /**
     * Código de idioma preferido para la interfaz de usuario.
     *
     * <p>Contiene un código de idioma definido en la norma [RFC5646].
     */
    @Nullable
    private String language1;

    /**
     * Lista de identificadores de EVSE específicos para los cuales el token es válido.
     *
     * <p>Este campo se utiliza si el token no es válido para toda la estación de carga.
     */
    @Nullable
    private Integer[] evseId;

    /**
     * Identificador del grupo al que pertenece el token.
     *
     * <p>Permite soportar múltiples formas de autorización.
     */
    @Nullable
    private IdToken groupIdToken;

    /**
     * Segundo idioma preferido para la interfaz de usuario.
     *
     * <p>No debe usarse si `language1` no está definido, y debe ser diferente de `language1`.
     */
    @Nullable
    private String language2;

    /**
     * Mensaje personalizado para mostrar en una estación de carga.
     */
    @Nullable
    private MessageContent personalMessage;

    /**
     * Constructor para la clase IdTokenInfo.
     *
     * @param status Estado actual del token de identificación.
     */
    public IdTokenInfo(AuthorizationStatusEnum status) {
        setStatus(status);
    }

    /** @return Datos personalizados asociados al identificador. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados al identificador.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /** Verifica si los datos personalizados son válidos. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al identificador.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado actual del token de identificación.
     *
     * @return Estado actual del token de identificación.
     */
    public AuthorizationStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado actual del token de identificación.
     *
     * @param status Estado actual del token de identificación.
     */
    public void setStatus(AuthorizationStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(AuthorizationStatusEnum status) {
        return status != null;
    }


    /** Retorna la fecha y hora de expiración del token. */
    @Nullable
    public ZonedDateTime getCacheExpiryDateTime() {
        return cacheExpiryDateTime;
    }

    /**
     * Establece la fecha y hora de expiración del token.
     *
     * @param cacheExpiryDateTime Fecha y hora de expiración del token.
     */
    public void setCacheExpiryDateTime(@Nullable ZonedDateTime cacheExpiryDateTime) {
        this.cacheExpiryDateTime = cacheExpiryDateTime;
    }

    /**
     * Agrega la fecha y hora de expiración del token.
     *
     * @param cacheExpiryDateTime Fecha y hora de expiración del token.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withCacheExpiryDateTime(@Nullable ZonedDateTime cacheExpiryDateTime) {
        setCacheExpiryDateTime(cacheExpiryDateTime);
        return this;
    }

    /**
     * Obtiene la prioridad de carga desde el punto de vista empresarial.
     *
     * @return Prioridad de carga desde el punto de vista empresarial.
     */
    @Nullable
    public Integer getChargingPriority() {
        return chargingPriority;
    }

    /**
     * Establece la prioridad de carga desde el punto de vista empresarial.
     *
     * @param chargingPriority Prioridad de carga desde el punto de vista empresarial.
     */
    public void setChargingPriority(@Nullable Integer chargingPriority) {
        this.chargingPriority = chargingPriority;
    }

    /**
     * Agrega la prioridad de carga desde el punto de vista empresarial.
     *
     * @param chargingPriority Prioridad de carga desde el punto de vista empresarial.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withChargingPriority(@Nullable Integer chargingPriority) {
        setChargingPriority(chargingPriority);
        return this;
    }

    /**
     * Obtiene el idioma preferido para la interfaz de usuario.
     *
     * @return Idioma preferido para la interfaz de usuario.
     */
    @Nullable
    public String getLanguage1() {
        return language1;
    }

    /**
     * Establece el idioma preferido para la interfaz de usuario.
     *
     * @param language1 Idioma preferido para la interfaz de usuario.
     */
    public void setLanguage1(@Nullable String language1) {
        if (!isValidLanguage1(language1)) {
            throw new PropertyConstraintException(language1, "language1 is invalid");
        }
        this.language1 = language1;
    }

    /**
     * Verifica si el idioma proporcionado es válido.
     *
     * @param language1 Idioma a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidLanguage1(@Nullable String language1) {
        return language1 == null || language1.length() <= 8;
    }

    /**
     * Agrega el idioma preferido para la interfaz de usuario.
     *
     * @param language1 Idioma preferido para la interfaz de usuario.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withLanguage1(@Nullable String language1) {
        setLanguage1(language1);
        return this;
    }

    /**
     * Obtiene la lista de identificadores de EVSE específicos para los cuales el token es válido.
     *
     * @return Lista de identificadores de EVSE específicos.
     */
    @Nullable
    public Integer[] getEvseId() {
        return evseId;
    }

    /**
     * Establece la lista de identificadores de EVSE específicos para los cuales el token es válido.
     *
     * @param evseId Lista de identificadores de EVSE específicos.
     */
    public void setEvseId(@Nullable Integer[] evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "evseId is invalid");
        }
        this.evseId = evseId;
    }

    /**
     * Verifica si los identificadores de EVSE proporcionados son válidos.
     *
     * @param evseId Identificadores de EVSE a verificar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidEvseId(@Nullable Integer[] evseId) {
        return evseId == null || (evseId.length >= 1);
    }

    /**
     * Agrega la lista de identificadores de EVSE específicos para los cuales el token es válido.
     *
     * @param evseId Lista de identificadores de EVSE específicos.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withEvseId(@Nullable Integer[] evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Obtiene un identificador insensible a mayúsculas y minúsculas para la autorización y el tipo de
     * autorización para admitir múltiples formas de identificadores.
     *
     * @return Un identificador insensible a mayúsculas y minúsculas para la autorización y el tipo de
     *     autorización para admitir múltiples formas de identificadores.
     */
    @Nullable
    public IdToken getGroupIdToken() {
        return groupIdToken;
    }

    /**
     * Establece un identificador insensible a mayúsculas y minúsculas para la autorización y el tipo de
     * autorización para admitir múltiples formas de identificadores.
     *
     * @param groupIdToken Un identificador insensible a mayúsculas y minúsculas para la autorización y el tipo de
     *     autorización para admitir múltiples formas de identificadores.
     */
    public void setGroupIdToken(@Nullable IdToken groupIdToken) {
        if (!isValidGroupIdToken(groupIdToken)) {
            throw new PropertyConstraintException(groupIdToken, "groupIdToken is invalid");
        }
        this.groupIdToken = groupIdToken;
    }

    /**
     * Verifica si el identificador proporcionado es válido.
     *
     * @param groupIdToken El identificador a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidGroupIdToken(@Nullable IdToken groupIdToken) {
        return groupIdToken == null || groupIdToken.validate();
    }

    /**
     * Agrega un identificador insensible a mayúsculas y minúsculas para la autorización y el tipo de
     * autorización para admitir múltiples formas de identificadores.
     *
     * @param groupIdToken Un identificador insensible a mayúsculas y minúsculas para la autorización y el tipo de
     *     autorización para admitir múltiples formas de identificadores.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withGroupIdToken(@Nullable IdToken groupIdToken) {
        setGroupIdToken(groupIdToken);
        return this;
    }

    /**
     * Obtiene el segundo idioma preferido para la interfaz de usuario.
     *
     * @return Segundo idioma preferido para la interfaz de usuario.
     */
    @Nullable
    public String getLanguage2() {
        return language2;
    }

    /**
     * Establece el segundo idioma preferido para la interfaz de usuario.
     *
     * @param language2 Segundo idioma preferido para la interfaz de usuario.
     */
    public void setLanguage2(@Nullable String language2) {
        if (!isValidLanguage2(language2)) {
            throw new PropertyConstraintException(language2, "language2 is invalid");
        }
        this.language2 = language2;
    }

    /**
     * Verifica si el idioma proporcionado es válido.
     *
     * @param language2 Idioma a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidLanguage2(@Nullable String language2) {
        return language2 == null || language2.length() <= 8;
    }

    /**
     * Agrega el segundo idioma preferido para la interfaz de usuario.
     *
     * @param language2 Segundo idioma preferido para la interfaz de usuario.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withLanguage2(@Nullable String language2) {
        setLanguage2(language2);
        return this;
    }

    /**
     * Obtiene mensaje personalizado para mostrar en una estación de carga.
     *
     * @return Mensaje personalizado para mostrar en una estación de carga.
     */
    @Nullable
    public MessageContent getPersonalMessage() {
        return personalMessage;
    }

    /**
     * Establece mensaje personalizado para mostrar en una estación de carga.
     *
     * @param personalMessage Mensaje personalizado para mostrar en una estación de carga.
     */
    public void setPersonalMessage(@Nullable MessageContent personalMessage) {
        if (!isValidPersonalMessage(personalMessage)) {
            throw new PropertyConstraintException(personalMessage, "personalMessage is invalid");
        }
        this.personalMessage = personalMessage;
    }

    /**
     * Verifica si el mensaje personalizado proporcionado es válido.
     *
     * @param personalMessage Mensaje personalizado a verificar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidPersonalMessage(@Nullable MessageContent personalMessage) {
        return personalMessage == null || personalMessage.validate();
    }

    /**
     * Agrega mensaje personalizado para mostrar en una estación de carga.
     *
     * @param personalMessage Mensaje personalizado para mostrar en una estación de carga.
     * @return La instancia actualizada de IdTokenInfo.
     */
    public IdTokenInfo withPersonalMessage(@Nullable MessageContent personalMessage) {
        setPersonalMessage(personalMessage);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidLanguage1(language1)
                && isValidEvseId(evseId)
                && isValidGroupIdToken(groupIdToken)
                && isValidLanguage2(language2)
                && isValidPersonalMessage(personalMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdTokenInfo that = (IdTokenInfo) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(cacheExpiryDateTime, that.cacheExpiryDateTime)
                && Objects.equals(chargingPriority, that.chargingPriority)
                && Objects.equals(language1, that.language1)
                && Arrays.equals(evseId, that.evseId)
                && Objects.equals(groupIdToken, that.groupIdToken)
                && Objects.equals(language2, that.language2)
                && Objects.equals(personalMessage, that.personalMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                status,
                cacheExpiryDateTime,
                chargingPriority,
                language1,
                Arrays.hashCode(evseId),
                groupIdToken,
                language2,
                personalMessage);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("cacheExpiryDateTime", cacheExpiryDateTime)
                .add("chargingPriority", chargingPriority)
                .add("language1", language1)
                .add("evseId", evseId)
                .add("groupIdToken", groupIdToken)
                .add("language2", language2)
                .add("personalMessage", personalMessage)
                .add("isValid", validate())
                .toString();
    }
}
