package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ModelUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Representa una solicitud de autorización en OCPP 1.6.
 * Esta solicitud es enviada por la estación de carga al sistema central para autorizar un idTag.
 */
@XmlRootElement
public class AuthorizeRequest extends RequestWithId {

    // Constantes
    private static final int IDTAG_MAX_LENGTH = 20;
    private static final String ERROR_MESSAGE = "idTag no puede superar los " + IDTAG_MAX_LENGTH + " caracteres";

    @NotNull(message = "El idTag es obligatorio.")
    @Size(max = IDTAG_MAX_LENGTH, message = ERROR_MESSAGE)
    @JsonProperty("idTag")
    private String idTag;

    /**
     * Constructor por defecto.
     * Requerido para la serialización y deserialización de la solicitud.
     */
    public AuthorizeRequest() {
    }

    /**
     * Constructor que inicializa la solicitud con el idTag.
     *
     * @param idTag el identificador que se debe autorizar.
     *              Debe ser una cadena de máximo 20 caracteres.
     */
    public AuthorizeRequest(String idTag) {
        setIdTag(idTag);
    }

    /**
     * Obtiene el idTag que se debe autorizar.
     *
     * @return una cadena que representa el idTag (máximo 20 caracteres).
     */
    public String getIdTag() {
        return idTag;
    }

    /**
     * Establece el idTag que debe autorizarse.
     * Este valor debe cumplir con las restricciones de longitud (máximo 20 caracteres).
     *
     * @param idTag una cadena que representa el idTag (máximo 20 caracteres).
     * @throws IllegalArgumentException si el idTag excede los 20 caracteres.
     */
    @XmlElement
    public void setIdTag(String idTag) {
        if (!ModelUtil.validate(idTag, IDTAG_MAX_LENGTH)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.idTag = idTag;
    }

    /**
     * Valida que el idTag cumpla con las reglas de longitud.
     *
     * @return {@code true} si el idTag es válido, {@code false} de lo contrario.
     */
    public boolean validate() {
        return ModelUtil.validate(idTag, IDTAG_MAX_LENGTH);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     * Para AuthorizeRequest, esto siempre es falso ya que la autorización
     * ocurre antes de que comience una transacción.
     *
     * @return {@code false} ya que AuthorizeRequest no está directamente relacionado con una transacción.
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
     * Compara si dos solicitudes de autorización son equivalentes basadas en el idTag.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizeRequest that = (AuthorizeRequest) o;
        return idTag.equals(that.idTag);
    }

    /**
     * Genera un código hash único basado en el idTag.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return idTag.hashCode();
    }

    /**
     * Devuelve una representación en cadena de la solicitud de autorización.
     *
     * @return una cadena que representa la solicitud, incluyendo el idTag y si es válido.
     */
    @Override
    public String toString() {
        return "AuthorizeRequest{" +
                "idTag='" + idTag + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}
