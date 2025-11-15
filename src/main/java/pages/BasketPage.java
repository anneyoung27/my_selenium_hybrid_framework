package pages;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LogUtils;

import java.util.*;
import java.util.stream.Collectors;

import static helper.PropertiesHelper.loadAllFiles;
import static keywords.WebUI.*;

public class BasketPage extends CommonPage{

    private Properties setUp = loadAllFiles();

    public BasketPage(){}

    String basketHeader = setUp.getProperty("BASKET_HEADER");
    String firstName = setUp.getProperty("FIRST_NAME");
    String lastName = setUp.getProperty("LAST_NAME");
    String userEmail = setUp.getProperty("USER_EMAIL");
    String address = setUp.getProperty("ADDRESS");
    String address_2 = setUp.getProperty("ADDRESS_2");
    String country = setUp.getProperty("COUNTRY");
    String city = setUp.getProperty("CITY");
    String zipCode = setUp.getProperty("ZIP");
    String nameOnCard = setUp.getProperty("NAME_ON_CARD");
    String ccNumber = setUp.getProperty("CC_NUMBER");
    String expiration = setUp.getProperty("EXPIRATION");
    String cvv = setUp.getProperty("CVV");
    String listItemsInBasket = setUp.getProperty("LIST_ITEMS_IN_BASKET");
    String deletePerItem = setUp.getProperty("DELETE_PER_ITEM");
    String pricePerItemInBasket = setUp.getProperty("PRICE_PER_ITEM_IN_BASKET");
    String deliveryCollect = setUp.getProperty("DELIVERY_COLLECT");
    String deliveryStandard = setUp.getProperty("DELIVERY_STANDARD");
    String resetBasket = setUp.getProperty("RESET_BASKET");
    String continueToCheckoutButton = setUp.getProperty("CONTINUE_CHECKOUT_BTN");

    public String getBasketHeader(){
        waitForElementPresent(By.xpath(basketHeader));
        return By.xpath(basketHeader).toString();
    }

    public void setFirstName(String fName){
        setText(By.xpath(firstName), fName);
    }

    public void setLastName(String lName){
        setText(By.xpath(lastName), lName);
    }

    public void setEmail(String email){
        setText(By.xpath(userEmail), email);
    }

    public void setAddress(String add){
        setText(By.xpath(this.address), add);
    }

    public void setAddress_2(String add_2){
        setText(By.xpath(address_2), add_2);
    }

    public void setCountry(String cName){
        setText(By.xpath(country), cName);
    }

    public void setCity(String cName){
        setText(By.xpath(city), cName);
    }

    public void setZip(String zip){
        setText(By.xpath(zipCode), zip);
    }

    public void setNameOnCard(String nameCard){
        setText(By.xpath(nameOnCard), nameCard);
    }

    public void setCCNumber(String ccNum) {
        setText(By.xpath(ccNumber), ccNum);

    }

    public void setExpiration(String exp){
        setText(By.xpath(expiration), exp);
    }

    public void setCVV(String cvvNum){
        setText(By.xpath(cvv), cvvNum);
    }

    public String getTotalAmountBasket(double totalAmount){
        // xpath = //strong[normalize-space()='£0.00']
        String xpath = "//strong[normalize-space()='£" + totalAmount + "']";

        waitForElementPresent(By.xpath(xpath));
        return By.xpath(xpath).toString();
    }

    public void clickCollectDelivery() {
        waitForElementPresent(By.xpath(deliveryCollect));
        clickElement(By.xpath(deliveryCollect));
    }

    public void clickStandardDelivery() {
        waitForElementPresent(By.xpath(deliveryStandard));
        clickElement(By.xpath(deliveryStandard));
    }

    public void clickResetBasket() {
        waitForElementPresent(By.cssSelector(resetBasket));
        clickElement(By.cssSelector(resetBasket));
    }

    public void clickContinueToCheckout() {
        waitForElementPresent(By.xpath(continueToCheckoutButton));
        clickElement(By.xpath(continueToCheckoutButton));
    }

    public void verifyAddedProductInCart(String [] addedProduct){
        List<WebElement> items = getListWebElements(
                By.xpath(listItemsInBasket)
        );

        Set<String> basketProductNames = items.stream()
                .map(e -> e.getText().trim())
                .collect(Collectors.toSet());

        for (String prod : addedProduct) {
            if (basketProductNames.contains(prod)) {
                LogUtils.info("Product found in basket: " + prod);
            } else {
                LogUtils.error("Product missing from basket: " + prod);
            }
        }
    }

    public void verifyAmountOfItemsInBasket(int expectedCount){
        List<WebElement> items = getListWebElements(
                By.xpath(listItemsInBasket)
        );

        int actualCount = items.size();

        if (actualCount == expectedCount) {
            LogUtils.info("Basket contains the expected number of items: " + expectedCount);
        } else {
            LogUtils.error("Basket item count mismatch. Expected: " + expectedCount + ", Actual: " + actualCount);
        }
    }

    public double verifyPricePerItemInBasket() {
        List<WebElement> priceElements = getListWebElements(
                By.xpath(pricePerItemInBasket)
        );

        double priceSum = 0.0;
        for (WebElement priceElement : priceElements) {
            String actualPrice = priceElement.getText().trim();
            actualPrice = actualPrice.replaceAll("[^0-9.]", "");
            if (!actualPrice.isEmpty()) {
                priceSum += Double.parseDouble(actualPrice);
            }
        }
        return priceSum;
    }


}
