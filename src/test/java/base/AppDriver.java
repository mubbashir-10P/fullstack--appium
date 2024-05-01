package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class AppDriver {
    private static final ThreadLocal<WebDriver>  driver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return  driver.get();
    }

    public static void setDriver(WebDriver wedriver){
        driver.set(wedriver);
        System.out.println("Driver is set");
    }
}
