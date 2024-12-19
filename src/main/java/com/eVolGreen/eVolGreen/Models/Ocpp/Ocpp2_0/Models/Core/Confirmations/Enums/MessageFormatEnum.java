package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Enumeración que representa los formatos posibles de un mensaje.
 *
 * <p>Se utiliza en el contexto del protocolo OCPP 2.0.1 para definir cómo se deben presentar los
 * mensajes en una estación de carga.
 */
public enum MessageFormatEnum {

    /** Formato ASCII. */
    ASCII,

    /** Formato HTML. */
    HTML,

    /** Formato URI. */
    URI,

    /** Formato UTF-8. */
    UTF8
}
