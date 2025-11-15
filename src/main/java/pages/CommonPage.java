package pages;

import keywords.WebUI;
import org.openqa.selenium.By;

import java.util.Properties;

import static helper.PropertiesHelper.loadAllFiles;

// Buat menampung beberapa halaman dan navigate page
public class CommonPage {
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;
    private BasketPage basketPage;
    private AboutPage aboutPage;


    private Properties setUp = loadAllFiles();

    public CommonPage(){}

    private String menuLogin = setUp.getProperty("LOGIN_PAGE");
    private String menuHome = setUp.getProperty("HOME_PAGE");
    private String menuProduct = setUp.getProperty("PRODUCT_PAGE");
    private String menuBasket = setUp.getProperty("BASKET_PAGE");
    private String menuAbout = setUp.getProperty("ABOUT_PAGE");


    public LoginPage getLoginPage(){
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage getHomePage(){
        if (homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public ProductPage getProductPage(){
        if (productPage == null){
            productPage = new ProductPage();
        }
        return productPage;
    }

    public BasketPage getBasketPage(){
        if (basketPage == null){
            basketPage = new BasketPage();
        }
        return basketPage;
    }

    public AboutPage getAboutPage(){
        if (aboutPage == null){
            aboutPage = new AboutPage();
        }
        return aboutPage;
    }

    public LoginPage clickLoginPage(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath(menuLogin));

        return new LoginPage();
    }

    public HomePage clickHomePage(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath(menuHome));

        return new HomePage();
    }

    public ProductPage clickProductPage(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath(menuProduct));

        return new ProductPage();
    }

    public BasketPage clickBasketPage(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath(menuBasket));

        return new BasketPage();
    }

    public AboutPage clickAboutPage(){
        WebUI.waitForPageLoaded();
        WebUI.clickElement(By.xpath(menuAbout));

        return new AboutPage();
    }
}