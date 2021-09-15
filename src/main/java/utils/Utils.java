package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String getLogin(){
    try {
    InputStream input = new FileInputStream("src/test/resources/config.properties");
        Properties properties = new Properties();
        properties.load(input);
        String login = properties.getProperty("login");
        return login;
    } catch (
    IOException ex) {
        throw new RuntimeException(ex);
    }
   }
    public static String getPassword(){
        try {
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(input);
            String password = properties.getProperty("password");
            return password;
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getToken(){
        try {
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(input);
            String login = properties.getProperty("token");
            return login;
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String getRepositoryName(){
        try {
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(input);
            String login = properties.getProperty("repositoryName");
            return login;
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getEmptyToken(){
        try {
            InputStream input = new FileInputStream("src/test/resources/config.properties");
            Properties properties = new Properties();
            properties.load(input);
            String login = properties.getProperty("emptyToken");
            return login;
        } catch (
                IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}

