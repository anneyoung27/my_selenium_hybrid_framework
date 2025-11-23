package testcases;

import common.BaseTest;
import constant.ConfigData;
import helper.CaptureHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import static keywords.WebUI.openURL;
import static keywords.WebUI.verifyEquals;

public class UserLoginTest extends BaseTest {
    LoginPage loginPage;

    private final String email = ConfigData.USER_EMAIL;
    private final String password = ConfigData.USER_PASSWORD;

    @Test
    public void userLoginTest(){
        // record every step while testing
        // CaptureHelper.startRecord("userLoginTest");

        loginPage = new LoginPage();
        loginPage.getLoginPage();

        loginPage.clickLoginPage();

        // capture every step
        // CaptureHelper.screenshot("Login Page");

        String actualHeader = loginPage.getHeaderText();
        verifyEquals(actualHeader, "Login");

        loginPage.login(email, password);

        loginPage.clickLoginButton();

        String actualSuccessfullyLogin = loginPage.getSuccessfullyLogin();
        verifyEquals(actualSuccessfullyLogin, "Welcome back test@user.com");

        // CaptureHelper.screenshot("User Profile page");
        //CaptureHelper.stopRecord();
    }
}
