package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends AbstractPage{
    private final String startPageUrl = "https://github.com/";

    @FindBy(xpath = "//*[contains(text(),'Sign in')]")
    private WebElement signInButton;

    protected StartPage(WebDriver driver) {
        super(driver);
    }

    public static StartPage open(WebDriver driver) {
        var startPage = new StartPage(driver);
        return startPage.open();

    }

    private StartPage open() {
        openUrl(startPageUrl);
        return this;
    }

    public Boolean checkSignInButtonExist() {
        return signInButton.isDisplayed();
    }

    public LoginPage clickSignInButton() {
        signInButton.click();
        return new LoginPage(getDriver());
    }
}
