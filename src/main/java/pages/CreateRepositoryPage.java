package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateRepositoryPage extends AbstractPage{
    @FindBy(id = "repository_name")
    WebElement repositoryNameField;
    @FindBy (xpath = "//form[@id='new_repository']//button[@type='submit']")
    WebElement createRepositoryButton;
    @FindBy(xpath = "//dd[contains(text(), \"is available.\")]")
    WebElement nameIsAvailablePopUp;

    public CreateRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void enterRepositoryName(String name){
        repositoryNameField.sendKeys(name);
        waitUntilVisible(nameIsAvailablePopUp);
    }

    public void clickCreateRepository(){
        scrollTo(createRepositoryButton);
        createRepositoryButton.click();
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(getDriver());
        waitUntilVisible(repositoryCodePage.repositoryName);
    }
}
