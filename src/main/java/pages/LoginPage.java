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
    String errorEmailMessage = setUp.getProperty("ERROR_EMAIL_MESSAGE");
    String errorPasswordMessage = setUp.getProperty("ERROR_PASSWORD_MESSAGE");

    public String getHeaderText(){
        waitForElementPresent(By.xpath(loginHeader));
        return getElementText(By.xpath(loginHeader));
    }

    public void login(String email, String password){
        setText(By.xpath(emailField), email);
        setText(By.xpath(passwordField), password);
    }

    public void clickLoginButton(){
        waitForElementClickable(By.cssSelector(loginButton));
        clickElement(By.cssSelector(loginButton));
    }

    public String getSuccessfullyLogin(){
        waitForElementClickable(By.xpath(successfullyLogin));
        return getElementText(By.xpath(successfullyLogin));
    }

    public String getErrorEmailMessage(){
        waitForElementPresent(By.xpath(errorEmailMessage));
        return getElementText(By.xpath(errorEmailMessage));
    }

    public String getErrorPasswordMessage(){
        waitForElementPresent(By.xpath(errorPasswordMessage));
        return getElementText(By.xpath(errorPasswordMessage));
    }

}
