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
        StartPage startPage = new StartPage(driver);
        LoginPage loginPage = startPage.open().clickSignInButton();
        loginPage.login();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickUserIconAndWailTillVisible();
        String signedInAs = mainPage.getSignedInAsText();
        Assertions.assertEquals(Utils.getLogin(), signedInAs);
        mainPage.clickUserIcon();

    }

    @Then("Create repository")
    public void testCreateNewRepository(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickNewRepositoryButton();
        CreateRepositoryPage createRepositoryPage = new CreateRepositoryPage(driver);
        createRepositoryPage.enterRepositoryName(Utils.getRepositoryName());
        createRepositoryPage.clickCreateRepository();
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
        MainPage mainPage = new MainPage(driver);
        mainPage.clickYourRepositoryInUserMenu();

        RepositoryListPage repositoryListPage = new RepositoryListPage(driver);
        repositoryListPage.findAndOpenRepository(Utils.getRepositoryName());
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(driver);
        Assertions.assertEquals(Utils.getRepositoryName(), repositoryCodePage.getRepositoryName());

    }

    @Then("Delete repository")
    public void deleteCreatedRepository() {
        MainPage mainPage = new MainPage(driver);
        RepositoryPage repositoryPage = new RepositoryPage(driver);
        RepositoryListPage repositoryListPage = new RepositoryListPage(driver);
        repositoryPage.clickSettings();
        RepositorySettings repositorySettings = new RepositorySettings(driver);
        repositorySettings.DeleteRepository(Utils.getRepositoryName());
        mainPage.waitRepositoryDeletedMessage();

    }

    @And("Repository is not displayed in repositories list")
    public void checkRepositoryDeleted() {
        MainPage mainPage = new MainPage(driver);
        RepositoryListPage repositoryListPage = new RepositoryListPage(driver);
        mainPage.clickYourRepositoryInUserMenu();
        repositoryListPage.findRepository(Utils.getRepositoryName());
        Assertions.assertEquals(0, repositoryListPage.getNumberOfSearchResults());
    }

    @Then("Sign out")
    public void testSignOut() {
        StartPage startPage = new StartPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickUserIconAndWailTillVisible();
        String signedInAs = mainPage.getSignedInAsText();
        Assertions.assertEquals(Utils.getLogin(), signedInAs);
        mainPage.clickSignOut();
        Assertions.assertTrue(startPage.checkSignInButtonExist());
    }

}
