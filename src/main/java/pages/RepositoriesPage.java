package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositoriesPage extends AbstractPage{

    @FindBy(css = ".wb-break-all a")
    WebElement repositoryNameInSearchResults;

    @FindBy(id = "your-repos-filter")
    WebElement repositorySearch;

    public RepositoriesPage(WebDriver driver) {
        super(driver);
    }

    public void findAndOpenRepository(String repositoryName) {
        repositorySearch.sendKeys(repositoryName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repositoryNameInSearchResults.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
