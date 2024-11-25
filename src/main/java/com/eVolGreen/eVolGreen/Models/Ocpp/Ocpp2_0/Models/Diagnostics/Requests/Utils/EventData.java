package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.EventNotificationEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.EventTriggerEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Datos de un evento.
 *
 * <p>Esta clase representa los datos de un evento que se notifican en el protocolo OCPP. Incluye
 * información detallada sobre el evento, como su identificador, el componente relacionado, el valor
 * actual de la variable, y más.
 */
public class EventData {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Identificador único del evento, que puede ser referenciado por otros eventos. */
    private Integer eventId;

    /** Marca de tiempo en la que se generó este evento. */
    private ZonedDateTime timestamp;

    /** Tipo de monitor que activó este evento, por ejemplo, exceder un valor umbral. */
    private EventTriggerEnum trigger;

    /** Identificador de un evento que se considera la causa de este evento. */
    @Nullable private Integer cause;

    /**
     * Valor actual (tipo de atributo Actual) de la variable.
     *
     * <p>El valor está limitado por la variable de configuración ReportingValueSize.
     */
    private String actualValue;

    /** Código técnico (error) reportado por el componente. */
    @Nullable private String techCode;

    /** Información técnica detallada reportada por el componente. */
    @Nullable private String techInfo;

    /**
     * Indicador de limpieza. Si es verdadero, informa el retorno a la normalidad de una situación
     * monitoreada.
     */
    @Nullable private Boolean cleared;

    /**
     * Si la notificación del evento está vinculada a una transacción específica, este campo puede
     * usarse para especificar su identificador.
     */
    @Nullable private String transactionId;

    /** Componente físico o lógico relacionado con el evento. */
    private Component component;

    /** Identificador de la VariableMonitoring que activó el evento. */
    @Nullable private Integer variableMonitoringId;

    /** Tipo de notificación del evento. */
    private EventNotificationEnum eventNotificationType;

    /** Referencia a una variable de un componente. */
    private Variable variable;

