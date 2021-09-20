package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateRepositoryPage extends BasePage{
    @FindBy(id = "repository_name")
    private WebElement repositoryNameField;
    @FindBy (xpath = "//form[@id='new_repository']//button[@type='submit']")
    private WebElement createRepositoryButton;
    @FindBy(xpath = "//dd[contains(text(), \"is available.\")]")
    private WebElement nameIsAvailablePopUp;

    public CreateRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public CreateRepositoryPage enterRepositoryName(String name){
        repositoryNameField.sendKeys(name);
        waitUntilVisible(nameIsAvailablePopUp);
        return this;
    }

    public RepositoryCodePage clickCreateRepository(){
        scrollTo(createRepositoryButton);
        createRepositoryButton.click();
        return new RepositoryCodePage(getDriver());

    }
}
