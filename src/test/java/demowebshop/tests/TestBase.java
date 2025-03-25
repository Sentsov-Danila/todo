package demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import demowebshop.pages.MainPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {
    private static final String mainPageURL = "https://demowebshop.tricentis.com/";
    private final Faker faker = new Faker();
    private final MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        Configuration.pageLoadTimeout = 100000;
    }

    @BeforeEach
    void registerNewUser() {
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        Selenide.open(mainPageURL, MainPage.class );

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
    void logOutUser() {
        mainPage
                .clickLogOutButton();
    }
}
