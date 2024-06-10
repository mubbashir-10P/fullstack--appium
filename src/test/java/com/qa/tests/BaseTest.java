package com.qa.tests;

import com.qa.base.AppFactory;
import com.qa.pages.MenuPage;
import org.testng.Assert;

public class BaseTest extends AppFactory {

    protected void login(){
        productPage = loginPage.loginToApp(loginUser.getJSONObject("validCredentials").getString("userName")
                ,loginUser.getJSONObject("validCredentials").getString("password"));

        String expectedResult = stringHashMap.get("product_title");
        Assert.assertEquals(productPage.isProductPageVisible(),expectedResult,"Login Is Not Successful");
    }

    protected void logOut(){
        menuPage = new MenuPage();
        settingsPage = menuPage.clickSettingsButton();
        settingsPage.clickLogoutButton();
    }
}
