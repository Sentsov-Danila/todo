package demowebshop.tests;

import demowebshop.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends TestBase {
    private final MainPage mainPage = new MainPage();

    @DisplayName("Проверка добавления товара в корзину")
    @Test
    void addToCartTest() {
        mainPage
                .clickOnProduct("Computers")
                .clickDesktopsButton()
                .verifyPageTitle()
                .getExpensiveDesktops()
                .verifyPageTitleProduct()
                .clickAddToCartButton()
                .verifyAddToCart()
                .openCartPage()
                .verifyPageTitleCart()
                .clickAgreeButton();

    }
}
