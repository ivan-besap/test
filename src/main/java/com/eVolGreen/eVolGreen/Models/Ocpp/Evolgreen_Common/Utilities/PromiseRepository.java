package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

//import com.eVolGreen.eVolGreen.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repositorio para gestionar las promesas asociadas a las solicitudes OCPP en eVolGreen.
 */
public class PromiseRepository implements IPromiseRepository {

    // Mapa concurrente para almacenar las promesas asociadas a solicitudes OCPP
    private Map<String, CompletableFuture<Confirmation>> promises;

    /**
     * Constructor que inicializa el mapa de promesas.
     */
    public PromiseRepository() {
        this.promises = new ConcurrentHashMap<>();
    }

    /**
     * Crea una promesa de devolución para una solicitud específica.
     *
     * @param uniqueId el identificador único de la solicitud.
     * @return una instancia de {@link CompletableFuture} para manejar la confirmación.
     */
    @Override
    public CompletableFuture<Confirmation> createPromise(String uniqueId) {
        CompletableFuture<Confirmation> promise = new CompletableFuture<>();
        promises.put(uniqueId, promise);
        return promise;
    }

    /**
     * Obtiene la promesa almacenada para una solicitud.
     *
     * @param uniqueId el identificador único de la solicitud.
     * @return un {@link Optional} que contiene la promesa si está presente.
     */
    @Override
    public Optional<CompletableFuture<Confirmation>> getPromise(String uniqueId) {
        return Optional.ofNullable(promises.get(uniqueId));
    }

    /**
     * Elimina la promesa almacenada asociada a una solicitud.
     *
     * @param uniqueId el identificador único de la solicitud.
     */
    @Override
    public void removePromise(String uniqueId) {
        promises.remove(uniqueId);
    }
}
