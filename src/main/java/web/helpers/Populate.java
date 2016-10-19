package web.helpers;

import command.message.MessageCommand;
import domain.MessageInformations;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by fabricejeannet on 13/10/2016.
 */
public class Populate {

    public static void main(String [] args) throws IOException {

        for (int index = 0; index <= 5; index ++) {
            MessageInformations messageInformations = new MessageInformations();
            messageInformations.body ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eu diam ut diam rutrum tincidunt ac id ligula. Sed accumsan turpis est, sed tempor turpis tempus ac. Maecenas facilisis enim orci, vitae sagittis arcu suscipit vel. Nullam feugiat id sem a vulputate. Etiam id diam sed sapien iaculis egestas.";
            messageInformations.title = "Message #" + index;


            if(index%2==0){
                messageInformations.coverUrl="http://www.coolworking.fr/wp-content/uploads/2013/03/logo-horizontal-fr-400x69.png";
                messageInformations.type="warning";
            } else if(index%3==0){
                messageInformations.type="danger";
            }



            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(messageInformations);

            System.out.println(json);

            CloseableHttpClient httpClient = HttpClients.createDefault();


            HttpPost post = new HttpPost("http://localhost:8080/messages");
            StringEntity postingString = new StringEntity(json);

            CloseableHttpResponse response = null;
            Scanner in = null;

            try {
                post.setEntity(postingString);
                post.setHeader("Content-type", "application/json");

                response = httpClient.execute(post);

                HttpEntity entity = response.getEntity();
                in = new Scanner(entity.getContent());
                EntityUtils.consume(entity);
            } finally  {
                in.close();
                response.close();
            }

        }

    }
}
