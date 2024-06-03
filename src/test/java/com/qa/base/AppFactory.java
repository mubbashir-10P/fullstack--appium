package com.qa.base;

import com.qa.utils.Utilities;
import com.qa.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class AppFactory {
    public static AppiumDriver driver;
    public static ConfigReader configReader;
    protected static HashMap<String, String> stringHashMap = new HashMap<>();
    Utilities utilities;
    InputStream stringIs;

    static Logger log = LogManager.getLogger(AppFactory.class.getName());

    @BeforeTest
    @Parameters({"platformName","platformVersion","deviceName"})
    public void initializer(String platformName, String platformVersion, String deviceName) throws Exception {
        try{
            log.debug("This is debug message");
            log.info("This is info message");
            log.warn("This is warning message.");
            log.error("This is error message.");
            configReader = new ConfigReader();
            utilities = new Utilities();
            String xmlFileName = "strings/strings.xml";
            stringIs = getClass().getClassLoader().getResourceAsStream(xmlFileName);

            stringHashMap = utilities.parseStringXML(stringIs);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName",platformName);
            capabilities.setCapability("platformVersion",platformVersion);
            capabilities.setCapability("deviceName",deviceName);
            capabilities.setCapability("appPackage",configReader.getAppPackage());
            capabilities.setCapability("appActivity",configReader.getAppActivity());
            capabilities.setCapability("newCommandTimeout",configReader.getCommandTimeoutValue());
            capabilities.setCapability("automationName",configReader.getAutomationName());
            capabilities.setCapability("noReset",configReader.getOnReset());
            capabilities.setCapability("app",System.getProperty("user.dir")+configReader.getApkPath());

            driver = new AndroidDriver(new URL(configReader.getAppiumServerEndPointURL()),capabilities);

            AppDriver.setDriver(driver);
            System.out.println("Android Driver Initialized!");
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        finally {
            if(stringIs != null){
                stringIs.close();
            }
        }
    }

    public WebElement waitForVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Utilities.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void clickElement(WebElement element){
        waitForVisibility(element).click();
    }

    public void sendKeys(WebElement element, String text){
        waitForVisibility(element).sendKeys(text);
    }

    public String getAttribute(WebElement element, String attribute){
        return waitForVisibility(element).getAttribute(attribute);
    }

    @AfterTest
    public static void quiteDriver(){
        if(null != driver){
            driver.quit();
        }
    }
}
