package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.FirmwareStatusNotificationRequest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Sistema Central al Punto de Carga en respuesta a una solicitud de
 * {@link FirmwareStatusNotificationRequest}.
 * Esta confirmación no tiene atributos adicionales, pero asegura que la solicitud de notificación del estado del firmware
 * ha sido recibida correctamente.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     FirmwareStatusNotificationConfirmation confirmacion = new FirmwareStatusNotificationConfirmation();
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "firmwareStatusNotificationResponse")
public class FirmwareStatusNotificationConfirmation extends Confirmation {

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public FirmwareStatusNotificationConfirmation() {}

    /**
     * Valida la confirmación. Como esta clase no tiene campos adicionales, siempre devuelve {@code true}.
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
        return true; // Todas las instancias de esta clase son iguales
    }

    @Override
    public int hashCode() {
        return Objects.hash(FirmwareStatusNotificationConfirmation.class);
    }

    @Override
    public String toString() {
        return "FirmwareStatusNotificationConfirmation{isValid=" + validate() + "}";
    }
}