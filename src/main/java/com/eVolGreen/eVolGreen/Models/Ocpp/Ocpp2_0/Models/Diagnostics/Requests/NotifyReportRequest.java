package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.ReportData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Notificación de Informe (NotifyReportRequest).
 * <p>
 * Clase que representa un mensaje de solicitud en el protocolo OCPP 2.0.1 para notificar
 * al CSMS sobre un informe generado por la estación de carga. Incluye detalles como el
 * momento de generación, datos del informe y el estado de continuación.
 * </p>
 */
public class NotifyReportRequest extends RequestWithId {

    /** Datos personalizados asociados a la solicitud. */
    @Nullable
    private CustomData customData;

    /** ID del informe solicitado previamente mediante GetReportRequest o GetBaseReportRequest. */
    private Integer requestId;

    /** Momento en que se generó este mensaje en la estación de carga. */
    private ZonedDateTime generatedAt;

    /** Datos del informe que describen componentes, variables y sus características. */
    @Nullable
    private ReportData[] reportData;

    /**
     * Indicador de continuación. Muestra si existe otra parte del informe
     * que se enviará en un mensaje NotifyReportRequest futuro.
     * Valor por defecto: {@code false}.
     */
    @Nullable
    private Boolean tbc;

    /** Número de secuencia de este mensaje. El primer mensaje comienza en 0. */
    private Integer seqNo;

    /**
     * Constructor de la clase NotifyReportRequest.
     *
     * @param requestId ID de la solicitud que generó este informe.
     * @param generatedAt Momento en que se generó el informe en la estación de carga.
     * @param seqNo Número de secuencia del mensaje.
     */
    public NotifyReportRequest(Integer requestId, ZonedDateTime generatedAt, Integer seqNo) {
        setRequestId(requestId);
        setGeneratedAt(generatedAt);
        setSeqNo(seqNo);
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
            throw new PropertyConstraintException(customData, "customData es inválido.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
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
    public NotifyReportRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el ID de la solicitud que generó este informe.
     *
     * @return ID de la solicitud.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el ID de la solicitud que generó este informe.
     *
     * @param requestId ID de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId is invalid");
        }
        this.requestId = requestId;
    }

    /**
     * Valida el ID de la solicitud.
     *
     * @param requestId ID de la solicitud a validar.
     * @return {@code true} si el ID es válido, de lo contrario {@code false}.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene el momento en que se generó el mensaje en la estación de carga.
     *
     * @return Momento de generación.
     */
    public ZonedDateTime getGeneratedAt() {
        return generatedAt;
    }

    /**
     * Establece el momento en que se generó el mensaje en la estación de carga.
     *
     * @param generatedAt Momento de generación.
     */
    public void setGeneratedAt(ZonedDateTime generatedAt) {
        if (!isValidGeneratedAt(generatedAt)) {
            throw new PropertyConstraintException(generatedAt, "generatedAt is invalid");
        }
        this.generatedAt = generatedAt;
    }

    /**
     * Valida el momento de generación.
     *
     * @param generatedAt Momento de generación a validar.
     * @return {@code true} si el momento es válido, de lo contrario {@code false}.
     */
    private boolean isValidGeneratedAt(ZonedDateTime generatedAt) {
        return generatedAt != null;
    }

    /**
     * Obtiene los datos del informe.
     *
     * @return Datos del informe.
     */
    @Nullable
    public ReportData[] getReportData() {
        return reportData;
    }

    /**
     * Establece los datos del informe.
     *
     * @param reportData Datos del informe.
     */
    public void setReportData(@Nullable ReportData[] reportData) {
        if (!isValidReportData(reportData)) {
            throw new PropertyConstraintException(reportData, "reportData is invalid");
        }
        this.reportData = reportData;
    }

    /**
     * Valida los datos del informe.
     *
     * @param reportData Datos del informe a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidReportData(@Nullable ReportData[] reportData) {
        return reportData == null
                || (reportData.length >= 1 && Arrays.stream(reportData).allMatch(item -> item.validate()));
    }

    /**
     * Añade datos del informe.
     *
     * @param reportData Datos del informe.
     * @return Esta instancia.
     */
    public NotifyReportRequest withReportData(@Nullable ReportData[] reportData) {
        setReportData(reportData);
        return this;
    }

    /**
     * Obtiene el indicador de continuación.
     *
     * @return {@code true} si se espera otro mensaje, de lo contrario {@code false}.
     */
    public Boolean getTbc() {
        return tbc != null && tbc;
    }

    /**
     * Establece el indicador de continuación.
     *
     * @param tbc Indicador de continuación.
     */
    public void setTbc(@Nullable Boolean tbc) {
        this.tbc = tbc;
    }

    /**
     * Añade el indicador de continuación.
     *
     * @param tbc Indicador de continuación.
     * @return Esta instancia.
     */
    public NotifyReportRequest withTbc(@Nullable Boolean tbc) {
        setTbc(tbc);
        return this;
    }

    /**
     * Obtiene el número de secuencia del mensaje.
     *
     * @return Número de secuencia.
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Establece el número de secuencia del mensaje.
     *
     * @param seqNo Número de secuencia.
     */
    public void setSeqNo(Integer seqNo) {
        if (!isValidSeqNo(seqNo)) {
            throw new PropertyConstraintException(seqNo, "seqNo is invalid");
        }
        this.seqNo = seqNo;
    }

    /**
     * Valida el número de secuencia.
     *
     * @param seqNo Número de secuencia a validar.
     * @return {@code true} si el número es válido, de lo contrario {@code false}.
     */
    private boolean isValidSeqNo(Integer seqNo) {
        return seqNo != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidRequestId(requestId)
                && isValidGeneratedAt(generatedAt)
                && isValidReportData(reportData)
                && isValidSeqNo(seqNo);
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
        NotifyReportRequest that = (NotifyReportRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(generatedAt, that.generatedAt)
                && Arrays.equals(reportData, that.reportData)
                && Objects.equals(tbc, that.tbc)
                && Objects.equals(seqNo, that.seqNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, requestId, generatedAt, Arrays.hashCode(reportData), tbc, seqNo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("requestId", requestId)
                .add("generatedAt", generatedAt)
                .add("reportData", reportData)
                .add("tbc", tbc)
                .add("seqNo", seqNo)
                .add("isValid", validate())
                .toString();
    }
}
