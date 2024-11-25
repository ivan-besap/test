package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.MessageContent;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Component;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Enums.MessagePriorityEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.Enums.MessageStateEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Información de Mensaje.
 *
 * <p>Detalles del mensaje que se mostrará en una estación de carga.
 */
public class MessageInfo {
    /** Datos personalizados. */
    @Nullable
    private CustomData customData;

    /** Componente físico o lógico donde se muestra el mensaje. */
    @Nullable
    private Component display;

    /**
     * Identificador único del recurso.
     *
     * <p>Identificador numérico único dentro de un contexto de intercambio. En OCPP, se define como
     * un valor entero positivo (mayor o igual a cero).
     */
    private Integer id;

    /**
     * Prioridad del mensaje.
     *
     * <p>Define con qué prioridad debe mostrarse este mensaje.
     */
    private MessagePriorityEnum priority;

    /**
     * Estado del mensaje.
     *
     * <p>Define durante qué estado debe mostrarse este mensaje. Si se omite, se muestra en cualquier
     * estado de la estación de carga.
     */
    @Nullable private MessageStateEnum state;

    /**
     * Fecha y hora de inicio del mensaje.
     *
     * <p>Desde qué fecha y hora debe mostrarse este mensaje. Si se omite, se muestra directamente.
     */
    @Nullable private ZonedDateTime startDateTime;

    /**
     * Fecha y hora de finalización del mensaje.
     *
     * <p>Hasta qué fecha y hora debe mostrarse este mensaje. Después de esta fecha y hora, el mensaje
     * será eliminado.
     */
    @Nullable private ZonedDateTime endDateTime;

    /**
     * Identificador de la transacción durante la cual debe mostrarse este mensaje.
     *
     * <p>El mensaje será eliminado por la estación de carga una vez que finalice la transacción.
     */
    @Nullable private String transactionId;

    /**
     * Contenido del mensaje.
     *
     * <p>Detalles del mensaje que se mostrará en una estación de carga.
     */
    private MessageContent message;

