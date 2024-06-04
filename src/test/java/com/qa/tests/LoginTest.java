package com.qa.tests;

import com.qa.base.AppFactory;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

public class LoginTest extends AppFactory {
    LoginPage loginPage;
    ProductPage productPage;
    InputStream inputStream;
    JSONObject loginUser;

    @BeforeClass
    public void setUpDataStream() throws IOException {
        try{
            String dataFile = "data/loginUser.json";
            inputStream = getClass().getClassLoader().getResourceAsStream(dataFile);

            JSONTokener jsonTokener = new JSONTokener(Objects.requireNonNull(inputStream));
            loginUser = new JSONObject(jsonTokener);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            if(inputStream != null)
                inputStream.close();
        }
    }

    @BeforeMethod
    public void setUp(Method method){
        loginPage = new LoginPage();
        utilities.log().info("\n"+ "******** Start Test:" + method.getName()+ "********" + "\n");
    }

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

    @AfterMethod
    public void tearDown(){
        //AppFactory.quiteDriver();
    }
}
