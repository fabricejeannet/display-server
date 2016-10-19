package web.resources;

import domain.Message;
import domain.MessageInformations;
import factories.TestFactories;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
import java.util.Scanner;
import java.util.logging.Logger;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by fabricejeannet on 10/10/2016.
 */
public class TestMessagesResource {
    @Test
    public void serverCreatesMessageFromPostedMessageInformations() throws IOException {
        MessageInformations messageInformations = TestFactories.messages().messageInformations();

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(messageInformations);

        System.out.println(json);

        CloseableHttpResponse response = Helpers.post("http://localhost:8080/messages", json);

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(STATUS_OK);
        assertThat(Repositories.messages().all().size()).isEqualTo(1);

    }

    @Test
    public void serverCanServeAllMessages() throws IOException {

        TestFactories.messages().multiple(2);

        String json = Helpers.get("http://localhost:8080/messages");

        ObjectMapper mapper = new ObjectMapper();
        Message [] messages = mapper.readValue(json, Message[].class);

        assertThat(messages[0].title).isEqualTo("message 0");
        assertThat(messages[1].title).isEqualTo("message 1");
    }



    @Before
    public void before() throws Exception {
        testServer = new Server(Protocol.HTTP, 8080, MessageResource.class);
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
