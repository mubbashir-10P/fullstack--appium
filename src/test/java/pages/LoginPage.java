package pages;

import base.AppDriver;
import base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AppFactory {
    public LoginPage(){
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()),this);
    }

    @AndroidFindBy(accessibility = "test-Username")
    private WebElement userNameTextBox;

    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordTextBox;

    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginButton;


    By swagLagBanner = By.xpath("//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]");

    public void enterUserName(String username){
        new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(swagLagBanner));
        userNameTextBox.sendKeys(username);

    }

    public void enterPassword(String password){
        new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(swagLagBanner));
        passwordTextBox.sendKeys(password);
    }

    public void clickLoginButton(){
        new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(swagLagBanner));
        loginButton.click();
    }
}
