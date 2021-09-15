package tests.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.IOException;
import java.util.Base64;

public class ApiRequestWithoutTokenTest {
    @Test
    public void testGetRepositoriesWithoutToken() throws IOException {

        HttpGet createRepository = new HttpGet("https://api.github.com/users/tiranozavrraw/repos");
        HttpClient client = HttpClientBuilder.create().build();


        HttpResponse response =  client.execute(createRepository);
        var bytes = response.getEntity().getContent().readAllBytes();
        String value = new String(bytes, "UTF-8");

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());

    }

    @Test
    public void testCreateNewRepositoryWithoutToken() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost createRepository = new HttpPost("https://api.github.com/user/repos");
        String login = Utils.getLogin();
        String token = Utils.getEmptyToken();
        createRepository.addHeader("authorization", basicAuth(login, token));
        createRepository.addHeader("content-type","application/json");
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("name", "TEST13"));
//            createRepository.setEntity(new UrlEncodedFormEntity(params));
        String json = "{\"name\":\"TEST15\"}";
        StringEntity entity = new StringEntity(json);
        createRepository.setEntity(entity);

        CloseableHttpResponse response = client.execute(createRepository);
        var str = new String(response.getEntity().getContent().readAllBytes());
        Assertions.assertEquals(401, response.getStatusLine().getStatusCode());
        client.close();



    }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
