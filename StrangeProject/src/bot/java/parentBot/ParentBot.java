package parentBot;

import com.github.javafaker.Faker;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.GooglePage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


import static java.util.concurrent.TimeUnit.SECONDS;

public class ParentBot {
    Logger logger = Logger.getLogger(getClass());
    WebDriver webDriver;
    protected Faker faker;
    protected GooglePage googlePage;

    String browser = System.getProperty("browser");

    @Before
    public void setUp(){
        initDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(20, SECONDS);
        faker = new Faker();
        googlePage = new GooglePage(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }

    private void initDriver(String browserName) {
        if ( browserName == null || browserName.equals("chrome")) {
            logger.info("Chrome will be started");
            File file = new File("./src/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            webDriver = new ChromeDriver(options);
            logger.info("Chrome is started");

        } else if ("remote".equals(browser)){
            logger.info("Remote Driver will be started");
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        else {
            logger.error("Can`t init driver");
            Assert.fail("Can`t init driver");
        }
    }
}
