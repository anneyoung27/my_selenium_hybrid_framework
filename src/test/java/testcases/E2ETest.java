package testcases;

import common.BaseTestE2E;
import constant.ConfigData;
import org.testng.annotations.Test;
import pages.*;

import java.util.Random;

import static keywords.WebUI.*;

public class E2ETest extends BaseTestE2E {
    LoginPage loginPage;
    HomePage homePage;
    AboutPage aboutPage;
    ProductPage productPage;
    BasketPage basketPage;
    int pickRandomProduct;

    static final String EMAIL = ConfigData.USER_EMAIL;
    static final String PASSWORD = ConfigData.USER_PASSWORD;

    static final String FIRST_NAME = ConfigData.FIRST_NAME;
    static final String LAST_NAME = ConfigData.LAST_NAME;
    static final String ADDRESS = ConfigData.ADDRESS;
    static final String ADDRESS_2 = ConfigData.ADDRESS_2;
    static final String ZIP_CODE = ConfigData.ZIP;
    static final String NAME_ON_CARD = ConfigData.NAME_ON_CARD;
    static final String CC_NUM = ConfigData.CC_NUM;
    static final String EXPIRATION = ConfigData.EXPIRATION;

    @Test(priority = 1)
    public void testLoginSuccess(){
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
        verifyEquals(actualHeader, "Sweet Shop Project");
    }

    @Test(priority = 3, dependsOnMethods = {"viewAboutApplication"})
    public void addPopularProductToCart(){
        Random rdm = new Random();
        homePage = new HomePage();

        homePage.getHomePage();
        homePage.clickHomePage();

        int expectedCountOfProduct = 4;
        int actualCountOfProduct = homePage.getCountOfPopularProducts();
        verifyEquals(actualCountOfProduct, expectedCountOfProduct);

        int pickRandomProduct = rdm.nextInt(actualCountOfProduct);
        homePage.clickAddToBasketButton(pickRandomProduct);

        int expectedCountOfItemInBasket = 1;
        int actualCountOfItemInBasket = homePage.getCountOfAddToBasketButtons();
        verifyEquals(actualCountOfItemInBasket, expectedCountOfItemInBasket);
    }

    @Test(priority = 4, dependsOnMethods = {"addPopularProductToCart"})
    public void addMoreProductToCart(){
        Random rdm = new Random();
        productPage = new ProductPage();

        productPage.getProductPage();
        productPage.clickProductPage();

        String actualHeader = productPage.getProductHeader();
        verifyEquals(actualHeader, "Browse sweets");

        int expectedProductsList = 12;
        int actualProductList = productPage.getCountOfProducts();
        verifyEquals(actualProductList, expectedProductsList);

        pickRandomProduct = rdm.nextInt(actualProductList);
        productPage.clickAddToBasketButton(pickRandomProduct);
    }

    @Test(priority = 5, dependsOnMethods = {"addMoreProductToCart"})
    public void verifyAddedProducts(){
        basketPage = new BasketPage();

        basketPage.getBasketPage();
        basketPage.clickBasketPage();

        String actualHeader = basketPage.getBasketHeader();
        verifyEquals(actualHeader, "Your Basket");

        int actualTotalProductInBasket = basketPage.getTotalProductInBasket();
        verifyEquals(actualTotalProductInBasket, 2);
    }

    @Test(priority = 6, dependsOnMethods = {"verifyAddedProducts"})
    public void checkOutProducts(){
        basketPage.clickContinueToCheckout();

        boolean isThereAnyError = basketPage.hasAnyError();
        verifyTrue(isThereAnyError);

        boolean expectedCountOfErrorFields = basketPage.allFieldsHaveErrors(11);
        verifyTrue(expectedCountOfErrorFields);

        basketPage.setFirstName(FIRST_NAME);
        basketPage.setLastName(LAST_NAME);
        basketPage.setEmail(EMAIL);
        basketPage.setAddress(ADDRESS);
        basketPage.setAddress_2(ADDRESS_2);
        basketPage.setCountry("United Kingdom");
        basketPage.setCity("Swansea");
        basketPage.setZip(ZIP_CODE);
        basketPage.setNameOnCard(NAME_ON_CARD);
        basketPage.setCCNumber(CC_NUM);
        basketPage.setExpiration(EXPIRATION);
        basketPage.setCVV("8");

        basketPage.clickContinueToCheckout();
    }
}
