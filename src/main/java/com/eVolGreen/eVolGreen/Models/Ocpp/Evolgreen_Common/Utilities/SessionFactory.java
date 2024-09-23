package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

//import com.eVolGreen.eVolGreen.Communication.Communicator;
//import com.eVolGreen.eVolGreen.Features.IFeatureRepository;
//import com.eVolGreen.eVolGreen.Promise.AsyncPromiseFulfillerDecorator;
//import com.eVolGreen.eVolGreen.Promise.SimplePromiseFulfiller;
//import com.eVolGreen.eVolGreen.Queue.Queue;

/**
 * Fábrica de sesiones para crear nuevas instancias de sesiones en eVolGreen.
 */
public class SessionFactory implements ISessionFactory {

    private final IFeatureRepository featureRepository;

    /**
     * Constructor que recibe el repositorio de características.
     *
     * @param featureRepository el repositorio de características que se usará en las sesiones.
     */
    public SessionFactory(IFeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    /**
     * Crea una nueva sesión con el comunicador proporcionado.
     *
     * @param communicator el comunicador que manejará la comunicación de la sesión.
     * @return una nueva instancia de {@link ISession}.
     */
    @Override
    public ISession createSession(Communicator communicator) {
        // Decorador para manejar la promesa de forma asíncrona
        AsyncPromiseFulfillerDecorator promiseFulfiller = new AsyncPromiseFulfillerDecorator(new SimplePromiseFulfiller());

        // Crea y devuelve una nueva sesión usando el comunicador, una cola, el decorador de promesas y el repositorio de características.
        return new Session(communicator, new Queue(), promiseFulfiller, this.featureRepository);
    }
}
