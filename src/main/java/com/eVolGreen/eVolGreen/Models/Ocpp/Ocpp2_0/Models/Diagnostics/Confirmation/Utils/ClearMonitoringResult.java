package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.StatusInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.Enums.ClearMonitoringStatusEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Resultado del borrado de monitorización.
 *
 * <p>Representa el resultado de intentar borrar un monitor específico en el sistema.</p>
 */
public class ClearMonitoringResult {

    /**
     * Datos personalizados.
     *
     * <p>Permite incluir atributos adicionales específicos de la implementación.</p>
     */
    @Nullable
    private CustomData customData;

    /**
     * Resultado del intento de borrar el monitor, identificado por su ID.
     */
    private ClearMonitoringStatusEnum status;

    /**
     * Identificador del monitor cuya eliminación fue solicitada.
     */
    private Integer id;

    /**
     * Información adicional sobre el estado del resultado.
     */
    @Nullable
    private StatusInfo statusInfo;

    /**
     * Constructor de la clase ClearMonitoringResult.
     *
     * @param status Resultado del intento de borrar el monitor.
     * @param id     Identificador del monitor cuya eliminación fue solicitada.
     */
    public ClearMonitoringResult(ClearMonitoringStatusEnum status, Integer id) {
        setStatus(status);
        setId(id);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Los datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
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
     * @param customData Los datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Método fluido para establecer los datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @return La instancia actual del resultado.
     */
    public ClearMonitoringResult withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el resultado del intento de borrar el monitor.
     *
     * @return El resultado del intento de borrar el monitor.
     */
    public ClearMonitoringStatusEnum getStatus() {
        return status;
    }

    /**
     * Establece el resultado del intento de borrar el monitor.
     *
     * @param status El resultado del intento de borrar el monitor.
     * @throws PropertyConstraintException si el estado es inválido.
     */
    public void setStatus(ClearMonitoringStatusEnum status) {
        if (!isValidStatus(status)) {
            throw new PropertyConstraintException(status, "El estado no es válido.");
        }
        this.status = status;
    }

    /**
     * Verifica si el estado es válido.
     *
     * @param status El estado a verificar.
     * @return {@code true} si el estado es válido, de lo contrario {@code false}.
     */
    private boolean isValidStatus(ClearMonitoringStatusEnum status) {
        return status != null;
    }

    /**
     * Obtiene el identificador del monitor cuya eliminación fue solicitada.
     *
     * @return El identificador del monitor.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador del monitor cuya eliminación fue solicitada.
     *
     * @param id El identificador del monitor.
     * @throws PropertyConstraintException si el identificador es inválido.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El identificador no es válido.");
        }
        this.id = id;
    }

    /**
     * Verifica si el identificador es válido.
     *
     * @param id El identificador a verificar.
     * @return {@code true} si el identificador es válido, de lo contrario {@code false}.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Obtiene información adicional sobre el estado del resultado.
     *
     * @return La información adicional sobre el estado, o {@code null} si no está definida.
     */
    @Nullable
    public StatusInfo getStatusInfo() {
        return statusInfo;
    }

    /**
     * Establece información adicional sobre el estado del resultado.
     *
     * @param statusInfo La información adicional sobre el estado.
     * @throws PropertyConstraintException si la información del estado no es válida.
     */
    public void setStatusInfo(@Nullable StatusInfo statusInfo) {
        if (!isValidStatusInfo(statusInfo)) {
            throw new PropertyConstraintException(statusInfo, "La información del estado no es válida.");
        }
        this.statusInfo = statusInfo;
    }

    /**
     * Verifica si la información del estado es válida.
     *
     * @param statusInfo La información del estado a verificar.
     * @return {@code true} si la información es válida, de lo contrario {@code false}.
     */
    private boolean isValidStatusInfo(@Nullable StatusInfo statusInfo) {
        return statusInfo == null || statusInfo.validate();
    }

    /**
     * Método fluido para establecer la información adicional sobre el estado.
     *
     * @param statusInfo La información del estado.
     * @return La instancia actual del resultado.
     */
    public ClearMonitoringResult withStatusInfo(@Nullable StatusInfo statusInfo) {
        setStatusInfo(statusInfo);
        return this;
    }

    /**
     * Valida el resultado del borrado.
     *
     * @return {@code true} si el resultado es válido, de lo contrario {@code false}.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidStatus(status)
                && isValidId(id)
                && isValidStatusInfo(statusInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClearMonitoringResult that = (ClearMonitoringResult) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(status, that.status)
                && Objects.equals(id, that.id)
                && Objects.equals(statusInfo, that.statusInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, status, id, statusInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("status", status)
                .add("id", id)
                .add("statusInfo", statusInfo)
                .add("isValid", validate())
                .toString();
    }
}
