package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.AvailabilityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * La clase {@code ChangeAvailabilityRequest} representa una solicitud enviada desde el sistema
 * central para cambiar la disponibilidad de un conector en una estación de carga. Esta clase
 * implementa la interfaz {@link RequestWithId}, que garantiza la capacidad de identificar
 * y gestionar transacciones en el protocolo OCPP.
 *
 * <p>El propósito de esta solicitud es permitir al sistema central indicar si un conector o
 * toda la estación de carga debe estar disponible o no.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 *     ChangeAvailabilityRequest request = new ChangeAvailabilityRequest(0, AvailabilityType.Operative);
 *     request.setConnectorId(1);
 *     request.setType(AvailabilityType.Inoperative);
 * </pre>
 */
@XmlRootElement
public class ChangeAvailabilityRequest extends RequestWithId {

    @NotNull(message = "El ID del conector es obligatorio")
    @Positive(message = "El ID del conector debe ser mayor o igual a 0")
    @JsonProperty("connectorId")
    private Integer connectorId;

    @NotNull(message = "El tipo de disponibilidad es obligatorio")
    @JsonProperty("type")
    private AvailabilityType type;

    /**
     * Constructor por defecto. No establece valores predeterminados.
     * <p>
     * Este constructor es útil cuando se desea crear una instancia vacía y establecer los valores
     * más tarde utilizando los métodos {@link #setConnectorId(Integer)} y {@link #setType(AvailabilityType)}.
     * </p>
     */
    public ChangeAvailabilityRequest() {}

    /**
     * Constructor que inicializa la solicitud con los valores obligatorios.
     *
     * @param connectorId El ID del conector que debe cambiar su disponibilidad. Un valor de 0
     *                    indica que se aplica a toda la estación de carga.
     * @param type El tipo de disponibilidad que se va a aplicar. Debe ser un valor de {@link AvailabilityType}.
     */
    public ChangeAvailabilityRequest(Integer connectorId, AvailabilityType type) {
        this.connectorId = connectorId;
        this.type = type;
    }

    /**
     * Obtiene el ID del conector al que se refiere esta solicitud.
     * Un valor de 0 indica que la solicitud se aplica a toda la estación de carga.
     *
     * @return El ID del conector.
     */
    public Integer getConnectorId() {
        return connectorId;
    }

    /**
     * Establece el ID del conector al que se refiere esta solicitud.
     * Un valor de 0 indica que la solicitud se aplica a toda la estación de carga.
     *
     * @param connectorId El ID del conector, debe ser mayor o igual a 0.
     */
    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }

    /**
     * Obtiene el tipo de disponibilidad que debe aplicarse a este conector.
     *
     * @return El tipo de disponibilidad, como {@link AvailabilityType}.
     */
    public AvailabilityType getType() {
        return type;
    }

    /**
     * Establece el tipo de disponibilidad que debe aplicarse a este conector.
     *
     * @param type El tipo de disponibilidad, como {@link AvailabilityType}.
     */
    public void setType(AvailabilityType type) {
        this.type = type;
    }

    /**
     * Valida la solicitud para asegurar que todos los campos obligatorios están presentes y son válidos.
     *
     * @return {@code true} si la solicitud es válida; {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return connectorId != null && connectorId >= 0 && type != null;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que la solicitud de cambio de disponibilidad no está relacionada con
     *         transacciones de carga.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    /**
     * Devuelve una representación en formato de cadena de texto de esta solicitud, incluyendo su
     * validez actual.
     *
     * @return Una cadena de texto que representa esta solicitud.
     */
    @Override
    public String toString() {
        return "ChangeAvailabilityRequest{" +
                "connectorId=" + connectorId +
                ", type=" + type +
                ", isValid=" + validate() +
                '}';
    }

    @Override
    public UUID getSessionIndex() {
        return null; // O devuelve el UUID específico si aplica.
    }
}
