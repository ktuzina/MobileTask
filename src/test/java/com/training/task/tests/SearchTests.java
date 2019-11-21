package com.training.task.tests;

import com.training.task.driver.WebDriverCreator;
import com.training.task.listeners.CustomSuiteListener;
import com.training.task.listeners.CustomTestListener;
import com.training.task.pages.MainPage;
import com.training.task.pojo.Car;
import com.training.task.utils.PojoUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Listeners({CustomTestListener.class, CustomSuiteListener.class})
public class SearchTests {

    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverCreator creator;
    private Map<String, Car> carMap;

    @BeforeSuite
    public void readTestData() {
        carMap = PojoUtil.fromStringToObject();
    }

    @BeforeMethod
    public void startBrowser() {
        creator = new WebDriverCreator();
        driver = creator.getDriver();
        setWebDriver(driver);
        open("https://www.mobile.de/");
        mainPage = new MainPage();
    }

    @Test(description = "Search car by quick search")
    public void quickSearch(Method method) {
        Car car = carMap.get(method.getName());
        mainPage.setMark(car.getMark())
                .setModel(car.getModel())
                .setPriceTo(car.getPrice())
                .setRegistrationFrom(car.getYear())
                .setMileageTo(car.getMileage())
                .startSearch()
                .assertCarIsFound(car.getTitle());
    }

    @Test(description = "Search car by detailed search")
    public void detailedSearch(Method method) {
        Car car = carMap.get(method.getName());
        mainPage.goToDetailedSearch()
                .selectNewVehicle()
                .setMark(car.getMark())
                .setModel(car.getModel())
                .selectPetrol()
                .selectColor(car.getColor())
                .startSearch()
                .assertCarIsFound(car.getTitle());
    }

    @Test(description = "Search car not appropriated to criteria by detailed search")
    public void detailedSearchFail(Method method) {
        Car car = carMap.get(method.getName());
        mainPage.goToDetailedSearch()
                .selectNewVehicle()
                .setMark(car.getMark())
                .setModel(car.getModel())
                .selectPetrol()
                .selectColor(car.getColor())
                .startSearch()
                .assertCarIsFound(car.getTitle());
    }

    @AfterMethod
    public void closeBrowser() {
        creator.killDriver();
    }
}
