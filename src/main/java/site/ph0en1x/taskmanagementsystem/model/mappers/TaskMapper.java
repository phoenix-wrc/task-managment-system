package site.ph0en1x.taskmanagementsystem.model.mappers;

import org.mapstruct.Mapper;
import site.ph0en1x.taskmanagementsystem.model.dto.task.TaskDto;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);

    Task toEntity(TaskDto dto);

    List<TaskDto> toDto(List<Task> taskList);
}
