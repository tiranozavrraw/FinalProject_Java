package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage{
    private final String mainPage = "https://github.com/";

    @FindBy(xpath = "//*[contains(text(),'Sign in')]")
    WebElement signInButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
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
