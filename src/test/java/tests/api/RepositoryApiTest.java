package tests.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import request.GitHttpClient;
import responseBody.RepositoryResponse;
import responseBody.RepositoryListResponse;
import utils.Utils;

import java.text.MessageFormat;
import java.util.Arrays;

public class RepositoryApiTest {

    @Given("Repository is created")
    public void testCreateNewRepository() {
        GitHttpClient gitHttpClient = new GitHttpClient(Utils.getBaseUrl());
        gitHttpClient.authorize();
        String body = "{\"name\":\"" + Utils.getRepositoryApiName() + "\"}";
        gitHttpClient.post("/user/repos", body);

        var result = gitHttpClient.execute();
        var responseData = RepositoryResponse.FromJson(result);

        Assertions.assertEquals(201, gitHttpClient.getResponseHttpCode());
        Assertions.assertEquals(Utils.getRepositoryApiName(), responseData.name);
    }

    @Then("Get created repository")
    public void getRepository() {
        GitHttpClient gitHttpClient = new GitHttpClient(Utils.getBaseUrl());
        gitHttpClient.authorize();
        gitHttpClient.get("/repos/" + Utils.getLogin() + "/" + Utils.getRepositoryApiName());

        var result = gitHttpClient.execute();
        var responseData = RepositoryResponse.FromJson(result);

        Assertions.assertEquals(200, gitHttpClient.getResponseHttpCode());
        Assertions.assertEquals(Utils.getRepositoryApiName(), responseData.name);
    }

    @And("Delete created repository")
    public void testDeleteRepository() {
        GitHttpClient gitHttpClient = new GitHttpClient(Utils.getBaseUrl());
        gitHttpClient.authorize();
        gitHttpClient.delete(MessageFormat.format("/repos/{0}/{1}", Utils.getLogin(), Utils.getRepositoryApiName()));

        gitHttpClient.execute();

        Assertions.assertEquals(204, gitHttpClient.getResponseHttpCode());
    }

    @Given("Public repository is available for non-logged in user")
    public void testGetRepositoriesWithoutToken() {
        GitHttpClient gitHttpClient = new GitHttpClient(Utils.getBaseUrl());
        gitHttpClient.get("/users/" + Utils.getLogin() + "/repos");

        var result = gitHttpClient.execute();
        var responseData = RepositoryListResponse.FromJson(result);

        Assertions.assertEquals(200, gitHttpClient.getResponseHttpCode());
        Assertions.assertTrue(Arrays.stream(responseData).anyMatch(s -> s.name.equals(Utils.getRepositoryAlwaysExistName())));
    }

    @Given("Repository is not created for not logged in user")
    public void testCreateNewRepositoryWithoutToken() {
        GitHttpClient gitHttpClient = new GitHttpClient(Utils.getBaseUrl());
        String body = "{\"name\":\"" + Utils.getRepositoryApiName() + "\"}";
        gitHttpClient.post("/user/repos", body);
        gitHttpClient.authorizeWithEmptyToken();

        gitHttpClient.execute();

        Assertions.assertEquals(401, gitHttpClient.getResponseHttpCode());
    }

    @Given("Get list of repositories")
    public void testGetRepositories() {
        GitHttpClient gitHttpClient = new GitHttpClient(Utils.getBaseUrl());
        gitHttpClient.authorize();
        gitHttpClient.get("/users/" + Utils.getLogin() + "/repos");

        var result = gitHttpClient.execute();
        var responseData = RepositoryListResponse.FromJson(result);

        Assertions.assertEquals(200, gitHttpClient.getResponseHttpCode());
        Assertions.assertTrue(Arrays.stream(responseData).anyMatch(s -> s.name.equals(Utils.getRepositoryAlwaysExistName())));
    }
}
