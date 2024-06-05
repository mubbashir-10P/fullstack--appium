package com.qa.base;

import com.aventstack.extentreports.Status;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import com.qa.reports.ExtentReport;
import com.qa.utils.Utilities;
import com.qa.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

public class AppFactory {
    protected static AppiumDriver driver;
    protected static ConfigReader configReader;
    protected static HashMap<String, String> stringHashMap = new HashMap<>();
    protected static String dateTime;
    protected static AppiumDriverLocalService service;
    protected Utilities utilities = new Utilities();
    InputStream stringIs;

    protected LoginPage loginPage;
    protected ProductPage productPage;
    protected InputStream inputStream;
    protected JSONObject loginUser;


    @BeforeSuite
    public void upAndRunningAppiumServer(){
        service = getAppiumServerDefault();
        if(!utilities.checkIfAppiumServerIsRunning(4723)){
            service.start();
            service.clearOutPutStreams();
            utilities.log().info("Starting appium server...");
        }else {
            utilities.log().info("Appium Server is already up and running...");
        }
    }

    @AfterSuite
    public void shutDownServer(){
        service.stop();
        utilities.log().info("Appium server shutdown...");
    }

    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService();
    }

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

    @BeforeTest
    @Parameters({"platformName","platformVersion","deviceName"})
    public void initializer(String platformName, String platformVersion, String deviceName) throws Exception {
        try{
            configReader = new ConfigReader();
            dateTime = utilities.getDateTime();

            String xmlFileName = "strings/strings.xml";
            stringIs = getClass().getClassLoader().getResourceAsStream(xmlFileName);

            stringHashMap = utilities.parseStringXML(stringIs);

            AppDriver.setPlatformName(platformName);
            AppDriver.setDeviceName(deviceName);

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
            utilities.log().info("appURL is {}",configReader.getAppiumServerEndPointURL());

            AppDriver.setDriver(driver);
            utilities.log().info("Android driver initialized...");
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

    public void clickElement(WebElement element, String message){
        waitForVisibility(element).click();
        utilities.log().info(message);
        ExtentReport.getTest().log(Status.INFO,message);
    }

    public void sendKeys(WebElement element, String text, String message){
        waitForVisibility(element).sendKeys(text);
        utilities.log().info(message);
        ExtentReport.getTest().log(Status.INFO,message);
    }

    public String getAttribute(WebElement element, String attribute){
        return waitForVisibility(element).getAttribute(attribute);
    }

    public  String getText(WebElement element, String message){
        String elementText = null;
        elementText = getAttribute(element,"text");
        utilities.log().info("{}{}",message,elementText);
        ExtentReport.getTest().log(Status.INFO,message + elementText);

        return  elementText;
    }

    public static String getDateAndTime(){
        return dateTime;
    }

    @AfterTest
    public static void quiteDriver(){
        if(null != driver) {
            driver.quit();
        }
    }
}
