package tests.ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import utils.Utils;

public class RepositoryUiTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("Sign in")
    public void testSignIn(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.open().clickSignInButton();
        loginPage.login();
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickUserIconAndWailTillVisible();
        String signedInAs = loggedInMainPage.getSignedInAsText();
        Assertions.assertEquals(Utils.getLogin(), signedInAs);
        loggedInMainPage.clickUserIcon();

    }

    @Then("Create repository")
    public void testCreateNewRepository(){
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

    @And("Copy repository link")
    public void testCopyRepositoryLink() {
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        repositoryPage.clickCode();
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        String url = repositoryCodePage.clickCopyUrlButton();
        Assertions.assertEquals("https://github.com/" + Utils.getLogin() + "/" + Utils.getRepositoryName() + ".git", url);
    }

    @When("Find created repository in repositories list")
    public void findCreatedRepository() {
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

    }

    @Then("Delete repository")
    public void deleteCreatedRepository() {
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        RepositoriesPage repositoriesPage = new RepositoriesPage(driver);
        repositoryPage.clickSettings();
        repositoryPage.DeleteRepository(Utils.getRepositoryName());

    }

    @And("Repository is not displayed in repositories list")
    public void checkRepositoryDeleted() {
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        RepositoriesPage repositoriesPage = new RepositoriesPage(driver);
        loggedInMainPage.clickYourRepositoryInUserMenu();
        repositoriesPage.findRepository(Utils.getRepositoryName());
        Assertions.assertEquals(0, repositoriesPage.getNumberOfSearchResults());
    }

    @Then("Sign out")
    public void testSignOut() {
        MainPage mainPage = new MainPage(driver);
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(driver);
        loggedInMainPage.clickUserIconAndWailTillVisible();
        String signedInAs = loggedInMainPage.getSignedInAsText();
        Assertions.assertEquals(Utils.getLogin(), signedInAs);
        loggedInMainPage.clickSignOut();
        Assertions.assertTrue(mainPage.checkSignInButtonExist());
    }

}
