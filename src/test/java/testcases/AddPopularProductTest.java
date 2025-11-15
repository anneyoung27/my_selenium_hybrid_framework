package testcases;

import common.BaseTest;
import constant.ConfigData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

import static keywords.WebUI.openURL;

public class AddPopularProductTest extends BaseTest {
    HomePage homePage;

    @Test
    public void addPopularProduct(){
        openURL(ConfigData.URL);

        homePage = new HomePage();
        homePage.getHomePage();

        homePage.clickHomePage();

        String actualHeader = homePage.getHomeHeader();
        Assert.assertEquals(actualHeader, "Welcome to the sweet shop!");

        int expectedCountOfProduct = 4;
        int actualCountOfProduct = homePage.getCountOfPopularProducts();
        Assert.assertEquals(actualCountOfProduct, expectedCountOfProduct);

        Random rdm = new Random();
        int pickRandomProduct = rdm.nextInt(actualCountOfProduct);
        homePage.clickAddToBasketButton(pickRandomProduct);

        int expectedCountOfItemInBasket = 1;
        int actualCountOfItemInBasket = homePage.getCountOfAddToBasketButtons();
        Assert.assertEquals(actualCountOfItemInBasket, expectedCountOfItemInBasket);
    }

}
