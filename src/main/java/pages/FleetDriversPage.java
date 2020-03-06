package pages;

import libs.Database;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FleetDriversPage extends ParentPage {

    public FleetDriversPage(WebDriver webDriver, Database dBMySQL) {
        super(webDriver, "dash/fleet/fleetDrivers/", dBMySQL);
    }

    @FindBy(xpath = ".//a[@href='/dash/fleet/fleetDrivers/']")
    private WebElement DriversPage;

    @FindBy(xpath = ".//*[@placeholder='Email']")
    private WebElement EmailInput;

    @FindBy(xpath = ".//button[text()='Driver Settings']")
    private WebElement DriverSettingsButton;

    @FindBy(xpath = ".//*[@id='dr_smart_safety']//*[text()='On']")
    private WebElement SmartSafetyButton;

    @FindBy(xpath = ".//h4[text()='Smart Safety Software Subscription Service Agreement']")
    private WebElement SmartSafetyAgreement;

    @FindBy(xpath = ".//button[text()='I Agree']")
    private WebElement iAgreeButton;

    @FindBy(xpath = ".//button[@class='btn btn-default oneDr save_edit' and text()='Save']")
    private WebElement saveButton;

    public void goToDriverPage(){actionsWithOurElements.clickOnElement(DriversPage);}
    public void enterDriverEmail(String driverEmail){actionsWithOurElements.enterTextToElement(EmailInput, driverEmail);}
    public void clickOnDriverInList(String driverId){actionsWithOurElements.clickOnElement(".//tr[@data-id='" + driverId + "']");}
    public void clickOnDriverSettings(){actionsWithOurElements.clickOnElement(DriverSettingsButton);}
    public void clickOnSmartSafety(){actionsWithOurElements.clickOnElement(SmartSafetyButton);}
    public boolean isAgreementPresent(){return actionsWithOurElements.isElementDisplay(SmartSafetyAgreement);}
    public void clickOnButtonIAgree(){
        actionsWithOurElements.moveToElement(iAgreeButton);
        actionsWithOurElements.clickOnElement(iAgreeButton);
    }
    public void clickOnSaveButton(){actionsWithOurElements.clickOnElement(saveButton);}




}
