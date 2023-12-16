package site.ph0en1x.taskmanagementsystem.task.entity;

import org.mapstruct.Mapper;
import site.ph0en1x.taskmanagementsystem.task.entity.TaskDto;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);

    Task toEntity(TaskDto dto);

    List<TaskDto> toDto(List<Task> taskList);
}
