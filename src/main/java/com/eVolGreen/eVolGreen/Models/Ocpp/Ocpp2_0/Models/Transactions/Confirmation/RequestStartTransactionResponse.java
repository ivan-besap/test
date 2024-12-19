package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.Enums.RequestStartStopStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa la respuesta a una solicitud para iniciar una transacción de carga.
 *
 * <p>Esta clase incluye detalles sobre si la estación de carga acepta la solicitud para iniciar
 * una transacción, información adicional sobre el estado y, en caso de que ya exista una transacción activa,
 * el identificador de dicha transacción.
 */
public class RequestStartTransactionResponse extends Confirmation {
    /** Datos personalizados opcionales para la respuesta. */
    @Nullable
    private CustomData customData;

    /**
     * Estado que indica si la estación de carga acepta o no la solicitud para iniciar una transacción.
     */
    private RequestStartStopStatusEnum status;

    /**
     * Información adicional sobre el estado proporcionado en la respuesta.
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Identificador de la transacción en caso de que ya haya sido iniciada por la estación de carga
     * antes de recibir la solicitud de inicio de transacción.
     */
    @Nullable
    private String transactionId;

    /**
     * Constructor para crear una respuesta de inicio de transacción.
     *
     * @param status Estado que indica si la solicitud para iniciar la transacción fue aceptada o rechazada.
     */
    public RequestStartTransactionResponse(RequestStartStopStatusEnum status) {
        setStatus(status);
    }

    /**
     * Obtiene los datos personalizados asociados con la respuesta.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados con la respuesta.
     *
     * @param customData Datos personalizados a incluir en la respuesta.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la respuesta.
     *
     * @param customData Datos personalizados a agregar.
     * @return Esta misma instancia de la clase.
     */
    public RequestStartTransactionResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el estado que indica si la solicitud fue aceptada o rechazada.
     *
     * @return Estado de la respuesta.
     */
    public RequestStartStopStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el estado que indica si la solicitud fue aceptada o rechazada.
     *
     * @param status Estado de la respuesta.
     */
    public void setStatus(RequestStartStopStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado proporcionado es válido.
     *
     * @param status Estado a validar.
     * @return {@code true} si el estado es válido, {@code false} en caso contrario.
     */
    private boolean isValidStatus(RequestStartStopStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene información adicional sobre el estado de la respuesta.
     *
     * @return Información adicional o {@code null} si no está definida.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado de la respuesta.
     *
     * @param statusInfo Información adicional sobre el estado.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información de estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información de estado proporcionada es válida.
     *
     * @param statusInfo Información de estado a validar.
     * @return {@code true} si la información de estado es válida, {@code false} en caso contrario.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado.
     *
     * @param statusInfo Información adicional a incluir.
     * @return Esta misma instancia de la clase.
     */
    public RequestStartTransactionResponse withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Obtiene el identificador de la transacción en caso de que ya haya sido iniciada previamente.
     *
     * @return Identificador de la transacción o {@code null} si no aplica.
     */
    @Nullable
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción si ya ha sido iniciada previamente.
     *
     * @param transactionId Identificador de la transacción.
     */
    public void setTransactionId(@Nullable String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "El ID de transacción no es válido.");
        }
        this.transactionId = transactionId;
    }

    /**
     * Verifica si el identificador de transacción proporcionado es válido.
     *
     * @param transactionId Identificador de transacción a validar.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidTransactionId(@Nullable String transactionId) {
        return transactionId == null || transactionId.length() <= 36;
    }

    /**
     * Agrega un identificador de transacción en caso de que ya haya sido iniciada previamente.
     *
     * @param transactionId Identificador de la transacción.
     * @return Esta misma instancia de la clase.
     */
    public RequestStartTransactionResponse withTransactionId(@Nullable String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidStatusInfo(statusInfo)
                && isValidTransactionId(transactionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestStartTransactionResponse that = (RequestStartTransactionResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(statusInfo, that.statusInfo)
                && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, statusInfo, transactionId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("statusInfo", statusInfo)
                .add("transactionId", transactionId)
                .add("isValid", validate())
                .toString();
    }
}
