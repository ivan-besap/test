package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Utils.IdTagInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.StopTransactionRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación de StopTransaction en OCPP 1.6.
 * Enviada por el sistema central a la estación de carga en respuesta a una {@link StopTransactionRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code idTagInfo}: Información sobre el estado de autorización, expiración e ID padre (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.ACCEPTED);
 *     StopTransactionConfirmation confirmation = new StopTransactionConfirmation(idTagInfo);
 *
 *     if (confirmation.validate()) {
 *         System.out.println("Confirmación válida: " + confirmation);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "stopTransactionResponse")
public class StopTransactionConfirmation extends Confirmation {

    @JsonProperty("idTagInfo")
    private IdTagInfo idTagInfo;

    /**
     * Constructor por defecto.
     */
    public StopTransactionConfirmation() {}

    /**
     * Constructor con campos.
     *
     * @param idTagInfo Información sobre la etiqueta de identificación.
     */
    public StopTransactionConfirmation(IdTagInfo idTagInfo) {
        this.idTagInfo = idTagInfo;
    }

    /**
     * Obtiene la información sobre la etiqueta de identificación.
     * Esta información contiene detalles sobre el estado de autorización, expiración e ID padre.
     * Puede ser null si la transacción se detuvo sin un identificador.
     *
     * @return la {@link IdTagInfo}.
     */
    public IdTagInfo getIdTagInfo() {
        return idTagInfo;
    }

    /**
     * Establece la información sobre la etiqueta de identificación.
     * Es opcional, ya que una transacción puede haberse detenido sin un identificador.
     *
     * @param idTagInfo la {@link IdTagInfo}.
     */
    @XmlElement
    public void setIdTagInfo(IdTagInfo idTagInfo) {
        this.idTagInfo = idTagInfo;
    }

    /**
     * Valida la confirmación.
     * La confirmación es válida si idTagInfo es null o si idTagInfo es válido.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return idTagInfo == null || idTagInfo.validate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StopTransactionConfirmation that = (StopTransactionConfirmation) o;
        return Objects.equals(idTagInfo, that.idTagInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTagInfo);
    }

    @Override
    public String toString() {
        return "StopTransactionConfirmation{" +
                "idTagInfo=" + idTagInfo +
                ", isValid=" + validate() +
                '}';
    }
}