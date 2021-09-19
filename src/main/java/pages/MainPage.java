package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage{
    @FindBy(xpath = "//a[contains(@data-hydro-click,'NEW_REPOSITORY_BUTTON')]")
    private WebElement newRepositoryButton;
    @FindBy (xpath = "//div[contains(., \"was successfully deleted.\")]/button")
    private WebElement repositoryDeletedMessage;

    public MainPage(WebDriver driver) {
        super(driver);
    }
    public void waitRepositoryDeletedMessage() {
        waitUntilVisible(repositoryDeletedMessage);
    }


    public CreateRepositoryPage clickNewRepositoryButton() {
        newRepositoryButton.click();
        return new CreateRepositoryPage(getDriver());
    }

}
