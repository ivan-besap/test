package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * NotifyCustomerInformationRequest
 *
 * <p>Notifica información específica relacionada con el cliente, como datos de costos o energía.
 *
 * <p>Este mensaje es parte de la especificación OCPP 2.0.1.
 */
public class NotifyCustomerInformationRequest extends RequestWithId {
    /** Datos personalizados específicos para el mensaje. */
    @Nullable
    private CustomData customData;

    /**
     * Parte de los datos solicitados. No se especifica el formato en que se devuelven los datos. Deben
     * ser legibles para humanos.
     */
    private String data;

    /**
     * Indicador "continuará". Indica si otra parte de los datos sigue en un próximo mensaje
     * NotifyMonitoringReportRequest. Valor predeterminado: false.
     */
    @Nullable private Boolean tbc;

    /** Número de secuencia de este mensaje. El primer mensaje comienza en 0. */
    private Integer seqNo;

    /** Marca de tiempo del momento en que se generó este mensaje en la estación de carga. */
    private ZonedDateTime generatedAt;

    /** El identificador de la solicitud. */
    private Integer requestId;

    /**
     * Constructor para la clase NotifyCustomerInformationRequest
     *
     * @param data Parte de los datos solicitados. Deben ser legibles para humanos.
     * @param seqNo Número de secuencia de este mensaje. El primer mensaje comienza en 0.
     * @param generatedAt Marca de tiempo del momento en que se generó este mensaje en la estación de
     *     carga.
     * @param requestId El identificador de la solicitud.
     */
    public NotifyCustomerInformationRequest(
            String data, Integer seqNo, ZonedDateTime generatedAt, Integer requestId) {
        setData(data);
        setSeqNo(seqNo);
        setGeneratedAt(generatedAt);
        setRequestId(requestId);
    }

    /**
     * Obtiene los datos personalizados específicos para el mensaje.
     *
     * @return Datos personalizados
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados específicos para el mensaje.
     *
     * @param customData Datos personalizados
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Retorna si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar
     * @return {@code true} si son válidos, {@code false} en caso contrario
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados
     * @return Esta instancia
     */
    public NotifyCustomerInformationRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene parte de los datos solicitados. Deben ser legibles para humanos.
     *
     * @return Parte de los datos solicitados
     */
    public String getData() {
        return data;
    }

    /**
     * Establece parte de los datos solicitados. Deben ser legibles para humanos.
     *
     * @param data Parte de los datos solicitados
     */
    public void setData(String data) {
        if (!isValidData(data)) {
            throw new PropertyConstraintException(data, "data es inválido");
        }
        this.data = data;
    }

    /**
     * Retorna si los datos son válidos.
     *
     * @param data Datos a validar
     * @return {@code true} si son válidos, {@code false} en caso contrario
     */
    private boolean isValidData(String data) {
        return data != null && data.length() <= 512;
    }

    /**
     * Obtiene el indicador "continuará".
     *
     * @return Indicador "continuará"
     */
    public Boolean getTbc() {
        return tbc != null ? tbc : false;
    }

    /**
     * Establece el indicador "continuará".
     *
     * @param tbc Indicador "continuará"
     */
    public void setTbc(@Nullable Boolean tbc) {
        this.tbc = tbc;
    }

    /**
     * Obtiene el número de secuencia de este mensaje.
     *
     * @return Número de secuencia
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Obtiene la marca de tiempo del momento en que se generó este mensaje en la estación de carga.
     *
     * @return Marca de tiempo
     */
    public NotifyCustomerInformationRequest withTbc(@Nullable Boolean tbc) {
        setTbc(tbc);
        return this;
    }

    /**
     * Establece el número de secuencia de este mensaje.
     *
     * @param seqNo Número de secuencia
     */
    public void setSeqNo(Integer seqNo) {
        if (!isValidSeqNo(seqNo)) {
            throw new PropertyConstraintException(seqNo, "seqNo es inválido");
        }
        this.seqNo = seqNo;
    }

    /**
     * Retorna si el número de secuencia es válido.
     *
     * @param seqNo Número de secuencia a validar
     * @return {@code true} si es válido, {@code false} en caso contrario
     */
    private boolean isValidSeqNo(Integer seqNo) {
        return seqNo != null;
    }

    /**
     * Obtiene la marca de tiempo del momento en que se generó este mensaje en la estación de carga.
     *
     * @return Marca de tiempo
     */
    public ZonedDateTime getGeneratedAt() {
        return generatedAt;
    }

    /**
     * Establece la marca de tiempo del momento en que se generó este mensaje en la estación de carga.
     *
     * @param generatedAt Marca de tiempo
     */
    public void setGeneratedAt(ZonedDateTime generatedAt) {
        if (!isValidGeneratedAt(generatedAt)) {
            throw new PropertyConstraintException(generatedAt, "generatedAt es inválido");
        }
        this.generatedAt = generatedAt;
    }

    /**
     * Retorna si la marca de tiempo es válida.
     *
     * @param generatedAt Marca de tiempo a validar
     * @return {@code true} si es válida, {@code false} en caso contrario
     */
    private boolean isValidGeneratedAt(ZonedDateTime generatedAt) {
        return generatedAt != null;
    }

    /**
     * Obtiene el identificador de la solicitud.
     *
     * @return Identificador de la solicitud
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador de la solicitud.
     *
     * @param requestId Identificador de la solicitud
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId es inválido");
        }
        this.requestId = requestId;
    }

    /**
     * Retorna si el identificador de la solicitud es válido.
     *
     * @param requestId Identificador de la solicitud a validar
     * @return {@code true} si es válido, {@code false} en caso contrario
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidData(data)
                && isValidSeqNo(seqNo)
                && isValidGeneratedAt(generatedAt)
                && isValidRequestId(requestId);
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
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("data", data)
                .add("tbc", tbc)
                .add("seqNo", seqNo)
                .add("generatedAt", generatedAt)
                .add("requestId", requestId)
                .add("isValid", validate())
                .toString();
    }
}
