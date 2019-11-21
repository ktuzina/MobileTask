package com.training.task.driver;

import com.training.task.utils.PropertyHandler;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverCreator {

    private static final ThreadLocal<RemoteWebDriver> DRIVER = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(initDriver());
        }
        return DRIVER.get();
    }

    private static RemoteWebDriver initDriver() {
        RemoteWebDriver driver = null;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platform", Platform.WINDOWS);
        chromeOptions.addArguments("start-maximized");
        try {
            driver = new RemoteWebDriver(new URL(PropertyHandler.getValue("hubURL")), chromeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public void killDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.set(null);
        }
    }

}
