package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import java.time.ZonedDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adaptador para convertir entre {@link ZonedDateTime} y su representación en {@link String}.
 * <p>
 * Este adaptador es utilizado por JAXB para transformar instancias de {@link ZonedDateTime} en
 * cadenas de texto (y viceversa) durante el proceso de serialización/deserialización XML.
 * <p>
 * Este proceso es especialmente útil cuando necesitamos trabajar con fechas y tiempos que incluyen
 * la zona horaria.
 * <p>
 * Ejemplo de una representación de un {@link ZonedDateTime} en formato de cadena: "2024-09-12T13:45:30+01:00[Europe/London]".
 * <p>
 * Este adaptador garantiza que las fechas y horas se manejen correctamente tanto al leer como al escribir XML.
 */
public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

    /**
     * Convierte una cadena de texto en una instancia de {@link ZonedDateTime}.
     *
     * @param string la representación de la fecha y hora en formato de texto.
     * @return una instancia de {@link ZonedDateTime} correspondiente a la cadena de texto proporcionada.
     * @throws Exception si la cadena no puede ser analizada correctamente en un {@link ZonedDateTime}.
     */
    @Override
    public ZonedDateTime unmarshal(String string) throws Exception {
        return ZonedDateTime.parse(string);
    }

    /**
     * Convierte una instancia de {@link ZonedDateTime} en una cadena de texto.
     *
     * @param zonedDateTime la instancia de {@link ZonedDateTime} que se desea convertir.
     * @return una cadena de texto que representa el valor de {@link ZonedDateTime}.
     * @throws Exception si ocurre un error durante la conversión.
     */
    @Override
    public String marshal(ZonedDateTime zonedDateTime) throws Exception {
        return zonedDateTime.toString();
    }
}
