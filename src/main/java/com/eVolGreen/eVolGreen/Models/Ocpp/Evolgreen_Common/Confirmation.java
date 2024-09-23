package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase abstracta utilizada para marcar un modelo como un payload de confirmación dentro del sistema eVolGreen.
 * <p>
 * Esta clase actúa como la base para todas las confirmaciones que serán enviadas y recibidas en las interacciones del sistema,
 * garantizando que cada confirmación sea validada antes de ser procesada.
 * </p>
 */
public abstract class Confirmation implements Validatable {

    private transient ConfirmationCompletedHandler completedHandler;

    /**
     * Obtiene el handler asociado con la confirmación cuando ha sido completada.
     *
     * @return El {@link ConfirmationCompletedHandler} asociado, o null si no está configurado.
     */
    @XmlTransient
    public ConfirmationCompletedHandler getCompletedHandler() {
        return completedHandler;
    }

    /**
     * Establece el handler que será llamado una vez que la confirmación haya sido completada.
     *
     * @param completedHandler El {@link ConfirmationCompletedHandler} a establecer.
     */
    public void setCompletedHandler(ConfirmationCompletedHandler completedHandler) {
        this.completedHandler = completedHandler;
    }
}
