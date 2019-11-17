package com.training.task.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DetailedSearchPage {

    private SelenideElement newVehicleCb = $("#usage-NEW-ds");
    private SelenideElement markField = $("#selectMake1-ds");
    private SelenideElement modelField = $("#selectModel1-ds");
    private SelenideElement petrolCb = $("#fuels-PETROL-ds");
    private SelenideElement colorCb;
    private SelenideElement searchBtn = $("#dsp-lower-search-btn");

    public DetailedSearchPage selectNewVehicle() {
        newVehicleCb.click();
        return new DetailedSearchPage();
    }

    public DetailedSearchPage setMark(String mark) {
        markField.selectOption(mark);
        return new DetailedSearchPage();
    }

    public DetailedSearchPage setModel(String modelValue) {
        modelField.selectOptionByValue(modelValue);
        return new DetailedSearchPage();
    }

    public DetailedSearchPage selectPetrol() {
        petrolCb.click();
        return new DetailedSearchPage();
    }

    public DetailedSearchPage selectColor(String color) {
        String xpathExpression = "//div[@title = '" + color + "']";
        colorCb = $(By.xpath(xpathExpression));
        colorCb.click();
        return new DetailedSearchPage();
    }

    public SearchResultPage startSearch() {
        searchBtn.click();
        return new SearchResultPage();
    }

}
