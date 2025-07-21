package api.todo2.model;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
}
