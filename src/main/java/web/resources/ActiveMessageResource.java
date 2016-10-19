package web.resources;

import domain.Message;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import persistence.Repositories;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by fabricejeannet on 12/10/2016.
 */
public class ActiveMessageResource extends ServerResource{

    @Get("json")
    public String getActiveMessages() throws IOException {
        List<Message> messages = Repositories.messages().all();

        final DateTime today = new DateTime();

        logger.info(today.toString());

        List<Message> activeMessages = messages.stream()
            .filter(m -> m.endDate.withTimeAtStartOfDay().isAfter(today.withTimeAtStartOfDay()) ||
                    m.endDate.withTimeAtStartOfDay().isEqual(today.withTimeAtStartOfDay()) ||
                    m.endDate == null)
            .collect(Collectors.toList());

        logger.info(activeMessages.size() + " active messages");

        ObjectMapper mapper = new ObjectMapper();

        final StringWriter sw =new StringWriter();

        mapper.writeValue(sw, activeMessages);

        return sw.toString();
    }

    private Logger logger = Logger.getLogger(this.getClass().getName());

}
