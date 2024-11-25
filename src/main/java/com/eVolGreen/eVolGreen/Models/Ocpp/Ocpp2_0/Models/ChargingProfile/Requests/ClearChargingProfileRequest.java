package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ClearChargingProfile;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para limpiar un perfil de carga.
 *
 * Representa una solicitud enviada al CSMS para eliminar un perfil de carga específico o
 * basado en ciertos criterios.
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class ClearChargingProfileRequest extends RequestWithId {
    /** Datos personalizados adicionales */
    @Nullable
    private CustomData customData;

    /** El ID del perfil de carga que se desea limpiar. */
    @Nullable
    private Integer chargingProfileId;

    /**
     * Criterios del perfil de carga.
     * Contiene los detalles del perfil de carga que especifican la cantidad de energía o corriente
     * que puede entregarse en intervalos de tiempo definidos.
     */
    @Nullable
    private ClearChargingProfile chargingProfileCriteria;

    /** Constructor vacío para la clase ClearChargingProfileRequest. */
    public ClearChargingProfileRequest() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
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
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados adicionales.
     * @return Verdadero si los datos son válidos, falso de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta solicitud con los datos personalizados establecidos.
     */
    public ClearChargingProfileRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el ID del perfil de carga que se desea limpiar.
     *
     * @return ID del perfil de carga.
     */
    @Nullable
    public Integer getChargingProfileId() {
        return chargingProfileId;
    }

    /**
     * Establece el ID del perfil de carga que se desea limpiar.
     *
     * @param chargingProfileId ID del perfil de carga.
     */
    public void setChargingProfileId(@Nullable Integer chargingProfileId) {
        this.chargingProfileId = chargingProfileId;
    }

    /**
     * Establece el ID del perfil de carga de forma fluida.
     *
     * @param chargingProfileId ID del perfil de carga.
     * @return Esta solicitud con el ID del perfil de carga establecido.
     */
    public ClearChargingProfileRequest withChargingProfileId(@Nullable Integer chargingProfileId) {
        setChargingProfileId(chargingProfileId);
        return this;
    }

    /**
     * Obtiene los criterios del perfil de carga.
     *
     * @return Criterios del perfil de carga.
     */
    @Nullable
    public ClearChargingProfile getChargingProfileCriteria() {
        return chargingProfileCriteria;
    }

    /**
     * Establece los criterios del perfil de carga.
     *
     * @param chargingProfileCriteria Criterios del perfil de carga.
     */
    public void setChargingProfileCriteria(@Nullable ClearChargingProfile chargingProfileCriteria) {
        if (!isValidChargingProfileCriteria(chargingProfileCriteria)) {
            throw new PropertyConstraintException(chargingProfileCriteria, "Los criterios del perfil de carga son inválidos.");
        }
        this.chargingProfileCriteria = chargingProfileCriteria;
    }

    /**
     * Valida los criterios del perfil de carga.
     *
     * @param chargingProfileCriteria Criterios del perfil de carga.
     * @return Verdadero si los criterios son válidos, falso de lo contrario.
     */
    private boolean isValidChargingProfileCriteria(
            @Nullable ClearChargingProfile chargingProfileCriteria) {
        return chargingProfileCriteria == null || chargingProfileCriteria.validate();
    }

    /**
     * Establece los criterios del perfil de carga de forma fluida.
     *
     * @param chargingProfileCriteria Criterios del perfil de carga.
     * @return Esta solicitud con los criterios establecidos.
     */
    public ClearChargingProfileRequest withChargingProfileCriteria(@Nullable ClearChargingProfile chargingProfileCriteria) {
        setChargingProfileCriteria(chargingProfileCriteria);
        return this;
    }

    /**
     * Valida esta solicitud.
     *
     * @return Verdadero si la solicitud es válida, falso de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidChargingProfileCriteria(chargingProfileCriteria);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return Falso ya que no está relacionada con una transacción.
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
        ClearChargingProfileRequest that = (ClearChargingProfileRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(chargingProfileId, that.chargingProfileId)
                && Objects.equals(chargingProfileCriteria, that.chargingProfileCriteria);
    }


    @Override
    public int hashCode() {
        return Objects.hash(customData, chargingProfileId, chargingProfileCriteria);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingProfileId", chargingProfileId)
                .add("chargingProfileCriteria", chargingProfileCriteria)
                .add("isValid", validate())
                .toString();
    }
}
