package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Utils.MessageInfo;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * NotifyDisplayMessagesRequest
 *
 * <p>Solicitud para notificar mensajes a ser mostrados en una estación de carga.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class NotifyDisplayMessagesRequest extends RequestWithId {
    /** Datos personalizados */
    @Nullable
    private CustomData customData;

    /**
     * Detalles del mensaje.
     *
     * <p>Información del mensaje que debe mostrarse en una estación de carga.
     */
    @Nullable
    private MessageInfo[] messageInfo;

    /** El ID de la solicitud GetDisplayMessagesRequest que originó este mensaje. */
    private Integer requestId;

    /**
     * Indicador de "continuará".
     *
     * <p>Indica si otra parte del reporte seguirá en un próximo mensaje NotifyDisplayMessagesRequest.
     * Por defecto, cuando se omite, es false.
     */
    @Nullable private Boolean tbc;

    /**
     * Constructor de la clase NotifyDisplayMessagesRequest
     *
     * @param requestId El ID de la solicitud GetDisplayMessagesRequest que originó este mensaje.
     */
    public NotifyDisplayMessagesRequest(Integer requestId) {
        setRequestId(requestId);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados
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
     * @param customData Datos personalizados a validar
     * @return {@code true} si son válidos, {@code false} si no
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Datos personalizados
     * @return Esta instancia
     */
    public NotifyDisplayMessagesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los detalles del mensaje.
     *
     * @return Información de los mensajes
     */
    @Nullable
    public MessageInfo[] getMessageInfo() {
        return messageInfo;
    }

    /**
     * Establece los detalles del mensaje.
     *
     * @param messageInfo Información de los mensajes
     */
    public void setMessageInfo(@Nullable MessageInfo[] messageInfo) {
        if (!isValidMessageInfo(messageInfo)) {
            throw new PropertyConstraintException(messageInfo, "messageInfo es inválido");
        }
        this.messageInfo = messageInfo;
    }

    /**
     * Valida si la información del mensaje es válida.
     *
     * @param messageInfo Información de los mensajes a validar
     * @return {@code true} si es válida, {@code false} si no
     */
    private boolean isValidMessageInfo(@Nullable MessageInfo[] messageInfo) {
        return messageInfo == null
                || (messageInfo.length >= 1
                && Arrays.stream(messageInfo).allMatch(item -> item.validate()));
    }

    /**
     * Añade detalles del mensaje.
     *
     * @param messageInfo Información de los mensajes
     * @return Esta instancia
     */
    public NotifyDisplayMessagesRequest withMessageInfo(@Nullable MessageInfo[] messageInfo) {
        setMessageInfo(messageInfo);
        return this;
    }

    /**
     * Obtiene el ID de la solicitud GetDisplayMessagesRequest que originó este mensaje.
     *
     * @return El ID de la solicitud
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el ID de la solicitud GetDisplayMessagesRequest que originó este mensaje.
     *
     * @param requestId El ID de la solicitud
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId es inválido");
        }
        this.requestId = requestId;
    }

    /**
     * Valida si el ID de la solicitud es válido.
     *
     * @param requestId ID de la solicitud a validar
     * @return {@code true} si es válido, {@code false} si no
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene el indicador de "continuará".
     *
     * @return {@code true} si continuará, {@code false} si no
     */
    public Boolean getTbc() {
        return tbc != null ? tbc : false;
    }

    /**
     * Establece el indicador de "continuará".
     *
     * @param tbc Indicador de "continuará"
     */
    public void setTbc(@Nullable Boolean tbc) {
        this.tbc = tbc;
    }

    /**
     * Añade el indicador de "continuará".
     *
     * @param tbc Indicador de "continuará"
     * @return Esta instancia
     */
    public NotifyDisplayMessagesRequest withTbc(@Nullable Boolean tbc) {
        setTbc(tbc);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidMessageInfo(messageInfo)
                && isValidRequestId(requestId);
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
        NotifyDisplayMessagesRequest that = (NotifyDisplayMessagesRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(messageInfo, that.messageInfo)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(tbc, that.tbc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(messageInfo), requestId, tbc);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("messageInfo", messageInfo)
                .add("requestId", requestId)
                .add("tbc", tbc)
                .add("isValid", validate())
                .toString();
    }
}
