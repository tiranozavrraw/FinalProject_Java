package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage{
    @FindBy(xpath = "//a[contains(@data-hydro-click,'NEW_REPOSITORY_BUTTON')]")
    private WebElement newRepositoryButton;
    @FindBy (xpath = "//div[contains(., \"was successfully deleted.\")]/button")
    private WebElement repositoryDeletedMessage;

    MainPage(WebDriver driver) {
        super(driver);
    }

    public static MainPage currentPage(WebDriver driver) {
        MainPage mainPage = new MainPage(driver);
        return mainPage;
    }

    public MainPage openYourRepositoryInUserMenu() {
        super.clickYourRepositoriesInUserMenu();
        return this;
    }

    public MainPage clickUserIcon() {
        super.clickUserIcon();
        return this;
    }

    public MainPage waitRepositoryDeletedMessage() {
        waitUntilVisible(repositoryDeletedMessage);
        return this;
    }


    public CreateRepositoryPage clickNewRepositoryButton() {
        newRepositoryButton.click();
        return new CreateRepositoryPage(getDriver());
    }


}
