package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfile;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa una solicitud para establecer un perfil de carga en una estación de carga.
 *
 * <p>Permite definir un perfil de carga específico para una o varias estaciones, con límites y
 * horarios predefinidos.
 */
public class SetChargingProfileRequest extends RequestWithId {
    /** Datos personalizados adicionales opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador del EVSE al que se aplicará el perfil de carga.
     *
     * <p>Para el perfil TxDefaultProfile, un evseId = 0 aplica el perfil a todos los EVSE. Para los
     * perfiles ChargingStationMaxProfile y ChargingStationExternalConstraints, un evseId = 0 contiene
     * un límite general para toda la estación de carga.
     */
    private Integer evseId;

    /**
     * Perfil de carga que contiene un horario de carga, describiendo la cantidad de potencia o
     * corriente que se puede entregar en intervalos de tiempo específicos.
     */
    private ChargingProfile chargingProfile;

    /**
     * Constructor para la clase SetChargingProfileRequest.
     *
     * @param evseId Identificador del EVSE donde se aplicará el perfil.
     * @param chargingProfile Perfil de carga con los límites y horarios especificados.
     */
    public SetChargingProfileRequest(Integer evseId, ChargingProfile chargingProfile) {
        setEvseId(evseId);
        setChargingProfile(chargingProfile);
    }

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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura los datos personalizados y devuelve la instancia actual.
     *
     * @param customData Datos personalizados adicionales.
     * @return La instancia actual de la solicitud.
     */
    public SetChargingProfileRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE.
     *
     * @return Identificador del EVSE.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "El EVSE ID no es válido.");
        }
        this.evseId = evseId;
    }

    /**
     * Valida si el identificador del EVSE es correcto.
     *
     * @param evseId Identificador del EVSE a validar.
     * @return {@code true} si el EVSE ID es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    /**
     * Obtiene el perfil de carga.
     *
     * @return Perfil de carga configurado.
     */
    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Establece el perfil de carga.
     *
     * @param chargingProfile Perfil de carga a configurar.
     */
    public void setChargingProfile(ChargingProfile chargingProfile) {
        if (!isValidChargingProfile(chargingProfile)) {
            throw new PropertyConstraintException(chargingProfile, "El perfil de carga no es válido.");
        }
        this.chargingProfile = chargingProfile;
    }

    /**
     * Valida si el perfil de carga es correcto.
     *
     * @param chargingProfile El perfil de carga a validar.
     * @return {@code true} si el perfil es válido, {@code false} en caso contrario.
     */
    private boolean isValidChargingProfile(ChargingProfile chargingProfile) {
        return chargingProfile != null && chargingProfile.validate();
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEvseId(evseId)
                && isValidChargingProfile(chargingProfile);
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
        SetChargingProfileRequest that = (SetChargingProfileRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(chargingProfile, that.chargingProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, evseId, chargingProfile);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evseId", evseId)
                .add("chargingProfile", chargingProfile)
                .add("isValid", validate())
                .toString();
    }
}
