package tests.api;

import net.minidev.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ApiGetRepositoriesTest {
    private String url = "https://api.github.com/user/repos";
    private String repositoryUri = "user/repos";

    @Test
    public void testGetRepositories() throws IOException {

        HttpGet createRepository = new HttpGet("https://api.github.com/users/tiranozavrraw/repos");
        String login = Utils.getLogin();
        String token = Utils.getToken();
        createRepository.addHeader("authorization", basicAuth(login, token));
        createRepository.addHeader("content-type","application/json; charset=utf-8");
        //createRepository.addHeader("accept","application/vnd.github.baptiste-preview+json");
        HttpClient client = HttpClientBuilder.create().build();

        //JSONObject json = new JSONObject();
        //json.put("name", "TEST13");
        //var body = json.toString();
        //createRepository.setEntity(new StringEntity(body));

        HttpResponse response =  client.execute(createRepository);
        var bytes = response.getEntity().getContent().readAllBytes();
        String value = new String(bytes, "UTF-8");

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());

    }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }
}
