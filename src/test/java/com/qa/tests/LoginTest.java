package com.qa.tests;

import com.qa.base.AppFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;

import java.lang.reflect.Method;

public class LoginTest extends AppFactory {
    LoginPage loginPage;
    ProductPage productPage;

    @BeforeMethod
    public void setUp(Method method){
        loginPage = new LoginPage();
        System.out.println("\n"+ "******** Start Test:" + method.getName()+ "********" + "\n");
    }

    @Test
    public void verifyInvalidUserName() throws InterruptedException {
        System.out.println("Verifying Valid credentials");

        loginPage.enterUserName("invalidUserName");
        loginPage.enterPassword("secret_sauce");

        loginPage.clickLoginButton();

        String expectedErrorMessage = "Username and password do not match any user in this service.";
        String actualErrorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Login not failed!");

        System.out.println("Login Failed");
        Thread.sleep(3000);
    }

    @Test
    public void verifyInvalidPassword() throws InterruptedException {
        System.out.println("Verifying Valid credentials");

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("invalidPassword");

        loginPage.clickLoginButton();

        String expectedErrorMessage = "Username and password do not match any user in this service.";
        String actualErrorMessage = loginPage.getErrorMessage();

        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"Login not failed!");
        System.out.println("Login Failed");
        Thread.sleep(3000);
    }

    @Test
    public void verifyValidUserLogin() throws InterruptedException {
        System.out.println("Verifying Valid credentials");

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");

        productPage = loginPage.clickLoginButton();

        Assert.assertEquals(productPage.isProductPageVisible(),true,"Login Is Not Successful");
        System.out.println("Login successfully.");
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown(){
        //AppFactory.quiteDriver();
    }
}
