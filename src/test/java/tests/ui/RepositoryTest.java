package tests.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;

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
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        Assertions.assertEquals("TEST11", repositoryCodePage.getRepositoryName());
    }

    @Test
    public void testCopyRepositoryLink() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.open().clickSignInButton();
        loginPage.login();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickYourRepositoryInUserMenu();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RepositoriesPage repositoriesPage = new RepositoriesPage(driver);
        repositoriesPage.findAndOpenRepository("TEST11");
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.clickCode();
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        String url = repositoryCodePage.clickCopyUrlButton();
        Assertions.assertEquals("https://github.com/tiranozavrraw/TEST11.git", url);
    }

    @Test
    public void findAndDeleteCreatedRepository() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.open().clickSignInButton();
        loginPage.login();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickYourRepositoryInUserMenu();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RepositoriesPage repositoriesPage = new RepositoriesPage(driver);
        repositoriesPage.findAndOpenRepository("TEST11");
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        Assertions.assertEquals("TEST11", repositoryCodePage.getRepositoryName());
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.clickSettings();
        repositoryPage.DeleteRepository("TEST11");
        loggedInMainPage.clickYourRepositoryInUserMenu();
        repositoriesPage.findRepository("TEST11");
        Assertions.assertEquals(0, repositoriesPage.getNumberOfSearchResults());

    }
}