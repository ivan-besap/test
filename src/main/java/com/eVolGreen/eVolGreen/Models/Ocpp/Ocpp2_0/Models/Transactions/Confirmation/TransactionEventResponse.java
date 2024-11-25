package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.IdTokenInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.MessageContent;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta a un evento de transacción
 *
 * <p>Esta clase representa la respuesta de un sistema de gestión (CSMS) a un evento relacionado
 * con una transacción en curso o completada en una estación de carga, siguiendo la especificación
 * OCPP 2.0.1.
 *
 * <p>Proporciona detalles sobre el costo final de la transacción, la prioridad temporal para la
 * carga, información actualizada sobre tokens de identificación y mensajes personalizados para
 * mostrar en la estación de carga.
 */
public class TransactionEventResponse extends Confirmation {
    /**
     * Datos personalizados.
     */
    @Nullable
    private CustomData customData;

    /**
     * Costo total de la transacción.
     */
    @Nullable
    private Double totalCost;

    /**
     * Prioridad de carga empresarial.
     */
    @Nullable
    private Integer chargingPriority;

    /**
     * Información del token de identificación.
     */
    @Nullable
    private IdTokenInfo idTokenInfo;

    /**
     * Mensaje personalizado para la estación de carga.
     */
    @Nullable
    private MessageContent updatedPersonalMessage;

    /**
     * Constructor vacío
     */
    public TransactionEventResponse() {}

    /**
     * Devuelve los datos personalizados.
     *
     * @return Datos personalizados.
     */
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
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
     * @return True si los datos son válidos, false en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia con los datos personalizados establecidos.
     */
    public TransactionEventResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Devuelve el costo total de la transacción (con impuestos).
     *
     * @return Costo total.
     */
    public Double getTotalCost() {
        return totalCost;
    }

    /**
     * Establece el costo total de la transacción.
     *
     * @param totalCost Costo total.
     */
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    /**
     * Establece la prioridad de carga empresarial de forma fluida.
     *
     * @param totalCost Prioridad de carga.
     * @return Esta instancia con la prioridad de carga establecida.
     */
    public TransactionEventResponse withTotalCost(@Nullable Double totalCost) {
        setTotalCost(totalCost);
        return this;
    }

    /**
     * Devuelve la prioridad de carga empresarial.
     *
     * @return Prioridad de carga.
     */
    public Integer getChargingPriority() {
        return chargingPriority;
    }

    /**
     * Establece la prioridad de carga empresarial.
     *
     * @param chargingPriority Prioridad de carga.
     */
    public void setChargingPriority(Integer chargingPriority) {
        this.chargingPriority = chargingPriority;
    }

    /**
     * Establece la prioridad de carga empresarial de forma fluida.
     *
     * @param chargingPriority Prioridad de carga.
     * @return Esta instancia con la prioridad de carga establecida.
     */
    public TransactionEventResponse withChargingPriority(@Nullable Integer chargingPriority) {
        setChargingPriority(chargingPriority);
        return this;
    }

    /**
     * Devuelve la información del token de identificación.
     *
     * @return Información del token.
     */
    public IdTokenInfo getIdTokenInfo() {
        return idTokenInfo;
    }

    /**
     * Establece la información del token de identificación.
     *
     * @param idTokenInfo Información del token.
     */
    public void setIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
        if (!isValidIdTokenInfo(idTokenInfo)) {
            throw new PropertyConstraintException(idTokenInfo, "idTokenInfo is invalid");
        }
        this.idTokenInfo = idTokenInfo;
    }

    /**
     * Verifica si la información del token de identificación es válida.
     *
     * @param idTokenInfo Información del token a validar.
     * @return True si la información es válida, false en caso contrario.
     */
    private boolean isValidIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
        return idTokenInfo == null || idTokenInfo.validate();
    }

    /**
     * Agrega información del token de identificación.
     *
     * @param idTokenInfo Información del token.
     * @return Esta instancia con la información del token establecida.
     */
    public TransactionEventResponse withIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
        setIdTokenInfo(idTokenInfo);
        return this;
    }

    /**
     * Devuelve el mensaje personalizado para la estación de carga.
     *
     * @return Mensaje personalizado.
     */
    public MessageContent getUpdatedPersonalMessage() {
        return updatedPersonalMessage;
    }

    /**
     * Establece el mensaje personalizado para la estación de carga.
     *
     * @param updatedPersonalMessage Mensaje personalizado.
     */
    public void setUpdatedPersonalMessage(@Nullable MessageContent updatedPersonalMessage) {
        if (!isValidUpdatedPersonalMessage(updatedPersonalMessage)) {
            throw new PropertyConstraintException(
                    updatedPersonalMessage, "updatedPersonalMessage is invalid");
        }
        this.updatedPersonalMessage = updatedPersonalMessage;
    }

    /**
     * Verifica si el mensaje personalizado es válido.
     *
     * @param updatedPersonalMessage Mensaje personalizado a validar.
     * @return True si el mensaje es válido, false en caso contrario.
     */
    private boolean isValidUpdatedPersonalMessage(@Nullable MessageContent updatedPersonalMessage) {
        return updatedPersonalMessage == null || updatedPersonalMessage.validate();
    }

    /**
     * Agrega un mensaje personalizado para la estación de carga.
     *
     * @param updatedPersonalMessage Mensaje personalizado.
     * @return Esta instancia con el mensaje personalizado establecido.
     */
    public TransactionEventResponse withUpdatedPersonalMessage(
            @Nullable MessageContent updatedPersonalMessage) {
        setUpdatedPersonalMessage(updatedPersonalMessage);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidIdTokenInfo(idTokenInfo)
                && isValidUpdatedPersonalMessage(updatedPersonalMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionEventResponse that = (TransactionEventResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(totalCost, that.totalCost)
                && Objects.equals(chargingPriority, that.chargingPriority)
                && Objects.equals(idTokenInfo, that.idTokenInfo)
                && Objects.equals(updatedPersonalMessage, that.updatedPersonalMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, totalCost, chargingPriority, idTokenInfo, updatedPersonalMessage);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("totalCost", totalCost)
                .add("chargingPriority", chargingPriority)
                .add("idTokenInfo", idTokenInfo)
                .add("updatedPersonalMessage", updatedPersonalMessage)
                .add("isValid", validate())
                .toString();
    }
}
