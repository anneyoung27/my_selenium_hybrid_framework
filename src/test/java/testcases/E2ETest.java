package testcases;

import common.BaseTestE2E;
import constant.ConfigData;
import org.testng.annotations.Test;
import pages.*;

import static keywords.WebUI.openURL;
import static keywords.WebUI.verifyEquals;

public class E2ETest extends BaseTestE2E {
    LoginPage loginPage;
    HomePage homePage;
    AboutPage aboutPage;
    ProductPage productPage;
    BasketPage basketPage;

    static final String EMAIL = ConfigData.USER_EMAIL;
    static final String PASSWORD = ConfigData.USER_PASSWORD;

    @Test(priority = 1)
    public void testLoginSuccess(){
        openURL(ConfigData.URL);

        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        String actualHeader = loginPage.getHeaderText();
        verifyEquals(actualHeader, "Login");

        loginPage.login(EMAIL, PASSWORD);
        loginPage.clickLoginButton();

        String actualSuccessLogin = loginPage.getSuccessfullyLogin();
        verifyEquals(actualSuccessLogin, "Welcome back test@user.com");
    }

    @Test(priority = 2, dependsOnMethods = {"testLoginSuccess"})
    public void viewAboutApplication(){
        aboutPage = new AboutPage();

        aboutPage.getAboutPage();
        aboutPage.clickAboutPage();

        String actualHeader = aboutPage.getAboutHeader();
        verifyEquals(actualHeader, "Sweer Shop Project");
    }

    @Test(priority = 3, dependsOnMethods = {"viewAboutApplication"})
    public void addPopularProductToCart(){
        homePage = new HomePage();

        homePage.getHomePage();
        homePage.clickHomePage();


    }
}
