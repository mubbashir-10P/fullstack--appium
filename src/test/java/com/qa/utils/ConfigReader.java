package com.qa.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader(){
        BufferedReader reader;
        String propertyFilePath = "configuration/config.properties";

        try{
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
            throw new RuntimeException("Configuration.properties file not found");
        }
    }


    public String getAppPackage(){
        String configValue = properties.getProperty("androidAppPackage");
        if(configValue != null) return configValue;
        else throw new RuntimeException("appPackage is not specified in the configuration.properties file.");
    }

    public String getAppActivity(){
        String configValue = properties.getProperty("androidAppActivity");
        if(configValue != null) return configValue;
        else throw new RuntimeException("appActivity is not specified in the configuration.properties file.");
    }
    public String getAutomationName(){
        String configValue = properties.getProperty("androidAutomationName");
        if(configValue != null) return configValue;
        else throw new RuntimeException("automationName is not specified in the configuration.properties file.");
    }
    public String getCommandTimeoutValue(){
        String configValue = properties.getProperty("androidCommandTimeoutValue");
        if(configValue != null) return configValue;
        else throw new RuntimeException("commandTimeoutValue is not specified in the configuration.properties file.");
    }
    public String getApkPath(){
        String configValue = properties.getProperty("androidApkPath");
        if(configValue != null) return configValue;
        else throw new RuntimeException("apkPath is not specified in the configuration.properties file.");
    }
    public String getOnReset(){
        String configValue = properties.getProperty("androidOnReset");
        if(configValue != null) return configValue;
        else throw new RuntimeException("onReset is not specified in the configuration.properties file.");
    }
    public String getAppiumServerEndPointURL(){
        String configValue = properties.getProperty("androidAppiumServerEndPointURL");
        if(configValue != null) return configValue;
        else throw new RuntimeException("appiumServerEndPointURL is not specified in the configuration.properties file.");
    }
}
