package tests.api;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ApiCreateNewRepositoryTest {
        private String url = "https://api.github.com/user/repos";
        private String repositoryUri = "user/repos";

        @Test
        public void testCreateNewRepository() throws IOException {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost createRepository = new HttpPost("https://api.github.com/user/repos");
            String login = Utils.getLogin();
            String token = Utils.getToken();
            createRepository.addHeader("authorization", basicAuth(login, token));
            createRepository.addHeader("content-type","application/json");
            String json = "{\"name\":\"TEST15\"}";
            StringEntity entity = new StringEntity(json);
            createRepository.setEntity(entity);

            CloseableHttpResponse response = client.execute(createRepository);
            var str = new String(response.getEntity().getContent().readAllBytes());
            Assertions.assertEquals(201, response.getStatusLine().getStatusCode());
            client.close();



        }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
