package com.training.task.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

    private ElementsCollection searchResults = $$(By.xpath("//*[@class = 'h3 u-text-break-word']"));

    public void assertCarIsFound(String carTitle) {
        searchResults.findBy(text(carTitle)).shouldBe(visible);
    }

}
