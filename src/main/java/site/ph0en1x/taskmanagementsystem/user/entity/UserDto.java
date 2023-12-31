package site.ph0en1x.taskmanagementsystem.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnCreate;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnUpdate;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;

import java.util.List;

@Data
@Schema(description = "Dto for User entity")
@Builder
public class UserDto {

    @Schema(description = "User entity id", example = "1")
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "Name User entity", example = "John")
    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255,
            message = "Name length must be smaller than 255 characters",
            groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @Schema(description = "User entity second name", example = "Ivanovich")
    @Length(max = 255, message = "Second name length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String secondName;

    @Schema(description = "User entity last name", example = "Doe")
    @NotNull(message = "Last name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Last name length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String lastName;

    @Schema(description = "User entity email as username", example = "yaplakal@yandex.ru")
    @NotNull(message = "Username must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @Schema(description = "User entity encrypted password", example = "54321")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @Schema(description = "User entity password confirmation, must mach with password",
            example = "54321")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null", groups = {OnCreate.class})
    private String passwordConfirmation;

    private List<Task> tasksOwn;

    private List<Task> tasksExecutor;
}
