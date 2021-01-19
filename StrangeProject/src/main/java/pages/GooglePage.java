package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends ParentPage {

    public GooglePage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(name = "q")
    private WebElement searchInput;

    private void enterText(String text){actionsWithWebElements.enterTextToElement(searchInput, text);}


    public void openPage() {
        try {
            webDriver.get(url);
            logger.info("Google Page was opened");
        } catch (Exception e) {
            logger.error("Can not open LoginPage");
        }
    }
    public void enterTextToSearchInput(String text){
        enterText(text);
        actionsWithWebElements.sendEnterKey(searchInput);
    }
}
