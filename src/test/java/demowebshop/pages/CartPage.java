package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement
            pageTitleCart = $(".page-title"),
            agreeButton = $("#termsofservice"),
            checkoutButton = $("#checkout");

    @Step("Проверить заголовок страницы")
    public CartPage verifyPageTitleCart() {
        pageTitleCart.shouldHave(text("Shopping cart"));
        return new CartPage();
    }

    @Step("Нажать на кнопку согласия")
    public CartPage clickAgreeButton() {
        agreeButton.click();
        return this;
    }
}
