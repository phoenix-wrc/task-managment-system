package site.ph0en1x.taskmanagementsystem.service;

import site.ph0en1x.taskmanagementsystem.model.task.Task;

import java.util.List;

public interface TaskService {
    Task getById(Long id);
    List<Task> getAllByUserId(Long id);
    Task update(Task task);
    Task create(Task task, Long userId);
    void delete(Long task);
}
