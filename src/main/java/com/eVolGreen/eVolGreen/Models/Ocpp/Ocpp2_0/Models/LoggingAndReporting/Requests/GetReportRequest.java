package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.ComponentVariable;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums.ComponentCriterionEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener un reporte detallado de los componentes y variables.
 *
 * <p>Esta clase permite especificar criterios y componentes para generar un reporte detallado de la estación de carga.
 */
public class GetReportRequest extends RequestWithId {

    /** Datos personalizados para extensiones específicas. */
    @Nullable
    private CustomData customData;

    /** Detalle de componentes, variables y atributos a reportar. */
    @Nullable
    private ComponentVariable[] componentVariable;

    /** Identificador único de la solicitud. */
    private Integer requestId;

    /** Criterios para componentes a incluir en el reporte. */
    @Nullable
    private ComponentCriterionEnum[] componentCriteria;

    /**
     * Constructor de la clase GetReportRequest.
     *
     * @param requestId Identificador único de la solicitud.
     */
    public GetReportRequest(Integer requestId) {
        setRequestId(requestId);
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
     * Configura los datos personalizados.
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
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Datos a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados y retorna esta instancia.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public GetReportRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene detalles de componentes, variables y atributos a reportar.
     *
     * @return Detalles de componentes y variables.
     */
    @Nullable
    public ComponentVariable[] getComponentVariable() {
        return componentVariable;
    }

    /**
     * Configura detalles de componentes, variables y atributos a reportar.
     *
     * @param componentVariable Detalles de componentes y variables.
     */
    public void setComponentVariable(@Nullable ComponentVariable[] componentVariable) {
        if (!isValidComponentVariable(componentVariable)) {
            throw new PropertyConstraintException(componentVariable, "componentVariable is invalid");
        }
        this.componentVariable = componentVariable;
    }

    /**
     * Valida si los componentes son correctos.
     *
     * @param componentVariable Componentes a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidComponentVariable(@Nullable ComponentVariable[] componentVariable) {
        return componentVariable == null
                || (componentVariable.length >= 1
                && Arrays.stream(componentVariable).allMatch(item -> item.validate()));
    }

    /**
     * Agrega detalles de componentes y variables a reportar.
     *
     * @param componentVariable Detalles de componentes y variables.
     * @return Esta instancia.
     */
    public GetReportRequest withComponentVariable(@Nullable ComponentVariable[] componentVariable) {
        setComponentVariable(componentVariable);
        return this;
    }

    /**
     * Obtiene el identificador único de la solicitud.
     *
     * @return Identificador único.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Configura el identificador único de la solicitud.
     *
     * @param requestId Identificador único.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId is invalid");
        }
        this.requestId = requestId;
    }

    /**
     * Valida si el identificador de solicitud es correcto.
     *
     * @param requestId Identificador a validar.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene los criterios para los componentes incluidos en el reporte.
     *
     * @return Criterios de los componentes.
     */
    @Nullable
    public ComponentCriterionEnum[] getComponentCriteria() {
        return componentCriteria;
    }

    /**
     * Configura criterios para componentes incluidos en el reporte.
     *
     * @param componentCriteria Criterios de los componentes.
     */
    public void setComponentCriteria(@Nullable ComponentCriterionEnum[] componentCriteria) {
        if (!isValidComponentCriteria(componentCriteria)) {
            throw new PropertyConstraintException(componentCriteria, "componentCriteria is invalid");
        }
        this.componentCriteria = componentCriteria;
    }

    /**
     * Valida si los criterios de componentes son correctos.
     *
     * @param componentCriteria Criterios a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidComponentCriteria(@Nullable ComponentCriterionEnum[] componentCriteria) {
        return componentCriteria == null
                || (componentCriteria.length >= 1 && componentCriteria.length <= 4);
    }

    /**
     * Agrega criterios de componentes al reporte.
     *
     * @param componentCriteria Criterios de componentes.
     * @return Esta instancia.
     */
    public GetReportRequest withComponentCriteria(@Nullable ComponentCriterionEnum[] componentCriteria) {
        setComponentCriteria(componentCriteria);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidComponentVariable(componentVariable)
                && isValidRequestId(requestId)
                && isValidComponentCriteria(componentCriteria);
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
        GetReportRequest that = (GetReportRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(componentVariable, that.componentVariable)
                && Objects.equals(requestId, that.requestId)
                && Arrays.equals(componentCriteria, that.componentCriteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                Arrays.hashCode(componentVariable),
                requestId,
                Arrays.hashCode(componentCriteria));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("componentVariable", componentVariable)
                .add("requestId", requestId)
                .add("componentCriteria", componentCriteria)
                .add("isValid", validate())
                .toString();
    }
}
