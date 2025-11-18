package common;

import factory.DriverFactory;
import helper.PropertiesHelper;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.CommonPage;
import utils.LogUtils;

@Listeners(TestListeners.class)
public class BaseTestE2E extends CommonPage {
    @BeforeClass
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver;
        if (PropertiesHelper.getValue("BROWSER") != null && !PropertiesHelper.getValue("BROWSER").isEmpty()){
            driver = setUpDriver(PropertiesHelper.getValue("BROWSER"));
        } else {
            driver = setUpDriver(browser);
        }
        DriverFactory.setDriver(driver);
    }

    public WebDriver setUpDriver(String browser) {
        WebDriver driver = switch (browser.toLowerCase()) {
            case "firefox" -> initFireFoxDriver();
            case "edge" -> initEdgeDriver();
            default -> initChromeDriver();
        };
        DriverFactory.setDriver(driver);
        return driver;
    }

    private WebDriver initChromeDriver() {
        LogUtils.info("Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        LogUtils.info("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFireFoxDriver() {
        LogUtils.info("Launching FireFox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterClass
    public void closeDriver(){
        DriverFactory.quit();
    }
}
