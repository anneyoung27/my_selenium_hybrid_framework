package datadriventest;

import common.BaseTest;
import helper.ExcelHelper;
import org.testng.annotations.Test;
import pages.LoginPage;

import static keywords.WebUI.verifyEquals;

public class LoginTestWithExcel extends BaseTest {
    private LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSuccess() {
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/java/testdata/ExcelData.xlsx", "LoginTest");

        for (int i = 1; i <= 2; i++) {

            String email = excel.getCellData("EMAIL", i);
            String password = excel.getCellData("PASSWORD", i);

            loginPage.login(email, password);
            loginPage.clickLoginButton();

            excel.setCellData("PASSED", "STATUS", i);

            loginPage.getLoginPage();
            loginPage.clickLoginPage();
        }

        loginPage.clickLoginButton();

    }


    @Test (priority = 2)
    public void testLoginFailWithEmailInvalid(){
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/java/testdata/ExcelData.xlsx", "LoginTest");

        loginPage.login(
            excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3)
        );

        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getErrorEmailMessage();
        verifyEquals(actualErrorMessage, "Please enter a valid email address.");

        excelHelper.setCellData("PASSED", "STATUS", 3); // set status = PASSED if success
    }

    @Test (priority = 3)
    public void testLoginFailWithEmailNull(){
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/java/testdata/ExcelData.xlsx", "LoginTest");

        loginPage.login(
                excelHelper.getCellData("EMAIL", 4),
                excelHelper.getCellData("PASSWORD", 4)
        );

        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getErrorEmailMessage();
        verifyEquals(actualErrorMessage, "Please enter a valid email address.");

        excelHelper.setCellData("PASSED", "STATUS", 4); // set status = PASSED if success
    }

    @Test (priority = 4)
    public void testLoginFailWithPasswordNull(){
        loginPage = new LoginPage();

        loginPage.getLoginPage();
        loginPage.clickLoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/java/testdata/ExcelData.xlsx", "LoginTest");

        loginPage.login(
                excelHelper.getCellData("EMAIL", 5),
                excelHelper.getCellData("PASSWORD", 5)
        );

        loginPage.clickLoginButton();

        String actualErrorMessage = loginPage.getErrorPasswordMessage();
        verifyEquals(actualErrorMessage, "Please enter a valid password.");

        excelHelper.setCellData("PASSED", "STATUS", 5); // set status = PASSED if success
    }
}
