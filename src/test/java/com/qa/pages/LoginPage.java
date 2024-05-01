package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AppFactory {
    public LoginPage(){
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()),this);
    }

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement userNameTextBox;

    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordTextBox;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
    private WebElement errorMessage;

    public void enterUserName(String username){
        sendKeys(userNameTextBox,username);
    }

    public void enterPassword(String password){
        sendKeys(passwordTextBox,password);
    }
    public ProductPage clickLoginButton(){
        clickElement(loginButton);

        return new ProductPage();
    }

    public String getErrorMessage(){
        return  getAttribute(errorMessage,"text");
    }
}
