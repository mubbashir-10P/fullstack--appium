package com.qa.listeners;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult result){
        if(result.getThrowable() != null){
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);

            result.getThrowable().printStackTrace(printWriter);

            String testStartMessage = String.format("Testcase '%s' failed see logs below",result.getMethod().getMethodName());
            System.out.println(testStartMessage);
            System.out.println(printWriter.toString());
        }

        File file = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
        Map<String,String> params = new HashMap<>();

        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        String imagePath = "screenshots" + File.separator + params.get("platformName") + "_"+params.get("platformVersion")
                +"_"+params.get("deviceName")+File.separator + AppFactory.getDateAndTime()
                + File.separator + result.getTestClass().getRealClass().getSimpleName()
                + File.separator + result.getName()
                + ".png";

        String completeImagePath = System.getProperty("user.dir")+File.separator + imagePath;
        System.out.println(completeImagePath);

        try{
            FileUtils.copyFile(file,new File(imagePath));
            Reporter.log("This is the sample screenshot");
            Reporter.log("<a href='"+completeImagePath+"'>< img src='"+completeImagePath+"' height='100' width='100'/> </a>");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result){
        String testStartMessage = String.format("Test Execution Started");
        System.out.println(testStartMessage);

        // TODO:
    }

    @Override
    public void onTestSuccess(ITestResult result){
        String testStartMessage = String.format("Testcase '%s' passed successfully",result.getMethod().getMethodName());
        System.out.println(testStartMessage);
    }

    @Override
    public void onTestSkipped(ITestResult result){

    }

    @Override
    public void onFinish(ITestContext context){

    }
}
