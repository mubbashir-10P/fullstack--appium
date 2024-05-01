package testCases;

import base.AppFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTest extends AppFactory {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    LoginPage loginPage;
    ProductPage productPage;



    @BeforeMethod
    public void setUp(){
        loginPage = new LoginPage();
        productPage = new ProductPage();
    }

    @Test
    public void verifyValidUserLogin() throws InterruptedException {
        System.out.println("Verifying Valid credentials");

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        Assert.assertEquals(productPage.isProductPageVisible(),true,"Login Is Not Successful");
        System.out.println("Login successfully.");
        Thread.sleep(3000);
    }

    @AfterMethod
    public void tearDown(){
        AppFactory.quiteDriver();
    }
}
