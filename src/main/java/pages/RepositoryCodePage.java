package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RepositoryCodePage extends AbstractPage{
    @FindBy(xpath = "//summary[contains(@data-hydro-click, \"CLONE_OR_DOWNLOAD_BUTTON\")]")
    WebElement codeButton;
    @FindBy(xpath = "//clipboard-copy[contains(@data-hydro-click, \"COPY_URL\")]")
    WebElement copyUrl;

    public void clickCodeButton(){
        codeButton.click();
    }

    public String clickCopyUrlButton(){
        copyUrl.click();
        return getCopiedValueFromClipboard();
    }

    public RepositoryCodePage(WebDriver driver) {
        super(driver);
    }
}
