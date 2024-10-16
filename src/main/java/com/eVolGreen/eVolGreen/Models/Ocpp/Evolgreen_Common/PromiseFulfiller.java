package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

//import com.evolgreen.ocpp.SessionEvents;
//import com.evolgreen.ocpp.model.Confirmation;
//import com.evolgreen.ocpp.model.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;

import java.util.concurrent.CompletableFuture;

/**
 * Interfaz que define el comportamiento para completar promesas de manera asíncrona.
 * Implementa la lógica para gestionar promesas {@link CompletableFuture} que esperan
 * recibir una confirmación en respuesta a una solicitud.
 */
public interface PromiseFulfiller {

    /**
     * Método responsable de completar la promesa vinculada a una solicitud OCPP.
     * El objetivo es cumplir la promesa una vez que el evento asociado se haya procesado
     * exitosamente o en caso de que se produzca un error.
     *
     * @param promise      el {@link CompletableFuture} que representa la promesa a ser cumplida.
     * @param eventHandler el manejador de eventos {@link SessionEvents} que recibirá la confirmación.
     * @param request      la solicitud {@link Request} que desencadena el cumplimiento de la promesa.
     */
    void fulfill(CompletableFuture<Confirmation> promise, SessionEvents eventHandler, Request request);
}
