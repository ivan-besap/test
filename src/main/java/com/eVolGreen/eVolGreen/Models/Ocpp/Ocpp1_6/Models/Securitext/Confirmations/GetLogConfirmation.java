package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.LogStatusEnumType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Validation.StringMaxLengthValidationRule;


import java.util.Objects;

/**
 * Representa la confirmación para una solicitud de obtención de logs en un punto de carga.
 * Esta confirmación incluye el estado de la solicitud y, opcionalmente, el nombre del archivo de logs.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     GetLogConfirmation confirmation = new GetLogConfirmation(LogStatusEnumType.Accepted);
 *     confirmation.setFilename("logfile.txt");
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class GetLogConfirmation extends Confirmation {

    private static final transient Validator filenameValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(255))
                    .build();

    private LogStatusEnumType status;
    private String filename;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private GetLogConfirmation() {
    }

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param status Estado de la solicitud de log. Ver {@link #setStatus(LogStatusEnumType)}.
     */
    public GetLogConfirmation(LogStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Indica si el punto de carga fue capaz de aceptar la solicitud.
     *
     * @return {@link LogStatusEnumType} que representa el estado de la solicitud.
     */
    public LogStatusEnumType getStatus() {
        return status;
    }

    /**
     * Campo obligatorio. Establece el estado de la solicitud de log.
     *
     * @param status {@link LogStatusEnumType} que indica si la solicitud fue aceptada o no.
     */
    public void setStatus(LogStatusEnumType status) {
        this.status = status;
    }

    /**
     * Contiene el nombre del archivo de log que será subido.
     * Este campo no está presente si no hay información de logs disponible.
     *
     * @return nombre del archivo de log, como {@link String}, de longitud máxima de 255 caracteres.
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Campo opcional. Establece el nombre del archivo de log que será subido.
     * La longitud máxima es de 255 caracteres.
     *
     * @param filename Nombre del archivo de log.
     */
    public void setFilename(String filename) {
        filenameValidator.validate(filename);
        this.filename = filename;
    }

    /**
     * Valida si la confirmación es válida, comprobando que el estado no sea nulo y que el nombre del archivo sea válido.
     *
     * @return {@code true} si la confirmación es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null && filenameValidator.safeValidate(filename);
    }

    /**
     * Compara si dos confirmaciones de logs son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetLogConfirmation that = (GetLogConfirmation) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(filename, that.filename);
    }

    /**
     * Genera un código hash único basado en el estado y el nombre del archivo.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status, filename);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de obtención de logs.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado y el nombre del archivo.
     */
    @Override
    public String toString() {
        return "GetLogConfirmation{" +
                "status=" + status +
                ", filename='" + filename + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}
