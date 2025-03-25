package demowebshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
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
            continueButton = $(".button-1"),
            femaleGenderButton = $("#gender-female"),
            invalidEmail = $("span.field-validation-error for Email"),
            invalidPassword = $("span.field-validation-error for Password"),
            invalidConfirmPassword = $("span.field-validation-error for ConfirmPassword");



    @Step("Выбрать любой пол")
    public RegistrationPage clickRandomGenderButton() {
        Random random = new Random();
        int randomChoice = random.nextInt(2);

        if (randomChoice == 0) {
            maleGenderButton.shouldBe(visible).click();
            System.out.println("Выбран мужской пол");
        } else {
            femaleGenderButton.shouldBe(visible).click();
            System.out.println("Выбран женский пол");
        }
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
    public RegistrationPage setConfirmPassword(String confirmPassword) {
        confirmPasswordLine.setValue(confirmPassword);

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
    public MainPage clickContinueButton() {
        continueButton.click();

        return new MainPage();
    }
    @Step ("Проверить сообщение некорректно введенного email")
    public RegistrationPage checkErrorMessege(String errorMessage) {
        $(byText(errorMessage)).shouldBe(visible);
        return this;

    }
}
