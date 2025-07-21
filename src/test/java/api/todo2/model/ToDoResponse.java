package api.todo2.model;

import lombok.Data;

@Data
public class ToDoResponse {
    private String completed;
    private String title;
    private int todo_id;
    private int user_id;

}
