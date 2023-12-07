package site.ph0en1x.taskmanagementsystem.model.mappers;

import org.mapstruct.Mapper;
import site.ph0en1x.taskmanagementsystem.model.dto.user.UserDto;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
