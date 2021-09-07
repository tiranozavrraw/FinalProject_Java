package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.CreateRepositoryPage;
import pages.LoggedInMainPage;
import pages.LoginPage;
import pages.MainPage;

public class RepositoryTest extends BaseTest{

    @Test
    public void testCreateNewRepository(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.open().clickSignInButton();
        loginPage.login();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickNewRepositoryButton();
        CreateRepositoryPage createRepositoryPage = new CreateRepositoryPage(driver);
        createRepositoryPage.enterRepositoryName("TEST11");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createRepositoryPage.clickCreateRepository();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
