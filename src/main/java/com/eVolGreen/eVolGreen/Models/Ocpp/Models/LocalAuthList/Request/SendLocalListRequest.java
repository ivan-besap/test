package com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request.Enums.UpdateType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud enviada desde el Sistema Central al Punto de Carga para actualizar la lista de autorizaciones locales.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 * La lista puede ser una actualización completa o diferencial.
 *
 * <b>Campos:</b>
 * - {@code listVersion}: La versión de la lista (obligatorio).
 * - {@code localAuthorizationList}: La lista de autorizaciones locales (opcional).
 * - {@code updateType}: El tipo de actualización (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     SendLocalListRequest solicitud = new SendLocalListRequest(1, UpdateType.FULL);
 *     AuthorizationData[] authList = new AuthorizationData[]{
 *         new AuthorizationData("TAG001", new IdTagInfo(AuthorizationStatus.ACCEPTED)),
 *         new AuthorizationData("TAG002", new IdTagInfo(AuthorizationStatus.BLOCKED))
 *     };
 *     solicitud.setLocalAuthorizationList(authList);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Punto de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class SendLocalListRequest extends RequestWithId {

    @NotNull(message = "El campo listVersion es obligatorio")
    @Min(value = 0, message = "listVersion debe ser >= 0")
    @JsonProperty("listVersion")
    private Integer listVersion;

    @JsonProperty("localAuthorizationList")
    private AuthorizationData[] localAuthorizationList;

    @NotNull(message = "El campo updateType es obligatorio")
    @JsonProperty("updateType")
    private UpdateType updateType;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #SendLocalListRequest(Integer, UpdateType)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public SendLocalListRequest() {}

    /**
     * Construye una nueva instancia de SendLocalListRequest con los campos requeridos.
     *
     * @param listVersion La versión de la lista.
     * @param updateType El tipo de actualización (completa o diferencial).
     */
    public SendLocalListRequest(Integer listVersion, UpdateType updateType) {
        setListVersion(listVersion);
        setUpdateType(updateType);
    }

    /**
     * Obtiene la versión de la lista.
     *
     * @return Integer, la versión de la lista.
     */
    public Integer getListVersion() {
        return listVersion;
    }

    /**
     * Establece la versión de la lista.
     *
     * @param listVersion Integer, la versión de la lista.
     */
    @XmlElement
    public void setListVersion(Integer listVersion) {
        if (listVersion < 0) {
            throw new IllegalArgumentException("listVersion debe ser >= 0");
        }
        this.listVersion = listVersion;
    }

    /**
     * Obtiene la lista de autorizaciones locales.
     *
     * @return Array de {@link AuthorizationData}.
     */
    public AuthorizationData[] getLocalAuthorizationList() {
        return localAuthorizationList;
    }

    /**
     * Establece la lista de autorizaciones locales.
     *
     * @param localAuthorizationList Array de {@link AuthorizationData}.
     */
    @XmlElement
    public void setLocalAuthorizationList(AuthorizationData[] localAuthorizationList) {
        this.localAuthorizationList = localAuthorizationList;
    }

    /**
     * Obtiene el tipo de actualización.
     *
     * @return {@link UpdateType}.
     */
    public UpdateType getUpdateType() {
        return updateType;
    }

    /**
     * Establece el tipo de actualización.
     *
     * @param updateType {@link UpdateType}.
     */
    @XmlElement
    public void setUpdateType(UpdateType updateType) {
        this.updateType = updateType;
    }

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        boolean valid = listVersion != null && listVersion >= 0 && updateType != null;

        if (localAuthorizationList != null) {
            for (AuthorizationData data : localAuthorizationList) {
                valid &= data.validate();

                if (updateType == UpdateType.Full) {
                    valid &= data.getIdTagInfo() != null;
                }
            }
        }

        return valid;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que esta solicitud no está directamente relacionada con una transacción.
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
        SendLocalListRequest that = (SendLocalListRequest) o;
        return Objects.equals(listVersion, that.listVersion) &&
                Arrays.equals(localAuthorizationList, that.localAuthorizationList) &&
                updateType == that.updateType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(listVersion, updateType);
        result = 31 * result + Arrays.hashCode(localAuthorizationList);
        return result;
    }

    @Override
    public String toString() {
        return "SendLocalListRequest{" +
                "listVersion=" + listVersion +
                ", localAuthorizationList=" + Arrays.toString(localAuthorizationList) +
                ", updateType=" + updateType +
                ", isValid=" + validate() +
                '}';
    }
}