package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.AboutPage;

import static keywords.WebUI.verifyEquals;

public class NavigateAboutPageTest extends BaseTest {
    AboutPage aboutPage;

    @Test
    public void navigateAboutPage(){
        aboutPage = new AboutPage();

        aboutPage.clickAboutPage();

        String actualHeader = aboutPage.getAboutHeader();
        verifyEquals(actualHeader, "Sweet Shop Project");
    }
}
