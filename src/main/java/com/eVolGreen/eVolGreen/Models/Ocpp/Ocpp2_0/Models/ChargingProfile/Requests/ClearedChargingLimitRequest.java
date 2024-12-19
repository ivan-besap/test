package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingLimitSourceEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para eliminar un límite de carga.
 *
 * Representa la solicitud enviada por la estación de carga para notificar que
 * un límite de carga ha sido eliminado.
 *
 * <p>Versión OCPP 2.0.1 FINAL</p>
 */
public class ClearedChargingLimitRequest extends RequestWithId {
    /** Datos personalizados para atributos adicionales. */
    @Nullable
    private CustomData customData;

    /** Fuente del límite de carga. */
    private ChargingLimitSourceEnum chargingLimitSource;

    /** Identificador del EVSE. */
    @Nullable
    private Integer evseId;

    /**
     * Constructor para la clase ClearedChargingLimitRequest.
     *
     * @param chargingLimitSource Fuente del límite de carga.
     */
    public ClearedChargingLimitRequest(ChargingLimitSourceEnum chargingLimitSource) {
        setChargingLimitSource(chargingLimitSource);
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public ClearedChargingLimitRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la fuente del límite de carga.
     *
     * @return Fuente del límite de carga.
     */
    public ChargingLimitSourceEnum getChargingLimitSource() {
        return chargingLimitSource;
    }

    /**
     * Establece la fuente del límite de carga.
     *
     * @param chargingLimitSource Fuente del límite de carga.
     */
    public void setChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
        if (!isValidChargingLimitSource(chargingLimitSource)) {
            throw new PropertyConstraintException(chargingLimitSource, "La fuente del límite de carga no puede ser nula.");
        }
        this.chargingLimitSource = chargingLimitSource;
    }

    /**
     * Asigna la fuente del límite de carga de forma fluida.
     *
     * @param chargingLimitSource Fuente del límite de carga.
     * @return Esta instancia.
     */
    private boolean isValidChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
        return chargingLimitSource != null;
    }

    /**
     * Obtiene el identificador del EVSE.
     *
     * @return Identificador del EVSE.
     */
    @Nullable
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(@Nullable Integer evseId) {
        this.evseId = evseId;
    }

    /**
     * Asigna el identificador del EVSE de forma fluida.
     *
     * @param evseId Identificador del EVSE.
     * @return Esta instancia.
     */
    public ClearedChargingLimitRequest withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Valida que la entidad sea válida.
     *
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidChargingLimitSource(chargingLimitSource);
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false} porque esta solicitud no está relacionada con transacciones.
     */
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
        ClearedChargingLimitRequest that = (ClearedChargingLimitRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(chargingLimitSource, that.chargingLimitSource)
                && Objects.equals(evseId, that.evseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, chargingLimitSource, evseId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingLimitSource", chargingLimitSource)
                .add("evseId", evseId)
                .add("isValid", validate())
                .toString();
    }
}
