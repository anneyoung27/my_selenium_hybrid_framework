package pages;

import org.openqa.selenium.By;

import java.util.Properties;

import static keywords.WebUI.*;

public class ProductPage {
    Properties setUp;

    public ProductPage(){}

    String productHeader = setUp.getProperty("PRODUCT_HEADER");
    String productNameList = setUp.getProperty("PRODUCT_LIST");


    public String getProductHeader(){
        waitForElementPresent(By.xpath(productHeader));
        return By.xpath(productHeader).toString();
    }

    public int getCountOfProducts(){
        waitForElementPresent(By.xpath(productNameList));
        return getListWebElements(By.xpath(productHeader)).size();
    }

    public String getProductName(int index){
        waitForElementPresent(By.xpath(productNameList));
        return getListWebElements(By.xpath(productNameList)).get(index).getText();
    }

    public void clickAddToBasket(int index){
        String addToBasketButton = "//div[@class='container']//div//a[@data-id='"+index+"']";
        waitForElementClickable(By.xpath(addToBasketButton));
        clickElement(By.xpath(addToBasketButton));
    }





}
