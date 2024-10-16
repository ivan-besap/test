package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.ZonedDateTime;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 * La clase {@code SugarUtil} proporciona utilidades comunes para manipular y convertir
 * diferentes tipos de datos, incluyendo transformaciones de fechas, documentos XML y mensajes SOAP.
 * Esta clase simplifica tareas repetitivas como la conversión de objetos a cadenas.
 */
public class SugarUtil {

    /**
     * Convierte un objeto {@link ZonedDateTime} en su representación de cadena de texto.
     * Si la fecha proporcionada es {@code null}, devuelve una cadena vacía.
     *
     * @param zonedDateTime el objeto {@code ZonedDateTime} a convertir.
     * @return la representación en texto de {@code zonedDateTime} o una cadena vacía si es nulo.
     */
    public static String zonedDateTimeToString(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) return "";
        return zonedDateTime.toString();
    }

    /**
     * Convierte un objeto {@link Document} (un documento XML) en su representación de cadena de texto.
     *
     * @param doc el documento XML a convertir.
     * @return la representación en texto del documento.
     * @throws RuntimeException si ocurre un error durante la conversión.
     */
    public static String docToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error al convertir el documento XML a cadena", ex);
        }
    }

    /**
     * Convierte un objeto {@link SOAPMessage} en su representación de cadena de texto.
     * Si ocurre un error durante la conversión, devuelve una cadena vacía.
     *
     * @param message el mensaje SOAP a convertir.
     * @return la representación en texto del mensaje SOAP o una cadena vacía si ocurre un error.
     */
    public static String soapMessageToString(SOAPMessage message) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            message.writeTo(out);
        } catch (SOAPException | IOException e) {
            return "";
        }
        return new String(out.toByteArray());
    }
}
