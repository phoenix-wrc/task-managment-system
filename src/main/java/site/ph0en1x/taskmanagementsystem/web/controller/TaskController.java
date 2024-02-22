package site.ph0en1x.taskmanagementsystem.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Slf4j
@Tag(name = "Task controller", description = "Task API")
public class TaskController {
    private final TaskService taskService;

    private final TaskMapper mapper;

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
//    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public TaskDto getById(@PathVariable Long id) {
        return mapper.toDto(taskService.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
//    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping
    @Operation(summary = "Update task")
//    @PreAuthorize("@customSecurityExpression.canAccessUser(#userDto.id)")
    public TaskDto update(@Validated(onUpdate.class) @RequestBody TaskDto dto) {
        Task updated = taskService.update(mapper.toEntity(dto));
        return mapper.toDto(updated);
    }
}
