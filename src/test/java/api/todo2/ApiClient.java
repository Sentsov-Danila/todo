package api.todo2;

import api.todo2.model.*;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiClient {
    Faker faker = new Faker();
    private final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://2.59.41.2:4300")
            .setContentType(JSON)
            .setBasePath("/api")
            .log(LogDetail.BODY)
            .log(LogDetail.URI)
            .build();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password();

    @Step("Регистрация пользователя")
    public RegistrationResponse register() {

        return register(email, password);
    }

    @Step("Регистрация пользователя")
    public RegistrationResponse register(String email, String password) {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail(email);
        registrationRequest.setPassword(password);

        return given(spec)
                .body(registrationRequest)
                .when()
                .post("/registration")
                .then()
                .statusCode(201)
                .extract().as(RegistrationResponse.class);
    }

    @Step("Авторизация пользователя")
    public RegistrationResponse login(String email, String password) {
        RegistrationRequest loginRequest = new RegistrationRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        return given(spec)
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .statusCode(201)
                .extract().as(RegistrationResponse.class);
    }

    @Step("Возвращение токена")
    public String getAuthToken(String email, String password) {
        RegistrationRequest loginRequest = new RegistrationRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        return given(spec)
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .statusCode(201)
                .extract().as(RegistrationResponse.class)
                .getAccessToken();
    }

    @Step("Создание задачи")
    public ToDoResponse createToDo(String title, int user_id) {
        ToDoRequest createRequest = new ToDoRequest();
        createRequest.setTitle(title);
        createRequest.setUser_id(user_id);

        return given(spec)
                .header("Authorization", "Bearer " + getAuthToken(email,password))
                .body(createRequest)
                .when()
                .post("/todos")
                .then()
                .statusCode(201)
                .extract().as(ToDoResponse.class);
    }

    @Step("Редактирование задачи")
    public ToDoResponse editToDo(int user_id, int todo_id, String completed, String title) {
        ToDoRequestEdit editToDoRequest = new ToDoRequestEdit();
        editToDoRequest.setCompleted(completed);
        editToDoRequest.setTitle(title);
        editToDoRequest.setUser_id(user_id);

        return given(spec)
                .header("Authorization", "Bearer " + getAuthToken(email,password))
                .body(editToDoRequest)
                .when()
                .put("/todos/" + todo_id)
                .then()
                .statusCode(200)
                .extract().as(ToDoResponse.class);
    }

    @Step("Удаление задачи")
    public ToDoResponse deleteToDo(int user_id, int todo_id) {
        return given(spec)
                .header("Authorization", "Bearer " + getAuthToken(email,password))
                .when()
                .delete("/todos/" + user_id + "/" + todo_id)
                .then()
                .statusCode(200)
                .extract().as(ToDoResponse.class);
    }

    @Step("Выйти из аккаунта")
    public LogOutResponse logOut() {
        return given(spec)
                .header("Authorization", "Bearer " + getAuthToken(email,password))
                .when()
                .get("/logout")
                .then()
                .statusCode(200)
                .extract().as(LogOutResponse.class);
    }

    @Step("Получение информации о задачах пользователя")
    public List<ToDoResponse> getUserToDos(int user_id) {
        return given(spec)
                .header("Authorization", "Bearer " + getAuthToken(email,password))
                .when()
                .pathParam("USER_ID",user_id)
                .get("/todos/{USER_ID}")
                .then()
                .statusCode(200)
                .extract().response().as(new TypeRef<List<ToDoResponse>>() {
                });
    }
}
