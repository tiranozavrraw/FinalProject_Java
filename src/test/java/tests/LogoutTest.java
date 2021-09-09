package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoggedInMainPage;
import pages.LoginPage;
import pages.MainPage;

public class LogoutTest extends BaseTest{
    @Test
    public void testLogOut() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.open().clickSignInButton();
        loginPage.login();
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickUserIcon();
        String signedInAs = loggedInMainPage.getSignedInAsText();
        Assertions.assertEquals("tiranozavrraw", signedInAs);
        loggedInMainPage.clickSignOut();
    }
}
