package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.Enums.ConnectorEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de reserva (ReserveNowRequest).
 *
 * <p>Esta clase representa una solicitud para reservar un conector en una estación de carga.
 * La reserva incluye información sobre el conector, el tiempo de expiración y los identificadores
 * de usuario.
 */
public class ReserveNowRequest extends RequestWithId {

    /** Datos personalizados específicos de la solicitud. */
    @Nullable
    private CustomData customData;

    /** Identificador único para la reserva. */
    private Integer id;

    /** Fecha y hora en que expira la reserva. */
    private ZonedDateTime expiryDateTime;

    /** Tipo de conector a reservar. */
    @Nullable
    private ConnectorEnum connectorType;

    /**
     * Identificador del usuario que autoriza la reserva. Puede ser un identificador RFID u otro tipo
     * de token.
     */
    private IdToken idToken;

    /** Identificador del EVSE (punto de carga) que será reservado. */
    @Nullable
    private Integer evseId;

    /**
     * Token de grupo opcional para identificar usuarios o permisos adicionales asociados a la
     * reserva.
     */
    @Nullable
    private IdToken groupIdToken;

    /**
     * Constructor para la clase ReserveNowRequest.
     *
     * @param id Identificador único para la reserva.
     * @param expiryDateTime Fecha y hora en que expira la reserva.
     * @param idToken Identificador del usuario que autoriza la reserva.
     */
    public ReserveNowRequest(Integer id, ZonedDateTime expiryDateTime, IdToken idToken) {
        setId(id);
        setExpiryDateTime(expiryDateTime);
        setIdToken(idToken);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Objeto CustomData con información adicional.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "El campo customData no es válido");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son correctos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos o null, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados.
     *
     * @param customData Objeto CustomData con información adicional.
     * @return La instancia actual para encadenar métodos.
     */
    public ReserveNowRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador de la reserva.
     *
     * @return Identificador único de la reserva.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador de la reserva.
     *
     * @param id Identificador único para la reserva.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El campo id no es válido");
        }
        this.id = id;
    }

    /**
     * Valida si el identificador de la reserva es válido.
     *
     * @param id Identificador único de la reserva.
     * @return {@code true} si el identificador es válido, {@code false} en caso contrario.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Obtiene la fecha y hora de expiración de la reserva.
     *
     * @return Fecha y hora en que expira la reserva.
     */
    public ZonedDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    /**
     * Establece la fecha y hora de expiración de la reserva.
     *
     * @param expiryDateTime Fecha y hora en que expira la reserva.
     */
    public void setExpiryDateTime(ZonedDateTime expiryDateTime) {
        if (!isValidExpiryDateTime(expiryDateTime)) {
            throw new PropertyConstraintException(expiryDateTime, "El campo expiryDateTime no es válido");
        }
        this.expiryDateTime = expiryDateTime;
    }

    /**
     * Valida si la fecha y hora de expiración son correctas.
     *
     * @param expiryDateTime Fecha y hora de expiración a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidExpiryDateTime(ZonedDateTime expiryDateTime) {
        return expiryDateTime != null;
    }

    /**
     * Obtiene el tipo de conector.
     *
     * @return Tipo de conector o null si no está definido.
     */
    @Nullable
    public ConnectorEnum getConnectorType() {
        return connectorType;
    }

    /**
     * Establece el tipo de conector.
     *
     * @param connectorType Tipo de conector.
     */
    public void setConnectorType(@Nullable ConnectorEnum connectorType) {
        this.connectorType = connectorType;
    }

    /**
     * Añade el tipo de conector.
     *
     * @param connectorType Tipo de conector.
     * @return La instancia actual para encadenar métodos.
     */
    public ReserveNowRequest withConnectorType(@Nullable ConnectorEnum connectorType) {
        setConnectorType(connectorType);
        return this;
    }

    /**
     * Obtiene el identificador de usuario.
     *
     * @return Objeto IdToken que representa al usuario.
     */
    public IdToken getIdToken() {
        return idToken;
    }

    /**
     * Establece el identificador de usuario.
     *
     * @param idToken Objeto IdToken que representa al usuario.
     */
    public void setIdToken(IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "El campo idToken no es válido");
        }
        this.idToken = idToken;
    }

    /**
     * Valida si el identificador de usuario es correcto.
     *
     * @param idToken Objeto IdToken a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidIdToken(IdToken idToken) {
        return idToken != null && idToken.validate();
    }

    /**
     * Añade el identificador de usuario.
     *
     * @return La instancia actual para encadenar métodos.
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
     * Añade el identificador del EVSE.
     *
     * @param evseId Identificador del EVSE.
     * @return La instancia actual para encadenar métodos.
     */
    public ReserveNowRequest withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Obtiene el token de grupo.
     *
     * @return Token de grupo o null si no está definido.
     */
    @Nullable
    public IdToken getGroupIdToken() {
        return groupIdToken;
    }

    /**
     * Establece el token de grupo.
     *
     * @param groupIdToken Token de grupo.
     */
    public void setGroupIdToken(@Nullable IdToken groupIdToken) {
        if (!isValidGroupIdToken(groupIdToken)) {
            throw new PropertyConstraintException(groupIdToken, "groupIdToken is invalid");
        }
        this.groupIdToken = groupIdToken;
    }

    /**
     * Valida si el token de grupo es correcto.
     *
     * @param groupIdToken Token de grupo a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidGroupIdToken(@Nullable IdToken groupIdToken) {
        return groupIdToken == null || groupIdToken.validate();
    }

    /**
     * Añade el token de grupo.
     *
     * @param groupIdToken Token de grupo.
     * @return La instancia actual para encadenar métodos.
     */
    public ReserveNowRequest withGroupIdToken(@Nullable IdToken groupIdToken) {
        setGroupIdToken(groupIdToken);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidId(id)
                && isValidExpiryDateTime(expiryDateTime)
                && isValidIdToken(idToken)
                && isValidGroupIdToken(groupIdToken);
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
        ReserveNowRequest that = (ReserveNowRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(expiryDateTime, that.expiryDateTime)
                && Objects.equals(connectorType, that.connectorType)
                && Objects.equals(idToken, that.idToken)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(groupIdToken, that.groupIdToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, id, expiryDateTime, connectorType, idToken, evseId, groupIdToken);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("expiryDateTime", expiryDateTime)
                .add("connectorType", connectorType)
                .add("idToken", idToken)
                .add("evseId", evseId)
                .add("groupIdToken", groupIdToken)
                .add("isValid", validate())
                .toString();
    }
}
