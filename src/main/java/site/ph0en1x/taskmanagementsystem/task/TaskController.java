package site.ph0en1x.taskmanagementsystem.task;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ph0en1x.taskmanagementsystem.task.entity.TaskDto;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnUpdate;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.task.entity.TaskMapper;
import site.ph0en1x.taskmanagementsystem.task.service.TaskService;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Task controller", description = "Task API")
public class TaskController {
    private final TaskService service;
    private final TaskMapper taskMapper;

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        Task task = service.getById(id);
        log.debug("{} {}", task.getAuthor().getId(), task.getExecutor().getId());
        return taskMapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task updatedTask = service.update(task);
        return taskMapper.toDto(updatedTask);
    }
}
