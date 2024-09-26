package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.DataTransferStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

/**
 * Representa la confirmación enviada como respuesta a una solicitud de transferencia de datos.
 * Esta confirmación puede ser enviada tanto por el punto de carga al sistema central como viceversa.
 *
 * <b>Campos:</b>
 * - {@code status}: Estado de la transferencia de datos (obligatorio).
 * - {@code data}: Datos opcionales en respuesta a la solicitud.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     DataTransferConfirmation confirmation = new DataTransferConfirmation(DataTransferStatus.Accepted);
 *     confirmation.setData("{\"result\": \"success\"}");
 *
 *     if (confirmation.validate()) {
 *         System.out.println("Confirmación válida: " + confirmation);
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "dataTransferResponse")
@XmlType(propOrder = {"status", "data"})
public class DataTransferConfirmation extends Confirmation {

    @NotNull(message = "El estado es obligatorio")
    @JsonProperty("status")
    private DataTransferStatus status;

    @JsonProperty("data")
    private String data;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #DataTransferConfirmation(DataTransferStatus)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public DataTransferConfirmation() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param status Estado de la transferencia de datos.
     */
    public DataTransferConfirmation(DataTransferStatus status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la transferencia de datos.
     *
     * @return Estado de la transferencia de datos.
     */
    public DataTransferStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de la transferencia de datos.
     *
     * @param status Estado de la transferencia de datos. No puede ser nulo.
     */
    @XmlElement
    public void setStatus(DataTransferStatus status) {
        this.status = status;
    }

    /**
     * Obtiene el estado de la transferencia de datos.
     *
     * @return Estado de la transferencia de datos.
     * @deprecated Use {@link #getStatus()} en su lugar.
     */
    @Deprecated
    public DataTransferStatus objStatus() {
        return status;
    }

    /**
     * Obtiene los datos opcionales en respuesta a la solicitud.
     *
     * @return Datos en respuesta a la solicitud.
     */
    public String getData() {
        return data;
    }

    /**
     * Establece los datos opcionales en respuesta a la solicitud.
     *
     * @param data Datos en respuesta a la solicitud.
     */
    @XmlElement
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Valida que el campo obligatorio status esté presente.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return this.status != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTransferConfirmation that = (DataTransferConfirmation) o;
        return status == that.status && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data);
    }

    @Override
    public String toString() {
        return "DataTransferConfirmation{" +
                "status=" + status +
                ", data='" + data + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}