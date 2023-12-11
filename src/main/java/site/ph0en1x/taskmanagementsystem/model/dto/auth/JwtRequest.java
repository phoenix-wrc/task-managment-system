package site.ph0en1x.taskmanagementsystem.model.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Request for login")
public class JwtRequest {
    @Schema(name = "Email as username", example = "johndoe@mail.ru")
    @NotNull(message = "Username must be not null")
    private String username;

    @Schema(name = "Dto for User entity",
            example = "$2a$10$yZC/FPekK9j9lkOucr60eOqjhwXxOn/9APcBaotGTdFgf/gO.zC0S")
    @NotNull(message = "Password must be not null")
    private String password;
}
