package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositoryListPage extends BasePage{

    @FindBy(css = ".wb-break-all a")
    private WebElement repositoryNameInSearchResults;

    @FindBy(id = "your-repos-filter")
    private WebElement repositorySearch;

    @FindBy(xpath = "//div[contains(@class, \"user-repo-search-results-summary\")]/strong[1]")
    private WebElement numberOfSearchResults;

    @FindBy(className = "user-repo-search-results-summary")
    private WebElement searchResults;

    public RepositoryListPage(WebDriver driver) {
        super(driver);
    }

    public Integer getNumberOfSearchResults() {
        return Integer.parseInt(numberOfSearchResults.getText());
    }

    public void findRepository(String repositoryName) {
        repositorySearch.sendKeys(repositoryName);
        waitUntilVisible(searchResults);
    }

    public void findAndOpenRepository(String repositoryName) {
        findRepository(repositoryName);
        repositoryNameInSearchResults.click();
        RepositoryCodePage repositoryCodePage = new RepositoryCodePage(getDriver());


    }

}
