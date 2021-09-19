package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage extends AbstractPage{
    @FindBy(className = "js-feature-preview-indicator-container")
    protected WebElement userIcon;
    @FindBy(xpath = "//a/strong")
    protected WebElement signedInAs;
    @FindBy(xpath = "//a[contains(@data-hydro-click, \"YOUR_REPOSITORIES\")]")
    protected WebElement yourRepositoriesMenuItem;
    @FindBy(xpath = "//button[contains(@data-hydro-click, \"SIGN_OUT\")]")
    protected WebElement signOut;

    protected BasePage(WebDriver driver) {
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

    public void clickSignOut(){
        signOut.click();
    }

    public void clickYourRepositoryInUserMenu(){
        clickUserIconAndWailTillVisible();
        yourRepositoriesMenuItem.click();

    }
}
