package tests.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Base64;

public class ApiDeleteRepositoryTest {
    private String url = "https://api.github.com/user/repos";
    private String repositoryUri = "user/repos";

    @Test
    public void testDeleteRepository() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete deleteRepository = new HttpDelete(MessageFormat.format("https://api.github.com/repos/{0}/{1}", Utils.getLogin(), Utils.getRepositoryName()));
        String login = Utils.getLogin();
        String token = Utils.getToken();
        deleteRepository.addHeader("authorization", basicAuth(login, token));
        deleteRepository.addHeader("content-type","application/json");
        CloseableHttpResponse response = client.execute(deleteRepository);
        Assertions.assertEquals(204, response.getStatusLine().getStatusCode());
        client.close();

    }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
