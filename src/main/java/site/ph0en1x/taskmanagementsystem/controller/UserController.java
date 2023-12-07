package site.ph0en1x.taskmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
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
public class UserController {
    private final UserService service;
    private final TaskService taskService;

    private final UserMapper mapper;
    private final TaskMapper taskMapper;

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto userDto) {
        User user = mapper.toEntity(userDto);
        User updatedUser = service.update(user);
        return mapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id) {
        List<Task> taskList = taskService.getAllByUserId(id);
        return taskMapper.toDto(taskList);
    }

    @PostMapping("/{id}/tasks")
    public TaskDto createTask(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

}
