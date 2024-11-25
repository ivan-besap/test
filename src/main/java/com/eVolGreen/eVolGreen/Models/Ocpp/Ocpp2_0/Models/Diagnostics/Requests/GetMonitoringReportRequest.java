package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MonitoringCriterionEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.ComponentVariable;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener un informe de monitoreo de componentes y variables de una
 * estación de carga.
 *
 * <p>Este mensaje forma parte del estándar OCPP 2.0.1 y permite especificar qué componentes,
 * variables o criterios deben incluirse en el informe.
 */
public class GetMonitoringReportRequest extends RequestWithId {

    /** Datos personalizados para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Lista de componentes y variables a incluir en el informe. */
    @Nullable private ComponentVariable[] componentVariable;

    /** Identificador único de la solicitud. */
    private Integer requestId;

    /**
     * Criterios para especificar qué componentes incluir en el informe de monitoreo, como errores,
     * límites, etc.
     */
    @Nullable private MonitoringCriterionEnum[] monitoringCriteria;

    /**
     * Constructor principal de la clase.
     *
     * @param requestId Identificador único de la solicitud.
     */
    public GetMonitoringReportRequest(Integer requestId) {
        setRequestId(requestId);
    }

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return La solicitud actualizada.
     */
    public GetMonitoringReportRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los componentes y variables que se incluirán en el informe.
     *
     * @return Lista de componentes y variables o {@code null} si no están definidos.
     */
    @Nullable
    public ComponentVariable[] getComponentVariable() {
        return componentVariable;
    }

    /**
     * Establece los componentes y variables que se incluirán en el informe.
     *
     * @param componentVariable Lista de componentes y variables.
     * @throws PropertyConstraintException si los componentes no son válidos.
     */
    public void setComponentVariable(@Nullable ComponentVariable[] componentVariable) {
        if (!isValidComponentVariable(componentVariable)) {
            throw new PropertyConstraintException(componentVariable, "Los componentes no son válidos");
        }
        this.componentVariable = componentVariable;
    }

    /**
     * Verifica si la lista de componentes y variables es válida.
     *
     * @param componentVariable Lista de componentes a validar.
     * @return {@code true} si la lista es válida o {@code false} en caso contrario.
     */
    private boolean isValidComponentVariable(@Nullable ComponentVariable[] componentVariable) {
        return componentVariable == null
                || (componentVariable.length >= 1
                && Arrays.stream(componentVariable).allMatch(item -> item.validate()));
    }

    /**
     * Añade componentes y variables a la solicitud.
     *
     * @param componentVariable Lista de componentes y variables.
     * @return La solicitud actualizada.
     */
    public GetMonitoringReportRequest withComponentVariable(
            @Nullable ComponentVariable[] componentVariable) {
        setComponentVariable(componentVariable);
        return this;
    }

    /**
     * Obtiene el identificador único de la solicitud.
     *
     * @return Identificador único de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador único de la solicitud.
     *
     * @param requestId Identificador único.
     * @throws PropertyConstraintException si el identificador no es válido.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "El identificador no es válido");
        }
        this.requestId = requestId;
    }

    /**
     * Verifica si el identificador único es válido.
     *
     * @param requestId Identificador a validar.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene los criterios de monitoreo solicitados.
     *
     * @return Lista de criterios de monitoreo o {@code null} si no están definidos.
     */
    @Nullable
    public MonitoringCriterionEnum[] getMonitoringCriteria() {
        return monitoringCriteria;
    }

    /**
     * Establece los criterios de monitoreo solicitados.
     *
     * @param monitoringCriteria Lista de criterios.
     * @throws PropertyConstraintException si los criterios no son válidos.
     */
    public void setMonitoringCriteria(@Nullable MonitoringCriterionEnum[] monitoringCriteria) {
        if (!isValidMonitoringCriteria(monitoringCriteria)) {
            throw new PropertyConstraintException(
                    monitoringCriteria, "Los criterios de monitoreo no son válidos");
        }
        this.monitoringCriteria = monitoringCriteria;
    }

    /**
     * Verifica si los criterios de monitoreo son válidos.
     *
     * @param monitoringCriteria Lista de criterios a validar.
     * @return {@code true} si son válidos o {@code false} en caso contrario.
     */
    private boolean isValidMonitoringCriteria(
            @Nullable MonitoringCriterionEnum[] monitoringCriteria) {
        return monitoringCriteria == null
                || (monitoringCriteria.length >= 1 && monitoringCriteria.length <= 3);
    }

    /**
     * Añade criterios de monitoreo a la solicitud.
     *
     * @param monitoringCriteria Lista de criterios.
     * @return La solicitud actualizada.
     */
    public GetMonitoringReportRequest withMonitoringCriteria(
            @Nullable MonitoringCriterionEnum[] monitoringCriteria) {
        setMonitoringCriteria(monitoringCriteria);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidComponentVariable(componentVariable)
                && isValidRequestId(requestId)
                && isValidMonitoringCriteria(monitoringCriteria);
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
        GetMonitoringReportRequest that = (GetMonitoringReportRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(componentVariable, that.componentVariable)
                && Objects.equals(requestId, that.requestId)
                && Arrays.equals(monitoringCriteria, that.monitoringCriteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                Arrays.hashCode(componentVariable),
                requestId,
                Arrays.hashCode(monitoringCriteria));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("componentVariable", componentVariable)
                .add("requestId", requestId)
                .add("monitoringCriteria", monitoringCriteria)
                .add("isValid", validate())
                .toString();
    }
}

