package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import java.util.Timer;
import java.util.TimerTask;

/**
 * La clase {@code TimeoutTimer} extiende {@link Timer} y proporciona una funcionalidad
 * para gestionar eventos de tiempo de espera (timeout). Permite iniciar, reiniciar y finalizar
 * un temporizador que ejecutará un {@link TimeoutHandler} cuando el tiempo haya expirado.
 */
public class TimeoutTimer extends Timer {

    private TimerTask timerTask;
    private long timeout;
    private TimeoutHandler handler;

    /**
     * Constructor para inicializar el temporizador con un tiempo de espera específico y
     * un manejador de tiempo de espera.
     *
     * @param timeout la duración del timeout en milisegundos.
     * @param handler el manejador que será invocado cuando el tiempo de espera expire.
     */
    public TimeoutTimer(long timeout, TimeoutHandler handler) {
        this.timeout = timeout;
        this.handler = handler;
    }

    /**
     * Establece un nuevo valor de tiempo de espera.
     *
     * @param timeout la nueva duración del timeout en milisegundos.
     */
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    /**
     * Inicia el temporizador, programando la tarea para que se ejecute cuando el tiempo de
     * espera expire.
     */
    public void begin() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.timeout();
            }
        };
        this.schedule(timerTask, timeout);
    }

    /**
     * Finaliza el temporizador cancelando la tarea programada actual.
     */
    public void end() {
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    /**
     * Reinicia el temporizador cancelando la tarea actual y comenzando un nuevo temporizador
     * con el tiempo de espera configurado.
     */
    public void reset() {
        end();
        begin();
    }
}
