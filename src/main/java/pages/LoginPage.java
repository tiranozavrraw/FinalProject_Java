package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

//    public LoggedInMainPage login(){
//        return LoggedInMainPage;
//    }
}
