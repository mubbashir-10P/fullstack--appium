package com.qa.base;

import com.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;

public class AppDriver {
    private static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();
    private static final Utilities utilities = new Utilities();

    protected static ThreadLocal<String> platform = new ThreadLocal<>();
    protected static ThreadLocal<String> device = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return  driver.get();
    }

    public static void setDriver(WebDriver wedriver){
        driver.set(wedriver);
        utilities.log().info("Driver is set");
    }

    public static String getPlatformName(){
        return platform.get();
    }

    public static void setPlatformName(String platformName){
        platform.set(platformName);
    }

    public static String getDeviceName(){
        return device.get();
    }

    public static void setDeviceName(String deviceName){
        device.set(deviceName);
    }
}
