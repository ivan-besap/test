package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MonitoringBaseEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa la solicitud para configurar la base de monitoreo en la estación de carga.
 *
 * <p>Esta solicitud permite especificar el tipo de base de monitoreo que se debe establecer para la estación de carga.
 */
public class SetMonitoringBaseRequest extends RequestWithId {

    /** Datos personalizados que pueden ser incluidos en la solicitud. */
    @Nullable
    private CustomData customData;

    /** Tipo de base de monitoreo que se establecerá. */
    private MonitoringBaseEnum monitoringBase;

    /**
     * Constructor de la clase SetMonitoringBaseRequest.
     *
     * @param monitoringBase Tipo de base de monitoreo que se debe establecer.
     */
    public SetMonitoringBaseRequest(MonitoringBaseEnum monitoringBase) {
        setMonitoringBase(monitoringBase);
    }

    /**
     * Obtiene los datos personalizados asociados con esta solicitud.
     *
     * @return Datos personalizados o {@code null} si no se proporcionaron.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Configura datos personalizados en la solicitud.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual para encadenamiento.
     */
    public SetMonitoringBaseRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de base de monitoreo configurado en esta solicitud.
     *
     * @return Tipo de base de monitoreo.
     */
    public MonitoringBaseEnum getMonitoringBase() {
        return monitoringBase;
    }

    /**
     * Configura el tipo de base de monitoreo.
     *
     * @param monitoringBase Tipo de base de monitoreo que se establecerá.
     */
    public void setMonitoringBase(MonitoringBaseEnum monitoringBase) {
        if (!isValidMonitoringBase(monitoringBase)) {
            throw new PropertyConstraintException(monitoringBase, "El tipo de base de monitoreo no es válido.");
        }
        this.monitoringBase = monitoringBase;
    }

    /**
     * Valida el tipo de base de monitoreo.
     *
     * @param monitoringBase El tipo de base de monitoreo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidMonitoringBase(MonitoringBaseEnum monitoringBase) {
        return monitoringBase != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidMonitoringBase(monitoringBase);
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
        SetMonitoringBaseRequest that = (SetMonitoringBaseRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(monitoringBase, that.monitoringBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, monitoringBase);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("monitoringBase", monitoringBase)
                .add("isValid", validate())
                .toString();
    }
}
