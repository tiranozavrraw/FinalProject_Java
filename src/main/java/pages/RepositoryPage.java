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

    public RepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickSettings() {
        settings.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickCode() {
        code.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void DeleteRepository(String repositoryName){
        String repositoryNameToEnter = Utils.getLogin() + "/" + repositoryName;
        scrollTo(deleteRepositoryButton);
        deleteRepositoryButton.click();
        deleteRepositoryFieldConfirmation.sendKeys(repositoryNameToEnter);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deleteRepositoryButtonConfirmation.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!getDriver().findElements( By.xpath( "//button[contains(text(), \"Confirm password\")]")).isEmpty()){
            passwordField.sendKeys(Utils.getPassword());
            confirmPasswordButton.click();
        }

    }
}
