package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.UpdateFirmwareRequest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central tras la solicitud {@link UpdateFirmwareRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <p>Esta confirmación no tiene campos adicionales, simplemente confirma la recepción de la solicitud de actualización de firmware.</p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     UpdateFirmwareConfirmation confirmacion = new UpdateFirmwareConfirmation();
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "updateFirmwareResponse")
public class UpdateFirmwareConfirmation extends Confirmation {

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public UpdateFirmwareConfirmation() {}

    /**
     * Valida la confirmación. Siempre retorna true ya que no hay campos que validar.
     *
     * @return {@code true}, indicando que la confirmación siempre es válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todas las instancias son iguales ya que no tienen campos
    }

    @Override
    public int hashCode() {
        return Objects.hash(UpdateFirmwareConfirmation.class);
    }

    @Override
    public String toString() {
        return "UpdateFirmwareConfirmation{" +
                "isValid=" + validate() +
                '}';
    }
}