package demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import demowebshop.pages.MainPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    private static final String MainPageURL = "https://demowebshop.tricentis.com/";
    private final Faker faker = new Faker();
    private final MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        Configuration.pageLoadTimeout = 100000;
    }

    @BeforeEach
    void RegisterNewUser() {
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        Selenide.open(MainPageURL, MainPage.class);

        mainPage.clickRegistrationButton()
                .clickMaleGenderButton()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(password)
                .clickRegisterButton()
                .verifySuccessfulRegistration()
                .clickContinueButton();
    }
    @AfterEach
    void LogOutUser() {
        mainPage
                .clickLogOutButton();
    }
}
