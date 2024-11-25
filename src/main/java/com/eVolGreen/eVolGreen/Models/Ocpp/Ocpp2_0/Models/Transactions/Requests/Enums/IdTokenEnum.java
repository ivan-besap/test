package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums;

/**
 * Enumeración de los posibles tipos de identificadores (IdToken) utilizados para autorización
 * en el protocolo OCPP.
 *
 * <p>Los valores de esta enumeración representan diferentes métodos de identificación que pueden
 * ser utilizados por los sistemas de carga y las estaciones de carga para validar usuarios o dispositivos.
 */
public enum IdTokenEnum {

    /**
     * Identificación centralizada, gestionada por un servidor central.
     */
    Central,

    /**
     * Identificador eMAID (Electric Mobility Account Identifier), usado para identificar cuentas de movilidad eléctrica.
     */
    eMAID,

    /**
     * Identificador basado en la tecnología ISO 14443 (común en tarjetas RFID de proximidad).
     */
    ISO14443,

    /**
     * Identificador basado en la tecnología ISO 15693 (usada en tarjetas RFID de largo alcance).
     */
    ISO15693,

    /**
     * Código de acceso manual, como un PIN o clave alfanumérica.
     */
    KeyCode,

    /**
     * Identificación local, gestionada directamente en la estación de carga sin necesidad de conexión a un servidor central.
     */
    Local,

    /**
     * Dirección MAC, utilizada como identificador único en redes.
     */
    MacAddress,

    /**
     * No se requiere autorización. Este tipo puede ser utilizado en entornos abiertos.
     */
    NoAuthorization
}
