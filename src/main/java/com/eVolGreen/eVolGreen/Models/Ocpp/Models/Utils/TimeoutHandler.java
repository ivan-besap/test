package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils;

/**
 * La interfaz {@code TimeoutHandler} define un contrato para manejar eventos de tiempo de espera.
 * Implementando esta interfaz, una clase puede definir su comportamiento específico cuando se
 * produce un timeout en el contexto de una operación.
 */
public interface TimeoutHandler {

    /**
     * Método a implementar para gestionar el evento de timeout.
     * Este método se ejecutará cuando ocurra un tiempo de espera y permitirá definir
     * qué acción tomar en respuesta a este evento.
     */
    void timeout();
}