    /**
     * Constructor de la clase MessageInfo.
     *
     * @param id Identificador único del recurso.
     * @param priority Prioridad del mensaje.
     * @param message Contenido del mensaje.
     */
    public MessageInfo(Integer id, MessagePriorityEnum priority, MessageContent message) {
        setId(id);
        setPriority(priority);
        setMessage(message);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
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
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public MessageInfo withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el componente físico o lógico donde se muestra el mensaje.
     *
     * @return Componente de visualización.
     */
    @Nullable
    public Component getDisplay() {
        return display;
    }

    /**
     * Establece el componente físico o lógico donde se muestra el mensaje.
     *
     * @param display Componente de visualización.
     */
    public void setDisplay(@Nullable Component display) {
        if (!isValidDisplay(display)) {
            throw new PropertyConstraintException(display, "display es inválido");
        }
        this.display = display;
    }

    /**
     * Valida si el componente de visualización es válido.
     *
     * @param display Componente de visualización a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidDisplay(@Nullable Component display) {
        return display == null || display.validate();
    }

    /**
     * Añade un componente de visualización.
     *
     * @param display Componente de visualización.
     * @return Esta instancia.
     */
    public MessageInfo withDisplay(@Nullable Component display) {
        setDisplay(display);
        return this;
    }

    /**
     * Obtiene el identificador único del recurso.
     *
     * @return Identificador único del recurso.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del recurso.
     *
     * @param id Identificador único del recurso.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "id is invalid");
        }
        this.id = id;
    }

    /**
     * Valida si el identificador es válido.
     *
     * @param id Identificador a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Obtiene la prioridad del mensaje.
     *
     * @return Prioridad del mensaje.
     */
    public MessagePriorityEnum getPriority() {
        return priority;
    }

    /**
     * Establece la prioridad del mensaje.
     *
     * @param priority Prioridad del mensaje.
     */
    public void setPriority(MessagePriorityEnum priority) {
        if (!isValidPriority(priority)) {
            throw new PropertyConstraintException(priority, "priority es inválido");
        }
        this.priority = priority;
    }

    /**
     * Valida si la prioridad es válida.
     *
     * @param priority Prioridad a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidPriority(MessagePriorityEnum priority) {
        return priority != null;
    }

    /**
     * Obtiene el estado durante el cual debe mostrarse este mensaje.
     * Si se omite, este mensaje se mostrará en cualquier estado de la estación de carga.
     *
     * @return Estado durante el cual debe mostrarse este mensaje.
     */
    @Nullable
    public MessageStateEnum getState() {
        return state;
    }

    /**
     * Establece el estado durante el cual debe mostrarse este mensaje.
     * Si se omite, este mensaje se mostrará en cualquier estado de la estación de carga.
     *
     * @param state Estado durante el cual debe mostrarse este mensaje.
     */
    public void setState(@Nullable MessageStateEnum state) {
        this.state = state;
    }

    /**
     * Añade el estado durante el cual debe mostrarse este mensaje.
     * Si se omite, este mensaje se mostrará en cualquier estado de la estación de carga.
     *
     * @param state Estado durante el cual debe mostrarse este mensaje.
     * @return Esta instancia.
     */
    public MessageInfo withState(@Nullable MessageStateEnum state) {
        setState(state);
        return this;
    }

    /**
     * Obtiene la fecha y hora desde la cual debe mostrarse este mensaje.
     * Si se omite, el mensaje se mostrará inmediatamente.
     *
     * @return Fecha y hora desde la cual debe mostrarse este mensaje.
     */
    @Nullable
    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Establece la fecha y hora desde la cual debe mostrarse este mensaje.
     * Si se omite, el mensaje se mostrará inmediatamente.
     *
     * @param startDateTime Fecha y hora desde la cual debe mostrarse este mensaje.
     */
    public void setStartDateTime(@Nullable ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Añade la fecha y hora desde la cual debe mostrarse este mensaje.
     * Si se omite, el mensaje se mostrará inmediatamente.
     *
     * @param startDateTime Fecha y hora desde la cual debe mostrarse este mensaje.
     * @return Esta instancia.
     */
    public MessageInfo withStartDateTime(@Nullable ZonedDateTime startDateTime) {
        setStartDateTime(startDateTime);
        return this;
    }

    /**
     * Obtiene la fecha y hora hasta la cual debe mostrarse este mensaje.
     * Después de esta fecha y hora, el mensaje será eliminado.
     *
     * @return Fecha y hora hasta la cual debe mostrarse este mensaje.
     */
    @Nullable
    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Establece la fecha y hora hasta la cual debe mostrarse este mensaje.
     * Después de esta fecha y hora, el mensaje será eliminado.
     *
     * @param endDateTime Fecha y hora hasta la cual debe mostrarse este mensaje.
     */
    public void setEndDateTime(@Nullable ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * Añade la fecha y hora hasta la cual debe mostrarse este mensaje.
     * Después de esta fecha y hora, el mensaje será eliminado.
     *
     * @param endDateTime Fecha y hora hasta la cual debe mostrarse este mensaje.
     * @return Esta instancia.
     */
    public MessageInfo withEndDateTime(@Nullable ZonedDateTime endDateTime) {
        setEndDateTime(endDateTime);
        return this;
    }

    /**
     * Obtiene el identificador de la transacción durante la cual debe mostrarse este mensaje.
     * El mensaje será eliminado por la estación de carga una vez finalizada la transacción.
     *
     * @return Identificador de la transacción durante la cual debe mostrarse este mensaje.
     */
    @Nullable
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción durante la cual debe mostrarse este mensaje.
     * El mensaje será eliminado por la estación de carga una vez finalizada la transacción.
     *
     * @param transactionId Identificador de la transacción durante la cual debe mostrarse este mensaje.
     */
    public void setTransactionId(@Nullable String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "El identificador de la transacción es inválido");
        }
        this.transactionId = transactionId;
    }

    /**
     * Valida si el identificador de la transacción es válido.
     *
     * @param transactionId Identificador de la transacción a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidTransactionId(@Nullable String transactionId) {
        return transactionId == null || transactionId.length() <= 36;
    }

    /**
     * Añade el identificador de la transacción durante la cual debe mostrarse este mensaje.
     * El mensaje será eliminado por la estación de carga una vez finalizada la transacción.
     *
     * @param transactionId Identificador de la transacción durante la cual debe mostrarse este mensaje.
     * @return Esta instancia.
     */
    public MessageInfo withTransactionId(@Nullable String transactionId) {
        setTransactionId(transactionId);
        return this;
    }


    /**
     * Obtiene el contenido del mensaje.
     *
     * @return Contenido del mensaje.
     */
    public MessageContent getMessage() {
        return message;
    }

    /**
     * Establece el contenido del mensaje.
     *
     * @param message Contenido del mensaje.
     */
    public void setMessage(MessageContent message) {
        if (!isValidMessage(message)) {
            throw new PropertyConstraintException(message, "message es inválido");
        }
        this.message = message;
    }

    /**
     * Valida si el contenido del mensaje es válido.
     *
     * @param message Contenido del mensaje a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidMessage(MessageContent message) {
        return message != null && message.validate();
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidDisplay(display)
                && isValidId(id)
                && isValidPriority(priority)
                && isValidTransactionId(transactionId)
                && isValidMessage(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageInfo that = (MessageInfo) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(display, that.display)
                && Objects.equals(id, that.id)
                && Objects.equals(priority, that.priority)
                && Objects.equals(state, that.state)
                && Objects.equals(startDateTime, that.startDateTime)
                && Objects.equals(endDateTime, that.endDateTime)
                && Objects.equals(transactionId, that.transactionId)
                && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                display,
                id,
                priority,
                state,
                startDateTime,
                endDateTime,
                transactionId,
                message);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("display", display)
                .add("id", id)
                .add("priority", priority)
                .add("state", state)
                .add("startDateTime", startDateTime)
                .add("endDateTime", endDateTime)
                .add("transactionId", transactionId)
                .add("message", message)
                .add("isValid", validate())
                .toString();
    }
}
