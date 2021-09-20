package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

public class LoginPage extends AbstractPage{

    @FindBy(id = "login_field")
    private WebElement loginField;

    @FindBy(id = "password")
    private WebElement passwordFiled;

    @FindBy(className = "js-sign-in-button")
    private WebElement signInButton;

    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(){
        loginField.sendKeys(Utils.getLogin());
        passwordFiled.sendKeys(Utils.getPassword());
        signInButton.click();
        MainPage mainPage = new MainPage(getDriver());
        mainPage.waitUntilUserIconVisible();
        return mainPage;
    }
}
