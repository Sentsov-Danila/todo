package api.todo.model;

import lombok.Data;


public record AuthRequest(
        String email,
        String password
) {
}
