package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String getLogin() {
        return getConfigProperty("login");
    }

    public static String getPassword() {
        return getConfigProperty("password");
    }

    public static String getToken() {
        return getConfigProperty("token");
    }

    public static String getRepositoryName() {
        return getConfigProperty("repositoryNameForCreateDeleteUi");
    }

    public static String getRepositoryAlwaysExistName() {
        return getConfigProperty("repositoryNameAlwaysExist");
    }

    public static String getRepositoryApiName() {
        return getConfigProperty("repositoryNameForCreateDeleteApi");
    }

    public static String getBaseUrl() {
        return getConfigProperty("baseUrl");
    }

    private static String getConfigProperty(String propertyKey) {
        try {
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(input);
            String propertyValue = properties.getProperty(propertyKey);
            return propertyValue;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}

