package pages;

import org.openqa.selenium.By;

import java.util.Properties;

import static helper.PropertiesHelper.loadAllFiles;
import static keywords.WebUI.*;

public class ProductPage extends CommonPage{
    Properties setUp = loadAllFiles();

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

    public void clickAddToBasketButton(int index){
        String addToBasketButton = "//div[@class='container']//div//a[@data-id='"+(index + 1)+"']"; // index for data-id: 1-4
        waitForElementClickable(By.xpath(addToBasketButton));

        scrollToElement(By.xpath(addToBasketButton), "false");
        clickElement(By.xpath(addToBasketButton));
    }
}