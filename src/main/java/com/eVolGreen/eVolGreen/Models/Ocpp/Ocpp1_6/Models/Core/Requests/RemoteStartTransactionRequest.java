package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Representa la solicitud para iniciar una transacción remota en OCPP 1.6.
 * Enviada por el Sistema Central a la Estación de Carga para iniciar una transacción de carga.
 * Esta solicitud puede incluir un perfil de carga opcional para controlar la potencia y duración de la carga.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: Identificador del conector (opcional).
 * - {@code idTag}: Identificador de la tarjeta o usuario (obligatorio).
 * - {@code chargingProfile}: Perfil de carga (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     RemoteStartTransactionRequest solicitud = new RemoteStartTransactionRequest("TAG001");
 *     solicitud.setConnectorId(1);
 *     ChargingProfile perfil = new ChargingProfile(/* ... );
 *     solicitud.setChargingProfile(perfil);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud a la Estación de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@JsonIgnoreProperties({"action", "sessionIndex"})
@XmlRootElement
@XmlType(propOrder = {"connectorId", "idTag", "chargingProfile"})
public class RemoteStartTransactionRequest extends RequestWithId {

    private static final Logger log = LoggerFactory.getLogger(RemoteStartTransactionRequest.class);

    @Min(value = 1, message = "connectorId debe ser mayor que 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "El campo idTag es obligatorio")
    @Size(max = 20, message = "idTag no puede superar los 20 caracteres")
    @JsonProperty("idTag")
    private String idTag;

    @JsonProperty("chargingProfile")
    private ChargingProfile chargingProfile;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #RemoteStartTransactionRequest(String)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public RemoteStartTransactionRequest() {}

    /**
     * Construye una nueva instancia de RemoteStartTransactionRequest con el idTag especificado.
     *
     * @param idTag El identificador que será usado por la estación de carga para iniciar la transacción.
     */
    public RemoteStartTransactionRequest(String idTag) {
        setIdTag(idTag);
        log.info("Constructor - RemoteStartTransactionRequest creado con idTag: {}", idTag);
    }

    /**
     * Obtiene el identificador del conector en el que se iniciará la transacción.
     *
     * @return Integer, el identificador del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el identificador del conector en el que se iniciará la transacción.
     *
     * @param connectorId Integer, el identificador del conector. Debe ser mayor que 0.
     * @throws IllegalArgumentException si connectorId es menor o igual a 0.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId != null && connectorId <= 0) {
            throw new IllegalArgumentException("connectorId debe ser mayor que 0");
        }
        log.info("Set - Configurando connectorId: {}", connectorId);
        this.connectorId = connectorId;
    }

    /**
     * Obtiene el idTag que será usado para iniciar la transacción.
     *
     * @return String, el identificador de la tarjeta o usuario (idTag).
     */
    public String getIdTag() {
        return idTag;
    }

    /**
     * Establece el idTag que será usado para iniciar la transacción.
     *
     * @param idTag String, el identificador de la tarjeta o usuario (idTag).
     * @throws IllegalArgumentException si idTag supera los 20 caracteres.
     */
    @XmlElement
    public void setIdTag(String idTag) {
        if (idTag != null && idTag.length() > 20) {
            throw new IllegalArgumentException("idTag no puede superar los 20 caracteres");
        }
        log.info("Set - Configurando idTag: {}", idTag);
        this.idTag = idTag;
    }

    /**
     * Obtiene el perfil de carga asociado a esta solicitud, si existe.
     *
     * @return ChargingProfile, el perfil de carga.
     */
    public ChargingProfile getChargingProfile() {
        return chargingProfile;
    }

    /**
     * Establece el perfil de carga que debe ser utilizado por la estación de carga para esta transacción.
     *
     * @param chargingProfile ChargingProfile, el perfil de carga.
     */
    @XmlElement
    public void setChargingProfile(ChargingProfile chargingProfile) {
        this.chargingProfile = chargingProfile;
        log.info("Set - Configurando chargingProfile: {}", chargingProfile);
    }

    /**
     * Valida si los datos proporcionados en la solicitud son válidos.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        boolean valid = idTag != null && !idTag.isEmpty() && idTag.length() <= 20;
        if (chargingProfile != null) {
            valid &= chargingProfile.validate();
        }
        if (connectorId != null) {
            valid &= connectorId > 0;
        }
        return valid;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que esta solicitud inicia una nueva transacción.
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
        RemoteStartTransactionRequest that = (RemoteStartTransactionRequest) o;
        return Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(idTag, that.idTag) &&
                Objects.equals(chargingProfile, that.chargingProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, idTag, chargingProfile);
    }

    @Override
    public String toString() {
        return "RemoteStartTransactionRequest{" +
                "connectorId=" + connectorId +
                ", idTag='" + idTag + '\'' +
                ", chargingProfile=" + chargingProfile +
                ", isValid=" + validate() +
                '}';
    }
}