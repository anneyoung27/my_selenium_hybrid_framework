package pages;

import org.openqa.selenium.By;

import java.util.Properties;

import static helper.PropertiesHelper.loadAllFiles;
import static keywords.WebUI.*;

public class LoginPage extends CommonPage{

    Properties setUp = loadAllFiles();

    public LoginPage(){

    }

    // Elements
    String loginHeader = setUp.getProperty("LOGIN_HEADER");
    String emailField = setUp.getProperty("EMAIL_ADDRESS");
    String passwordField = setUp.getProperty("PASSWORD");
    String loginButton = setUp.getProperty("LOGIN_BTN");
    String successfullyLogin = setUp.getProperty("SUCCESSFULLY_LOGIN"); // Welcome back test@user.com

    public String getHeaderText(){
        waitForElementPresent(By.xpath(loginHeader));
        return By.xpath(loginHeader).toString();
    }

    public void login(String email, String password){
        setText(By.xpath(emailField), email);
        setText(By.xpath(passwordField), password);
        clickElement(By.xpath(loginButton));
    }

    public String getSuccessfullyLogin(){
        waitForElementClickable(By.xpath(successfullyLogin));
        return By.xpath(successfullyLogin).toString();
    }

}
