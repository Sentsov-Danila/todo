package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private final SelenideElement
            registrationButton = $(".ico-register"),
            computerButton = $$("ul.top-menu li").get(1),
            logOutButton = $(".ico-logout");

    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegistrationButton() {
        registrationButton.click();

        return new RegistrationPage();
    }

    @Step("Нажать на кнопку компьютеры")
    public ComputerPage clickComputerButton() {
        computerButton.click();

        return new ComputerPage();
    }
    @Step("Нажать на кнопку выйти")
    public MainPage clickLogOutButton() {
        logOutButton.click();

        return new MainPage();
    }

}