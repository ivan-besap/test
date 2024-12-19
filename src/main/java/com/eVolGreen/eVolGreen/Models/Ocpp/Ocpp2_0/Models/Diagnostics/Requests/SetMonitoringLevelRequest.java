package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para establecer el nivel de monitoreo en la estación de carga.
 *
 * <p>La estación de carga solo reportará eventos con un nivel de severidad menor o igual al nivel especificado.
 */
public class SetMonitoringLevelRequest extends RequestWithId {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Nivel de severidad máxima de eventos a reportar.
     *
     * <p>Rango permitido: 0 (máxima severidad) a 9 (mínima severidad). Los niveles tienen los siguientes significados:
     * <ul>
     *   <li><b>0 - Peligro</b>: Riesgo para la vida. Atención urgente requerida.</li>
     *   <li><b>1 - Fallo de hardware</b>: Imposible continuar operaciones regulares debido a fallos físicos. Acción requerida.</li>
     *   <li><b>2 - Fallo del sistema</b>: Imposible continuar operaciones regulares debido a problemas de software o hardware menor. Acción requerida.</li>
     *   <li><b>3 - Crítico</b>: Error crítico. Acción requerida.</li>
     *   <li><b>4 - Error</b>: Error no urgente. Acción requerida.</li>
     *   <li><b>5 - Alerta</b>: Evento de alerta. Severidad predeterminada para eventos de monitoreo.</li>
     *   <li><b>6 - Advertencia</b>: Advertencia. Puede requerir acción.</li>
     *   <li><b>7 - Aviso</b>: Evento inusual. No se requiere acción inmediata.</li>
     *   <li><b>8 - Informativo</b>: Evento regular. Para reportes o métricas. No requiere acción.</li>
     *   <li><b>9 - Depuración</b>: Información útil para desarrolladores. No es relevante para operaciones.</li>
     * </ul>
     */
    private Integer severity;

    /**
     * Constructor de la clase SetMonitoringLevelRequest.
     *
     * @param severity Nivel de severidad máxima de eventos a reportar.
     */
    public SetMonitoringLevelRequest(Integer severity) {
        setSeverity(severity);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados o {@code null}.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados o {@code null}.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados no válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos; {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia actualizada.
     */
    public SetMonitoringLevelRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el nivel de severidad máxima.
     *
     * @return Nivel de severidad máxima.
     */
    public Integer getSeverity() {
        return severity;
    }

    /**
     * Establece el nivel de severidad máxima.
     *
     * @param severity Nivel de severidad máxima.
     */
    public void setSeverity(Integer severity) {
        if (!isValidSeverity(severity)) {
            throw new PropertyConstraintException(severity, "Nivel de severidad no válido.");
        }
        this.severity = severity;
    }

    /**
     * Verifica si el nivel de severidad es válido.
     *
     * @param severity Nivel de severidad a validar.
     * @return {@code true} si es válido; {@code false} en caso contrario.
     */
    private boolean isValidSeverity(Integer severity) {
        return severity != null && severity >= 0 && severity <= 9;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidSeverity(severity);
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
        SetMonitoringLevelRequest that = (SetMonitoringLevelRequest) o;
        return Objects.equals(customData, that.customData) && Objects.equals(severity, that.severity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, severity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("severity", severity)
                .add("isValid", validate())
                .toString();
    }
}
