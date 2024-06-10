package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage{

    public SettingsPage(){
        super();
    }

    @AndroidFindBy(accessibility = "test-LOGOUT")
    public WebElement logoutButton;

    public LoginPage clickLogoutButton() {
        clickElement(logoutButton, "Clicking on Logout Button");
        return new LoginPage();
    }
}
