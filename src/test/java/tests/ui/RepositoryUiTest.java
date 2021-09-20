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
        String copiedUrl = RepositoryPage.currentPage(driver)
                .clickCode()
                .clickCopyUrlButton();

        String expectedUrl = "https://github.com/" + Utils.getLogin() + "/" + Utils.getRepositoryName() + ".git";
        Assertions.assertEquals(expectedUrl, copiedUrl);
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
