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
        client.login("knkfodsledk@gmail.com", "123qweasdzxc");
    }

    @Test
    void createToDoTest() {
        String title = "Войти в прайм";
        client.createToDo(title, userId);
        List<ToDoResponse> userToDos = client.getUserToDos(userId);
        long count = userToDos.stream()
                .filter(toDo -> title.equals(toDo.getTitle())).count();
        assertThat(count).isEqualTo(1);
    }

    @Test
    void editToDoTest() {
        ToDoResponse task = client.createToDo(
                "Абракадабра",
                65490
        );
        client.editToDo(
                65490,
                task.getTodo_id(),
                "true",
                "Джигурда"
        );
    }

    @Test
    void deleteToDoTest() {
        ToDoResponse task = client.createToDo(
                "Пойти погулять",
                65490
        );
        client.deleteToDo(
                task.getUser_id(),
                task.getTodo_id()
        );
    }

    @Test
    void logOutTest() {
        client.login(
                "knkfodsledk@gmail.com",
                "123qweasdzxc"
        );
        LogOutResponse response = client.logOut();

        assertThat(response.getSuccess()).isEqualTo("true");
    }

    @Test
    void getUserToDosTest() {
        List<ToDoResponse> response = client.getUserToDos(65490);
        assertThat(response).isNotNull();
    }
}

