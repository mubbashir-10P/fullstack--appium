package com.qa.tests;

import com.qa.base.AppFactory;
import org.testng.Assert;

public class BaseTest extends AppFactory {

    protected void LoginToSwagLabAppWithValidCredentials() throws InterruptedException {
        utilities.log().info("Verifying Valid credentials");

        loginPage.enterUserName(loginUser.getJSONObject("validCredentials").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("validCredentials").getString("password"));

        productPage = loginPage.clickLoginButton();

        String expectedResult = stringHashMap.get("product_title");
        Assert.assertEquals(productPage.isProductPageVisible(),expectedResult,"Login Is Not Successful");
        utilities.log().info("Login successfully.");
        Thread.sleep(3000);
    }

}
