package web.application;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import persistence.Repositories;
import persistence.memory.MemoryRepositories;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiApplication extends Application {


    public ApiApplication(Context context) {
        super(context);
    }

    @Override
    public synchronized void start() throws Exception {
        LOGGER.log(Level.INFO, "Démarrage de l'API");
        super.start();
        Repositories.initialise(new MemoryRepositories());
    }

    @Override
    public Restlet createInboundRoot() {
        FiltreCors filtreCors = new FiltreCors(getContext());
        filtreCors.setNext(new ApiRouteur(getContext()));
        return filtreCors;
    }

    private final Logger LOGGER = Logger.getLogger(this.getClass().toString());
}
