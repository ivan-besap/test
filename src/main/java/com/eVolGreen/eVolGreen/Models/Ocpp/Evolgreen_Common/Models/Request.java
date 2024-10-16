package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models;

import java.util.UUID;

/**
 * Interfaz utilizada para marcar un modelo como una solicitud (Request) en el sistema eVolGreen.
 * Define los métodos necesarios para identificar una solicitud y validar su estado.
 */
public interface Request extends Validatable {

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return true si la solicitud está relacionada con una transacción, de lo contrario false.
     */
    boolean transactionRelated();

    /**
     * Obtiene el identificador único de la solicitud en el protocolo OCPP.
     *
     * @return el identificador de la solicitud en formato de cadena.
     */
    String getOcppMessageId();

    /**
     * Establece el identificador único de la solicitud en el protocolo OCPP.
     *
     * @param id el identificador único que se va a asignar.
     */
    void setOcppMessageId(String id);

    String getAction();

    UUID getSessionIndex();
}
