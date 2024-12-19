package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums.GetVariableStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.AttributeEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Component;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.Variable;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa el resultado de una solicitud para obtener variables.
 * Contiene información sobre el estado del atributo solicitado, su valor
 * y metadatos adicionales.
 */
public class GetVariableResult {

    /** Datos personalizados asociados al resultado. */
    @Nullable
    private CustomData customData;

    /** Información adicional sobre el estado del atributo. */
    @Nullable
    private StatusInfo attributeStatusInfo;

    /** Estado del atributo solicitado. */
    private GetVariableStatusEnum attributeStatus;

    /** Tipo de atributo solicitado (por defecto Actual si no se especifica). */
    @Nullable
    private AttributeEnum attributeType;

    /**
     * Valor del atributo solicitado. Este campo solo puede ser vacío si el estado
     * del atributo no es aceptado.
     */
    @Nullable private String attributeValue;

    /** Componente físico o lógico asociado al resultado. */
    private Component component;

    /** Variable específica dentro del componente. */
    private Variable variable;

    /**
     * Constructor principal de la clase.
     *
     * @param attributeStatus Estado del atributo solicitado.
     * @param component Componente al que pertenece la variable.
     * @param variable Variable específica dentro del componente.
     */
    public GetVariableResult(
            GetVariableStatusEnum attributeStatus, Component component, Variable variable) {
        setAttributeStatus(attributeStatus);
        setComponent(component);
        setVariable(variable);
    }

    /**
     * Obtiene los datos personalizados asociados al resultado.
     *
     * @return Datos personalizados, o null si no están presentes.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para el resultado.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Datos personalizados a validar.
     * @return true si son válidos, false en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Obtiene información adicional sobre el estado del atributo.
     *
     * @return Información adicional, o null si no está presente.
     */
    @Nullable
    public StatusInfo getAttributeStatusInfo() {
        return attributeStatusInfo;
    }

