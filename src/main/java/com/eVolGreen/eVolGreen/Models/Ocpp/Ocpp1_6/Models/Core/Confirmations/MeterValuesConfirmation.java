package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.MeterValuesRequest;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Sistema Central al Punto de Carga en respuesta a una {@link MeterValuesRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 * <p>
 * Esta confirmación no contiene campos adicionales y siempre es válida.
 * </p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     MeterValuesConfirmation confirmacion = new MeterValuesConfirmation();
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "meterValuesResponse")
public class MeterValuesConfirmation extends Confirmation {

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public MeterValuesConfirmation() {}

    /**
     * Valida la confirmación. Siempre devuelve true ya que no hay campos específicos para validar.
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
        return true; // Todas las instancias son iguales ya que no tienen propiedades específicas
    }

    @Override
    public int hashCode() {
        return Objects.hash(MeterValuesConfirmation.class);
    }

    @Override
    public String toString() {
        return "MeterValuesConfirmation{isValid=" + validate() + "}";
    }
}