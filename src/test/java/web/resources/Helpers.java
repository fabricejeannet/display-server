package web.resources;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by fabricejeannet on 12/10/2016.
 */
public class Helpers {

    public static String get(String url) throws IOException {

        Scanner out = null;
        CloseableHttpResponse response = null;
        String json;

        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("accept", "application/json");
            response = httpClient.execute(getRequest);
            json = new BasicResponseHandler().handleResponse(response);

        } finally  {
            response.close();
        }
        return json;
    }

    public static CloseableHttpResponse post(String url, String json) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();


        HttpPost post = new HttpPost(url);
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

        return response;
    }

}
