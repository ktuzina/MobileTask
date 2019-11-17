package com.training.task.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement markField = $("#qsmakeBuy");
    private SelenideElement modelField = $("#qsmodelBuy");
    private SelenideElement priceToField = $("#qsprc");
    private SelenideElement registrationFromField = $("#qsfrg");
    private SelenideElement mileageToField = $("#qsmil");
    private SelenideElement searchBtn = $("#qssub");
    private SelenideElement detailedSearchLink = $("#qsdet");

    public MainPage setMark(String mark) {
        markField.selectOption(mark);
        return new MainPage();
    }

    public MainPage setModel(String model) {
        modelField.selectOption(model);
        return new MainPage();
    }

    public MainPage setPriceTo(String price) {
        priceToField.setValue(price);
        return new MainPage();
    }

    public MainPage setRegistrationFrom(String registrationDate) {
        registrationFromField.setValue(registrationDate);
        return new MainPage();
    }

    public MainPage setMileageTo(String mileageTo) {
        mileageToField.setValue(mileageTo);
        return new MainPage();
    }

    public SearchResultPage startSearch() {
        searchBtn.click();
        return new SearchResultPage();
    }

    public DetailedSearchPage goToDetailedSearch() {
        detailedSearchLink.click();
        return new DetailedSearchPage();
    }
}
