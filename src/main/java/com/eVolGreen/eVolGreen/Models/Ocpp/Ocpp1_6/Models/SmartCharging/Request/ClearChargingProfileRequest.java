package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfilePurposeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa la solicitud de eliminación de un perfil de carga enviada por el Sistema Central al Punto de Carga.
 * Esta clase permite especificar un perfil de carga para eliminar en función de los criterios dados.
 *
 * <b>Campos:</b>
 * - {@code id}: Identificador del perfil de carga a eliminar.
 * - {@code connectorId}: El conector asociado al perfil de carga.
 * - {@code chargingProfilePurpose}: El propósito del perfil de carga.
 * - {@code stackLevel}: Nivel de la pila del perfil de carga.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ClearChargingProfileRequest request = new ClearChargingProfileRequest();
 *     request.setId(1);
 *     request.setConnectorId(2);
 *     request.setChargingProfilePurpose(ChargingProfilePurposeType.TxProfile);
 *     request.setStackLevel(0);
 *
 *     if (request.validate()) {
 *         // Enviar la solicitud
 *     } else {
 *         // Manejar error de validación
 *     }
 * </pre>
 */
@XmlRootElement
public class ClearChargingProfileRequest extends RequestWithId {

    @JsonProperty("id")
    private Integer id;

    @Min(value = 0, message = "El valor de connectorId debe ser mayor o igual a 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @JsonProperty("chargingProfilePurpose")
    private ChargingProfilePurposeType chargingProfilePurpose;

    @Min(value = 0, message = "El valor de stackLevel debe ser mayor o igual a 0")
    @JsonProperty("stackLevel")
    private Integer stackLevel;

    public ClearChargingProfileRequest() {}

    /**
     * Obtiene el ID del perfil de carga a eliminar.
     *
     * @return ID del perfil de carga.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el ID del perfil de carga a eliminar.
     *
     * @param id ID del perfil de carga.
     */
    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del conector asociado al perfil de carga.
     *
     * @return ID del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el ID del conector asociado al perfil de carga.
     *
     * @param connectorId ID del conector. Debe ser mayor o igual a 0.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }

    /**
     * Obtiene el propósito del perfil de carga.
     *
     * @return Propósito del perfil de carga.
     */
    public ChargingProfilePurposeType getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    /**
     * Establece el propósito del perfil de carga.
     *
     * @param chargingProfilePurpose Propósito del perfil de carga.
     */
    @XmlElement
    public void setChargingProfilePurpose(ChargingProfilePurposeType chargingProfilePurpose) {
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    /**
     * Obtiene el nivel de la pila del perfil de carga.
     *
     * @return Nivel de la pila.
     */
    public Integer getStackLevel() {
        return stackLevel;
    }

    /**
     * Establece el nivel de la pila del perfil de carga.
     *
     * @param stackLevel Nivel de la pila. Debe ser mayor o igual a 0.
     */
    @XmlElement
    public void setStackLevel(Integer stackLevel) {
        this.stackLevel = stackLevel;
    }

    /**
     * Valida que los valores de los campos sean correctos.
     *
     * @return {@code true} si la solicitud es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return (connectorId == null || connectorId >= 0) &&
                (stackLevel == null || stackLevel >= 0);
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearChargingProfileRequest that = (ClearChargingProfileRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(connectorId, that.connectorId) &&
                chargingProfilePurpose == that.chargingProfilePurpose &&
                Objects.equals(stackLevel, that.stackLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, connectorId, chargingProfilePurpose, stackLevel);
    }

    @Override
    public String toString() {
        return "ClearChargingProfileRequest{" +
                "id=" + id +
                ", connectorId=" + connectorId +
                ", chargingProfilePurpose=" + chargingProfilePurpose +
                ", stackLevel=" + stackLevel +
                ", isValid=" + validate() +
                '}';
    }
}