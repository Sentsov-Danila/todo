package demowebshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {

    private final SelenideElement
            productDropdown = $("select#products-pagesize"),
            desktopsButton = $$("div.sub-category-grid div").first();

    private final ElementsCollection
            productItems = $$(".product-item");

    @Step("Выбрать количество продуктов на странице: {count}")
    public CatalogPage selectProductsPerPage(int count) {
        productDropdown.selectOptionContainingText(String.valueOf(count));

        return this;
    }

    @Step("Проверить, что на странице отображается {count} продуктов")
    public CatalogPage verifyProductsCount(int count) {
        productItems.shouldHave(size(count));

        return this;
    }
    @Step("Нажать на кнопку настольные компьютеры")
    public DesktopsPage clickDesktopsButton() {
        desktopsButton.click();

        return new DesktopsPage();
    }
}
