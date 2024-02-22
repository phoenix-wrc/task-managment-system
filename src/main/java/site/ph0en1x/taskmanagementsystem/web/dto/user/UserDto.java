package site.ph0en1x.taskmanagementsystem.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import site.ph0en1x.taskmanagementsystem.validation.*;

@Data
@Schema(description = "User DTO")
public class UserDto {

    @Schema(description = "User ID", example = "3")
    @NotNull(message = "Id must be not null", groups = onUpdate.class)
    private Long id;

    @Schema(description = "User name", example = "Ronald")
    @NotNull(message = "Name must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Name must be less than 255 symbols", groups = {onCreate.class, onUpdate.class} )
    private String name;

    @Schema(description = "User father name", example = "Arthur")
    @NotNull(message = "Father name must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Second name be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String fatherName;

    @Schema(description = "User Last name", example = "Weasley")
    @NotNull(message = "Last name must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Last name must be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String lastName;

    @Schema(description = "User Last name", example = "Weasley@magic-ministry.bmm")
    @NotNull(message = "Username must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Username must be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String username;

    @Schema(description = "User password", example = "12345")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "password name must be not null", groups = {onCreate.class, onUpdate.class})
    private String password;

    @Schema(description = "User password confirmation", example = "12345")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "password confirmation name must be not null", groups = {onCreate.class})
    private String passwordConfirmation;
}
