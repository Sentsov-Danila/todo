package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
private static final String successfulRegistrationMessage = "Your registration completed";

    private final SelenideElement
            maleGenderButton = $("#gender-male"),
            firstNameLine = $("#FirstName"),
            lastNameLine = $("#LastName"),
            emailLine = $("#Email"),
            passwordLine = $("#Password"),
            confirmPasswordLine = $("#ConfirmPassword"),
            registerButton = $("#register-button"),
            successfulRegistration = $(".result"),
            continueButton = $(".button-1");


    @Step("Выбрать мужской пол")
    public RegistrationPage clickMaleGenderButton() {
        maleGenderButton.click();

        return new RegistrationPage();
    }

    @Step("Ввести имя")
    public RegistrationPage setFirstName(String firstName) {
        firstNameLine.setValue(firstName);

        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationPage setLastName(String lastName) {
        lastNameLine.setValue(lastName);

        return this;
    }

    @Step("Ввести Email")
    public RegistrationPage setEmail(String email) {
        emailLine.setValue(email);

        return this;
    }

    @Step("Ввести Пароль")
    public RegistrationPage setPassword(String password) {
        passwordLine.setValue(password);

        return this;
    }

    @Step("Ввести Пароль Повторно")
    public RegistrationPage setConfirmPassword(String password) {
        confirmPasswordLine.setValue(password);

        return this;
    }
    @Step("Нажать на кнопку регистрации")
    public RegistrationPage clickRegisterButton() {
        registerButton.click();

        return this;
    }
    @Step("Проверить сообщение успешной регистрации")
    public RegistrationPage verifySuccessfulRegistration() {
        successfulRegistration.shouldHave(text(successfulRegistrationMessage));

        return this;
    }
    @Step("Нажать на кнопку продолжить")
    public ComputerPage.MainPage clickContinueButton() {
        continueButton.click();

        return new ComputerPage.MainPage();
    }
}
