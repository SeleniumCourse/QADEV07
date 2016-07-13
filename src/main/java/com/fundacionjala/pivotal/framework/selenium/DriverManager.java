package com.fundacionjala.pivotal.framework.selenium;

import java.util.concurrent.TimeUnit;

import com.fundacionjala.pivotal.framework.util.Constants;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import com.fundacionjala.pivotal.framework.util.PropertiesInfo;

public class DriverManager {

    private static final String SRC_MAIN_RESOURCES_LOG4J_PROPERTIES = "src/main/resources/log4j.properties";

    private static DriverManager instance;

    private WebDriver driver;

    private DriverManager() {
        initWebDriver();
        PropertyConfigurator.configure(SRC_MAIN_RESOURCES_LOG4J_PROPERTIES);
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    private void initWebDriver() {
        driver = FactoryDriver.getDriver(PropertiesInfo.getInstance().getBrowser()).initDriver();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Constants.LOAD_PAGE_TIME, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

}
