package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AppFactory {

    public ProductPage(){
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()),this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    private WebElement productPageBanner;

    public boolean isProductPageVisible(){

        String expectedResult = "PRODUCTS";
        String actualResult = getAttribute(productPageBanner, "text");

        if(expectedResult.equals(actualResult))
            return true;
        else return false;
    }
}