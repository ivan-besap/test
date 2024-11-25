package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Utils.IdTagInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.AuthorizeRequest;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación de autorización en OCPP 1.6.
 * Esta confirmación es enviada por el sistema central a la estación de carga en respuesta a una {@link AuthorizeRequest}.
 * Contiene información sobre el estado de la autorización del idTag.
 */
@XmlRootElement
public class AuthorizeConfirmation extends Confirmation {

    @NotNull(message = "idTagInfo es obligatorio")
    private IdTagInfo idTagInfo;

    /**
     * Constructor por defecto.
     * Requerido para la deserialización de la respuesta.
     */
    public AuthorizeConfirmation() {}

    /**
     * Constructor que inicializa la confirmación con la información de idTag.
     *
     * @param idTagInfo objeto que contiene el estado del idTag y la autorización.
     */
    public AuthorizeConfirmation(IdTagInfo idTagInfo) {
        this.idTagInfo = idTagInfo;
    }

    /**
     * Obtiene la información sobre la autorización del idTag.
     *
     * @return un objeto {@link IdTagInfo} que contiene detalles sobre el estado de autorización del idTag.
     */
    public IdTagInfo getIdTagInfo() {
        return idTagInfo;
    }

    /**
     * Establece la información sobre la autorización del idTag.
     *
     * @param idTagInfo objeto que contiene detalles sobre el estado de autorización del idTag.
     */
    @XmlElement
    public void setIdTagInfo(IdTagInfo idTagInfo) {
        this.idTagInfo = idTagInfo;
    }

    /**
     * Valida que la confirmación sea válida.
     * Comprueba que el campo {@code idTagInfo} no sea nulo y que contenga información válida.
     *
     * @return {@code true} si la confirmación es válida, {@code false} de lo contrario.
     */
    public boolean validate() {
        return idTagInfo != null && idTagInfo.validate();
    }

    /**
     * Compara si dos confirmaciones de autorización son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizeConfirmation that = (AuthorizeConfirmation) o;
        return Objects.equals(idTagInfo, that.idTagInfo);
    }

    /**
     * Genera un código hash único basado en el campo idTagInfo.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idTagInfo);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de autorización.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado del idTag.
     */
    @Override
    public String toString() {
        return "AuthorizeConfirmation{" +
                "idTagInfo=" + idTagInfo +
                ", isValid=" + validate() +
                '}';
    }
}
