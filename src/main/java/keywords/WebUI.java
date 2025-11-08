package keywords;

import com.aventstack.extentreports.Status;
import constant.ConfigData;
import factory.DriverFactory;
import helper.SystemHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class WebUI {
    private static int EXPLICIT_WAIT_TIMEOUT = ConfigData.EXPLICIT_WAIT_TIMEOUT;
    private static double STEP_TIME = ConfigData.STEP_TIME;
    private static int PAGE_LOAD_TIMEOUT = ConfigData.PAGE_LOAD_TIMEOUT;

    @Step("Check data: {1} in Table by column {2}")
    public static void checkDataInTableByColumn_Contains(int column, String value, String columnName){
        LogUtils.info("\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
        ExtentTestManager.logMessage(Status.INFO, "\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);

        List<WebElement> row = DriverFactory.getDriver().findElements(By.xpath("//table/tbody/tr"));
        int rowTotal = row.size();
        LogUtils.info("Total row in table: " + rowTotal);

        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverFactory.getDriver().findElement(By.xpath("//table/tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            LogUtils.info("Data in row " + i + " : " + elementCheck.getText());
            Assert.assertTrue(SystemHelper.removeSpecialCharacters(elementCheck.getText()).toUpperCase().contains(SystemHelper.removeSpecialCharacters(value).toUpperCase()),
                    "Data " + value + " is not found in table at row " + i + " and column " + columnName);
        }
    }

    @Step("Check data: {1} in Table by column {2}")
    public static void checkDataInTableByColumn_Equals(int column, String value, String columnName){
        LogUtils.info("\uD83D\uDFE2 Check data " + value + " in Table by column " + columnName);
        ExtentTestManager.logMessage(Status.INFO, "\uD8ED\uDFE2 Check data " + value + " in Table by column " + columnName);

        List<WebElement> row = DriverFactory.getDriver().findElements(By.xpath("//table/tbody/tr"));
        int rowTotal = row.size();
        LogUtils.info("Total row in table: " + rowTotal);

        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = DriverFactory.getDriver().findElement(By.xpath("//table/tbody/tr[" + i + "]/td[" + column + "]"));

            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            LogUtils.info("Data in row " + i + " : " + elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().equals(value.toUpperCase()),
                    "Data " + value + " is not found in table at row " + i + " and column " + columnName);
        }
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void uploadFileWithRobotClass(By elementFileFrom, String filePath){
        WebUI.clickElement(elementFileFrom);
        WebUI.sleep(2);

        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy file path to clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        sleep(1);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        sleep(2);
    }

    public static void logConsole(Object message){
        LogUtils.info(message);
    }

    public static WebElement getWebElement(By by){
        return DriverFactory.getDriver().findElement(by);
    }

    public static List<WebElement> getListWebElements(By by){
        return DriverFactory.getDriver().findElements(by);
    }

    public static Boolean checkElementExists(By by){
        sleep(2);
        List<WebElement> elements = DriverFactory.getDriver().findElements(by);

        if (elements.size() > 0){
            LogUtils.info("Element is found: " + by.toString());
            return true;
        } else {
            LogUtils.error("Element is not found: " + by.toString());
            return false;
        }
    }



}
