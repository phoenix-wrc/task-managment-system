package site.ph0en1x.taskmanagementsystem.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ph0en1x.taskmanagementsystem.model.task.Task;
import site.ph0en1x.taskmanagementsystem.model.user.User;
import site.ph0en1x.taskmanagementsystem.service.TaskService;
import site.ph0en1x.taskmanagementsystem.service.UserService;
import site.ph0en1x.taskmanagementsystem.validation.onCreate;
import site.ph0en1x.taskmanagementsystem.validation.onUpdate;
import site.ph0en1x.taskmanagementsystem.web.dto.task.TaskDto;
import site.ph0en1x.taskmanagementsystem.web.dto.user.UserDto;
import site.ph0en1x.taskmanagementsystem.web.mapper.TaskMapper;
import site.ph0en1x.taskmanagementsystem.web.mapper.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "User controller", description = "User API")
public class UserController {

    private final UserService service;

    private final TaskService taskService;

    private final UserMapper mapper;

    private final TaskMapper taskMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public UserDto update(@Validated(onUpdate.class) @RequestBody UserDto dto) {
        User updated = service.update(mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get user's tasks, where he is a creator by user id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTasksById(@PathVariable Long id) {
        return taskMapper.toDto(taskService.getAllByUserId(id));
    }

    @PostMapping("/{id}/task")
    @Operation(summary = "Create task")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public TaskDto createTask(@PathVariable Long id,
            @Validated(onCreate.class) @RequestBody TaskDto taskDto) {
        Task created = taskService.create(taskMapper.toEntity(taskDto), id);
        return taskMapper.toDto(created);
    }
}
