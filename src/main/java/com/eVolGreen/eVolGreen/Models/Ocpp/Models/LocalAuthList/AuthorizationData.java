package com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Validatable;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.IdTagInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa los datos de autorización local asociados a un {@code idTag}.
 * Esta clase contiene la información necesaria para autorizar una operación, como el {@code idTag} y la información adicional {@code idTagInfo}.
 * Implementa la interfaz {@link Validatable} para asegurar que los datos sean válidos.
 *
 * <b>Campos:</b>
 * - {@code idTag}: El identificador de la etiqueta (obligatorio).
 * - {@code idTagInfo}: Información adicional sobre la etiqueta (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     AuthorizationData data = new AuthorizationData("1234567890");
 *     IdTagInfo info = new IdTagInfo(AuthorizationStatus.ACCEPTED);
 *     data.setIdTagInfo(info);
 *
 *     if (data.validate()) {
 *         System.out.println("Datos de autorización válidos: " + data);
 *     } else {
 *         System.out.println("Datos de autorización inválidos");
 *     }
 * </pre>
 */
@XmlRootElement
public class AuthorizationData implements Validatable {

    private static final int ID_TAG_MAX_LENGTH = 20;

    @NotNull(message = "El campo idTag es obligatorio")
    @Size(max = ID_TAG_MAX_LENGTH, message = "idTag no puede exceder los " + ID_TAG_MAX_LENGTH + " caracteres")
    @JsonProperty("idTag")
    private String idTag;

    @JsonProperty("idTagInfo")
    private IdTagInfo idTagInfo;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #AuthorizationData(String)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public AuthorizationData() {}

    /**
     * Construye una nueva instancia de AuthorizationData con el idTag especificado.
     *
     * @param idTag El identificador de la etiqueta.
     */
    public AuthorizationData(String idTag) {
        setIdTag(idTag);
    }

    /**
     * Obtiene el identificador de la etiqueta.
     *
     * @return String, el idTag.
     */
    public String getIdTag() {
        return idTag;
    }

    /**
     * Establece el identificador de la etiqueta.
     *
     * @param idTag String, el idTag.
     * @throws IllegalArgumentException si idTag excede el límite de caracteres.
     */
    @XmlElement
    public void setIdTag(String idTag) {
        if (idTag != null && idTag.length() > ID_TAG_MAX_LENGTH) {
            throw new IllegalArgumentException("idTag no puede exceder los " + ID_TAG_MAX_LENGTH + " caracteres");
        }
        this.idTag = idTag;
    }

    /**
     * Obtiene la información adicional de la etiqueta.
     *
     * @return {@link IdTagInfo}, la información adicional.
     */
    public IdTagInfo getIdTagInfo() {
        return idTagInfo;
    }

    /**
     * Establece la información adicional de la etiqueta.
     *
     * @param idTagInfo {@link IdTagInfo}, la información adicional.
     * @throws IllegalArgumentException si idTagInfo no es válido.
     */
    @XmlElement
    public void setIdTagInfo(IdTagInfo idTagInfo) {
        if (idTagInfo != null && !idTagInfo.validate()) {
            throw new IllegalArgumentException("IdTagInfo no es válido");
        }
        this.idTagInfo = idTagInfo;
    }

    /**
     * Valida si los datos de autorización son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return idTag != null && idTag.length() <= ID_TAG_MAX_LENGTH && (idTagInfo == null || idTagInfo.validate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationData that = (AuthorizationData) o;
        return Objects.equals(idTag, that.idTag) && Objects.equals(idTagInfo, that.idTagInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTag, idTagInfo);
    }

    @Override
    public String toString() {
        return "AuthorizationData{" +
                "idTag='" + idTag + '\'' +
                ", idTagInfo=" + idTagInfo +
                ", isValid=" + validate() +
                '}';
    }
}