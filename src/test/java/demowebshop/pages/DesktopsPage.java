package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DesktopsPage {
    private final SelenideElement
            pageTitle = $("div.page-title"),
            expensiveDesktops = $$("div.item-box").get(2).$(".button-2");

    @Step("Проверить заголовок страницы")
    public DesktopsPage verifyPageTitle() {
        pageTitle.shouldHave(text("Desktops"));

        return new DesktopsPage();
    }

    @Step("Выбрать дорогой компьютер")

    public ProductPage getExpensiveDesktops() {
        expensiveDesktops.click();

        return new ProductPage();
    }
}

