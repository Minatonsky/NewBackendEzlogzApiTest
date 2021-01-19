package pages;

import libs.ActionsWithWebElements;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ParentPage {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    String url;
    ActionsWithWebElements actionsWithWebElements;

    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.url = "https://www.google.com/";
        PageFactory.initElements(webDriver, this);
        actionsWithWebElements = new ActionsWithWebElements(webDriver);
    }
}
