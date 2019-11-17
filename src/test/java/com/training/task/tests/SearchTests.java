package com.training.task.tests;

import com.training.task.listeners.CustomSuiteListener;
import com.training.task.listeners.CustomTestListener;
import com.training.task.driver.WebDriverCreator;
import com.training.task.pages.MainPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners({CustomTestListener.class, CustomSuiteListener.class})
public class SearchTests {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverCreator creator;

    @BeforeMethod()
    public void startBrowser() {
        creator = new WebDriverCreator();
        driver = creator.getDriver();
        setWebDriver(driver);
        open("https://www.mobile.de/");
        mainPage = new MainPage();
    }

    @Test
    public void quickSearch() {
        mainPage.setMark("BMW")
                .setModel("i3")
                .setPriceTo("20000")
                .setRegistrationFrom("2016")
                .setMileageTo("30000")
                .startSearch()
                .assertCarIsFound("BMW i3 60 Ah,Navi, Schnellladen");
    }

    @Test
    public void detailedSearch() {
        mainPage.goToDetailedSearch()
                .selectNewVehicle()
                .setMark("BMW")
                .setModel("49")
                .selectPetrol()
                .selectColor("Blau")
                .startSearch()
                .assertCarIsFound("BMW X5 xDrive40i A");
    }

    @Test
    public void detailedSearchFail() {
        mainPage.goToDetailedSearch()
                .selectNewVehicle()
                .setMark("BMW")
                .setModel("49")
                .selectPetrol()
                .selectColor("Grau")
                .startSearch()
                .assertCarIsFound("BMW X5 xDrive40i A");
    }

    @AfterMethod
    public void closeBrowser() {
        creator.killDriver();
    }
}
