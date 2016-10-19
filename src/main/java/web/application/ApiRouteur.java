package web.application;


import org.restlet.Context;
import org.restlet.routing.Router;
import web.resources.ActiveMessageResource;
import web.resources.MessageResource;

public class ApiRouteur extends Router {
    public ApiRouteur(Context context) {
        super(context);
        attacheRessources();
    }

    private void attacheRessources() {

        attach("/messages", MessageResource.class);
        attach("/messages/active", ActiveMessageResource.class);
    }

}
