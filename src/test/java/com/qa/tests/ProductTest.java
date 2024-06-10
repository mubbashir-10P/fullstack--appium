package com.qa.tests;

import com.qa.pages.MenuPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void loginToApp(){
        login();
    }

    @Test
    public void validateProductOnProductsPage() {
        SoftAssert softAssert = new SoftAssert();

        String SLBTitle = productPage.getBackpackTitle();
        softAssert.assertEquals(SLBTitle, stringHashMap.get("products_page_slb_title"));

        String SLBPrice = productPage.getBackpackPrice();
        softAssert.assertEquals(SLBPrice, stringHashMap.get("products_page_slb_price"));
        softAssert.assertAll();
    }

    @AfterMethod
    public void logOutToApp(){
        logOut();
    }
}
