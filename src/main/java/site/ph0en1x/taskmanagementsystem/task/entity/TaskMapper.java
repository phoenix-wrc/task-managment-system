package site.ph0en1x.taskmanagementsystem.task.entity;

import org.springframework.stereotype.Component;
import site.ph0en1x.taskmanagementsystem.user.entity.User;
import site.ph0en1x.taskmanagementsystem.utils.Mapper;


@Component
public class TaskMapper implements Mapper<Task, TaskDto> {
    @Override
    public TaskDto toDto(Task entity) {

        if ( entity == null ) {
            return null;
        }

        return TaskDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .priority(entity.getPriority())
                .author(entity.getAuthor().getId())
                .executor(entity.getExecutor().getId())
                .createDate(entity.getCreateDate())
                .expirationDate(entity.getExpirationDate())
                .comments(entity.getComments())
                .build();
    }

    @Override
    public Task toEntity(TaskDto dto) {

        if ( dto == null ) {
            return null;
        }

        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .author(User.builder().id(dto.getAuthor()).build())
                .executor(User.builder().id(dto.getExecutor()).build())
                .createDate(dto.getCreateDate())
                .expirationDate(dto.getExpirationDate())
                .comments(dto.getComments())
                .build();
    }
}
