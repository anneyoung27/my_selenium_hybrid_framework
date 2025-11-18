package pages;

import org.openqa.selenium.By;
import java.util.Properties;

import static helper.PropertiesHelper.loadAllFiles;
import static keywords.WebUI.*;

public class HomePage extends CommonPage{
    Properties setUp = loadAllFiles();

    public HomePage(){}

    String homeHeader = setUp.getProperty("HOME_HEADER");
    String popularProductList = setUp.getProperty("POPULAR_PRODUCTS_SECTION");
    String basketCount = setUp.getProperty("BASKET_COUNT");

    public String getHomeHeader(){
        waitForElementPresent(By.xpath(homeHeader));
        return getElementText(By.xpath(homeHeader));
    }

    public int getCountOfPopularProducts(){
        waitForElementPresent(By.xpath(popularProductList));
        return getListWebElements(By.xpath(popularProductList)).size();
    }

    public void clickAddToBasketButton(int index){
        String addToBasketButton = "//div[@class='row text-center']/div//a[@data-id='"+(index + 1)+"']"; // index for data-id: 1-4
        waitForElementClickable(By.xpath(addToBasketButton));
        scrollToElement(By.xpath(addToBasketButton), "false");
        clickElement(By.xpath(addToBasketButton));
    }

    public int getCountOfAddToBasketButtons(){
        waitForElementPresent(By.xpath(basketCount));
        return Integer.parseInt(getElementText(By.xpath(basketCount)));
    }
}