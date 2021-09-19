package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoggedInMainPage extends AbstractPage{
    @FindBy(className = "js-feature-preview-indicator-container")
    WebElement userIcon;
    @FindBy(xpath = "//a/strong")
    WebElement signedInAs;
    @FindBy(xpath = "//a[contains(@data-hydro-click,'NEW_REPOSITORY_BUTTON')]")
    WebElement newRepositoryButton;
    @FindBy(xpath = "//a[contains(@data-hydro-click, \"YOUR_REPOSITORIES\")]")
    WebElement yourRepositoriesMenuItem;
    @FindBy(xpath = "//button[contains(@data-hydro-click, \"SIGN_OUT\")]")
    WebElement signOut;
    @FindBy (xpath = "//div[contains(., \"was successfully deleted.\")]/button")
    WebElement repositoryDeletedMessage;

    public LoggedInMainPage(WebDriver driver) {
        super(driver);
    }

    public void clickUserIconAndWailTillVisible() {
        userIcon.click();
        waitUntilVisible(signedInAs);
    }

    public void clickUserIcon() {
        userIcon.click();
    }

    public String getSignedInAsText() {
        return signedInAs.getText();
    }

    public CreateRepositoryPage clickNewRepositoryButton() {
        newRepositoryButton.click();
        return new CreateRepositoryPage(getDriver());
    }

    public void clickSignOut(){
        signOut.click();
    }

    public void clickYourRepositoryInUserMenu(){
        clickUserIconAndWailTillVisible();
        yourRepositoriesMenuItem.click();

    }
}
