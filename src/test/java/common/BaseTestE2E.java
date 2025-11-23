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

import java.util.Properties;

import static helper.PropertiesHelper.loadAllFiles;

@Listeners(TestListeners.class)
public class BaseTestE2E extends CommonPage {
    Properties setUp = loadAllFiles();

    @BeforeClass
    @Parameters({"browser", "url"})
    public void createDriver(@Optional("chrome") String browser, @Optional("url") String url) {
        WebDriver driver;
        if (PropertiesHelper.getValue("BROWSER") != null && !PropertiesHelper.getValue("BROWSER").isEmpty()){
            driver = setUpDriver(PropertiesHelper.getValue("BROWSER"));
        } else {
            driver = setUpDriver(browser);
        }
        DriverFactory.setDriver(driver);

        String targetUrl = setUp.getProperty("URL");
        if (targetUrl == null || targetUrl.trim().isEmpty()) {
            targetUrl = url;
        }

        if (targetUrl != null && !targetUrl.trim().isEmpty()) {
            driver.get(targetUrl);
        } else {
            LogUtils.error("⚠️ WARNING: No URL provided in config or TestNG parameters.");
        }
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
