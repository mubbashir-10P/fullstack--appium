package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.*;


public class LoginTest extends BaseTest {

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
        LoginToSwagLabAppWithValidCredentials();
    }
}
