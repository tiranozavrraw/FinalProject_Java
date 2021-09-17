package tests.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import responseBody.CreateRepositoryResponse;
import responseBody.GetRepositoriesResponse;
import utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Base64;

public class RepositoryTest {

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    @Given("Repository is created")
    public void testCreateNewRepository() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost createRepository = new HttpPost("https://api.github.com/user/repos");
        String login = Utils.getLogin();
        String token = Utils.getToken();
        createRepository.addHeader("authorization", basicAuth(login, token));
        createRepository.addHeader("content-type", "application/json");
        String json = "{\"name\":\"" + Utils.getRepositoryApiName() + "\"}";
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

    @Then("Get created repository")
    public void getRepository() throws IOException {
        HttpGet createRepository = new HttpGet("https://api.github.com/repos/" + Utils.getLogin() + "/" + Utils.getRepositoryApiName());
        String login = Utils.getLogin();
        String token = Utils.getToken();
        createRepository.addHeader("authorization", basicAuth(login, token));
        createRepository.addHeader("content-type", "application/json; charset=utf-8");
        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response = client.execute(createRepository);
        HttpEntity entity2 = response.getEntity();
        String result = EntityUtils.toString(entity2);
        var responseData = CreateRepositoryResponse.FromJson(result);

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
        Assertions.assertEquals(Utils.getRepositoryApiName(), responseData.name);

    }

    @And("Delete created repository")
    public void testDeleteRepository() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete deleteRepository = new HttpDelete(MessageFormat.format("https://api.github.com/repos/{0}/{1}", Utils.getLogin(), Utils.getRepositoryApiName()));
        String login = Utils.getLogin();
        String token = Utils.getToken();
        deleteRepository.addHeader("authorization", basicAuth(login, token));
        deleteRepository.addHeader("content-type", "application/json");
        CloseableHttpResponse response = client.execute(deleteRepository);
        Assertions.assertEquals(204, response.getStatusLine().getStatusCode());
        client.close();

    }

    @Given("Public repository is available for non-logged in user")
    public void testGetRepositoriesWithoutToken() throws IOException {

        HttpGet createRepository = new HttpGet("https://api.github.com/users/" + Utils.getLogin() + "/repos");
        HttpClient client = HttpClientBuilder.create().build();


        HttpResponse response = client.execute(createRepository);
        var bytes = response.getEntity().getContent().readAllBytes();
        String value = new String(bytes, StandardCharsets.UTF_8);

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());

    }

    @Given("Repository is not created for not logged in user")
    public void testCreateNewRepositoryWithoutToken() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost createRepository = new HttpPost("https://api.github.com/user/repos");
        String login = Utils.getLogin();
        String token = Utils.getEmptyToken();
        createRepository.addHeader("authorization", basicAuth(login, token));
        createRepository.addHeader("content-type", "application/json");
        String json = "{\"name\":\"" + Utils.getRepositoryAlwaysExistName() + "\"}";
        StringEntity entity = new StringEntity(json);
        createRepository.setEntity(entity);

        CloseableHttpResponse response = client.execute(createRepository);
        var str = new String(response.getEntity().getContent().readAllBytes());
        Assertions.assertEquals(401, response.getStatusLine().getStatusCode());
        client.close();


    }

    @Given("Get list of repositories")
    public void testGetRepositories() throws IOException {

        HttpGet getRepositories = new HttpGet("https://api.github.com/users/" + Utils.getLogin() + "/repos");
        String login = Utils.getLogin();
        String token = Utils.getToken();
        getRepositories.addHeader("authorization", basicAuth(login, token));
        getRepositories.addHeader("content-type", "application/json; charset=utf-8");
        HttpClient client = HttpClientBuilder.create().build();

        HttpResponse response = client.execute(getRepositories);
        HttpEntity entity2 = response.getEntity();
        String result = EntityUtils.toString(entity2);
        var responseData = GetRepositoriesResponse.FromJson(result);

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
        Assertions.assertTrue(Arrays.stream(responseData).anyMatch(s -> s.name.equals(Utils.getRepositoryAlwaysExistName())));

    }
}
