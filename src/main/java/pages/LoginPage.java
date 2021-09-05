package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

public class LoginPage extends AbstractPage{

    @FindBy(id = "login_field")
    WebElement loginField;

    @FindBy(id = "password")
    WebElement passwordFiled;

    @FindBy(className = "js-sign-in-button")
    WebElement signInButton;

    protected LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoggedInMainPage login(){
        loginField.sendKeys(Utils.getLogin());
        passwordFiled.sendKeys(Utils.getPassword());
        signInButton.click();

        return new LoggedInMainPage(getDriver());
    }
}
