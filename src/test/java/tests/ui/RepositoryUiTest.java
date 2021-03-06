package tests.ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;
import utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;

public class RepositoryUiTest {
    protected WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        String remote_url_chrome = "http://localhost:4444/wd/hub";
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new RemoteWebDriver(new URL(remote_url_chrome), options);
        driver.manage().window().maximize();

    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("Sign in")
    public void testSignIn(){
         String signedInAs = StartPage.open(driver)
                .clickSignInButton()
                .login()
                .clickUserIconAndWailTillVisible()
                .getSignedInAsText();

        Assertions.assertEquals(Utils.getLogin(), signedInAs);

    }

    @Then("Create repository")
    public void testCreateNewRepository(){
        String createdRepositoryName = MainPage.currentPage(driver)
                .clickUserIcon()
                .clickNewRepositoryButton()
                .enterRepositoryName(Utils.getRepositoryName())
                .clickCreateRepository()
                .getRepositoryName();

        Assertions.assertEquals(Utils.getRepositoryName(), createdRepositoryName);
    }

    @And("Copy repository link")
    public void testCopyRepositoryLink() {
        String repositoryUrl = RepositoryPage.currentPage(driver)
                .clickCode()
                .getUrlValue();

        String expectedUrl = "https://github.com/" + Utils.getLogin() + "/" + Utils.getRepositoryName() + ".git";
        Assertions.assertEquals(expectedUrl, repositoryUrl);
    }

    @When("Find created repository in repositories list")
    public void findCreatedRepository() {
        String createdRepositoryName = MainPage.currentPage(driver)
                .clickYourRepositoriesInUserMenu()
                .findAndOpenRepository(Utils.getRepositoryName())
                .getRepositoryName();

        Assertions.assertEquals(Utils.getRepositoryName(), createdRepositoryName);
    }

    @Then("Delete repository")
    public void deleteCreatedRepository() {
        RepositoryPage.currentPage(driver)
                .clickSettings()
                .DeleteRepository(Utils.getRepositoryName())
                .waitRepositoryDeletedMessage();
    }

    @And("Repository is not displayed in repositories list")
    public void checkRepositoryDeleted() {
        int searchResultsNumber = MainPage.currentPage(driver)
                .clickYourRepositoriesInUserMenu()
                .findRepository(Utils.getRepositoryName())
                .getNumberOfSearchResults();

        Assertions.assertEquals(0, searchResultsNumber);
    }

    @Then("Sign out")
    public void testSignOut() {
        Boolean isSignInExist = MainPage.currentPage(driver)
                .clickUserIconAndWailTillVisible()
                .clickSignOut()
                .checkSignInButtonExist();

        Assertions.assertTrue(isSignInExist);
    }

}
