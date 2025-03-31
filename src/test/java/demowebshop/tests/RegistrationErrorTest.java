package demowebshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import demowebshop.pages.MainPage;
import demowebshop.pages.RegistrationPage;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
@Tag("WebShop")
public class RegistrationErrorTest {
    private static final String registerPageURL = "https://demowebshop.tricentis.com/register";
    private final Faker faker = new Faker();
    private final RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    public void setUp() {
        Selenide.open(registerPageURL, RegistrationPage.class);
        Configuration.pageLoadTimeout = 100000;
    }

    @ParameterizedTest()
    @DisplayName("Проверка сообщений об ошибке регистрации")
    @MethodSource("provideInvalidData")
    void errorRegistrationTest(String firstName, String lastName, String email, String password, String confirmPassword, String errorMessage) {
        registrationPage
                .clickRandomGenderButton()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setConfirmPassword(confirmPassword)
                .clickRegisterButton()
                .checkErrorMessege(errorMessage);
    }
    static Stream<Arguments>provideInvalidData(){
        Faker faker = new Faker();
        return Stream.of(
                Arguments.of(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        "123",
                        "123",
                        "The password should have at least 6 characters."
                ),
                Arguments.of(
                        faker.name().firstName(),
                        "",
                        faker.internet().emailAddress(),
                        "dAfsfr123",
                        "dAfsfr123",
                        "Last name is required."
        ),
                Arguments.of(
                        "",
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        "dAfsfr123",
                        "dAfsfr123",
                        "First name is required."
                ),
                Arguments.of(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.internet().password(),
                        "dAfsfr123",
                        "The password and confirmation password do not match."
                )
        );
    }
}


