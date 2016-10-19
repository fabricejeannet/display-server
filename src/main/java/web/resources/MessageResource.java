package web.resources;

import command.message.MessageCommand;
import domain.Message;
import domain.MessageInformations;
import org.codehaus.jackson.map.ObjectMapper;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import persistence.Repositories;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

/**
 * Created by fabricejeannet on 10/10/2016.
 */
public class MessageResource extends ServerResource {

    @Post("json")
    public String postMessages(Representation representation) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        MessageInformations messageInformations = mapper.readValue(representation.getText(), MessageInformations.class);

        MessageCommand command = new MessageCommand(messageInformations);
        UUID idMessage = command.execute();
        return toJson(idMessage);
    }

    @Get("json")
    public String getMessages() throws IOException {
        List<Message> messages = Repositories.messages().all();
        return toJson(messages);
    }

    public String toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final StringWriter sw =new StringWriter();
        mapper.writeValue(sw, object);
        return sw.toString();
    }
}
