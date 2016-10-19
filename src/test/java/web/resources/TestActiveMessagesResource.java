package web.resources;

import command.message.MessageCommand;
import domain.Message;
import domain.MessageInformations;
import factories.TestFactories;
import org.codehaus.jackson.map.ObjectMapper;
import org.fest.util.Systems;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.restlet.Server;
import org.restlet.data.Protocol;
import persistence.FakeRepositories;
import persistence.Repositories;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by fabricejeannet on 12/10/2016.
 */
public class TestActiveMessagesResource {


    @Test
    public void serverCanServeActiveMessages() throws IOException {

        DateTime today = new DateTime();
        DateTime yesterday = today.minusDays(1);
        DateTime tomorrow = today.plusDays(1);

        MessageInformations messageInformations = TestFactories.messages().messageInformations();

        messageInformations.endDate = today.toString();
        new MessageCommand(messageInformations).execute();

        messageInformations.endDate = tomorrow.toString();
        new MessageCommand(messageInformations).execute();

        messageInformations.endDate = yesterday.toString();
        new MessageCommand(messageInformations).execute();

        messageInformations.endDate = null;
        new MessageCommand(messageInformations).execute();

        String json = Helpers.get("http://localhost:8080/messages/actives");

        ObjectMapper mapper = new ObjectMapper();
        Message [] activeMessages = mapper.readValue(json, Message[].class);
        assertThat(activeMessages.length).isEqualTo(3);
    }



    @Before
    public void before() throws Exception {
        testServer = new Server(Protocol.HTTP, 8080, ActiveMessageResource.class);
        testServer.start();
        Repositories.initialise(new FakeRepositories());

    }

    @After
    public void after() throws Exception {
        testServer.stop();

        Repositories.initialise(null);
    }

    private Server testServer;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private int STATUS_OK = 200;
}