    /**
     * Constructor para la clase EventData.
     *
     * @param eventId Identificador único del evento.
     * @param timestamp Marca de tiempo en la que se generó este evento.
     * @param trigger Tipo de monitor que activó este evento.
     * @param actualValue Valor actual (tipo de atributo Actual) de la variable.
     * @param component Componente físico o lógico relacionado con el evento.
     * @param eventNotificationType Tipo de notificación del evento.
     * @param variable Referencia a una variable de un componente.
     */
    public EventData(
            Integer eventId,
            ZonedDateTime timestamp,
            EventTriggerEnum trigger,
            String actualValue,
            Component component,
            EventNotificationEnum eventNotificationType,
            Variable variable) {
        setEventId(eventId);
        setTimestamp(timestamp);
        setTrigger(trigger);
        setActualValue(actualValue);
        setComponent(component);
        setEventNotificationType(eventNotificationType);
        setVariable(variable);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados adicionales son válidos.
     *
     * @param customData Datos personalizados adicionales.
     * @return Verdadero si los datos personalizados adicionales son válidos, falso en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia.
     */
    public EventData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único del evento.
     *
     * @return Identificador del evento.
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * Establece el identificador único del evento.
     *
     * @param eventId Identificador del evento.
     */
    public void setEventId(Integer eventId) {
        if (!isValidEventId(eventId)) {
            throw new PropertyConstraintException(eventId, "El identificador del evento es inválido.");
        }
        this.eventId = eventId;
    }

    /**
     * Agrega el identificador único del evento.
     *
     * @param eventId Identificador del evento.
     * @return Esta instancia.
     */
    private boolean isValidEventId(Integer eventId) {
        return eventId != null;
    }

    /**
     * Obtiene la marca de tiempo en la que se generó el evento.
     *
     * @return Marca de tiempo del evento.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la marca de tiempo en la que se generó el evento.
     *
     * @param timestamp Marca de tiempo del evento.
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        if (!isValidTimestamp(timestamp)) {
            throw new PropertyConstraintException(timestamp, "La marca de tiempo es inválida.");
        }
        this.timestamp = timestamp;
    }

    /**
     * Agrega la marca de tiempo en la que se generó el evento.
     *
     * @param timestamp Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidTimestamp(ZonedDateTime timestamp) {
        return timestamp != null;
    }

    /**
     * Obtiene el tipo de monitor que activó este evento.
     *
     * @return Tipo de monitor que activó este evento.
     */
    public EventTriggerEnum getTrigger() {
        return trigger;
    }

    /**
     * Establece el tipo de monitor que activó este evento.
     *
     * @param trigger Tipo de monitor que activó este evento.
     */
    public void setTrigger(EventTriggerEnum trigger) {
        if (!isValidTrigger(trigger)) {
            throw new PropertyConstraintException(trigger, "El tipo de monitor es inválido.");
        }
        this.trigger = trigger;
    }

    /**
     * Obtiene el identificador de un evento que se considera la causa de este evento.
     *
     * @return Identificador de la causa del evento.
     */
    @Nullable
    public Integer getCause() {
        return cause;
    }

    /**
     * Establece el identificador de un evento que se considera la causa de este evento.
     *
     * @param cause Identificador de la causa del evento.
     */
    public void setCause(@Nullable Integer cause) {
        this.cause = cause;
    }

    /**
     * Agrega el identificador de un evento que se considera la causa de este evento.
     *
     * @param cause Identificador de la causa del evento.
     * @return Esta instancia.
     */
    public EventData withCause(@Nullable Integer cause) {
        setCause(cause);
        return this;
    }

    /**
     * Agrega el tipo de monitor que activó este evento.
     *
     * @param trigger Tipo de monitor que activó este evento.
     * @return Esta instancia.
     */
    private boolean isValidTrigger(EventTriggerEnum trigger) {
        return trigger != null;
    }

    /**
     * Obtiene el valor actual de la variable.
     *
     * @return Valor actual de la variable.
     */
    public String getActualValue() {
        return actualValue;
    }

    /**
     * Establece el valor actual de la variable.
     *
     * @param actualValue Valor actual de la variable.
     */
    public void setActualValue(String actualValue) {
        if (!isValidActualValue(actualValue)) {
            throw new PropertyConstraintException(actualValue, "El valor actual es inválido.");
        }
        this.actualValue = actualValue;
    }

    /**
     * Agrega el valor actual de la variable.
     *
     * @param actualValue Valor actual de la variable.
     * @return Esta instancia.
     */
    private boolean isValidActualValue(String actualValue) {
        return actualValue != null && actualValue.length() <= 2500;
    }

    /**
     * Obtiene el código técnico (error) reportado por el componente.
     *
     * @return Código técnico (error) reportado por el componente.
     */
    @Nullable
    public String getTechCode() {
        return techCode;
    }

    /**
     * Establece el código técnico (error) reportado por el componente.
     *
     * @param techCode Código técnico (error) reportado por el componente.
     */
    public void setTechCode(@Nullable String techCode) {
        if (!isValidTechCode(techCode)) {
            throw new PropertyConstraintException(techCode, "techCode is invalid");
        }
        this.techCode = techCode;
    }

    /**
     * Agrega el código técnico (error) reportado por el componente.
     *
     * @param techCode Código técnico (error) reportado por el componente.
     * @return Esta instancia.
     */
    private boolean isValidTechCode(@Nullable String techCode) {
        return techCode == null || techCode.length() <= 50;
    }

    /**
     * Obtiene la información técnica detallada reportada por el componente.
     *
     * @return Información técnica detallada reportada por el componente.
     */
    public EventData withTechCode(@Nullable String techCode) {
        setTechCode(techCode);
        return this;
    }

    /**
     * Obtiene la información técnica detallada reportada por el componente.
     *
     * @return Información técnica detallada reportada por el componente.
     */
    @Nullable
    public String getTechInfo() {
        return techInfo;
    }

    /**
     * Establece la información técnica detallada reportada por el componente.
     *
     * @param techInfo Información técnica detallada reportada por el componente.
     */
    public void setTechInfo(@Nullable String techInfo) {
        if (!isValidTechInfo(techInfo)) {
            throw new PropertyConstraintException(techInfo, "techInfo is invalid");
        }
        this.techInfo = techInfo;
    }

    /**
     * Agrega la información técnica detallada reportada por el componente.
     *
     * @param techInfo Información técnica detallada reportada por el componente.
     * @return Esta instancia.
     */
    private boolean isValidTechInfo(@Nullable String techInfo) {
        return techInfo == null || techInfo.length() <= 500;
    }

    /**
     * Obtiene el indicador de limpieza.
     *
     * @return Indicador de limpieza.
     */
    public EventData withTechInfo(@Nullable String techInfo) {
        setTechInfo(techInfo);
        return this;
    }

    /**
     * Obtiene el indicador de limpieza.
     *
     * @return Indicador de limpieza.
     */
    @Nullable
    public Boolean getCleared() {
        return cleared;
    }

    /**
     * Establece el indicador de limpieza.
     *
     * @param cleared Indicador de limpieza.
     */
    public void setCleared(@Nullable Boolean cleared) {
        this.cleared = cleared;
    }

    /**
     * Agrega el indicador de limpieza.
     *
     * @param cleared Indicador de limpieza.
     * @return Esta instancia.
     */
    public EventData withCleared(@Nullable Boolean cleared) {
        setCleared(cleared);
        return this;
    }

    /**
     * Obtiene el identificador de la transacción.
     *
     * @return Identificador de la transacción.
     */
    @Nullable
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción.
     *
     * @param transactionId Identificador de la transacción.
     */
    public void setTransactionId(@Nullable String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "transactionId is invalid");
        }
        this.transactionId = transactionId;
    }

    /**
     * Agrega el identificador de la transacción.
     *
     * @param transactionId Identificador de la transacción.
     * @return Esta instancia.
     */
    private boolean isValidTransactionId(@Nullable String transactionId) {
        return transactionId == null || transactionId.length() <= 36;
    }

    /**
     * Agrega el identificador de la transacción.
     *
     * @param transactionId Identificador de la transacción.
     * @return Esta instancia.
     */
    public EventData withTransactionId(@Nullable String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    /**
     * Obtiene el componente físico o lógico relacionado con el evento.
     *
     * @return Componente físico o lógico relacionado con el evento.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente físico o lógico relacionado con el evento.
     *
     * @param component Componente físico o lógico relacionado con el evento.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "component is invalid");
        }
        this.component = component;
    }

    /**
     * Agrega el componente físico o lógico relacionado con el evento.
     *
     * @param component Componente físico o lógico relacionado con el evento.
     * @return Esta instancia.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene el tipo de notificación del evento.
     *
     * @return Tipo de notificación del evento.
     */
    @Nullable
    public Integer getVariableMonitoringId() {
        return variableMonitoringId;
    }

    /**
     * Establece el identificador de la VariableMonitoring que activó el evento.
     *
     * @param variableMonitoringId Identificador de la VariableMonitoring que activó el evento.
     */
    public void setVariableMonitoringId(@Nullable Integer variableMonitoringId) {
        this.variableMonitoringId = variableMonitoringId;
    }

    /**
     * Agrega el identificador de la VariableMonitoring que activó el evento.
     *
     * @param variableMonitoringId Identificador de la VariableMonitoring que activó el evento.
     * @return Esta instancia.
     */
    public EventData withVariableMonitoringId(@Nullable Integer variableMonitoringId) {
        setVariableMonitoringId(variableMonitoringId);
        return this;
    }

    /**
     * Obtiene el tipo de notificación del evento.
     *
     * @return Tipo de notificación del evento.
     */
    public EventNotificationEnum getEventNotificationType() {
        return eventNotificationType;
    }

    /**
     * Establece el tipo de notificación del evento.
     *
     * @param eventNotificationType Tipo de notificación del evento.
     */
    public void setEventNotificationType(EventNotificationEnum eventNotificationType) {
        if (!isValidEventNotificationType(eventNotificationType)) {
            throw new PropertyConstraintException(
                    eventNotificationType, "eventNotificationType is invalid");
        }
        this.eventNotificationType = eventNotificationType;
    }

    /**
     * Agrega el tipo de notificación del evento.
     *
     * @param eventNotificationType Tipo de notificación del evento.
     * @return Esta instancia.
     */
    private boolean isValidEventNotificationType(EventNotificationEnum eventNotificationType) {
        return eventNotificationType != null;
    }

    /**
     * Obtiene la referencia a una variable de un componente.
     *
     * @return Referencia a una variable de un componente.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Verifica si la referencia a una variable de un componente es válida.
     *
     * @param variable Referencia a una variable de un componente.
     * @return {@code true} si es válida, {@code false} si no.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Establece la referencia a una variable de un componente.
     *
     * @param variable Referencia a una variable de un componente.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "variable is invalid");
        }
        this.variable = variable;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEventId(eventId)
                && isValidTimestamp(timestamp)
                && isValidTrigger(trigger)
                && isValidActualValue(actualValue)
                && isValidTechCode(techCode)
                && isValidTechInfo(techInfo)
                && isValidTransactionId(transactionId)
                && isValidComponent(component)
                && isValidEventNotificationType(eventNotificationType)
                && isValidVariable(variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventData that = (EventData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(eventId, that.eventId)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(trigger, that.trigger)
                && Objects.equals(cause, that.cause)
                && Objects.equals(actualValue, that.actualValue)
                && Objects.equals(techCode, that.techCode)
                && Objects.equals(techInfo, that.techInfo)
                && Objects.equals(cleared, that.cleared)
                && Objects.equals(transactionId, that.transactionId)
                && Objects.equals(component, that.component)
                && Objects.equals(variableMonitoringId, that.variableMonitoringId)
                && Objects.equals(eventNotificationType, that.eventNotificationType)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                eventId,
                timestamp,
                trigger,
                cause,
                actualValue,
                techCode,
                techInfo,
                cleared,
                transactionId,
                component,
                variableMonitoringId,
                eventNotificationType,
                variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("eventId", eventId)
                .add("timestamp", timestamp)
                .add("trigger", trigger)
                .add("cause", cause)
                .add("actualValue", actualValue)
                .add("techCode", techCode)
                .add("techInfo", techInfo)
                .add("cleared", cleared)
                .add("transactionId", transactionId)
                .add("component", component)
                .add("variableMonitoringId", variableMonitoringId)
                .add("eventNotificationType", eventNotificationType)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
