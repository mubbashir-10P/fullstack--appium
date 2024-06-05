package com.qa.tests;

import com.qa.base.AppFactory;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginTest extends AppFactory {

    @Test
    public void verifyInvalidUserName() throws InterruptedException {
        utilities.log().info("Verifying Valid credentials");

        loginPage.enterUserName(loginUser.getJSONObject("invalidUser").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("invalidUser").getString("password"));

        loginPage.clickLoginButton();

        String expectedErrorMessage = stringHashMap.get("error_invalid_username_and_password");
        String actualErrorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Login not failed!");

        utilities.log().info("Login Failed");
        Thread.sleep(3000);
    }

    @Test
    public void verifyInvalidPassword() throws InterruptedException {
        utilities.log().info("Verifying Valid credentials");

        loginPage.enterUserName(loginUser.getJSONObject("invalidPassword").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("invalidPassword").getString("password"));

        loginPage.clickLoginButton();

        String expectedErrorMessage = stringHashMap.get("error_invalid_username_and_password");
        String actualErrorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Login not failed!");
        utilities.log().info("Login Failed");
        Thread.sleep(3000);
    }

    @Test
    public void verifyValidUserLogin() throws InterruptedException {
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
