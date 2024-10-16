package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de transferencia de datos que puede ser enviada tanto por el sistema central como por el punto de carga.
 * Esta clase permite la transferencia de datos específicos del vendedor entre el sistema central y el punto de carga.
 *
 * <b>Campos:</b>
 * - {@code vendorId}: Identificador del vendedor (obligatorio).
 * - {@code messageId}: Identificador adicional del mensaje (opcional).
 * - {@code data}: Datos a transferir sin formato específico (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     DataTransferRequest request = new DataTransferRequest("VendorXYZ");
 *     request.setMessageId("CustomMessage001");
 *     request.setData("{\"key\": \"value\"}");
 *
 *     if (request.validate()) {
 *         // Enviar la solicitud
 *         System.out.println("Solicitud válida: " + request);
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
@XmlType(propOrder = {"vendorId", "messageId", "data"})
public class DataTransferRequest extends RequestWithId {

    private static final String ERROR_MESSAGE = "Excedido el límite de %s caracteres";

    @NotNull(message = "El vendorId es obligatorio")
    @Size(max = 255, message = "El vendorId no puede exceder los 255 caracteres")
    @JsonProperty("vendorId")
    private String vendorId;

    @Size(max = 50, message = "El messageId no puede exceder los 50 caracteres")
    @JsonProperty("messageId")
    private String messageId;

    @JsonProperty("data")
    private String data;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #DataTransferRequest(String)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public DataTransferRequest() {}

    /**
     * Constructor con el campo obligatorio.
     *
     * @param vendorId Identificador del vendedor.
     */
    public DataTransferRequest(String vendorId) {
        setVendorId(vendorId);
    }

    /**
     * Obtiene el identificador del vendedor.
     *
     * @return Identificador del vendedor.
     */
    public String getVendorId() {
        return vendorId;
    }

    /**
     * Establece el identificador del vendedor.
     *
     * @param vendorId Identificador del vendedor. No puede ser nulo y debe tener máximo 255 caracteres.
     */
    @XmlElement
    public void setVendorId(String vendorId) {
        if (vendorId != null && vendorId.length() > 255) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, 255));
        }
        this.vendorId = vendorId;
    }

    /**
     * Obtiene el identificador adicional del mensaje.
     *
     * @return Identificador adicional del mensaje.
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Establece el identificador adicional del mensaje.
     *
     * @param messageId Identificador adicional del mensaje. Debe tener máximo 50 caracteres.
     */
    @XmlElement
    public void setMessageId(String messageId) {
        if (messageId != null && messageId.length() > 50) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, 50));
        }
        this.messageId = messageId;
    }

    /**
     * Obtiene los datos a transferir.
     *
     * @return Datos a transferir.
     */
    public String getData() {
        return data;
    }

    /**
     * Establece los datos a transferir.
     *
     * @param data Datos a transferir sin formato específico.
     */
    @XmlElement
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Valida que los campos obligatorios estén presentes y sean válidos.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return vendorId != null && !vendorId.isEmpty() && vendorId.length() <= 255;
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
        DataTransferRequest that = (DataTransferRequest) o;
        return Objects.equals(vendorId, that.vendorId) &&
                Objects.equals(messageId, that.messageId) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorId, messageId, data);
    }

    @Override
    public String toString() {
        return "DataTransferRequest{" +
                "vendorId='" + vendorId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", data='" + data + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}