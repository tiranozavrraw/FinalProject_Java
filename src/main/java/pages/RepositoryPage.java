package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

public class RepositoryPage extends BasePage{
    @FindBy(xpath = "//span[contains(text(), \"Settings\")]")
    private WebElement settings;
    @FindBy(xpath = "//span[contains(text(), \"Code\")]")
    private WebElement code;

    public static RepositoryPage currentPage(WebDriver driver) {
        return new RepositoryPage(driver);
    }


    protected RepositoryPage(WebDriver driver) {
        super(driver);
    }

    public RepositorySettings clickSettings() {
        settings.click();
        return new RepositorySettings(getDriver());

    }
    public RepositoryCodePage clickCode() {
        code.click();
        return new RepositoryCodePage(getDriver());
    }




}
