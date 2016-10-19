package web.resources;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.io.IOException;

/**
 * Created by fabricejeannet on 19/10/2016.
 */
public class HelloResource extends ServerResource {

    @Get
    public String hello() throws IOException {
        return "<h1>Welcome to DisplayServer API</h1>";
    }

}
