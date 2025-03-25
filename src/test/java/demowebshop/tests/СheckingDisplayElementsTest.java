package demowebshop.tests;

import demowebshop.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class СheckingDisplayElementsTest extends TestBase {
    private final MainPage mainPage = new MainPage();
    @Test
    @DisplayName("Проверка отоброжаемых элементов на странице (4,8,12)")
    void verifyDisplayElements() {
        mainPage
                .clickOnProduct("Apparel & Shoes")
                .selectProductsPerPage(4)
                .verifyProductsCount(4)
                .selectProductsPerPage(8)
                .verifyProductsCount(8)
                .selectProductsPerPage(12)
                .verifyProductsCount(12);
    }
}
