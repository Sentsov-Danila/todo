package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement
            PageTitleCart = $(".page-title"),
            AgreeButton = $("#termsofservice");

    @Step("Проверить заголовок страницы")
    public CartPage verifyPageTitleCart() {
        PageTitleCart.shouldHave(text("Shopping cart"));
        return new CartPage();
    }

    @Step("Нажать на кнопку согласия")
    public CartPage clickAgreeButton() {
        AgreeButton.click();
        return this;
    }
}
