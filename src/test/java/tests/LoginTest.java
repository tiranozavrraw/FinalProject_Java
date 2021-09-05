package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoggedInMainPage;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest{
    @Test
    public void testLogin(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.open().clickSignInButton();
        loginPage.login();
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickUserIcon();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String signedInAs = loggedInMainPage.getSignedInAsText();

        Assertions.assertEquals("tiranozavrraw", signedInAs);
    }

}
