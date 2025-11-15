package pages;

import keywords.WebUI;
import org.openqa.selenium.By;

import java.util.Properties;

import static keywords.WebUI.*;

public class HomePage {
    Properties setUp;

    public HomePage(){}

    String homeHeader = setUp.getProperty("HOME_HEADER");
    String popularProductList = setUp.getProperty("POPULAR_PRODUCTS_SECTION");
    String basketCount = setUp.getProperty("BASKET_COUNT");

    public String getHomeHeader(){
        waitForElementPresent(By.xpath(homeHeader));
        return By.xpath(homeHeader).toString();
    }

    public int getCountOfPopularProducts(){
        waitForElementPresent(By.xpath(popularProductList));
        return getListWebElements(By.xpath(popularProductList)).size();
    }

    public String getPopularProductsName(){
        waitForElementPresent(By.xpath(popularProductList));
        return By.xpath(popularProductList).toString();
    }

    public void clickAddToBasketButton(int index){
        String addToBasketButton = "//div[@class='row text-center']/div//a[@data-id='"+index+"']";
        waitForElementClickable(By.xpath(addToBasketButton));
        clickElement(By.xpath(addToBasketButton));
    }

    public int getCountOfAddToBasketButtons(){
        waitForElementPresent(By.xpath(basketCount));
        return Integer.parseInt(getWebElement(By.xpath(basketCount)).toString());
    }

}
