package testcases;

import common.BaseTest;
import constant.ConfigData;
import org.testng.annotations.Test;
import pages.BasketPage;
import pages.ProductPage;

import java.util.Random;

import static keywords.WebUI.openURL;
import static keywords.WebUI.verifyEquals;

public class AddAndCheckoutProductTest extends BaseTest {
    ProductPage productPage;
    BasketPage basketPage;

    Random rdm;

    static final String FIRST_NAME = ConfigData.FIRST_NAME;
    static final String LAST_NAME = ConfigData.LAST_NAME;
    static final String EMAIL = ConfigData.USER_EMAIL;
    static final String ADDRESS = ConfigData.ADDRESS;
    static final String ADDRESS_2 = ConfigData.ADDRESS_2;
    static final String ZIP_CODE = ConfigData.ZIP;
    static final String NAME_ON_CARD = ConfigData.NAME_ON_CARD;
    static final String CC_NUM = ConfigData.CC_NUM;
    static final String EXPIRATION = ConfigData.EXPIRATION;

    int pickRandomProduct;

    @Test(priority = 1)
    public void addProductFromProductList() {
        openURL(ConfigData.URL);

        productPage = new ProductPage();
        rdm = new Random();

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

    @Test(priority = 2, dependsOnMethods = {"addProductFromProductList"})
    public void checkOutAddedProduct() {
        basketPage = new BasketPage();
        basketPage.getBasketPage();

        basketPage.clickBasketPage();

        String actualHeader = basketPage.getBasketHeader();
        verifyEquals(actualHeader, "Your Basket");

        basketPage.verifyAmountOfItemsInBasket(pickRandomProduct);

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

        basketPage.clickCollectDelivery();
    }

}
// abis itu E2ETest setelah itu > bagian datadriventest & testdata
// > bagian suites

// cara agar @test 1 dan @test2 gabung, @test 2 ngikutin @test 1
