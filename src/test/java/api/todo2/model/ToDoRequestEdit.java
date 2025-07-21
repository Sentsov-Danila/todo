package api.todo2.model;

import lombok.Data;

@Data
public class ToDoRequestEdit {
    private String completed;
    private String title;
    private int user_id;
}
