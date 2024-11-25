package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud enviada desde el Sistema Central al Punto de Carga para establecer un perfil de carga.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code connectorId}: Identifica el conector en el Punto de Carga (obligatorio).
 * - {@code csChargingProfiles}: Contiene el perfil de carga a aplicar (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ChargingProfile chargingProfile = new ChargingProfile(
 *         1, 1, ChargingProfilePurposeType.TX_PROFILE, ChargingProfileKindType.RELATIVE
 *     );
 *     SetChargingProfileRequest solicitud = new SetChargingProfileRequest(1, chargingProfile);
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
public class SetChargingProfileRequest extends RequestWithId {

    @NotNull(message = "El campo connectorId es obligatorio")
    @Min(value = 0, message = "connectorId debe ser >= 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "El campo csChargingProfiles es obligatorio")
    @JsonProperty("csChargingProfiles")
    private ChargingProfile csChargingProfiles;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #SetChargingProfileRequest(Integer, ChargingProfile)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public SetChargingProfileRequest() {}

    /**
     * Construye una nueva instancia de SetChargingProfileRequest con los campos requeridos.
     *
     * @param connectorId El identificador del conector.
     * @param csChargingProfiles El perfil de carga.
     */
    public SetChargingProfileRequest(Integer connectorId, ChargingProfile csChargingProfiles) {
        setConnectorId(connectorId);
        setCsChargingProfiles(csChargingProfiles);
    }

    /**
     * Obtiene el identificador del conector.
     *
     * @return Integer, el identificador del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el identificador del conector.
     *
     * @param connectorId Integer, el identificador del conector.
     * @throws IllegalArgumentException si connectorId es menor que 0.
     */
    @XmlElement
    public void setConnectorId(Integer connectorId) {
        if (connectorId != null && connectorId < 0) {
            throw new IllegalArgumentException("connectorId debe ser >= 0");
        }
        this.connectorId = connectorId;
    }

    /**
     * Obtiene el perfil de carga.
     *
     * @return {@link ChargingProfile}, el perfil de carga.
     */
    public ChargingProfile getCsChargingProfiles() {
        return csChargingProfiles;
    }

    /**
     * Establece el perfil de carga.
     *
     * @param csChargingProfiles {@link ChargingProfile}, el perfil de carga.
     */
    @XmlElement(name = "csChargingProfiles")
    public void setCsChargingProfiles(ChargingProfile csChargingProfiles) {
        this.csChargingProfiles = csChargingProfiles;
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

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        boolean valid = connectorId != null && connectorId >= 0;

        if (csChargingProfiles != null) {
            valid &= csChargingProfiles.validate();
        }

        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetChargingProfileRequest that = (SetChargingProfileRequest) o;
        return Objects.equals(connectorId, that.connectorId) &&
                Objects.equals(csChargingProfiles, that.csChargingProfiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectorId, csChargingProfiles);
    }

    @Override
    public String toString() {
        return "SetChargingProfileRequest{" +
                "connectorId=" + connectorId +
                ", csChargingProfiles=" + csChargingProfiles +
                ", isValid=" + validate() +
                '}';
    }
}