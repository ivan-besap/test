package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.GetDiagnosticsRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central tras la solicitud {@link GetDiagnosticsRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code fileName}: Nombre del archivo con la información de diagnóstico (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     GetDiagnosticsConfirmation confirmacion = new GetDiagnosticsConfirmation();
 *     confirmacion.setFileName("diagnostico_20230101.log");
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "getDiagnosticsResponse")
public class GetDiagnosticsConfirmation extends Confirmation {

    @JsonProperty("fileName")
    @Size(max = 255, message = "El nombre del archivo no debe exceder los 255 caracteres")
    private String fileName;

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public GetDiagnosticsConfirmation() {}

    /**
     * Obtiene el nombre del archivo con la información de diagnóstico.
     *
     * @return String, el nombre del archivo.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Establece el nombre del archivo con la información de diagnóstico.
     *
     * @param fileName String, el nombre del archivo. No debe exceder los 255 caracteres.
     * @throws IllegalArgumentException si el nombre del archivo excede los 255 caracteres.
     */
    @XmlElement
    public void setFileName(String fileName) {
        if (fileName != null && fileName.length() > 255) {
            throw new IllegalArgumentException("El nombre del archivo excede el límite de 255 caracteres");
        }
        this.fileName = fileName;
    }

    /**
     * Valida la confirmación. Siempre devuelve true ya que {@code fileName} es opcional.
     *
     * @return true, indicando que la confirmación siempre es válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDiagnosticsConfirmation that = (GetDiagnosticsConfirmation) o;
        return Objects.equals(fileName, that.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }

    @Override
    public String toString() {
        return "GetDiagnosticsConfirmation{" +
                "fileName='" + fileName + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}