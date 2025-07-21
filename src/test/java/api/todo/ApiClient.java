package api.todo;

import api.todo.model.AuthRequest;
import api.todo.model.AuthResponse;
import api.todo.model.ToDoRequest;
import api.todo.model.ToDoResponse;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiClient {
    private final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("http://2.59.41.2:7320")
            .setContentType(JSON)
            .setBasePath("/api")
            .build();
    private final Faker faker = new Faker();
    String email = "kfmvodvod@gmail.com";
    String password = "123321";


    @Step("Регистрация пользователя")
    public AuthResponse register(String email, String password) {
        return given(spec)
                .log().uri()
                .body(new AuthRequest(email, password))
                .when()
                .post("/auth/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(AuthResponse.class);
    }

    @Step("Авторизация пользователя")
    public AuthResponse login(String email, String password) {
        return given(spec)
                .log().uri()
                .body(new AuthRequest(email, password))
                .when()
                .post("/auth/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(AuthResponse.class);
    }

    @Step("Создача задачи")
    public ToDoResponse createToDo(String title, String description, String date, String time, boolean checked) {
        ToDoRequest request = new ToDoRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setDate(date);
        request.setTime(time);
        request.setChecked(checked);

        return given(spec)
                .log().uri()
                .header("Authorization", "Bearer " + login(email, password).getAccessToken())
                .body(request)
                .when()
                .post("/todos/create")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(ToDoResponse.class);
    }

    @Step("Создача задачи")
    public ToDoResponse editToDo(int todoId, String title, String description, String date, String time, boolean checked) {
        ToDoRequest request = new ToDoRequest();
        request.setTitle(title);
        request.setDescription(description);
        request.setDate(date);
        request.setTime(time);
        request.setChecked(checked);

        return given(spec)
                .log().uri()
                .header("Authorization", "Bearer " + login(email, password).getAccessToken())
                .body(request)
                .when()
                .patch("/todos/edit/" + todoId)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(ToDoResponse.class);
    }
}



