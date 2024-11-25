package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para iniciar una transacción de carga en un punto de carga.
 *
 * <p>Esta solicitud es parte del protocolo OCPP 2.0.1 y contiene toda la información necesaria
 * para que una estación de carga inicie una sesión de carga con un vehículo eléctrico.
 */
public class RequestStartTransactionRequest extends RequestWithId {

    /** Datos personalizados específicos para la implementación. */
    @Nullable
    private CustomData customData;

    /** Identificador del EVSE donde se iniciará la transacción. Debe ser mayor a 0. */
    @Nullable
    private Integer evseId;

    /**
     * Token de grupo utilizado para la autorización. Este token es insensible a mayúsculas y
     * puede representar diferentes formas de autorización.
     */
    @Nullable
    private IdToken groupIdToken;

    /**
     * Token utilizado para la autorización. Este token es insensible a mayúsculas y permite
     * múltiples formas de identificación para autorizar la transacción.
     */
    private IdToken idToken;

    /**
     * Identificador asignado por el servidor a esta solicitud de inicio remoto. La estación de carga
     * puede devolver este identificador en la solicitud de evento de transacción
     * (TransactionEventRequest) para informar al servidor sobre la transacción asociada.
     */
    private Integer remoteStartId;

    /**
     * Perfil de carga que define cómo se gestionará la energía durante la sesión de carga.
     *
     * <p>Un perfil de carga incluye un horario de carga que describe la cantidad de energía o
     * corriente que se puede entregar en intervalos de tiempo específicos.
     */
    @Nullable
    private ChargingProfile chargingProfile;

    /**
     * Constructor para la clase RequestStartTransactionRequest.
     *
     * @param idToken Token utilizado para autorizar la transacción.
     * @param remoteStartId Identificador único asignado a la solicitud de inicio remoto.
     */
    public RequestStartTransactionRequest(IdToken idToken, Integer remoteStartId) {
        setIdToken(idToken);
        setRemoteStartId(remoteStartId);
    }

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a la solicitud.
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
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de la solicitud.
     */
    public RequestStartTransactionRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE donde se iniciará la transacción.
     *
     * @return Identificador del EVSE, o {@code null} si no está definido.
     */
    @Nullable
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE donde se iniciará la transacción.
     *
     * @param evseId Identificador del EVSE (debe ser mayor a 0).
     */
    public void setEvseId(@Nullable Integer evseId) {
        this.evseId = evseId;
    }

    /**
     * Agrega el identificador del EVSE donde se iniciará la transacción.
     *
     * @param evseId Identificador del EVSE.
     * @return La instancia actual de la solicitud.
     */
    public RequestStartTransactionRequest withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Obtiene el token de grupo utilizado para la autorización.
     *
     * @return Token de grupo, o {@code null} si no está definido.
     */
    @Nullable
    public IdToken getGroupIdToken() {
        return groupIdToken;
    }

    /**
     * Establece el token de grupo utilizado para la autorización.
     *
     * @param groupIdToken Token de grupo.
     */
    public void setGroupIdToken(@Nullable IdToken groupIdToken) {
        if (!isValidGroupIdToken(groupIdToken)) {
            throw new PropertyConstraintException(groupIdToken, "El token de grupo no es válido.");
        }
        this.groupIdToken = groupIdToken;
    }

    /**
     * Verifica si el token de grupo es válido.
     *
     * @param groupIdToken Token de grupo a validar.
     * @return {@code true} si el token de grupo es válido, {@code false} en caso contrario.
     */
    private boolean isValidGroupIdToken(@Nullable IdToken groupIdToken) {
        return groupIdToken == null || groupIdToken.validate();
    }

    /**
     * Agrega un token de grupo a la solicitud.
     *
     * @param groupIdToken Token de grupo.
     * @return La instancia actual de la solicitud.
     */
    public RequestStartTransactionRequest withGroupIdToken(@Nullable IdToken groupIdToken) {
        setGroupIdToken(groupIdToken);
        return this;
    }

    /**
     * Obtiene el token utilizado para la autorización de la transacción.
     *
     * @return Token de autorización.
     */
    public IdToken getIdToken() {
        return idToken;
    }

    /**
     * Establece el token utilizado para la autorización de la transacción.
     *
     * @param idToken Token de autorización.
     */
    public void setIdToken(IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "El token de autorización no es válido.");
        }
        this.idToken = idToken;
    }

    /**
     * Verifica si el token de autorización es válido.
     *
     * @param idToken Token de autorización a validar.
     * @return {@code true} si el token de autorización es válido, {@code false} en caso contrario.
     */
    private boolean isValidIdToken(IdToken idToken) {
        return idToken != null && idToken.validate();
    }

    /**
     * Agrega un token de autorización a la solicitud.
     *
     * @return La instancia actual de la solicitud.
     */
    public Integer getRemoteStartId() {
        return remoteStartId;
    }

    /**
     * Establece el identificador asignado por el servidor a la solicitud de inicio remoto.
     *
     * @param remoteStartId Identificador único asignado a la solicitud de inicio remoto.
     */
    public void setRemoteStartId(Integer remoteStartId) {
        if (!isValidRemoteStartId(remoteStartId)) {
            throw new PropertyConstraintException(remoteStartId, "remoteStartId is invalid");
        }
        this.remoteStartId = remoteStartId;
    }

    /**
     * Verifica si el identificador asignado a la solicitud de inicio remoto es válido.
     *
     * @param remoteStartId Identificador a validar.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidRemoteStartId(Integer remoteStartId) {
        return remoteStartId != null;
    }

    /**
     * Agrega un identificador único a la solicitud de inicio remoto.
     *
     * @return La instancia actual de la solicitud.
     */
    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Establece el perfil de carga que define cómo se gestionará la energía durante la sesión de carga.
     *
     * @param chargingProfile Perfil de carga.
     */
    public void setChargingProfile(@Nullable ChargingProfile chargingProfile) {
        if (!isValidChargingProfile(chargingProfile)) {
            throw new PropertyConstraintException(chargingProfile, "chargingProfile is invalid");
        }
        this.chargingProfile = chargingProfile;
    }

    /**
     * Verifica si el perfil de carga es válido.
     *
     * @param chargingProfile Perfil de carga a validar.
     * @return {@code true} si el perfil de carga es válido, {@code false} en caso contrario.
     */
    private boolean isValidChargingProfile(@Nullable ChargingProfile chargingProfile) {
        return chargingProfile == null || chargingProfile.validate();
    }

    /**
     * Agrega un perfil de carga a la solicitud.
     *
     * @param chargingProfile Perfil de carga.
     * @return La instancia actual de la solicitud.
     */
    public RequestStartTransactionRequest withChargingProfile(
            @Nullable ChargingProfile chargingProfile) {
        setChargingProfile(chargingProfile);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidGroupIdToken(groupIdToken)
                && isValidIdToken(idToken)
                && isValidRemoteStartId(remoteStartId)
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
        RequestStartTransactionRequest that = (RequestStartTransactionRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(groupIdToken, that.groupIdToken)
                && Objects.equals(idToken, that.idToken)
                && Objects.equals(remoteStartId, that.remoteStartId)
                && Objects.equals(chargingProfile, that.chargingProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, evseId, groupIdToken, idToken, remoteStartId, chargingProfile);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evseId", evseId)
                .add("groupIdToken", groupIdToken)
                .add("idToken", idToken)
                .add("remoteStartId", remoteStartId)
                .add("chargingProfile", chargingProfile)
                .add("isValid", validate())
                .toString();
    }
}
