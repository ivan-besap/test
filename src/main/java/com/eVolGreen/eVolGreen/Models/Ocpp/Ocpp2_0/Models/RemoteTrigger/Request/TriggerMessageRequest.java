package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.EVSE;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.Enums.MessageTriggerEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Mensaje de Disparo (TriggerMessageRequest)
 *
 * <p>Clase que representa la solicitud para disparar un mensaje específico desde el sistema de
 * gestión (CSMS) a la estación de carga, según la especificación OCPP 2.0.1.
 *
 * <p>Permite indicar el tipo de mensaje que se debe disparar y opcionalmente el EVSE (Electric
 * Vehicle Supply Equipment) asociado a la solicitud.
 */
public class TriggerMessageRequest extends RequestWithId {

    /**
     * Datos personalizados asociados a la solicitud.
     */
    @Nullable
    private CustomData customData;

    /**
     * Información del EVSE asociado a la solicitud.
     */
    @Nullable
    private EVSE evse;

    /**
     * Tipo de mensaje que se debe disparar.
     */
    @Nullable
    private MessageTriggerEnum requestedMessage;

    /**
     * Constructor para inicializar un mensaje TriggerMessageRequest
     *
     * @param requestedMessage El tipo de mensaje que se debe disparar.
     */
    public TriggerMessageRequest(MessageTriggerEnum requestedMessage) {
        setRequestedMessage(requestedMessage);
    }

    /**
     * Obtiene los datos personalizados de la solicitud.
     *
     * @return Datos personalizados.
     */
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la solicitud.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return `true` si los datos son válidos; `false` de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados.
     * @return Instancia actualizada de TriggerMessageRequest.
     */
    public TriggerMessageRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la información del EVSE asociado.
     *
     * @return Información del EVSE.
     */
    public EVSE getEvse() {
        return evse;
    }

    /**
     * Establece el EVSE asociado a la solicitud.
     *
     * @param evse Información del EVSE.
     */
    public void setEvse(@Nullable EVSE evse) {
        if (!isValidEvse(evse)) {
            throw new PropertyConstraintException(evse, "evse is invalid");
        }
        this.evse = evse;
    }

    /**
     * Verifica si la información del EVSE es válida.
     *
     * @param evse Información del EVSE a validar.
     * @return `true` si la información es válida; `false` de lo contrario.
     */
    private boolean isValidEvse(@Nullable EVSE evse) {
        return evse == null || evse.validate();
    }

    /**
     * Establece la información del EVSE de forma fluida.
     *
     * @param evse Información del EVSE.
     * @return Instancia actualizada de TriggerMessageRequest.
     */
    public TriggerMessageRequest withEvse(@Nullable EVSE evse) {
        setEvse(evse);
        return this;
    }

    /**
     * Obtiene el tipo de mensaje que se debe disparar.
     *
     * @return Tipo de mensaje.
     */
    public MessageTriggerEnum getRequestedMessage() {
        return requestedMessage;
    }

    /**
     * Establece el tipo de mensaje que se debe disparar.
     *
     * @param requestedMessage Tipo de mensaje.
     */
    public void setRequestedMessage(MessageTriggerEnum requestedMessage) {
        if (!isValidRequestedMessage(requestedMessage)) {
            throw new PropertyConstraintException(requestedMessage, "requestedMessage is invalid");
        }
        this.requestedMessage = requestedMessage;
    }

    /**
     * Verifica si el tipo de mensaje es válido.
     *
     * @param requestedMessage Tipo de mensaje a validar.
     * @return `true` si el tipo de mensaje es válido; `false` de lo contrario.
     */
    private boolean isValidRequestedMessage(MessageTriggerEnum requestedMessage) {
        return requestedMessage != null;
    }

    /**
     * Valida si la solicitud TriggerMessageRequest cumple con los requisitos del protocolo.
     *
     * @return `true` si la solicitud es válida; `false` de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEvse(evse)
                && isValidRequestedMessage(requestedMessage);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return `false` ya que no está relacionada con una transacción.
     */
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
        TriggerMessageRequest that = (TriggerMessageRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evse, that.evse)
                && Objects.equals(requestedMessage, that.requestedMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, evse, requestedMessage);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evse", evse)
                .add("requestedMessage", requestedMessage)
                .add("isValid", validate())
                .toString();
    }
}
