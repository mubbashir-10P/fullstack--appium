package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    public ProductPage(){
        super();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    private WebElement productPageBanner;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Backpack\"]")
    private WebElement backPackTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\" and @text=\"$29.99\"]")
    private WebElement backpackPrice;

    public String isProductPageVisible(){
        String actualResult = getText(productPageBanner, "Product Page Title Is: ");
        return actualResult;
    }

    public String getBackpackTitle() {
        return getText(backPackTitle, "Title is: ");
    }

    public String getBackpackPrice() {
        return getText(backpackPrice, "Product Price is: ");
    }

//    public ProductDetailsPage clickSLBackpackTitle() {
//        clickElement(SLBackpackTitle, "Clicking on SLB Title link");
//        return new ProductDetailsPage();
//    }
}
