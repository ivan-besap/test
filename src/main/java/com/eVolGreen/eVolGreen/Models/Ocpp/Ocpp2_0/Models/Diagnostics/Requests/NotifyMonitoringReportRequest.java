package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.MonitoringData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Informe de Monitoreo.
 *
 * <p>Esta clase representa la solicitud de notificación de un informe de monitoreo generado por una estación de carga.
 */
public class NotifyMonitoringReportRequest extends RequestWithId {

    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Parámetros del monitoreo definidos en una solicitud SetVariableMonitoring. */
    @Nullable
    private MonitoringData[] monitor;

    /** ID de la solicitud GetMonitoringRequest que generó este informe. */
    private Integer requestId;

    /**
     * Indicador "por continuar". Indica si otra parte de los datos de monitoreo sigue en un próximo
     * mensaje NotifyMonitoringReportRequest. Valor predeterminado: false.
     */
    @Nullable
    private Boolean tbc;

    /** Número de secuencia de este mensaje. El primer mensaje empieza en 0. */
    private Integer seqNo;

    /** Marca de tiempo en que se generó este mensaje en la estación de carga. */
    private ZonedDateTime generatedAt;

    /**
     * Constructor de la clase NotifyMonitoringReportRequest.
     *
     * @param requestId ID de la solicitud GetMonitoringRequest que generó este informe.
     * @param seqNo Número de secuencia de este mensaje. El primer mensaje empieza en 0.
     * @param generatedAt Marca de tiempo en que se generó este mensaje en la estación de carga.
     */
    public NotifyMonitoringReportRequest(
            Integer requestId, Integer seqNo, ZonedDateTime generatedAt) {
        setRequestId(requestId);
        setSeqNo(seqNo);
        setGeneratedAt(generatedAt);
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
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} si no.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public NotifyMonitoringReportRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los parámetros del monitoreo definidos en una solicitud SetVariableMonitoring.
     *
     * @return Parámetros del monitoreo.
     */
    @Nullable
    public MonitoringData[] getMonitor() {
        return monitor;
    }

    /**
     * Establece los parámetros del monitoreo definidos en una solicitud SetVariableMonitoring.
     *
     * @param monitor Parámetros del monitoreo.
     */
    public void setMonitor(@Nullable MonitoringData[] monitor) {
        if (!isValidMonitor(monitor)) {
            throw new PropertyConstraintException(monitor, "El monitoreo especificado es inválido.");
        }
        this.monitor = monitor;
    }

    /**
     * Verifica si los parámetros del monitoreo son válidos.
     *
     * @param monitor Parámetros del monitoreo a validar.
     * @return {@code true} si son válidos, {@code false} si no.
     */
    private boolean isValidMonitor(@Nullable MonitoringData[] monitor) {
        return monitor == null
                || (monitor.length >= 1 && Arrays.stream(monitor).allMatch(item -> item.validate()));
    }

    /**
     * Agrega parámetros del monitoreo.
     *
     * @param monitor Parámetros del monitoreo.
     * @return Esta instancia.
     */
    public NotifyMonitoringReportRequest withMonitor(@Nullable MonitoringData[] monitor) {
        setMonitor(monitor);
        return this;
    }

    /**
     * Obtiene el ID de la solicitud GetMonitoringRequest que generó este informe.
     *
     * @return ID de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el ID de la solicitud GetMonitoringRequest que generó este informe.
     *
     * @param requestId ID de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "El ID de la solicitud es inválido.");
        }
        this.requestId = requestId;
    }

    /**
     * Verifica si el ID de la solicitud es válido.
     *
     * @param requestId ID de la solicitud a validar.
     * @return {@code true} si es válido, {@code false} si no.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene el indicador "por continuar".
     *
     * @return Indicador "por continuar".
     */
    public Boolean getTbc() {
        return tbc != null ? tbc : false;
    }

    /**
     * Establece el indicador "por continuar".
     *
     * @param tbc Indicador "por continuar".
     */
    public void setTbc(@Nullable Boolean tbc) {
        this.tbc = tbc;
    }

    /**
     * Agrega el indicador "por continuar".
     *
     * @param tbc Indicador "por continuar".
     * @return Esta instancia.
     */
    public NotifyMonitoringReportRequest withTbc(@Nullable Boolean tbc) {
        setTbc(tbc);
        return this;
    }

    /**
     * Obtiene el número de secuencia de este mensaje.
     *
     * @return Número de secuencia.
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Establece el número de secuencia de este mensaje.
     *
     * @param seqNo Número de secuencia.
     */
    public void setSeqNo(Integer seqNo) {
        if (!isValidSeqNo(seqNo)) {
            throw new PropertyConstraintException(seqNo, "El número de secuencia es inválido.");
        }
        this.seqNo = seqNo;
    }

    /**
     * Verifica si el número de secuencia es válido.
     *
     * @param seqNo Número de secuencia a validar.
     * @return {@code true} si es válido, {@code false} si no.
     */
    private boolean isValidSeqNo(Integer seqNo) {
        return seqNo != null;
    }

    /**
     * Obtiene la marca de tiempo en que se generó este mensaje.
     *
     * @return Marca de tiempo.
     */
    public ZonedDateTime getGeneratedAt() {
        return generatedAt;
    }

    /**
     * Establece la marca de tiempo en que se generó este mensaje.
     *
     * @param generatedAt Marca de tiempo.
     */
    public void setGeneratedAt(ZonedDateTime generatedAt) {
        if (!isValidGeneratedAt(generatedAt)) {
            throw new PropertyConstraintException(generatedAt, "La marca de tiempo es inválida.");
        }
        this.generatedAt = generatedAt;
    }

    /**
     * Verifica si la marca de tiempo es válida.
     *
     * @param generatedAt Marca de tiempo a validar.
     * @return {@code true} si es válida, {@code false} si no.
     */
    private boolean isValidGeneratedAt(ZonedDateTime generatedAt) {
        return generatedAt != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidMonitor(monitor)
                && isValidRequestId(requestId)
                && isValidSeqNo(seqNo)
                && isValidGeneratedAt(generatedAt);
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
        NotifyMonitoringReportRequest that = (NotifyMonitoringReportRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(monitor, that.monitor)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(tbc, that.tbc)
                && Objects.equals(seqNo, that.seqNo)
                && Objects.equals(generatedAt, that.generatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(monitor), requestId, tbc, seqNo, generatedAt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("monitor", monitor)
                .add("requestId", requestId)
                .add("tbc", tbc)
                .add("seqNo", seqNo)
                .add("generatedAt", generatedAt)
                .add("isValid", validate())
                .toString();
    }
}
