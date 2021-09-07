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

    public LoggedInMainPage(WebDriver driver) {
        super(driver);
    }

    public void clickUserIcon() {
        userIcon.click();
        waitUntilVisible(signedInAs);
    }

    public String getSignedInAsText() {
        return signedInAs.getText();
    }

    public CreateRepositoryPage clickNewRepositoryButton() {
        newRepositoryButton.click();
        return new CreateRepositoryPage();
    }
}
