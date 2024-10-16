package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Interfaz para la gestión del repositorio de promesas en eVolGreen.
 */
public interface IPromiseRepository {

    /**
     * Crea una nueva promesa para una solicitud con un identificador único.
     *
     * @param uniqueId el identificador único de la solicitud.
     * @return una {@link CompletableFuture} para manejar la confirmación.
     */
    CompletableFuture<Confirmation> createPromise(String uniqueId);

    /**
     * Obtiene la promesa almacenada para una solicitud específica.
     *
     * @param uniqueId el identificador único de la solicitud.
     * @return un {@link Optional} que contiene la promesa si existe.
     */
    Optional<CompletableFuture<Confirmation>> getPromise(String uniqueId);

    /**
     * Elimina la promesa almacenada para la solicitud identificada por su ID único.
     *
     * @param uniqueId el identificador único de la solicitud.
     */
    void removePromise(String uniqueId);
}
