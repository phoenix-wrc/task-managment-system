package site.ph0en1x.taskmanagementsystem.user.entity;

import org.mapstruct.Mapper;
import site.ph0en1x.taskmanagementsystem.user.entity.UserDto;
import site.ph0en1x.taskmanagementsystem.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
