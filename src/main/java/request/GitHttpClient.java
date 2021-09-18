package request;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class GitHttpClient {
    private final String baseUrl;
    private String postRequest;
    private String getRequest;
    private boolean isAuthorize;
    private String body;
    private String deleteRequest;
    private int responseHttpCode;
    private boolean isAuthorizeWithEmptyToken;

    public GitHttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private static String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    public int getResponseHttpCode() {
        return responseHttpCode;
    }

    public void post(String request, String body) {
        if (getRequest != null || deleteRequest != null) {
            throw new IllegalArgumentException();
        }
        this.postRequest = request;
        this.body = body;
    }

    public void get(String request) {
        if (postRequest != null || deleteRequest != null) {
            throw new IllegalArgumentException();
        }
        this.getRequest = request;
    }

    public void delete(String request) {
        if (postRequest != null || getRequest != null) {
            throw new IllegalArgumentException();
        }
        this.deleteRequest = request;
    }

    public void authorize() {
        this.isAuthorize = true;
    }

    public String execute() {
        CloseableHttpClient client = HttpClients.createDefault();
        var isPostRequest = postRequest != null;
        var isGetRequest = getRequest != null;
        var isDeleteRequest = deleteRequest != null;
        HttpRequestBase request;
        if (isPostRequest) {
            var httpPost = new HttpPost(baseUrl + postRequest);
            try {
                StringEntity stringEntity = new StringEntity(this.body);
                httpPost.setEntity(stringEntity);
                request = httpPost;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        } else if (isGetRequest) {
            request = new HttpGet(baseUrl + getRequest);
        } else if (isDeleteRequest) {
            request = new HttpDelete(baseUrl + deleteRequest);
        } else {
            throw new IllegalArgumentException();
        }
        request.addHeader("content-type", "application/json");
        if (isAuthorize) {
            String login = Utils.getLogin();
            String token = Utils.getToken();
            request.addHeader("authorization", basicAuth(login, token));
        } else if (isAuthorizeWithEmptyToken) {
            String login = Utils.getLogin();
            String token = "";
            request.addHeader("authorization", basicAuth(login, token));
        }

        CloseableHttpResponse response = null;
        try {
            response = client.execute(request);
            this.responseHttpCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
                return null;
            }
            String result = EntityUtils.toString(responseEntity);
            response.close();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void authorizeWithEmptyToken() {
        this.isAuthorizeWithEmptyToken = true;
    }
}
