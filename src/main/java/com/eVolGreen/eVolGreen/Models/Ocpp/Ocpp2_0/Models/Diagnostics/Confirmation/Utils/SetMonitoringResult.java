package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums.SetMonitoringStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MonitorEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Component;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Variable;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa los resultados del monitoreo de variables solicitado en un mensaje OCPP.
 * <p>
 * Contiene la información del estado del monitoreo, detalles de la variable, el tipo de monitor,
 * el nivel de severidad y otros datos adicionales relacionados.
 */
public class SetMonitoringResult {

    /**
     * Datos personalizados adicionales que extienden la información del resultado.
     */
    @Nullable
    private CustomData customData;

    /**
     * Identificador único del monitor asignado por la estación de carga.
     * Solo se devuelve cuando el estado es aceptado.
     */
    @Nullable
    private Integer id;

    /**
     * Información adicional sobre el estado del monitoreo.
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Estado del monitoreo, indicando si fue aceptado, rechazado, o cualquier otro motivo.
     */
    private SetMonitoringStatusEnum status;

    /**
     * Tipo de monitor, por ejemplo, umbral, delta o periódico.
     */
    private MonitorEnum type;

    /**
     * Componente físico o lógico relacionado con el monitoreo.
     */
    private Component component;

    /**
     * Variable específica asociada al componente monitoreado.
     */
    private Variable variable;

    /**
     * Nivel de severidad asignado a los eventos disparados por este monitor.
     * Los valores varían entre 0 (mayor severidad) y 9 (menor severidad).
     */
    private Integer severity;

    /**
     * Constructor para la clase SetMonitoringResult.
     *
     * @param status    Estado del monitoreo.
     * @param type      Tipo de monitor.
     * @param component Componente físico o lógico.
     * @param variable  Variable asociada.
     * @param severity  Nivel de severidad del evento.
     */
    public SetMonitoringResult(
            SetMonitoringStatusEnum status,
            MonitorEnum type,
            Component component,
            Variable variable,
            Integer severity) {
        setStatus(status);
        setType(type);
        setComponent(component);
        setVariable(variable);
        setSeverity(severity);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Objeto {@link CustomData} con información adicional o {@code null} si no está definido.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados adicionales.
     *
     * @param customData Objeto {@link CustomData}.
     * @throws PropertyConstraintException si los datos no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Objeto {@link CustomData}.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados de forma fluida.
     *
     * @param customData Objeto {@link CustomData}.
     * @return Esta misma instancia de {@link SetMonitoringResult}.
     */
    public SetMonitoringResult withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único del monitor.
     *
     * @return Identificador único del monitor o {@code null} si no está definido.
     */
    @Nullable
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del monitor.
     *
     * @param id Identificador único del monitor.
     */
    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    /**
     * Obtiene información adicional sobre el estado del monitoreo.
     *
     * @return Objeto {@link StatusInfo} con información adicional o {@code null} si no está definido.
     */
    public SetMonitoringResult withId(@Nullable Integer id) {
        setId(id);
        return this;
    }

    /**
     * Establece información adicional sobre el estado del monitoreo.
     *
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado del monitoreo.
     *
     * @param statusInfo Objeto {@link StatusInfo}.
     * @throws PropertyConstraintException si los datos no son válidos.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "statusInfo is invalid");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Valida la información adicional sobre el estado del monitoreo.
     *
     * @param statusInfo Objeto {@link StatusInfo}.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado del monitoreo de forma fluida.
     *
     * @return Esta misma instancia de {@link SetMonitoringResult}.
     */
    public SetMonitoringStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado del monitoreo.
     *
     * @param status Estado del monitoreo.
     */
    public void setStatus(SetMonitoringStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "status is invalid");
        }
        this.status = status;
    }

    /**
     * Valida el estado del monitoreo.
     *
     * @param status Estado del monitoreo.
     * @return {@code true} si el estado es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(SetMonitoringStatusEnum status) {
        return status != null;
    }

    /**
     * Agrega el estado del monitoreo de forma fluida.
     *
     * @return Esta misma instancia de {@link SetMonitoringResult}.
     */
    public MonitorEnum getType() {
        return type;
    }

    /**
     * Establece el tipo de monitor.
     *
     * @param type Tipo de monitor.
     */
    public void setType(MonitorEnum type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "type is invalid");
        }
        this.type = type;
    }

    /**
     * Valida el tipo de monitor.
     *
     * @param type Tipo de monitor.
     * @return {@code true} si el tipo es válido, de lo contrario {@code false}.
     */
    private boolean isValidType(MonitorEnum type) {
        return type != null;
    }

    /**
     * Agrega el tipo de monitor de forma fluida.
     *
     * @return Esta misma instancia de {@link SetMonitoringResult}.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente físico o lógico relacionado con el monitoreo.
     *
     * @param component Componente físico o lógico.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "component is invalid");
        }
        this.component = component;
    }

    /**
     * Valida el componente físico o lógico.
     *
     * @param component Componente físico o lógico.
     * @return {@code true} si el componente es válido, de lo contrario {@code false}.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Agrega el componente físico o lógico de forma fluida.
     *
     * @return Esta misma instancia de {@link SetMonitoringResult}.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la variable específica asociada al componente monitoreado.
     *
     * @param variable Variable asociada.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "variable is invalid");
        }
        this.variable = variable;
    }

    /**
     * Valida la variable asociada.
     *
     * @param variable Variable asociada.
     * @return {@code true} si la variable es válida, de lo contrario {@code false}.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Agrega la variable asociada de forma fluida.
     *
     * @return Esta misma instancia de {@link SetMonitoringResult}.
     */
    public Integer getSeverity() {
        return severity;
    }

    /**
     * Establece el nivel de severidad asignado a los eventos disparados por este monitor.
     *
     * @param severity Nivel de severidad del evento.
     */
    public void setSeverity(Integer severity) {
        if (!isValidSeverity(severity)) {
            throw new PropertyConstraintException(severity, "severity is invalid");
        }
        this.severity = severity;
    }

    /**
     * Valida el nivel de severidad.
     *
     * @param severity Nivel de severidad del evento.
     * @return {@code true} si el nivel de severidad es válido, de lo contrario {@code false}.
     */
    private boolean isValidSeverity(Integer severity) {
        return severity != null;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatusInfo(statusInfo)
                && isValidStatus(status)
                && isValidType(type)
                && isValidComponent(component)
                && isValidVariable(variable)
                && isValidSeverity(severity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetMonitoringResult that = (SetMonitoringResult) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(status, that.status)
                && Objects.equals(type, that.type)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable)
                && Objects.equals(severity, that.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, id, statusInfo, status, type, component, variable, severity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("statusInfo", statusInfo)
                .add("status", status)
                .add("type", type)
                .add("component", component)
                .add("variable", variable)
                .add("severity", severity)
                .add("isValid", validate())
                .toString();
    }
}

