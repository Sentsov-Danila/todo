package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ComputerPage {

    private final SelenideElement
            desktopsButton = $$("div.sub-category-grid div").first();

    @Step("Нажать на кнопку настольные компьютеры")
    public DesktopsPage clickDesktopsButton() {
        desktopsButton.click();

        return new DesktopsPage();
    }

    public static class MainPage {

        private final SelenideElement
                RegistrationButton = $(".ico-register"),
                ComputerButton = $$("ul.top-menu li").get(1),
                LogOutButton = $(".ico-logout");

        @Step("Нажать на кнопку регистрации")
        public RegistrationPage clickRegistrationButton() {
            RegistrationButton.click();

            return new RegistrationPage();
        }

        @Step("Нажать на кнопку компьютеры")
        public ComputerPage clickComputerButton() {
            ComputerButton.click();

            return new ComputerPage();
        }
        @Step("Нажать на кнопку выйти")
        public MainPage clickLogOutButton() {
            LogOutButton.click();

            return new MainPage();
        }

    }
}

