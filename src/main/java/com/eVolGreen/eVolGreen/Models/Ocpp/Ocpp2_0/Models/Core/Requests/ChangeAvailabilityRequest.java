package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.OperationalStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.EVSE;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Cambio de Disponibilidad
 *
 * Representa una solicitud para cambiar el estado de disponibilidad de una estación de carga.
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class ChangeAvailabilityRequest extends RequestWithId {

    /** Datos personalizados para atributos adicionales */
    @Nullable
    private CustomData customData;

    /**
     * EVSE (Equipo de Suministro de Energía para Vehículos Eléctricos)
     * Representa el equipo específico de una estación de carga.
     */
    @Nullable
    private EVSE evse;

    /**
     * El tipo de cambio de disponibilidad que la estación de carga debe realizar.
     */
    private OperationalStatusEnum operationalStatus;

    /**
     * Constructor de la clase ChangeAvailabilityRequest.
     *
     * @param operationalStatus El tipo de cambio de disponibilidad que la estación de carga debe realizar.
     */
    public ChangeAvailabilityRequest(OperationalStatusEnum operationalStatus) {
        setOperationalStatus(operationalStatus);
    }

    /** Obtiene los datos personalizados. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /** Establece los datos personalizados. */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /** Valida los datos personalizados. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /** Establece los datos personalizados de forma fluida. */
    public ChangeAvailabilityRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** Obtiene el EVSE (Equipo de Suministro de Energía para Vehículos Eléctricos). */
    @Nullable
    public EVSE getEvse() {
        return evse;
    }

    /** Establece el EVSE (Equipo de Suministro de Energía para Vehículos Eléctricos). */
    public void setEvse(@Nullable EVSE evse) {
        if (!isValidEvse(evse)) {
            throw new PropertyConstraintException(evse, "El EVSE no es válido.");
        }
        this.evse = evse;
    }

    /** Valida el EVSE. */
    private boolean isValidEvse(@Nullable EVSE evse) {
        return evse == null || evse.validate();
    }

    /** Establece el EVSE de forma fluida. */
    public ChangeAvailabilityRequest withEvse(@Nullable EVSE evse) {
        setEvse(evse);
        return this;
    }

    /** Obtiene el tipo de cambio de disponibilidad que la estación de carga debe realizar. */
    public OperationalStatusEnum getOperationalStatus() {
        return operationalStatus;
    }

    /** Establece el tipo de cambio de disponibilidad que la estación de carga debe realizar. */
    public void setOperationalStatus(OperationalStatusEnum operationalStatus) {
        if (!isValidOperationalStatus(operationalStatus)) {
            throw new PropertyConstraintException(operationalStatus, "El estado operacional no es válido.");
        }
        this.operationalStatus = operationalStatus;
    }

    /** Establece el tipo de cambio de disponibilidad de forma fluida. */
    private boolean isValidOperationalStatus(OperationalStatusEnum operationalStatus) {
        return operationalStatus != null;
    }

    /** Valida la entidad. */
    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEvse(evse)
                && isValidOperationalStatus(operationalStatus);
    }

    /** Indica si la solicitud está relacionada con una transacción. */
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

    /** Compara esta instancia con otro objeto. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeAvailabilityRequest that = (ChangeAvailabilityRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evse, that.evse)
                && Objects.equals(operationalStatus, that.operationalStatus);
    }
    /** Genera un código hash para esta instancia. */
    @Override
    public int hashCode() {
        return Objects.hash(customData, evse, operationalStatus);
    }

    /** Representación en cadena de la entidad. */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evse", evse)
                .add("operationalStatus", operationalStatus)
                .add("isValid", validate())
                .toString();
    }
}
