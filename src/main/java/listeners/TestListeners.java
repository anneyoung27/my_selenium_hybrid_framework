package listeners;

import com.aventstack.extentreports.Status;
import helper.CaptureHelper;
import helper.PropertiesHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;

public class TestListeners implements ITestListener {

    public String getTestName(ITestResult result){
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result){
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("⭐\uFE0F ********* START TESTING " + context.getName() + " *********");
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("⭐\uFE0F ********* END TESTING " + context.getName() + " *********");
        ExtentReportManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("➡\uFE0F Starting test case " + getTestName(result));

        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.startRecord(getTestName(result));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅ Test case " + getTestName(result) + " passed.");

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, getTestName(result) + " is passed.");

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_PASS").equals("true")) {
            CaptureHelper.screenshot(getTestName(result));
        }

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ Test case " + getTestName(result) + " failed.");
        LogUtils.error(result.getThrowable());

        //Extent Report
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, getTestName(result) + " is failed.");

        //Allure Report
        //AllureManager.saveTextLog(iTestResult.getThrowable().toString());
        //AllureManager.saveTextLog(iTestResult.getName() + " is failed.");

        if(PropertiesHelper.getValue("SCREENSHOT_STEP_FAIL").equals("true")) {
            CaptureHelper.screenshot(getTestName(result));
            ExtentTestManager.addScreenshot(getTestName(result));
            AllureManager.saveScreenshotPNG();
        }

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("\uD83D\uDD1C Test case " + getTestName(result) + " skipped.");
        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());

        if(PropertiesHelper.getValue("RECORD_VIDEO").equals("true")){
            CaptureHelper.stopRecord();
        }
    }
}
