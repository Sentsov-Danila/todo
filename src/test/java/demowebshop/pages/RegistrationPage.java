package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
private static final String SuccessfulRegistrationMessage = "Your registration completed";

    private final SelenideElement
            MaleGenderButton = $("#gender-male"),
            FirstNameLine = $("#FirstName"),
            LastNameLine = $("#LastName"),
            EmailLine = $("#Email"),
            PasswordLine = $("#Password"),
            ConfirmPasswordLine = $("#ConfirmPassword"),
            RegisterButton = $("#register-button"),
            SuccessfulRegistration = $(".result"),
            ContinueButton = $(".button-1");


    @Step("Выбрать мужской пол")
    public RegistrationPage clickMaleGenderButton() {
        MaleGenderButton.click();

        return new RegistrationPage();
    }

    @Step("Ввести имя")
    public RegistrationPage setFirstName(String firstName) {
        FirstNameLine.setValue(firstName);

        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationPage setLastName(String lastName) {
        LastNameLine.setValue(lastName);

        return this;
    }

    @Step("Ввести Email")
    public RegistrationPage setEmail(String email) {
        EmailLine.setValue(email);

        return this;
    }

    @Step("Ввести Пароль")
    public RegistrationPage setPassword(String password) {
        PasswordLine.setValue(password);

        return this;
    }

    @Step("Ввести Пароль Повторно")
    public RegistrationPage setConfirmPassword(String password) {
        ConfirmPasswordLine.setValue(password);

        return this;
    }
    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegisterButton() {
        RegisterButton.click();

        return this;
    }
    @Step("Проверить сообщение успешной регистрации")
    public RegistrationPage verifySuccessfulRegistration() {
        SuccessfulRegistration.shouldHave(text(SuccessfulRegistrationMessage));

        return this;
    }
    @Step("Нажать на кнопку продолжить")
    public MainPage clickContinueButton() {
        ContinueButton.click();

        return new MainPage();
    }
}
