package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.UpdateEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.AuthorizationData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa la solicitud para enviar la lista local de autorizaciones.
 *
 * Esta solicitud permite actualizar o reemplazar la lista local de datos de autorización
 * en una estación de carga.
 * Compatible con OCPP 2.0.1 FINAL.
 */
public class SendLocalListRequest extends RequestWithId {

    /** Datos personalizados opcionales para la solicitud. */
    @Nullable
    private CustomData customData;

    /** Lista local de datos de autorización. */
    @Nullable private AuthorizationData[] localAuthorizationList;

    /**
     * Número de versión de la lista:
     * - En una actualización completa, este es el número de versión de la lista completa.
     * - En una actualización diferencial, es el número de versión después de aplicar los cambios.
     */
    private Integer versionNumber;

    /** Tipo de actualización de la lista (completa o diferencial). */
    private UpdateEnum updateType;

    /**
     * Constructor para inicializar la solicitud de envío de lista local.
     *
     * @param versionNumber Número de versión de la lista.
     * @param updateType Tipo de actualización (completa o diferencial).
     */
    public SendLocalListRequest(Integer versionNumber, UpdateEnum updateType) {
        setVersionNumber(versionNumber);
        setUpdateType(updateType);
    }

    /**
     * Obtiene los datos personalizados asociados a la solicitud.
     *
     * @return Los datos personalizados o null si no se especificaron.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Asigna datos personalizados a la solicitud.
     *
     * @param customData Los datos personalizados a asignar.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return true si son válidos, false en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la solicitud.
     *
     * @param customData Datos personalizados.
     * @return La instancia de la solicitud actualizada.
     */
    public SendLocalListRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la lista local de datos de autorización.
     *
     * @return Un arreglo de datos de autorización o null si no se especificó.
     */
    @Nullable
    public AuthorizationData[] getLocalAuthorizationList() {
        return localAuthorizationList;
    }

    /**
     * Asigna la lista local de datos de autorización.
     *
     * @param localAuthorizationList Arreglo con los datos de autorización.
     */
    public void setLocalAuthorizationList(@Nullable AuthorizationData[] localAuthorizationList) {
        if (!isValidLocalAuthorizationList(localAuthorizationList)) {
            throw new PropertyConstraintException(
                    localAuthorizationList, "La lista local de autorización no es válida");
        }
        this.localAuthorizationList = localAuthorizationList;
    }

    /**
     * Verifica si la lista local de datos de autorización es válida.
     *
     * @param localAuthorizationList Lista a validar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean isValidLocalAuthorizationList(
            @Nullable AuthorizationData[] localAuthorizationList) {
        return localAuthorizationList == null
                || (localAuthorizationList.length >= 1
                && Arrays.stream(localAuthorizationList).allMatch(AuthorizationData::validate));
    }

    /**
     * Agrega la lista local de datos de autorización a la solicitud.
     *
     * @param localAuthorizationList Lista de datos de autorización.
     * @return La instancia de la solicitud actualizada.
     */
    public SendLocalListRequest withLocalAuthorizationList(
            @Nullable AuthorizationData[] localAuthorizationList) {
        setLocalAuthorizationList(localAuthorizationList);
        return this;
    }

    /**
     * Obtiene el número de versión de la lista local.
     *
     * @return El número de versión.
     */
    public Integer getVersionNumber() {
        return versionNumber;
    }

    /**
     * Asigna el número de versión de la lista local.
     *
     * @param versionNumber Número de versión.
     */
    public void setVersionNumber(Integer versionNumber) {
        if (!isValidVersionNumber(versionNumber)) {
            throw new PropertyConstraintException(versionNumber, "El número de versión no es válido");
        }
        this.versionNumber = versionNumber;
    }

    /**
     * Verifica si el número de versión es válido.
     *
     * @param versionNumber Número de versión a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidVersionNumber(Integer versionNumber) {
        return versionNumber != null;
    }

    /**
     * Obtiene el tipo de actualización de la lista local.
     *
     * @return Tipo de actualización (completa o diferencial).
     */
    public UpdateEnum getUpdateType() {
        return updateType;
    }

    /**
     * Asigna el tipo de actualización de la lista local.
     *
     * @param updateType Tipo de actualización.
     */
    public void setUpdateType(UpdateEnum updateType) {
        if (!isValidUpdateType(updateType)) {
            throw new PropertyConstraintException(updateType, "El tipo de actualización no es válido");
        }
        this.updateType = updateType;
    }

    /**
     * Verifica si el tipo de actualización es válido.
     *
     * @param updateType Tipo a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidUpdateType(UpdateEnum updateType) {
        return updateType != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidLocalAuthorizationList(localAuthorizationList)
                && isValidVersionNumber(versionNumber)
                && isValidUpdateType(updateType);
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
        SendLocalListRequest that = (SendLocalListRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(localAuthorizationList, that.localAuthorizationList)
                && Objects.equals(versionNumber, that.versionNumber)
                && Objects.equals(updateType, that.updateType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, Arrays.hashCode(localAuthorizationList), versionNumber, updateType);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("localAuthorizationList", localAuthorizationList)
                .add("versionNumber", versionNumber)
                .add("updateType", updateType)
                .add("isValid", validate())
                .toString();
    }
}
