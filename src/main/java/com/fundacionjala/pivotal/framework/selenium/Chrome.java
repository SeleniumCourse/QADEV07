package com.fundacionjala.pivotal.framework.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements IDriver {

    private static final String DRIVER_PATH = "lib/chromedriver.exe";
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    public WebDriver initDriver() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER, DRIVER_PATH);
        return new ChromeDriver();
    }
}