    /**
     * Establece información adicional sobre el estado del atributo.
     *
     * @param attributeStatusInfo Información adicional.
     * @throws PropertyConstraintException si la información no es válida.
     */
    public void setAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
        if (!isValidAttributeStatusInfo(attributeStatusInfo)) {
            throw new PropertyConstraintException(attributeStatusInfo, "La información adicional no es válida.");
        }
        this.attributeStatusInfo = attributeStatusInfo;
    }

    /**
     * Valida si la información adicional sobre el estado es válida.
     *
     * @param attributeStatusInfo Información adicional a validar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean isValidAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
        return attributeStatusInfo == null || attributeStatusInfo.validate();
    }

    /**
     * Crea una nueva instancia de GetVariableResult con información adicional sobre el estado.
     *
     * @param attributeStatusInfo Información adicional sobre el estado.
     * @return Nueva instancia de GetVariableResult.
     */
    public GetVariableResult withAttributeStatusInfo(@Nullable StatusInfo attributeStatusInfo) {
        setAttributeStatusInfo(attributeStatusInfo);
        return this;
    }

    /**
     * Obtiene el estado del atributo solicitado.
     *
     * @return Estado del atributo.
     */
    public GetVariableStatusEnum getAttributeStatus() {
        return attributeStatus;
    }

    /**
     * Establece el estado del atributo solicitado.
     *
     * @param attributeStatus Estado del atributo.
     * @throws PropertyConstraintException si el estado es nulo.
     */
    public void setAttributeStatus(GetVariableStatusEnum attributeStatus) {
        if (!isValidAttributeStatus(attributeStatus)) {
            throw new PropertyConstraintException(attributeStatus, "El estado del atributo no es válido.");
        }
        this.attributeStatus = attributeStatus;
    }

    /**
     * Valida si el estado del atributo es válido.
     *
     * @param attributeStatus Estado del atributo a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidAttributeStatus(GetVariableStatusEnum attributeStatus) {
        return attributeStatus != null;
    }

    /**
     * Obtiene el tipo de atributo solicitado.
     *
     * @return Tipo de atributo, o Actual si no se especificó.
     */
    public AttributeEnum getAttributeType() {
        return attributeType != null ? attributeType : AttributeEnum.Actual;
    }

    /**
     * Establece el tipo de atributo solicitado.
     *
     * @param attributeType Tipo de atributo.
     */
    public void setAttributeType(@Nullable AttributeEnum attributeType) {
        this.attributeType = attributeType;
    }

    /**
     * Crea una nueva instancia de GetVariableResult con el tipo de atributo especificado.
     *
     * @param attributeType Tipo de atributo.
     * @return Nueva instancia de GetVariableResult.
     */
    public GetVariableResult withAttributeType(@Nullable AttributeEnum attributeType) {
        setAttributeType(attributeType);
        return this;
    }

    /**
     * Obtiene el valor del atributo solicitado.
     *
     * @return Valor del atributo, o null si no aplica.
     */
    @Nullable
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * Establece el valor del atributo solicitado.
     *
     * @param attributeValue Valor del atributo.
     * @throws PropertyConstraintException si el valor es inválido.
     */
    public void setAttributeValue(@Nullable String attributeValue) {
        if (!isValidAttributeValue(attributeValue)) {
            throw new PropertyConstraintException(attributeValue, "El valor del atributo no es válido.");
        }
        this.attributeValue = attributeValue;
    }

    /**
     * Valida si el valor del atributo es válido.
     *
     * @param attributeValue Valor del atributo a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidAttributeValue(@Nullable String attributeValue) {
        if (attributeStatus != GetVariableStatusEnum.Accepted) {
            return attributeValue == null || attributeValue.length() <= 2500;
        } else {
            return attributeValue != null && attributeValue.length() <= 2500;
        }
    }

    /**
     * Crea una nueva instancia de GetVariableResult con el valor del atributo especificado.
     *
     * @param attributeValue Valor del atributo.
     * @return Nueva instancia de GetVariableResult.
     */
    public GetVariableResult withAttributeValue(@Nullable String attributeValue) {
        setAttributeValue(attributeValue);
        return this;
    }

    /**
     * Obtiene el componente asociado al resultado.
     *
     * @return Componente asociado.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Establece el componente asociado al resultado.
     *
     * @param component Componente asociado.
     * @throws PropertyConstraintException si el componente no es válido.
     */
    public void setComponent(Component component) {
        if (!isValidComponent(component)) {
            throw new PropertyConstraintException(component, "El componente no es válido.");
        }
        this.component = component;
    }

    /**
     * Valida si el componente asociado es válido.
     *
     * @param component Componente a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidComponent(Component component) {
        return component != null && component.validate();
    }

    /**
     * Obtiene la variable específica dentro del componente.
     *
     * @return Variable asociada.
     */
    public Variable getVariable() {
        return variable;
    }

    /**
     * Establece la variable específica dentro del componente.
     *
     * @param variable Variable asociada.
     * @throws PropertyConstraintException si la variable no es válida.
     */
    public void setVariable(Variable variable) {
        if (!isValidVariable(variable)) {
            throw new PropertyConstraintException(variable, "La variable no es válida.");
        }
        this.variable = variable;
    }

    /**
     * Valida si la variable asociada es válida.
     *
     * @param variable Variable a validar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean isValidVariable(Variable variable) {
        return variable != null && variable.validate();
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAttributeStatusInfo(attributeStatusInfo)
                && isValidAttributeStatus(attributeStatus)
                && isValidAttributeValue(attributeValue)
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
        GetVariableResult that = (GetVariableResult) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(attributeStatusInfo, that.attributeStatusInfo)
                && Objects.equals(attributeStatus, that.attributeStatus)
                && Objects.equals(attributeType, that.attributeType)
                && Objects.equals(attributeValue, that.attributeValue)
                && Objects.equals(component, that.component)
                && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                attributeStatusInfo,
                attributeStatus,
                attributeType,
                attributeValue,
                component,
                variable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("attributeStatusInfo", attributeStatusInfo)
                .add("attributeStatus", attributeStatus)
                .add("attributeType", attributeType)
                .add("attributeValue", attributeValue)
                .add("component", component)
                .add("variable", variable)
                .add("isValid", validate())
                .toString();
    }
}
