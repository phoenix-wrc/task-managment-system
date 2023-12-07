package site.ph0en1x.taskmanagementsystem.model.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import site.ph0en1x.taskmanagementsystem.model.dto.validation.OnCreate;
import site.ph0en1x.taskmanagementsystem.model.dto.validation.OnUpdate;

@Data
public class UserDto {
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @Length(max = 255, message = "Second name length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String secondName;

    @NotNull(message = "Last name must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Last name length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String lastName;

    @NotNull(message = "Username must be not null", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller than 255 characters", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password confirmation must be not null", groups = {OnCreate.class})
    private String passwordConfirmation;
}
