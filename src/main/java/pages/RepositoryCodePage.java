package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositoryCodePage extends BasePage{
    @FindBy(xpath = "//summary[contains(@data-hydro-click, \"CLONE_OR_DOWNLOAD_BUTTON\")]")
    private WebElement codeButton;
    @FindBy(xpath = "//clipboard-copy[contains(@data-hydro-click, \"COPY_URL\")]")
    private WebElement copyUrl;
    @FindBy(xpath = "//strong[contains(@itemprop, \"name\")]/a")
    private WebElement repositoryName;

    public String getRepositoryName() {
        return repositoryName.getText();
    }

    public void clickCodeButton(){
        codeButton.click();
    }

    public String clickCopyUrlButton(){
        waitUntilVisible(copyUrl);
        copyUrl.click();
        return getCopiedValueFromClipboard();
    }

    public RepositoryCodePage(WebDriver driver) {
        super(driver);
        waitUntilVisible(repositoryName);
    }
}
