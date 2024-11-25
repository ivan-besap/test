package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Enums.MessagePriorityEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Enums.MessageStateEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener mensajes de visualización en una estación de carga.
 *
 * <p>Permite filtrar los mensajes por ID, prioridad o estado, según lo especificado en la solicitud.
 */
public class GetDisplayMessagesRequest extends RequestWithId {

    @Nullable
    private CustomData customData;

    @Nullable
    private Integer[] id;

    private Integer requestId;

    @Nullable
    private MessagePriorityEnum priority;

    @Nullable
    private MessageStateEnum state;

    /**
     * Constructor para la clase GetDisplayMessagesRequest.
     *
     * @param requestId Identificador único de esta solicitud.
     */
    public GetDisplayMessagesRequest(Integer requestId) {
        setRequestId(requestId);
    }

    /**
     * Obtiene los datos personalizados asociados con esta solicitud.
     *
     * @return Objeto CustomData, si está presente.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados con esta solicitud.
     *
     * @param customData Objeto CustomData a asociar.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido.");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asocia datos personalizados con esta solicitud.
     *
     * @param customData Objeto CustomData a asociar.
     * @return La instancia actual de GetDisplayMessagesRequest.
     */
    public GetDisplayMessagesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los IDs de mensajes que deben ser devueltos por la estación de carga.
     *
     * @return Lista de IDs de mensajes.
     */
    @Nullable
    public Integer[] getId() {
        return id;
    }

    /**
     * Establece los IDs de mensajes que deben ser devueltos por la estación de carga.
     *
     * @param id Lista de IDs de mensajes.
     */
    public void setId(@Nullable Integer[] id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "id no es válido.");
        }
        this.id = id;
    }

    private boolean isValidId(@Nullable Integer[] id) {
        return id == null || id.length >= 1;
    }

    /**
     * Asocia una lista de IDs de mensajes con esta solicitud.
     *
     * @param id Lista de IDs de mensajes.
     * @return La instancia actual de GetDisplayMessagesRequest.
     */
    public GetDisplayMessagesRequest withId(@Nullable Integer[] id) {
        setId(id);
        return this;
    }

    /**
     * Obtiene el identificador único de esta solicitud.
     *
     * @return ID de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador único de esta solicitud.
     *
     * @param requestId ID de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId no es válido.");
        }
        this.requestId = requestId;
    }

    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene la prioridad de los mensajes que deben ser devueltos por la estación de carga.
     *
     * @return Prioridad de los mensajes.
     */
    @Nullable
    public MessagePriorityEnum getPriority() {
        return priority;
    }

    /**
     * Establece la prioridad de los mensajes que deben ser devueltos por la estación de carga.
     *
     * @param priority Prioridad de los mensajes.
     */
    public void setPriority(@Nullable MessagePriorityEnum priority) {
        this.priority = priority;
    }

    /**
     * Asocia una prioridad a esta solicitud.
     *
     * @param priority Prioridad de los mensajes.
     * @return La instancia actual de GetDisplayMessagesRequest.
     */
    public GetDisplayMessagesRequest withPriority(@Nullable MessagePriorityEnum priority) {
        setPriority(priority);
        return this;
    }

    /**
     * Obtiene el estado de los mensajes que deben ser devueltos por la estación de carga.
     *
     * @return Estado de los mensajes.
     */
    @Nullable
    public MessageStateEnum getState() {
        return state;
    }

    /**
     * Establece el estado de los mensajes que deben ser devueltos por la estación de carga.
     *
     * @param state Estado de los mensajes.
     */
    public void setState(@Nullable MessageStateEnum state) {
        this.state = state;
    }

    /**
     * Asocia un estado a esta solicitud.
     *
     * @param state Estado de los mensajes.
     * @return La instancia actual de GetDisplayMessagesRequest.
     */
    public GetDisplayMessagesRequest withState(@Nullable MessageStateEnum state) {
        setState(state);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidId(id) && isValidRequestId(requestId);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDisplayMessagesRequest that = (GetDisplayMessagesRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(id, that.id)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(priority, that.priority)
                && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(id), requestId, priority, state);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("requestId", requestId)
                .add("priority", priority)
                .add("state", state)
                .add("isValid", validate())
                .toString();
    }
}
