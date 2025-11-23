package dataDrivenTest;

import common.BaseTest;
import factory.DataProviderFactory;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Hashtable;

public class LoginTestWithDataProvider extends BaseTest {
    private LoginPage loginPage;

    @Test(priority = 1, dataProvider = "data_provider_login", dataProviderClass = DataProviderFactory.class)
    public void loginSuccessfulTest(String email, String password){
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        loginPage.login(email, password);
        loginPage.clickLoginButton();
    }

    @Test(priority = 2, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessDataProviderFromExcel(String email, String password){
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        loginPage.login(email, password);
        loginPage.clickLoginButton();
    }

    @Test(priority = 3, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
    public void testLoginSuccessDataProviderFromExcel_Hashtable(Hashtable<String, String> data){
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        loginPage.login(data.get("EMAIL"), data.get("PASSWORD"));
        loginPage.clickLoginButton();
    }
}