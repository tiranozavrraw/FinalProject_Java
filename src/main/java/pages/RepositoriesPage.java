package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositoriesPage extends AbstractPage{

    @FindBy(css = ".wb-break-all a")
    WebElement repositoryNameInSearchResults;

    @FindBy(id = "your-repos-filter")
    WebElement repositorySearch;

    @FindBy(xpath = "//div[contains(@class, \"user-repo-search-results-summary\")]/strong[1]")
    WebElement numberOfSearchResults;

    public RepositoriesPage(WebDriver driver) {
        super(driver);
    }

    public Integer getNumberOfSearchResults() {
        return Integer.parseInt(numberOfSearchResults.getText());
    }

    public void findRepository(String repositoryName) {
        repositorySearch.sendKeys(repositoryName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void findAndOpenRepository(String repositoryName) {
        findRepository(repositoryName);
        repositoryNameInSearchResults.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
