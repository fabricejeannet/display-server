package web;

import org.restlet.Component;
import org.restlet.Context;
import org.restlet.data.Protocol;
import web.application.ApiApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by fabricejeannet on 12/10/2016.
 */
public class DisplayServer {
    public static void main(String[] args) throws Exception {
        String port = System.getenv("PORT");
        if(port == null || port.equals("")) {
            port = "8080";
        }
        LOGGER.log(Level.INFO, "Server listening on port " + port);
        new DisplayServer(Integer.parseInt(port)).start();
    }

    public DisplayServer(int port) {
        component = new Component();
        component.getServers().add(Protocol.HTTP, port);
        ApiApplication application = new ApiApplication(new Context());
        component.getDefaultHost().attach(application);
    }

    public void start() throws Exception {
        component.start();
    }

    public void stop() throws Exception {
        component.stop();
    }

    private final static Logger LOGGER = Logger.getLogger("Server");
    private final Component component;
}
