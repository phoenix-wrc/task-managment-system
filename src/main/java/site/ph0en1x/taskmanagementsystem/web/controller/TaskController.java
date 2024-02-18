package site.ph0en1x.taskmanagementsystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ph0en1x.taskmanagementsystem.model.task.Task;
import site.ph0en1x.taskmanagementsystem.service.TaskService;
import site.ph0en1x.taskmanagementsystem.validation.onUpdate;
import site.ph0en1x.taskmanagementsystem.web.dto.task.TaskDto;
import site.ph0en1x.taskmanagementsystem.web.mapper.TaskMapper;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {
    private final TaskService taskService;

    private final TaskMapper mapper;

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        return mapper.toDto(taskService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping
    public TaskDto update(@Validated(onUpdate.class) @RequestBody TaskDto dto) {
        Task updated = taskService.update(mapper.toEntity(dto));
        return mapper.toDto(updated);
    }
}
