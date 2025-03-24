package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.files.DownloadActions.click;

public class ProductPage {
    private static final String expensive = "Build your own expensive computer";
    private final SelenideElement
            pageTitleProduct = $("div.product-name"),
            addToCartButton = $("[id=add-to-cart-button-74]"),
            successfulAddToCart = $("span.cart-qty"),
            openCart = $$(".header-links ul li").get(2);


    @Step("Проверить заголовок страницы")
    public ProductPage verifyPageTitleProduct() {
        pageTitleProduct.shouldHave(text(expensive));

        return new ProductPage();
    }

    @Step("Добавить товар в корзину")
    public ProductPage clickAddToCartButton() {
        addToCartButton.click();

        return this;
    }

    @Step("Проверить,что товар добавился в корзину")
    public ProductPage verifyAddToCart() {
        successfulAddToCart.shouldHave(text("(1)"));

        return this;
    }

    @Step("Перейти в корзину")
    public CartPage openCartPage() {
        openCart.click();

        return new CartPage();
    }
}
