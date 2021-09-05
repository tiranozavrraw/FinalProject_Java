package tests;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest{
    @Test
    public void testLogin(){
        MainPage mainPage = new MainPage(driver);
        mainPage.open().clickSignInButton();
    }

}
