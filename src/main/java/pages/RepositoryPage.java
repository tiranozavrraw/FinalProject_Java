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



    public RepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickSettings() {
        settings.click();
        RepositorySettings repositorySettings = new RepositorySettings(getDriver());

    }
    public void clickCode() {
        code.click();
    }




}
