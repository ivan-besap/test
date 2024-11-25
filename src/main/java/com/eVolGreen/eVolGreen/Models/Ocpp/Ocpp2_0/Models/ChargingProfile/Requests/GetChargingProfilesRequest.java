package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfileCriterion;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener perfiles de carga instalados en la estación de carga.
 *
 * <p>Este mensaje solicita información sobre los perfiles de carga almacenados, que incluyen
 * horarios de carga y criterios asociados a la estación de carga.
 */
public class GetChargingProfilesRequest extends RequestWithId {

    /** Datos personalizados específicos de la implementación. */
    @Nullable
    private CustomData customData;

    /**
     * Identificación de referencia utilizada por la estación de carga para responder con un
     * ReportChargingProfilesRequest.
     */
    private Integer requestId;

    /**
     * Identificador del EVSE para el cual deben informarse los perfiles de carga instalados.
     * <p>
     * Si el valor es 0, solo se informarán los perfiles de carga instalados en la estación de carga
     * misma (conexión a la red eléctrica). Si se omite, se informarán todos los perfiles de carga
     * instalados.
     */
    @Nullable
    private Integer evseId;

    /**
     * Criterios de los perfiles de carga solicitados.
     *
     * <p>Un perfil de carga incluye horarios de carga y define la cantidad de energía o corriente
     * que puede entregarse en intervalos de tiempo específicos.
     */
    private ChargingProfileCriterion chargingProfile;

    /**
     * Constructor para la clase GetChargingProfilesRequest.
     *
     * @param requestId Identificación de referencia para la estación de carga.
     * @param chargingProfile Criterios de los perfiles de carga solicitados.
     */
    public GetChargingProfilesRequest(Integer requestId, ChargingProfileCriterion chargingProfile) {
        setRequestId(requestId);
        setChargingProfile(chargingProfile);
    }

    /**
     * Obtiene los datos personalizados específicos de la implementación.
     *
     * @return Datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados específicos de la implementación.
     *
     * @param customData Datos personalizados a configurar.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Comprueba si los datos personalizados son válidos.
     *
     * @param customData Los datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura y retorna la instancia con los datos personalizados establecidos.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public GetChargingProfilesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador de referencia utilizado en la solicitud.
     *
     * @return Identificador de referencia.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador de referencia para la solicitud.
     *
     * @param requestId Identificador de referencia.
     * @throws PropertyConstraintException Si el identificador es nulo.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "requestId is invalid");
        }
        this.requestId = requestId;
    }

    /**
     * Comprueba si el identificador de referencia es válido.
     *
     * @param requestId Identificador de referencia a validar.
     * @return {@code true} si el identificador es válido, de lo contrario {@code false}.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene el identificador del EVSE asociado a la solicitud.
     *
     * @return Identificador del EVSE, o {@code null} si no está definido.
     */
    @Nullable
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE asociado a la solicitud.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(@Nullable Integer evseId) {
        this.evseId = evseId;
    }

    /**
     * Configura y retorna la instancia con el identificador del EVSE establecido.
     *
     * @param evseId Identificador del EVSE.
     * @return Esta instancia.
     */
    public GetChargingProfilesRequest withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Obtiene los criterios de los perfiles de carga solicitados.
     *
     * @return Criterios de los perfiles de carga.
     */
    public ChargingProfileCriterion getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Establece los criterios de los perfiles de carga solicitados.
     *
     * @param chargingProfile Criterios de los perfiles de carga.
     * @throws PropertyConstraintException Si los criterios no son válidos.
     */
    public void setChargingProfile(ChargingProfileCriterion chargingProfile) {
        if (!isValidChargingProfile(chargingProfile)) {
            throw new PropertyConstraintException(chargingProfile, "chargingProfile is invalid");
        }
        this.chargingProfile = chargingProfile;
    }

    /**
     * Comprueba si los criterios de los perfiles de carga son válidos.
     *
     * @param chargingProfile Criterios a validar.
     * @return {@code true} si los criterios son válidos, de lo contrario {@code false}.
     */
    private boolean isValidChargingProfile(ChargingProfileCriterion chargingProfile) {
        return chargingProfile != null && chargingProfile.validate();
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidRequestId(requestId)
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
        GetChargingProfilesRequest that = (GetChargingProfilesRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(chargingProfile, that.chargingProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, requestId, evseId, chargingProfile);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("requestId", requestId)
                .add("evseId", evseId)
                .add("chargingProfile", chargingProfile)
                .add("isValid", validate())
                .toString();
    }
}
