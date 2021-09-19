package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

public class RepositoryPage extends AbstractPage{
    @FindBy(xpath = "//span[contains(text(), \"Settings\")]")
    WebElement settings;
    @FindBy(xpath = "//span[contains(text(), \"Code\")]")
    WebElement code;
    @FindBy(xpath = "//summary[contains(text(), \"Delete this repository\")]")
    WebElement deleteRepositoryButton;
    @FindBy(xpath = "//input[contains(@aria-label, \"Type in the name of the repository to confirm that you want to delete this repository.\")]")
    WebElement deleteRepositoryFieldConfirmation;
    @FindBy(xpath = "//span[contains(text(), \"I understand the consequences, delete this repository\")]")
    WebElement deleteRepositoryButtonConfirmation;
    @FindBy(xpath = "//button[contains(text(), \"Confirm password\")]")
    WebElement confirmPasswordButton;
    @FindBy(id = "sudo_password")
    WebElement passwordField;
    @FindBy(xpath = "//h2[contains(text(), \"Settings\")]")
    WebElement settingMenuTitle;


    public RepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickSettings() {
        settings.click();
        waitUntilVisible(settingMenuTitle);
    }

    public void clickCode() {
        code.click();
    }

    public void DeleteRepository(String repositoryName){
        String repositoryNameToEnter = Utils.getLogin() + "/" + repositoryName;
        scrollTo(deleteRepositoryButton);
        deleteRepositoryButton.click();
        deleteRepositoryFieldConfirmation.sendKeys(repositoryNameToEnter);
        waitUntilClickable(deleteRepositoryButtonConfirmation);
        deleteRepositoryButtonConfirmation.click();

        if(!getDriver().findElements( By.xpath( "//button[contains(text(), \"Confirm password\")]")).isEmpty()){
            passwordField.sendKeys(Utils.getPassword());
            confirmPasswordButton.click();
        }
        LoggedInMainPage loggedInMainPage = new LoggedInMainPage(getDriver());
        waitUntilVisible(loggedInMainPage.repositoryDeletedMessage);

    }


}
