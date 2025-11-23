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
public class BaseTest extends CommonPage {
    Properties setUp = loadAllFiles();
    /**
     * @BeforeMethod > dieksekusi sebelum setiap @Test method, jika dalam 1 class ada 5 @Test method, maka @BeforeMethod
     * dipanggil 5x
     * Kapan digunakan?
     *   - Setiap test harus mulai dari kondisi baru
     *   - Setiap test ingin membuka halaman awal
     *   - Setiap test tidak saling tergantung
     * @BeforeClass > Dieksekusi sekali saja sebelum seluruh @Test dalam class, walaupun ada 10 @Test method, method
     * ini tetap hanya dipanggil 1 kali
     * Kapan digunakan?
     *    - Jika semua test case di dalam class menggunakan browser yang sama
     *    - Jika test saling bergantung
     *    - Jika ingin test berjalan cepat tanpa restart brwoser
     *    - Cocok untuk end-to-end flow
     */
    @BeforeMethod // using @BeforeClass to make browser running just 1x for all the @Test cases in class
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
