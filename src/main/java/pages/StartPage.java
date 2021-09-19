package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends AbstractPage{
    private final String mainPage = "https://github.com/";

    @FindBy(xpath = "//*[contains(text(),'Sign in')]")
    private WebElement signInButton;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage open() {
        openUrl(mainPage);
        return this;
    }

    public Boolean checkSignInButtonExist() {
        return signInButton.isDisplayed();
    }

    public LoginPage clickSignInButton() {
        signInButton.click();
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }
}
