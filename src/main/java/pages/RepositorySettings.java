package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

public class RepositorySettings extends BasePage{
    public RepositorySettings(WebDriver driver) {
        super(driver);
        waitUntilVisible(settingMenuTitle);
    }
    @FindBy(xpath = "//summary[contains(text(), \"Delete this repository\")]")
    private WebElement deleteRepositoryButton;
    @FindBy(xpath = "//input[contains(@aria-label, \"Type in the name of the repository to confirm that you want to delete this repository.\")]")
    private WebElement deleteRepositoryFieldConfirmation;
    @FindBy(xpath = "//span[contains(text(), \"I understand the consequences, delete this repository\")]")
    private WebElement deleteRepositoryButtonConfirmation;
    @FindBy(xpath = "//button[contains(text(), \"Confirm password\")]")
    private WebElement confirmPasswordButton;
    @FindBy(id = "sudo_password")
    private WebElement passwordField;
    @FindBy(xpath = "//h2[contains(text(), \"Settings\")]")
    private WebElement settingMenuTitle;



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
        MainPage mainPage = new MainPage(getDriver());

    }
}
