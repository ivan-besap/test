package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingLimitSourceEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfile;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para reportar perfiles de carga.
 *
 * <p>Este mensaje informa sobre los perfiles de carga configurados en una estación de carga
 * específica o en un conector particular (EVSE).
 */
public class ReportChargingProfilesRequest extends RequestWithId {
    /** Datos personalizados asociados a la solicitud. */
    @Nullable private CustomData customData;

    /**
     * Identificador usado para correlacionar el mensaje de solicitud GetChargingProfilesRequest con
     * los mensajes resultantes ReportChargingProfilesRequest. Si CSMS proporcionó un requestId en la
     * solicitud original, este campo debe contener el mismo valor.
     */
    private Integer requestId;

    /** Fuente que instaló este perfil de carga. */
    private ChargingLimitSourceEnum chargingLimitSource;

    /**
     * Lista de perfiles de carga.
     *
     * <p>Un perfil de carga consiste en un conjunto de horarios que describen la cantidad de energía
     * o corriente que puede suministrarse en intervalos de tiempo específicos.
     */
    private ChargingProfile[] chargingProfile;

    /**
     * Indicador "por continuar". Valor predeterminado cuando se omite: false. false indica que no hay
     * más mensajes como parte de este reporte.
     */
    @Nullable private Boolean tbc;

    /**
     * Identificador del EVSE al que aplica el perfil de carga. Si evseId = 0, el mensaje contiene un
     * límite general para toda la estación de carga.
     */
    private Integer evseId;

    /**
     * Constructor de la clase ReportChargingProfilesRequest.
     *
     * @param requestId Identificador usado para correlacionar el mensaje original con la respuesta.
     * @param chargingLimitSource Fuente que instaló el perfil de carga.
     * @param chargingProfile Lista de perfiles de carga.
     * @param evseId Identificador del EVSE al que aplica el perfil de carga.
     */
    public ReportChargingProfilesRequest(
            Integer requestId,
            ChargingLimitSourceEnum chargingLimitSource,
            ChargingProfile[] chargingProfile,
            Integer evseId) {
        setRequestId(requestId);
        setChargingLimitSource(chargingLimitSource);
        setChargingProfile(chargingProfile);
        setEvseId(evseId);
    }

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Objeto CustomData con los datos personalizados, o {@code null} si no existen.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para esta solicitud.
     *
     * @param customData Objeto CustomData con los datos personalizados. Puede ser {@code null}.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Objeto CustomData con los datos personalizados.
     * @return Esta misma instancia para encadenar métodos.
     */
    public ReportChargingProfilesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador de la solicitud original.
     *
     * @return Identificador de la solicitud original.
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Establece el identificador de la solicitud original.
     *
     * @param requestId Identificador de la solicitud. No puede ser {@code null}.
     */
    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId, "El requestId es inválido.");
        }
        this.requestId = requestId;
    }

    /**
     * Valida si el identificador de la solicitud es válido.
     *
     * @param requestId Identificador a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene la fuente que instaló este perfil de carga.
     *
     * @return Fuente que instaló este perfil.
     */
    public ChargingLimitSourceEnum getChargingLimitSource() {
        return chargingLimitSource;
    }

    /**
     * Establece la fuente que instaló este perfil de carga.
     *
     * @param chargingLimitSource Fuente que instaló este perfil.
     */
    public void setChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
        if (!isValidChargingLimitSource(chargingLimitSource)) {
            throw new PropertyConstraintException(
                    chargingLimitSource, "La fuente de límite de carga es inválida.");
        }
        this.chargingLimitSource = chargingLimitSource;
    }

    /**
     * Valida si la fuente de límite de carga es válida.
     *
     * @param chargingLimitSource Fuente a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
        return chargingLimitSource != null;
    }

    /**
     * Obtiene la lista de perfiles de carga.
     *
     * @return Lista de perfiles de carga.
     */
    public ChargingProfile[] getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Establece la lista de perfiles de carga.
     *
     * @param chargingProfile Lista de perfiles de carga.
     */
    public void setChargingProfile(ChargingProfile[] chargingProfile) {
        if (!isValidChargingProfile(chargingProfile)) {
            throw new PropertyConstraintException(chargingProfile, "El perfil de carga es inválido.");
        }
        this.chargingProfile = chargingProfile;
    }

    /**
     * Valida si los perfiles de carga son válidos.
     *
     * @param chargingProfile Perfiles de carga a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidChargingProfile(ChargingProfile[] chargingProfile) {
        return chargingProfile != null
                && chargingProfile.length >= 1
                && Arrays.stream(chargingProfile).allMatch(ChargingProfile::validate);
    }

    /**
     * Obtiene el indicador "por continuar".
     *
     * @return {@code true} si hay más mensajes, {@code false} en caso contrario.
     */
    public Boolean getTbc() {
        return tbc != null ? tbc : false;
    }

    /**
     * Establece el indicador "por continuar".
     *
     * @param tbc {@code true} si hay más mensajes, {@code false} en caso contrario.
     */
    public void setTbc(@Nullable Boolean tbc) {
        this.tbc = tbc;
    }

    /**
     * Agrega el indicador "por continuar".
     *
     * @param tbc Valor del indicador.
     * @return Esta misma instancia para encadenar métodos.
     */
    public ReportChargingProfilesRequest withTbc(@Nullable Boolean tbc) {
        setTbc(tbc);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE al que aplica el perfil.
     *
     * @return Identificador del EVSE.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE al que aplica el perfil.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "El evseId es inválido.");
        }
        this.evseId = evseId;
    }

    /**
     * Valida si el identificador del EVSE es válido.
     *
     * @param evseId Identificador a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidRequestId(requestId)
                && isValidChargingLimitSource(chargingLimitSource)
                && isValidChargingProfile(chargingProfile)
                && isValidEvseId(evseId);
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
        ReportChargingProfilesRequest that = (ReportChargingProfilesRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(chargingLimitSource, that.chargingLimitSource)
                && Arrays.equals(chargingProfile, that.chargingProfile)
                && Objects.equals(tbc, that.tbc)
                && Objects.equals(evseId, that.evseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, requestId, chargingLimitSource, Arrays.hashCode(chargingProfile), tbc, evseId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("requestId", requestId)
                .add("chargingLimitSource", chargingLimitSource)
                .add("chargingProfile", chargingProfile)
                .add("tbc", tbc)
                .add("evseId", evseId)
                .add("isValid", validate())
                .toString();
    }
}

