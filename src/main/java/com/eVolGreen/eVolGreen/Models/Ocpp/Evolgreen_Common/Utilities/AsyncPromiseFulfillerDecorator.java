package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Decorador para la clase {@link PromiseFulfiller} que añade funcionalidad de ejecución
 * asíncrona usando un pool de hilos. Esta clase es útil para manejar promesas de manera
 * concurrente sin bloquear el flujo principal de la aplicación.
 */
public class AsyncPromiseFulfillerDecorator implements PromiseFulfiller {

    private final PromiseFulfiller promiseFulfiller;
    private static ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Establece un nuevo ejecutor (pool de hilos) para el manejo de las promesas.
     *
     * @param newExecutor el nuevo {@link ExecutorService} a utilizar para la ejecución asíncrona.
     */
    public static void setExecutor(ExecutorService newExecutor) {
        executor = newExecutor;
    }

    /**
     * Constructor que inicializa el decorador con una implementación de {@link PromiseFulfiller}.
     *
     * @param promiseFulfiller una instancia de {@link PromiseFulfiller} a la que se delegará
     *                         la lógica principal.
     */
    public AsyncPromiseFulfillerDecorator(PromiseFulfiller promiseFulfiller) {
        this.promiseFulfiller = promiseFulfiller;
    }

    /**
     * Método que delega la tarea de completar la promesa de forma asíncrona. Usa un pool de hilos
     * para ejecutar la tarea en un hilo separado, evitando el bloqueo del hilo principal.
     *
     * @param promise      la promesa {@link CompletableFuture} que se debe cumplir.
     * @param eventHandler el manejador de eventos {@link SessionEvents} que recibirá la confirmación.
     * @param request      la solicitud {@link Request} que desencadena la acción.
     */
    @Override
    public void fulfill(CompletableFuture<Confirmation> promise, SessionEvents eventHandler, Request request) {
        executor.submit(() -> promiseFulfiller.fulfill(promise, eventHandler, request));
    }
}
