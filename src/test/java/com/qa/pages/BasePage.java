package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends AppFactory {

    public BasePage(){
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()),this);
    }

}
