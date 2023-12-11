package site.ph0en1x.taskmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ph0en1x.taskmanagementsystem.model.dto.task.TaskDto;
import site.ph0en1x.taskmanagementsystem.model.dto.user.UserDto;
import site.ph0en1x.taskmanagementsystem.model.dto.validation.OnCreate;
import site.ph0en1x.taskmanagementsystem.model.dto.validation.OnUpdate;
import site.ph0en1x.taskmanagementsystem.model.entity.task.Task;
import site.ph0en1x.taskmanagementsystem.model.entity.user.User;
import site.ph0en1x.taskmanagementsystem.model.mappers.TaskMapper;
import site.ph0en1x.taskmanagementsystem.model.mappers.UserMapper;
import site.ph0en1x.taskmanagementsystem.service.TaskService;
import site.ph0en1x.taskmanagementsystem.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
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
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = mapper.toEntity(userDto);
        User updatedUser = service.update(user);
        return mapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get user's tasks, where he is creator by user id")
    public List<TaskDto> getOwnTasksByUserId(@PathVariable Long id) {
        List<Task> taskList = taskService.getAllByUserId(id);
        return taskMapper.toDto(taskList);
    }

    @PostMapping("/{id}/task")
    @Operation(summary = "Create task")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

}
