package com.training.task.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class WebDriverCreator {

    private static final String DRIVER_PROPERTIES = "driver.properties";

    private static Properties pr = new Properties();

    static {
        try {
            FileInputStream inp = new FileInputStream(Objects.requireNonNull(WebDriverCreator.class.getClassLoader().getResource(DRIVER_PROPERTIES)).getPath());
            pr.load(inp);
            inp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String driverPath = pr.getProperty("chromeDriverPath");
    private static String hubURL = pr.getProperty("hubURL");

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(initDriver());
        }
        return DRIVER.get();
    }

    private static WebDriver initDriver() {
        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platform", Platform.WINDOWS);
        chromeOptions.addArguments("start-maximized");
        try {
            driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
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
