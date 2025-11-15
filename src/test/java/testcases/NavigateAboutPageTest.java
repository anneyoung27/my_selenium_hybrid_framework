package testcases;

import common.BaseTest;
import constant.ConfigData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AboutPage;

import static keywords.WebUI.openURL;

public class NavigateAboutPageTest extends BaseTest {
    AboutPage aboutPage;

    @Test
    public void navigateAboutPage(){
        openURL(ConfigData.URL);

        aboutPage = new AboutPage();

        aboutPage.clickAboutPage();

        String actualHeader = aboutPage.getAboutHeader();
        Assert.assertEquals(actualHeader, "Sweet Shop Project");
    }
}
