package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BasePage{
    public MenuPage(){
        super();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement settingsButton;

    public SettingsPage clickSettingsButton() {
        waitForElementToBeClickable(settingsButton);
        clickElement(settingsButton, "Clicking on Settings Button");
        return new SettingsPage();
    }
}
