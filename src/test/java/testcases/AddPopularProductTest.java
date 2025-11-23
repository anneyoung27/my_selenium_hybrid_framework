package testcases;

import common.BaseTest;
import constant.ConfigData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

import static keywords.WebUI.openURL;
import static keywords.WebUI.verifyEquals;

public class AddPopularProductTest extends BaseTest {
    HomePage homePage;
    Random rdm;

    @Test
    public void addPopularProduct(){
        homePage = new HomePage();
        homePage.getHomePage();

        homePage.clickHomePage();

        String actualHeader = homePage.getHomeHeader();
        verifyEquals(actualHeader, "Welcome to the sweet shop!");

        int expectedCountOfProduct = 4;
        int actualCountOfProduct = homePage.getCountOfPopularProducts();
        verifyEquals(actualCountOfProduct, expectedCountOfProduct);

        rdm = new Random();

        int pickRandomProduct = rdm.nextInt(actualCountOfProduct);
        homePage.clickAddToBasketButton(pickRandomProduct);

        int expectedCountOfItemInBasket = 1;
        int actualCountOfItemInBasket = homePage.getCountOfAddToBasketButtons();
        verifyEquals(actualCountOfItemInBasket, expectedCountOfItemInBasket);
    }

}
