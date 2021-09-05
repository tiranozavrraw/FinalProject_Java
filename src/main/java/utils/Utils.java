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

}

