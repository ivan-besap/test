package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonRootName;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa una solicitud enviada por el sistema central al punto de carga para limpiar la caché.
 * Esta solicitud no contiene ningún valor en sus propiedades y siempre es válida.
 */
@XmlRootElement
@JsonRootName("ClearCacheRequest")
public class ClearCacheRequest extends RequestWithId {

    /**
     * Constructor por defecto.
     */
    public ClearCacheRequest() {}

    /**
     * Valida la solicitud.
     * Esta solicitud siempre es válida, por lo que siempre retorna true.
     *
     * @return true, ya que la solicitud siempre es válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que la limpieza de caché no está directamente relacionada con una transacción.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todas las instancias de ClearCacheRequest son iguales
    }

    @Override
    public int hashCode() {
        return Objects.hash(ClearCacheRequest.class);
    }

    @Override
    public String toString() {
        return "ClearCacheRequest{isValid=" + validate() + "}";
    }
}