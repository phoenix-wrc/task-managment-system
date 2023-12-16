package site.ph0en1x.taskmanagementsystem.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.ph0en1x.taskmanagementsystem.task.entity.TaskDto;
import site.ph0en1x.taskmanagementsystem.user.entity.UserDto;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnCreate;
import site.ph0en1x.taskmanagementsystem.auth.validation.OnUpdate;
import site.ph0en1x.taskmanagementsystem.task.entity.Task;
import site.ph0en1x.taskmanagementsystem.user.entity.User;
import site.ph0en1x.taskmanagementsystem.task.entity.TaskMapper;
import site.ph0en1x.taskmanagementsystem.user.entity.UserMapper;
import site.ph0en1x.taskmanagementsystem.task.service.TaskService;
import site.ph0en1x.taskmanagementsystem.user.service.UserService;

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
    @PreAuthorize("@customSecurityExpression.canAccesUser(#userDto.id)")
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = mapper.toEntity(userDto);
        User updatedUser = service.update(user);
        return mapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    @PreAuthorize("@customSecurityExpression.canAccesUser(#id)")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("@customSecurityExpression.canAccesUser(#id)")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get user's tasks, where he is creator by user id")
    @PreAuthorize("@customSecurityExpression.canAccesUser(#id)")
    public List<TaskDto> getOwnTasksByUserId(@PathVariable Long id) {
        List<Task> taskList = taskService.getAllByUserId(id);
        return taskMapper.toDto(taskList);
    }

    @PostMapping("/{id}/task")
    @Operation(summary = "Create task")
    @PreAuthorize("@customSecurityExpression.canAccesUser(#id)")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

}
