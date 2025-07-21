package api.todo2;

import api.todo2.model.LogOutResponse;
import api.todo2.model.RegistrationResponse;
import api.todo2.model.ToDoResponse;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ToDoTest {
    ApiClient client = new ApiClient();
    Faker faker = new Faker();
    int userId;

    @BeforeEach
    void setUp() {
        userId = client.register().getUser().getId();
    }

    @Test
    void registrationTest() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

      RegistrationResponse response = client.register(email, password);

      assertThat(response.getAccessToken()).isNotNull();
      assertThat(response.getRefreshToken()).isNotNull();
      assertThat(response.getUser().getEmail()).isEqualTo(email);
    }

    @Test
    void loginTest() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        client.register(email, password);

        client.login(email, password);
    }

    @Test
    void createToDoTest() {
        String title = faker.book().title();
        client.createToDo(title, userId);
        List<ToDoResponse> userToDos = client.getUserToDos(userId);
        long count = userToDos.stream()
                .filter(toDo -> title.equals(toDo.getTitle())).count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    void editToDoTest() {
        String title = faker.book().title();

        ToDoResponse task = client.createToDo(
                "Абракадабра",
                userId
        );
        client.editToDo(
                userId,
                task.getTodo_id(),
                "true",
                title
        );
        List<ToDoResponse> userToDo = client.getUserToDos(userId);
        boolean exist = userToDo.stream().
                anyMatch(toDoResponse -> toDoResponse.getTitle().equals(title));
        assertThat(exist).as(title).isTrue();

    }

    @Test
    void deleteToDoTest() {
        String title = faker.book().title();

        ToDoResponse task = client.createToDo(
                title,
                userId
        );
        client.deleteToDo(
                task.getUser_id(),
                task.getTodo_id()
        );
        List<ToDoResponse> userToDos = client.getUserToDos(userId);
        long count = userToDos.stream()
                .filter(toDo -> title.equals(toDo.getTitle())).count();
        assertThat(count).isEqualTo(0);
    }

    @Test
    void logOutTest() {
        LogOutResponse response = client.logOut();

        assertThat(response.getSuccess()).isEqualTo("true");
    }

    @Test
    void getUserToDosTest() {
        List<ToDoResponse> response = client.getUserToDos(userId);
        assertThat(response).isNotNull();
    }
}

