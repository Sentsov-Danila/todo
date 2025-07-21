package api.todo2.model;

import lombok.Data;

@Data
public class ToDoRequest {
    private String title;
    private int user_id;
}
