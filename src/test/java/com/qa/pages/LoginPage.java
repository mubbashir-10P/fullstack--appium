package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {
    public LoginPage(){
        super();
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
        sendKeys(userNameTextBox,username, "Username is: "+username);
    }

    public void enterPassword(String password){
        sendKeys(passwordTextBox,password, "Password is: "+password);
    }
    public ProductPage clickLoginButton(){
        clickElement(loginButton, "Clicking on login button");
        return new ProductPage();
    }

    public String getErrorMessage(){
        return  getText(errorMessage,"Error text is: ");
    }
}
