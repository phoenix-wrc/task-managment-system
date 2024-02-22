package site.ph0en1x.taskmanagementsystem.web.security.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Request for login")
public class JwtRequest {
    @Schema(description = "Email as username", example = "johndoe@mail.ru")
    @NotNull(message = "Username must be not null")
    private String username;

    @Schema(description = "Dto for User entity",
            example = "1234")
    @NotNull(message = "Password must be not null")
    private String password;
}
