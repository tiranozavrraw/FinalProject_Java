package tests.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.Utils;

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
        createRepositoryPage.enterRepositoryName(Utils.getRepositoryName());
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
        Assertions.assertEquals(Utils.getRepositoryName(), repositoryCodePage.getRepositoryName());
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
        repositoriesPage.findAndOpenRepository(Utils.getRepositoryAlwaysExistName());
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.clickCode();
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        String url = repositoryCodePage.clickCopyUrlButton();
        Assertions.assertEquals("https://github.com/tiranozavrraw/" + Utils.getRepositoryAlwaysExistName() + ".git", url);
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
        repositoriesPage.findAndOpenRepository(Utils.getRepositoryName());
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        Assertions.assertEquals(Utils.getRepositoryName(), repositoryCodePage.getRepositoryName());
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.clickSettings();
        repositoryPage.DeleteRepository(Utils.getRepositoryName());
        loggedInMainPage.clickYourRepositoryInUserMenu();
        repositoriesPage.findRepository(Utils.getRepositoryName());
        Assertions.assertEquals(0, repositoriesPage.getNumberOfSearchResults());

    }
}
