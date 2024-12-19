package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums.SetVariableStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.AttributeEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Component;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Variable;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Resultado de establecer una variable en la estación de carga.
 *
 * <p>Esta clase encapsula el estado y los detalles adicionales del intento de modificar una variable.
 */
public class SetVariableResult {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Tipo del atributo: Actual, Target, MinSet, MaxSet. Por defecto es Actual si no se especifica. */
    @Nullable private AttributeEnum attributeType;

    /** Estado del resultado al intentar establecer la variable. */
    private SetVariableStatusEnum attributeStatus;

    /** Información adicional sobre el estado. */
    @Nullable private StatusInfo attributeStatusInfo;

    /** Componente físico o lógico. */
    private Component component;

    /** Clave de referencia para una variable del componente. */
    private Variable variable;

    /**
     * Constructor para la clase SetVariableResult.
     *
     * @param attributeStatus Estado del resultado al intentar establecer la variable.
     * @param component Componente físico o lógico.
     * @param variable Clave de referencia para una variable del componente.
     */
    public SetVariableResult(
            SetVariableStatusEnum attributeStatus, Component component, Variable variable) {
        setAttributeStatus(attributeStatus);
        setComponent(component);
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
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
     * Añade datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Instancia actual de SetVariableResult.
     */
    public SetVariableResult withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo del atributo.
     *
     * @return Tipo del atributo.
     */
    public AttributeEnum getAttributeType() {
        return attributeType != null ? attributeType : AttributeEnum.Actual;
    }

    /**
     * Establece el tipo del atributo.
     *
     * @param attributeType Tipo del atributo.
     */
    public void setAttributeType(@Nullable AttributeEnum attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * Añade el tipo del atributo.
     *
     * @param attributeType Tipo del atributo.
     * @return Instancia actual de SetVariableResult.
     */
    public SetVariableResult withAttributeType(@Nullable AttributeEnum attributeType) {
        setAttributeType(attributeType);
        return this;
    }

    /**
     * Obtiene el estado del resultado al intentar establecer la variable.
     *
     * @return Estado del resultado.
     */
    public SetVariableStatusEnum getAttributeStatus() {
        return attributeStatus;
    }

    /**
     * Establece el estado del resultado al intentar establecer la variable.
     *
     * @param attributeStatus Estado del resultado.
     */
    public void setAttributeStatus(SetVariableStatusEnum attributeStatus) {
        if (!isValidAttributeStatus(attributeStatus)) {
            throw new PropertyConstraintException(attributeStatus, "El estado del atributo no es válido.");
        }
        this.attributeStatus = attributeStatus;
    }

    /**
     * Valida si el estado del atributo es válido.
     *
     * @param attributeStatus Estado del atributo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidAttributeStatus(SetVariableStatusEnum attributeStatus) {
        return attributeStatus != null;
    }

    /**
     * Obtiene información adicional sobre el estado.
     *
     * @return Información adicional sobre el estado.
     */
    @Nullable
    public StatusInfo getAttributeStatusInfo() {
        return attributeStatusInfo;
    }

    /**
     * Establece información adicional sobre el estado.
     *
     * @param attributeStatusInfo Información adicional sobre el estado.
     */
    public void setAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
        if (!isValidAttributeStatusInfo(attributeStatusInfo)) {
            throw new PropertyConstraintException(
                    attributeStatusInfo, "La información de estado no es válida.");
        }
        this.attributeStatusInfo = attributeStatusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param attributeStatusInfo Información adicional a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
        return attributeStatusInfo == null || attributeStatusInfo.validate();
    }

    /**
     * Añade información adicional sobre el estado.
     *
     * @param attributeStatusInfo Información adicional sobre el estado.
     * @return Instancia actual de SetVariableResult.
     */
    public SetVariableResult withAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
        setAttributeStatusInfo(attributeStatusInfo);
        return this;
    }

    /**
     * Obtiene el componente físico o lógico.
     *
     * @return Componente físico o lógico.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente físico o lógico.
     *
     * @param component Componente físico o lógico.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "El componente no es válido.");
        }
        this.component = component;
    }

    /**
     * Valida si el componente es válido.
     *
     * @param component Componente a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la clave de referencia para una variable del componente.
     *
     * @return Clave de referencia.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la clave de referencia para una variable del componente.
     *
     * @param variable Clave de referencia.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "La variable no es válida.");
        }
        this.variable = variable;
    }

    /**
     * Valida si la variable es válida.
     *
     * @param variable Variable a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    /**
     * Valida si el objeto SetVariableResult es válido.
     *
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAttributeStatus(attributeStatus)
                && isValidAttributeStatusInfo(attributeStatusInfo)
                && isValidComponent(component)
                && isValidVariable(variable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetVariableResult that = (SetVariableResult) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(attributeType, that.attributeType)
                && attributeStatus == that.attributeStatus
                && Objects.equals(attributeStatusInfo, that.attributeStatusInfo)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, attributeType, attributeStatus, attributeStatusInfo, component, variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("attributeType", attributeType)
                .add("attributeStatus", attributeStatus)
                .add("attributeStatusInfo", attributeStatusInfo)
                .add("component", component)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
