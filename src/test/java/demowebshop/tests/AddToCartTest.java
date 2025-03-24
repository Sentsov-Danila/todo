package demowebshop.tests;

import demowebshop.pages.MainPage;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends TestBase {
    private final MainPage mainPage = new MainPage();
@Test
    void addToCartTest() {
        mainPage
                .clickComputerButton()
                .clickDesktopsButton()
                .verifyPageTitle()
                .GetExpensiveDesktops()
                .verifyPageTitleProduct()
                .clickAddToCartButton()
                .verifyAddToCart()
                .OpenCartPage()
                .verifyPageTitleCart()
                .clickAgreeButton();

    }
}
