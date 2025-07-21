package api.todo2.model;

import api.todo.model.AuthResponse;
import lombok.Data;

@Data
public class RegistrationResponse {
   private String accessToken;
   private  String refreshToken;
   private User user;

    @Data
    public static class User{
       private String email;
       private int id;
    }
}
