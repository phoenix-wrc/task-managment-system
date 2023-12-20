package site.ph0en1x.taskmanagementsystem.user.entity;

import org.springframework.stereotype.Component;
import site.ph0en1x.taskmanagementsystem.utils.Mapper;

import java.util.Set;

@Component
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto toDto(User user) {

        if ( user == null ) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .secondName(user.getSecondName())
                .lastName(user.getLastName())
                .password("approve")
                .passwordConfirmation("approve")
                .username(user.getUsername())
                .tasksExecutor(user.getTasksExecutor())
                .tasksOwn(user.getTasksOwn())
                .build();
    }

    @Override
    public User toEntity(UserDto dto) {

        if ( dto == null ) {
            return null;
        }

        return User.builder()
                .id(dto.getId())
                .roles(Set.of(Role.ROLE_USER))
                .name(dto.getName())
                .secondName(dto.getSecondName())
                .lastName(dto.getSecondName())
                .passwordConfirmation(dto.getPasswordConfirmation())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .tasksExecutor(dto.getTasksExecutor())
                .tasksOwn(dto.getTasksOwn())
                .build();
    }
}
