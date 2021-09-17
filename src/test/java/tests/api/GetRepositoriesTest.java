package tests.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import responseBody.CreateRepositoryResponse;
import responseBody.GetRepositoriesResponse;
import utils.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

public class GetRepositoriesTest {
    private String url = "https://api.github.com/user/repos";
    private String repositoryUri = "user/repos";

    @Test
    public void testGetRepositories() throws IOException {

        HttpGet getRepositories = new HttpGet("https://api.github.com/users/"+ Utils.getLogin() +"/repos");
        String login = Utils.getLogin();
        String token = Utils.getToken();
        getRepositories.addHeader("authorization", basicAuth(login, token));
        getRepositories.addHeader("content-type","application/json; charset=utf-8");
        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response =  client.execute(getRepositories);
        HttpEntity entity2 = response.getEntity();
        String result = EntityUtils.toString(entity2);
        var responseData = GetRepositoriesResponse.FromJson(result);

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
        Assertions.assertTrue(Arrays.stream(responseData).anyMatch(s->s.name.equals(Utils.getRepositoryAlwaysExistName())));

    }

    @Test
    public void getRepository() throws IOException {
        HttpGet createRepository = new HttpGet("https://api.github.com/repos/" + Utils.getLogin() + "/" + Utils.getRepositoryName());
        String login = Utils.getLogin();
        String token = Utils.getToken();
        createRepository.addHeader("authorization", basicAuth(login, token));
        createRepository.addHeader("content-type","application/json; charset=utf-8");
        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response =  client.execute(createRepository);
        HttpEntity entity2 = response.getEntity();
        String result = EntityUtils.toString(entity2);
        var responseData = CreateRepositoryResponse.FromJson(result);

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
        Assertions.assertEquals(Utils.getRepositoryName(), responseData.name);

    }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
