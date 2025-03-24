package demowebshop.tests;

import demowebshop.pages.ComputerPage;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends TestBase {
    private final ComputerPage.MainPage mainPage = new ComputerPage.MainPage();
@Test
    void addToCartTest() {
        mainPage
                .clickComputerButton()
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
