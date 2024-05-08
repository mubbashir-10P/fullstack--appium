package com.qa.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;

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
    }

    public void onTestStart(ITestResult result){
        String testStartMessage = String.format("Test Execution Started");
        System.out.println(testStartMessage);
    }

    public void onTestSuccess(ITestResult result){
        String testStartMessage = String.format("Testcase '%s' passed successfully",result.getMethod().getMethodName());
        System.out.println(testStartMessage);
    }
}
