package pages;

import org.openqa.selenium.By;

import java.util.Properties;

import static helper.PropertiesHelper.loadAllFiles;
import static keywords.WebUI.waitForElementPresent;

public class AboutPage extends CommonPage{

    private Properties setUp = loadAllFiles();

    public AboutPage() {}

    // Elements
    String aboutHeader = setUp.getProperty("ABOUT_HEADER");

    public String getAboutHeader(){
        waitForElementPresent(By.xpath(aboutHeader));
        return By.xpath(aboutHeader).toString();
    }
}
