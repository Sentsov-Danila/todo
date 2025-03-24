package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.files.DownloadActions.click;

public class ProductPage {
    private static final String Expensive = "Build your own expensive computer";
    private final SelenideElement
            PageTitleProduct = $("div.product-name"),
            AddToCartButton = $("[id=add-to-cart-button-74]"),
            SuccessfulAddToCart = $("span.cart-qty"),
            OpenCart = $$(".header-links ul li").get(2);


    @Step("Проверить заголовок страницы")
    public ProductPage verifyPageTitleProduct() {
        PageTitleProduct.shouldHave(text(Expensive));

        return new ProductPage();
    }

    @Step("Добавить товар в корзину")
    public ProductPage clickAddToCartButton() {
        AddToCartButton.click();

        return this;
    }

    @Step("Проверить,что товар добавился в корзину")
    public ProductPage verifyAddToCart() {
        SuccessfulAddToCart.shouldHave(text("(1)"));

        return this;
    }

    @Step("Перейти в корзину")
    public CartPage OpenCartPage() {
        OpenCart.click();

        return new CartPage();
    }
}
