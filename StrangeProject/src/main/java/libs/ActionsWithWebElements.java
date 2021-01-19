package libs;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionsWithWebElements {
    WebDriver webDriver;
    Actions action;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait20;
    WebDriverWait webDriverWait40;

    public ActionsWithWebElements(WebDriver webDriver) {

        this.webDriver = webDriver;
        webDriverWait20 = new WebDriverWait(webDriver, 20);
        webDriverWait40 = new WebDriverWait(webDriver, 40);
        action = new Actions(webDriver);
    }

    public void clickOnElement(WebElement webElement){
        try{
            webDriverWait40.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    private void printErrorAndStopTest(Exception e) {
        logger.error("Cannot work with element " + e);
    }
    public void enterTextToElement(WebElement webElement, String text){
        try{
            webDriverWait20.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element");
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
    public void sendEnterKey(WebElement webElement){
        try {
            webElement.sendKeys(Keys.ENTER);
        } catch (Exception e){
            printErrorAndStopTest(e);
        }
    }
}
