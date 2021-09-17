package tests.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import responseBody.CreateRepositoryResponse;
import utils.Utils;

import java.io.IOException;
import java.util.Base64;

public class CreateRepositoryTest {
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
            String json = "{\"name\":\""+ Utils.getRepositoryApiName() +"\"}";
            StringEntity entity = new StringEntity(json);
            createRepository.setEntity(entity);

            CloseableHttpResponse response = client.execute(createRepository);
            HttpEntity entity2 = response.getEntity();
            String result = EntityUtils.toString(entity2);
            var responseData = CreateRepositoryResponse.FromJson(result);
            Assertions.assertEquals(201, response.getStatusLine().getStatusCode());
            Assertions.assertEquals(Utils.getRepositoryApiName(), responseData.name);
            client.close();



        }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
