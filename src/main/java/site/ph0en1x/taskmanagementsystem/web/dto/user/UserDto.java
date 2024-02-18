package site.ph0en1x.taskmanagementsystem.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import site.ph0en1x.taskmanagementsystem.validation.*;

@Data
public class UserDto {
    @NotNull(message = "Id must be not null", groups = onUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Name must be less than 255 symbols", groups = {onCreate.class, onUpdate.class} )
    private String name;

    @NotNull(message = "Second name must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Second name be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String secondName;

    @NotNull(message = "Last name must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Last name must be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String lastName;

    @NotNull(message = "Username must be not null", groups = {onCreate.class, onUpdate.class})
    @Length(max = 255, message = "Username must be less than 255 symbols", groups = {onCreate.class, onUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "password name must be not null", groups = {onCreate.class, onUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "password confirmation name must be not null", groups = {onCreate.class})
    private String passwordConfirmation;
}
