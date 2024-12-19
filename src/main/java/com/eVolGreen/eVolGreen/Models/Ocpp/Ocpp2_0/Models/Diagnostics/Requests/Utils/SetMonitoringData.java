package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MonitorEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa los datos para configurar el monitoreo de una variable específica en un componente.
 *
 * <p>Esta clase permite definir parámetros como el tipo de monitor, su severidad y las condiciones
 * específicas para el monitoreo.
 */
public class SetMonitoringData {

    /** Datos personalizados adicionales para extender la información del monitoreo. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador del monitor. Este ID se utiliza para reemplazar un monitor existente. Si es
     * nulo, la estación de carga generará un nuevo ID automáticamente.
     */
    @Nullable private Integer id;

    /**
     * Indica si el monitor solo estará activo cuando una transacción esté en curso en un componente
     * relevante. Valor predeterminado: false.
     */
    @Nullable private Boolean transaction;

    /**
     * Valor umbral, delta o intervalo en segundos según el tipo de monitor. Este valor define la
     * condición que activará el evento.
     */
    private Double value;

    /** Tipo de monitor, como umbral, delta o periódico. */
    private MonitorEnum type;

    /**
     * Nivel de severidad asignado a un evento activado por este monitor. Rango: 0 (mayor severidad) a
     * 9 (menor severidad).
     */
    private Integer severity;

    /** Componente físico o lógico asociado al monitoreo. */
    private Component component;

    /** Variable asociada al componente que será monitoreada. */
    private Variable variable;

    /**
     * Constructor de la clase SetMonitoringData.
     *
     * @param value Valor umbral, delta o intervalo en segundos.
     * @param type Tipo de monitor.
     * @param severity Nivel de severidad del evento activado.
     * @param component Componente asociado.
     * @param variable Variable a monitorear.
     */
    public SetMonitoringData(
            Double value, MonitorEnum type, Integer severity, Component component, Variable variable) {
        setValue(value);
        setType(type);
        setSeverity(severity);
        setComponent(component);
        setVariable(variable);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Objeto {@link CustomData} con información adicional.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Objeto {@link CustomData} con información adicional.
     * @throws PropertyConstraintException si los datos no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Objeto {@link CustomData} a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece datos personalizados adicionales de forma fluida.
     *
     * @param customData Objeto {@link CustomData} con información adicional.
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public SetMonitoringData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del monitor.
     *
     * @return ID del monitor o {@code null} si es un nuevo monitor.
     */
    @Nullable
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador del monitor.
     *
     * @param id ID del monitor.
     */
    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    /**
     * Establece el identificador del monitor de forma fluida.
     *
     * @param id ID del monitor.
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public SetMonitoringData withId(@Nullable Integer id) {
        setId(id);
        return this;
    }

    /**
     * Indica si el monitor está activo solo durante transacciones.
     *
     * @return {@code true} si solo está activo durante transacciones, {@code false} en caso
     *     contrario.
     */
    public Boolean getTransaction() {
        return transaction != null ? transaction : false;
    }

    /**
     * Establece si el monitor estará activo solo durante transacciones.
     *
     * @param transaction Valor booleano que indica la condición.
     */
    public void setTransaction(@Nullable Boolean transaction) {
        this.transaction = transaction;
    }

    /**
     * Establece si el monitor estará activo solo durante transacciones de forma fluida.
     *
     * @param transaction Valor booleano que indica la condición.
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public SetMonitoringData withTransaction(@Nullable Boolean transaction) {
        setTransaction(transaction);
        return this;
    }

    /**
     * Obtiene el valor umbral, delta o intervalo del monitor.
     *
     * @return Valor asociado al monitor.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Establece el valor umbral, delta o intervalo del monitor.
     *
     * @param value Valor asociado al monitor.
     * @throws PropertyConstraintException si el valor no es válido.
     */
    public void setValue(Double value) {
        if (!isValidValue(value)) {
            throw new PropertyConstraintException(value, "El valor proporcionado no es válido.");
        }
        this.value = value;
    }

    /**
     * Valida si el valor es válido.
     *
     * @param value Valor a validar.
     * @return {@code true} si el valor es válido, {@code false} en caso contrario.
     */
    private boolean isValidValue(Double value) {
        return value != null;
    }

    /**
     * Establece el valor umbral, delta o intervalo del monitor de forma fluida.
     *
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public MonitorEnum getType() {
        return type;
    }

    /**
     * Establece el tipo de monitor.
     *
     * @param type Tipo de monitor.
     * @throws PropertyConstraintException si el tipo no es válido.
     */
    public void setType(MonitorEnum type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "type is invalid");
        }
        this.type = type;
    }

    /**
     * Valida si el tipo de monitor es válido.
     *
     * @param type Tipo de monitor a validar.
     * @return {@code true} si el tipo es válido, {@code false} en caso contrario.
     */
    private boolean isValidType(MonitorEnum type) {
        return type != null;
    }

    /**
     * Establece el tipo de monitor de forma fluida.
     *
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public Integer getSeverity() {
        return severity;
    }

    /**
     * Establece el nivel de severidad del evento activado.
     *
     * @param severity Nivel de severidad.
     * @throws PropertyConstraintException si el nivel de severidad no es válido.
     */
    public void setSeverity(Integer severity) {
        if (!isValidSeverity(severity)) {
            throw new PropertyConstraintException(severity, "severity is invalid");
        }
        this.severity = severity;
    }

    /**
     * Valida si el nivel de severidad es válido.
     *
     * @param severity Nivel de severidad a validar.
     * @return {@code true} si el nivel de severidad es válido, {@code false} en caso contrario.
     */
    private boolean isValidSeverity(Integer severity) {
        return severity != null;
    }

    /**
     * Establece el nivel de severidad del evento activado de forma fluida.
     *
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente asociado al monitor.
     *
     * @param component Componente asociado.
     * @throws PropertyConstraintException si el componente no es válido.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "component is invalid");
        }
        this.component = component;
    }

    /**
     * Valida si el componente es válido.
     *
     * @param component Componente a validar.
     * @return {@code true} si el componente es válido, {@code false} en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Establece el componente asociado al monitor de forma fluida.
     *
     * @return Instancia actualizada de {@link SetMonitoringData}.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la variable asociada al monitor.
     *
     * @param variable Variable asociada.
     * @throws PropertyConstraintException si la variable no es válida.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "variable is invalid");
        }
        this.variable = variable;
    }

    /**
     * Valida si la variable es válida.
     *
     * @param variable Variable a validar.
     * @return {@code true} si la variable es válida, {@code false} en caso contrario.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidValue(value)
                && isValidType(type)
                && isValidSeverity(severity)
                && isValidComponent(component)
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
        SetMonitoringData that = (SetMonitoringData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(transaction, that.transaction)
                && Objects.equals(value, that.value)
                && Objects.equals(type, that.type)
                && Objects.equals(severity, that.severity)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, id, transaction, value, type, severity, component, variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("transaction", transaction)
                .add("value", value)
                .add("type", type)
                .add("severity", severity)
                .add("component", component)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
