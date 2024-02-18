package site.ph0en1x.taskmanagementsystem.web.controller;

import lombok.RequiredArgsConstructor;
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
public class UserController {

    private final UserService service;

    private final TaskService taskService;

    private final UserMapper mapper;

    private final TaskMapper taskMapper;

    @PutMapping
    public UserDto update(@Validated(onUpdate.class) @RequestBody UserDto dto) {
        User updated = service.update(mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksById(@PathVariable Long id) {
        return taskMapper.toDto(taskService.getAllByUserId(id));
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id,
            @Validated(onCreate.class) @RequestBody TaskDto taskDto) {
        Task created = taskService.create(taskMapper.toEntity(taskDto), id);
        return taskMapper.toDto(created);
    }
}
