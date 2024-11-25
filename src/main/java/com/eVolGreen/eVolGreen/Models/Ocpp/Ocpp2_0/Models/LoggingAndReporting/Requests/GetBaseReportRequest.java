package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums.ReportBaseEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener un informe base en el contexto de OCPP 2.0.1.
 *
 * <p>Este mensaje se utiliza para solicitar al punto de carga que envíe un informe de acuerdo
 * a una base de datos específica.</p>
 */
public class GetBaseReportRequest extends RequestWithId {
    /** Datos personalizados específicos de la solicitud. */
    @Nullable
    private CustomData customData;

    /** Identificador único de la solicitud. */
    private Integer requestId;

    /** Base del informe solicitado. */
    private ReportBaseEnum reportBase;

    /**
     * Constructor para la clase GetBaseReportRequest.
     *
     * @param requestId Identificador único de la solicitud.
     * @param reportBase Base del informe solicitado.
     */
    public GetBaseReportRequest(Integer requestId, ReportBaseEnum reportBase) {
        setRequestId(requestId);
        setReportBase(reportBase);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados específicos de la solicitud.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados específicos de la solicitud.
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
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados específicos de la solicitud.
     * @return La instancia actual de la solicitud.
     */
    public GetBaseReportRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
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
     * @param requestId Identificador único de la solicitud.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "El identificador de solicitud no es válido.");
        }
        this.requestId = requestId;
    }

    /**
     * Valida si el identificador de solicitud es válido.
     *
     * @param requestId Identificador de solicitud a validar.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene la base del informe solicitado.
     *
     * @return Base del informe solicitado.
     */
    public ReportBaseEnum getReportBase() {
        return reportBase;
    }

    /**
     * Establece la base del informe solicitado.
     *
     * @param reportBase Base del informe solicitado.
     */
    public void setReportBase(ReportBaseEnum reportBase) {
        if (!isValidReportBase(reportBase)) {
            throw new PropertyConstraintException(reportBase, "La base del informe no es válida.");
        }
        this.reportBase = reportBase;
    }

    /**
     * Valida si la base del informe es válida.
     *
     * @param reportBase Base del informe a validar.
     * @return {@code true} si la base del informe es válida, {@code false} en caso contrario.
     */
    private boolean isValidReportBase(ReportBaseEnum reportBase) {
        return reportBase != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidRequestId(requestId)
                && isValidReportBase(reportBase);
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return null;
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
        GetBaseReportRequest that = (GetBaseReportRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(reportBase, that.reportBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, requestId, reportBase);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("requestId", requestId)
                .add("reportBase", reportBase)
                .add("isValid", validate())
                .toString();
    }
}
